package scoreManage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScoreManage {
    private static final Logger logger = LoggerFactory.getLogger(ScoreManage.class);

    public static void main(String[] args) {
        SpringApplication.run(ScoreManage.class, args);
        try {
            Runtime.getRuntime().exec("cmd /c start http://localhost:8080/");
        } catch (Exception e) {
            logger.error("Failed to open browser", e);
        }
        System.out.println("http://localhost:8080/homePage");
    }
}