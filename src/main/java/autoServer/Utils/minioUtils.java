package autoServer.Utils;

import autoServer.config.minioConfig;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
@Log4j2
@Service
public class minioUtils {
    @Autowired
    MinioClient minioClient ;
    @Value("${minio.bucket}")
    String defaultBucketName;

    @Value("${minio.default.folder}")
    String defaultBaseFolder;

    public List<Bucket> getAllBuckets() {
        try {
            return minioClient.listBuckets();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
    public String uploadFile(String name, MultipartFile content) {
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(defaultBucketName)
                    .object(name)
                    .stream(content.getInputStream(), content.getSize(), -1)
                    .contentType(content.getContentType())
                    .build());
            return name;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public byte[] getFile(String fileName) {
        try {
            InputStream obj = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(defaultBucketName)
                    .object(fileName)
                    .build());

            byte[] content = IOUtils.toByteArray(obj);
            obj.close();
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getFileUrl(String fileName){
        try {
           String url =  minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(defaultBucketName).object(fileName).method(Method.GET).build());
           return url;
        }catch (Exception e){
           log.error(e.getMessage());
        }
        return null;
    }
}
