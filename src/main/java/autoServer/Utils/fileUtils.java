package autoServer.Utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class fileUtils {
	public static boolean checkContenTypeFile(MultipartFile fileName) {
		boolean result = false;
		try {
			if (fileName!=null) {
				System.out.println(fileName.getContentType());
				String path = fileName.getContentType();

				if (path != null){
					if(path.contains(contains.contenTypeImg)  || path.contains(contains.contentTypeVideo) && !StringUtils.isBlank(fileName.getContentType())) {
						result = true;
					}
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return result;
	}
}
