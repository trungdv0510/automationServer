package autoServer.Utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class fileUtils {
	public static boolean checkContenTypeFile(MultipartFile fileName, String contentTye) {
		boolean result = false;
		try {
			if (fileName!=null) {
				String path = fileName.getOriginalFilename().split(".")[1];
				if(path.contains(contentTye) && !StringUtils.isBlank(fileName.getContentType())) {
					result = true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
}
