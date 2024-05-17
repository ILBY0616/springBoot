package userManage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserManage {
    private static final Logger logger = LoggerFactory.getLogger(UserManage.class);

    public static void main(String[] args) {
        SpringApplication.run(UserManage.class, args);
        try {
            Runtime.getRuntime().exec("cmd /c start http://localhost:8080/");
            System.out.println("http://localhost:8080/");
        } catch (Exception e) {
            logger.error("Failed to open browser", e);
        }
    }
}
