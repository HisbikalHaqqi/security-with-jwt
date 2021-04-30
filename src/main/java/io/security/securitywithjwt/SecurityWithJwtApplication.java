package io.security.securitywithjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class SecurityWithJwtApplication {

    public static void main(String[] args) {
            SpringApplication.run(SecurityWithJwtApplication.class, args);
    }
}