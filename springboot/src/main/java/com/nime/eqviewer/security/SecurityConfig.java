package com.nime.eqviewer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Security configuration for CORS and security headers
 */
@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    /**
     * Configure CORS to allow requests from the Vue frontend
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        
        // Allow specific origins (adjust based on your frontend URL)
        // Frontend runs on port 80, backend on 9090
        config.setAllowedOriginPatterns(Arrays.asList(
            "http://localhost:80", 
            "http://localhost", 
            "http://127.0.0.1:80",
            "http://127.0.0.1"
        ));
        
        // Allow specific HTTP methods
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // Allow specific headers
        config.setAllowedHeaders(Arrays.asList("*"));
        
        // Allow credentials
        config.setAllowCredentials(true);
        
        // Set max age for preflight requests
        config.setMaxAge(3600L);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config);
        
        return new CorsFilter(source);
    }

    /**
     * Add security headers to all responses
     */
    @Bean
    public Filter securityHeadersFilter() {
        return new Filter() {
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                    throws IOException, ServletException {
                
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                
                // Prevent clickjacking
                httpResponse.setHeader("X-Frame-Options", "DENY");
                
                // Prevent MIME type sniffing
                httpResponse.setHeader("X-Content-Type-Options", "nosniff");
                
                // Enable XSS protection
                httpResponse.setHeader("X-XSS-Protection", "1; mode=block");
                
                // Enforce HTTPS (in production)
                // Uncomment in production with HTTPS enabled
                // httpResponse.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
                
                // Content Security Policy
                httpResponse.setHeader("Content-Security-Policy", "default-src 'self'");
                
                // Referrer Policy
                httpResponse.setHeader("Referrer-Policy", "strict-origin-when-cross-origin");
                
                chain.doFilter(request, response);
            }
        };
    }
}
