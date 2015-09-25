package com.github.caofangkun;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorUtils {

	public static String stringifyError(Throwable error) {
		StringWriter result = new StringWriter();
		PrintWriter printer = new PrintWriter(result);
		error.printStackTrace(printer);
		printer.close();
		return result.toString();
	}

}
