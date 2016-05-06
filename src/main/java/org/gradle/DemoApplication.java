package org.gradle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ratpack.spring.config.EnableRatpack;

@SpringBootApplication
@EnableRatpack
public class DemoApplication {
    public static void main(String[] args) {
    	SpringApplication.run(DemoApplication.class, args);
	}
}
