package com.BD.Service_Auto;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

//clasa care imi permite autorizatia front end ului sa acceseze rutele pentru cererile HTTP
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/Clienti/add", "/Clienti/", "/Clienti/test", "/Clienti/get", "/Angajati/add", "/api/auth/login",
                                "/api/auth/register", "/Angajati/get", "/Masini/get", "/Piese/get", "/Servicii/get", "/Reparatii/get",
                        "/Angajati/delete/{id}", "/Clienti/delete/{id}", "/Masini/delete/{id}", "/Piese/delete/{id}", "/Reparatii/delete/{id}",
                                "/Servicii/delete/{id}", "/Angajati/update/{id}", "/Angajati/get/{id}", "/Clienti/get/{id}",
                                "/Clienti/update/{id}", "/reparatii_angajati/get/filterYear/{year}", "/Clienti/get/filterInsurance/{insurance}", "/Masini/get/filterPrice/{price}",
                                "/reparatii_piese/get/filter", "/reparatii_angajati/get/filterHours/{hours}", "Clienti/get/filter_cost",
                                "/Angajati/get/employees/filterSalary/{salary}", "/Masini/get/filter2", "Clienti/get/filterCost/{cost}",
                                "Reparatii/get/filterYear/{year}", "Masini/update/{id}", "Masini/add", "Masini/get/{id}",
                                "Masini/get/filterOwner/{nume}/{prenume}", "Piese/add", "Piese/get/{id}", "Piese/update/{id}",
                                "reparatii_servicii/get/filter", "/Servicii/add", "/Servicii/get/{id}", "/Servicii/update/{id}",
                                "Reparatii/add", "Reparatii/get/{id}", "Reparatii/update/{id}").permitAll() // Permite accesul public pentru aceste rute
                        .anyRequest().authenticated() // Oricare altă cerere necesită autentificare
                );
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public ServletContextInitializer sameSiteCookieConfig() {
        return servletContext -> {
            servletContext.getSessionCookieConfig().setSecure(true); // Activează Secure dacă folosești HTTPS
            servletContext.getSessionCookieConfig().setHttpOnly(true);
        };
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowCredentials(true);
            }
        };
    }


}
