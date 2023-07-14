package pl.hairbybieszczii.hair_bieszczii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * <strong>HairApp 2.0</strong><br>
 * This code represents the latest version of HairApp
 * which is the back-end system of https://hairbybieszczii.pl
 * <br>
 * The main changes:
 * <li>Migration from MySQL to PostgreSQL</li>
 * <li>Client management system is better and more stable</li>
 * <li>Code is more readable and easier to modify</li>
 * <li>Better documentation</li>
 * <li>More tests</li>
 *
 * @author Karol Melak
 * @since 2.0
 */
@SpringBootApplication
public class HairBieszcziiApplication {

	public static void main(String[] args) {

		SpringApplication.run(HairBieszcziiApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	public CorsFilter corsFilter(){
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
	    /*test*/
//		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		/*prod*/
		corsConfiguration.setAllowedOrigins(Arrays.asList("https://hairbybieszczii.pl"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",

				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "TOKEN"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
