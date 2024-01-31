package cz.gayerdavid.ChickenBook.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import cz.gayerdavid.ChickenBook.security.filter.AuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        authenticationFilter.setFilterProcessesUrl("/authenticate"); //Set on which path authenticationFilter works

        http
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // Allows frame on h2 console view
                .csrf(csrf -> csrf.disable()) // cross site request forgery (disabled because of use of JWT token)
                .authorizeHttpRequests(auth -> auth // Handles which path are authenticated and how.
                        .requestMatchers("/h2/**").permitAll() // Permit's access on path "/h2/**"
                        .requestMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll() //Permits acces on registration path saved at SecurityConstants
                        .anyRequest().authenticated()) // any other request, except the ones noted at requestMatchers
                                                       // nedds to be authenticated
                .addFilter(authenticationFilter)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Preventing
                                                                                                              // of
                                                                                                              // creating
                                                                                                              // Http
                                                                                                              // session

        return http.build();

    }
}
