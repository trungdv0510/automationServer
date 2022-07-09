package autoServer.Controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(value = "/public/")
public class ImageAndVideoController {
	@RequestMapping(value = "/img/{imgName}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImage(@PathVariable(value = "imgName") String imgName,HttpServletResponse response) throws IOException {
        File imgFile = new File("public/img/"+imgName);
        byte[] imgByte =  Files.readAllBytes(imgFile.toPath());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imgByte, response.getOutputStream());
    }
	@RequestMapping(value = "/video/{videoName}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public void getVideo(@PathVariable(value = "videoName") String videoName,HttpServletResponse response) throws IOException {
        File videoFile = new File("public/video/"+videoName);
        byte[] videoByte =  Files.readAllBytes(videoFile.toPath());
        response.setContentType(MediaType.ALL_VALUE);
        StreamUtils.copy(videoByte, response.getOutputStream());
    }
}
