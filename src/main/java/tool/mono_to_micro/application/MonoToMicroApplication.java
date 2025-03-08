package tool.mono_to_micro.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = "tool.mono_to_micro")
public class MonoToMicroApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonoToMicroApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**")
						.allowedOrigins("http://localhost:5500")  // Allow frontend
						.allowedMethods("POST", "GET")
						.allowedHeaders("*")
						.allowCredentials(true);
			}
		};

	}
}
