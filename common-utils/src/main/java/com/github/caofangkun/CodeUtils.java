package com.github.caofangkun;

import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeUtils {

	/**
	 * Change "\\u4e00\\u84dd\\u5929" to "\u4e00\u84dd\u5929"
	 * 
	 * @param s
	 * @return
	 */
	public static String formatUnicode(String s) {
		StringBuffer buf = new StringBuffer();
		Matcher m = Pattern.compile("\\\\u([0-9A-Fa-f]{4})").matcher(s);
		while (m.find()) {
			try {
				int cp = Integer.parseInt(m.group(1), 16);
				m.appendReplacement(buf, "");
				buf.appendCodePoint(cp);
			} catch (NumberFormatException e) {
			}
		}
		m.appendTail(buf);
		return buf.toString();

	}

	private final static Charset UTF8_CHARSET = Charset.forName("UTF-8");

	public static String decodeUTF8(byte[] bytes) {
		return new String(bytes, UTF8_CHARSET);
	}

	public static String UnicodeToString(String str) {
		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(str);
		char ch;
		while (matcher.find()) {
			ch = (char) Integer.parseInt(matcher.group(2), 16);
			str = str.replace(matcher.group(1), ch + "");
		}
		return decodeUTF8(str.getBytes());
	}

}
