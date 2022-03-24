package autoServer.Utils;

import org.springframework.http.HttpHeaders;

public class contains {
	public static String pass = "Pass";
	public static String fail = "Fail";
	public static String contentType = "Content-Type";
	public static String contentTypeConfig = "application/json; charset=utf-8";
	public static HttpHeaders configHeader() {
		 HttpHeaders headers = new HttpHeaders();
		 headers.add(contentType, contentTypeConfig);
		 return headers;
	}
}
