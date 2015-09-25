package com.github.caofangkun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

public class NetUtils {

	public static String readURL(String url, String proxyHostname, int proxyPort)
			throws MalformedURLException, IOException {
		StringBuilder sb = new StringBuilder();
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
				proxyHostname, proxyPort));
		URLConnection conn;
		conn = new URL(url).openConnection(proxy);
		conn.setConnectTimeout(3000);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));

		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			sb.append(inputLine);
		}
		return sb.toString();

	}

}
