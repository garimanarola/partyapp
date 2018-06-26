package com.party.giftzzle.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.MultipartConfigElement;

/**
 * Created by agarima on 20-06-2018.
 */

@Component
@ConfigurationProperties()
public class AppConfig {

  private final CorsConfiguration cors = new CorsConfiguration();

  public CorsConfiguration getCors() {
    return cors;
  }

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = this.getCors();
    if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
      source.registerCorsConfiguration("/api/**", config);
      source.registerCorsConfiguration("/management/**", config);
      source.registerCorsConfiguration("/v2/api-docs", config);
    }
    return new CorsFilter(source);
  }

  @Bean
  public MultipartConfigElement multipartConfigElement() {
    MultipartConfigFactory factory = new MultipartConfigFactory();
    return factory.createMultipartConfig();
  }

}
