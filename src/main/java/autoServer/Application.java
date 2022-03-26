package autoServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
// dùng để tắt chức năng bảo mật của spring exclude = {SecurityAutoConfiguration.class }
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Application {
	public static void main(String[] arg) {
		SpringApplication.run(Application.class, arg);
	}
	
}
