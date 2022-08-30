package autoServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
// dùng để tắt chức năng bảo mật của spring exclude = {SecurityAutoConfiguration.class }
@SpringBootApplication()
public class Application extends SpringBootServletInitializer{
	public static void main(String[] arg) {
		SpringApplication.run(Application.class, arg);
	}
	
}
