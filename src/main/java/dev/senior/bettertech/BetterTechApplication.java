package dev.senior.bettertech;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BetterTechApplication {

    public static Dotenv dotenv;

    public static void main(String[] args) {
        dotenv = Dotenv.configure()
                .directory("./") // Path to the directory containing your .env file
                .load();

        SpringApplication.run(BetterTechApplication.class, args);
    }

}
