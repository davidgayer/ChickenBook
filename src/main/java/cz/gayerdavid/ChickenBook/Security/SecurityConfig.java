package cz.gayerdavid.ChickenBook.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // Allows frame on h2 console view
                .csrf(csrf -> csrf.disable()) // cross site request forgery (disabled because of use of JWT token)
                .authorizeHttpRequests(auth -> auth // All http request needs to be authenticated
                        .requestMatchers("/h2/**").permitAll() // Permit's access on path "/h2/**"
                        .requestMatchers("/user/register").permitAll()
                        .anyRequest().authenticated()) // any other request, except the ones noted at requestMatchers
                                                       // nedds to be authenticated
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Preventing
                                                                                                              // of
                                                                                                              // creating
                                                                                                              // Http
                                                                                                              // session
                .httpBasic(Customizer.withDefaults());

        return http.build();

    }
}
