package autoServer.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpHeaders;

public class contains {
	public static String pass = "Pass";
	public static String fail = "Fail";
	public static String contentType = "Content-Type";
	public static String contentTypeConfig = "application/json; charset=utf-8";
	public static String folderPublic  = "public/";
	public static String folderImg  = "img/";
	public static String folderVideo = "video/";
	public static String contenTypeImg = "image/jpeg";
	public static String contentTypeVideo = "video/mp4";
	public static String roleAdmin = "ADMIN";
	public static String roleUser = "USER";
	public static String roleManager = "MANAGER";




	// config cho phần send to server
	public static HttpHeaders configHeader() {
		 HttpHeaders headers = new HttpHeaders();
		 headers.add(contentType, contentTypeConfig);
		 return headers;
	}
	public static String randomDate() {
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyy-HHmmss");
		Date date = new Date();
		return String.valueOf(format.format(date));
	}


	public static class ResultSetMapping{
		private ResultSetMapping() {
		}
		public  static final String REGRESSTION_MAPPTING = "REGRESSTION_MAPPTING";
	}
}
