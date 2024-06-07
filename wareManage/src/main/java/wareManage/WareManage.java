package wareManage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WareManage {
    private static final Logger logger = LoggerFactory.getLogger(WareManage.class);

    public static void main(String[] args) {
        SpringApplication.run(WareManage.class, args);
        try {
            Runtime.getRuntime().exec("cmd /c start http://localhost:8080/");
            System.out.println("http://localhost:8080/");
        } catch (Exception e) {
            logger.error("Failed to open browser", e);
        }
    }
}