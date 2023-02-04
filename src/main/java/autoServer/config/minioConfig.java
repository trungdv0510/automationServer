package autoServer.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class minioConfig {
    @Value("${minio.username}")
    private String accessKey;

    @Value("${minio.password}")
    private String secretKey;

    @Value("${minio.url}")
    private String minioUrl;
    @Bean
    @Primary
    public MinioClient minioClient() {
        return new MinioClient.Builder()
                .credentials(accessKey, secretKey)
                .endpoint(minioUrl)
                .build();
    }
}
