package com.github.caofangkun;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ReflectionUtils {
	private static final Class<?>[] EMPTY_ARRAY = new Class[] {};

	private static final Map<Class<?>, Constructor<?>> CONSTRUCTOR_CACHE = new ConcurrentHashMap<Class<?>, Constructor<?>>();

	@SuppressWarnings("unchecked")
	public static <T> T newInstance(String classStr)
			throws ClassNotFoundException {
		T result;

		Class<T> theClass = (Class<T>) Class.forName(classStr);
		try {
			Constructor<T> meth = (Constructor<T>) CONSTRUCTOR_CACHE
					.get(theClass);
			if (meth == null) {
				meth = theClass.getDeclaredConstructor(EMPTY_ARRAY);
				meth.setAccessible(true);
				CONSTRUCTOR_CACHE.put(theClass, meth);
			}
			result = meth.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T> T newInstance(String classStr, ClassLoader classLoader)
			throws ClassNotFoundException {
		T result;

		Class<T> theClass = (Class<T>) classLoader.loadClass(classStr);
		// Class<T> theClass = (Class<T>) Class.forName(classStr, false,
		// classLoader);
		try {
			Constructor<T> meth = (Constructor<T>) CONSTRUCTOR_CACHE
					.get(theClass);
			if (meth == null) {
				meth = theClass.getDeclaredConstructor(EMPTY_ARRAY);
				meth.setAccessible(true);
				CONSTRUCTOR_CACHE.put(theClass, meth);
			}
			result = meth.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
}
