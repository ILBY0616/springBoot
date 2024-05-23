package spongeBob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpongeBob {
    private static final Logger logger = LoggerFactory.getLogger(SpongeBob.class);

    public static void main(String[] args) {
        SpringApplication.run(SpongeBob.class, args);
        try {
            Runtime.getRuntime().exec("cmd /c start http://localhost:8080/homePage");
        } catch (Exception e) {
            logger.error("Failed to open browser", e);
        }
    }
}
