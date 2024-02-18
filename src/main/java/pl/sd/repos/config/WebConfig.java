package pl.sd.repos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for customizing content negotiation in a Spring Boot application.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configure content negotiation to set the default content type to JSON.
     * @param configurer The ContentNegotiationConfigurer to configure content negotiation.
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }
}
