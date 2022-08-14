package com.y2829.whai;

import com.y2829.whai.config.properties.AppProperties;
import com.y2829.whai.config.properties.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		CorsProperties.class,
		AppProperties.class
})
public class WhaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhaiApplication.class, args);
	}

}
