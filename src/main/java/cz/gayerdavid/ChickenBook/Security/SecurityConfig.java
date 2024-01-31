package cz.gayerdavid.ChickenBook.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import cz.gayerdavid.ChickenBook.security.filter.AuthenticationFilter;
import cz.gayerdavid.ChickenBook.security.filter.ExceptionHandlerFilter;
import cz.gayerdavid.ChickenBook.security.manager.CustomAuthenticationManager;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationManager customAuthenticationManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl("/authenticate"); // Set on which path authenticationFilter works

        http
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // Allows correct view of h2
                                                                                    // database in the web browser
                .csrf(csrf -> csrf.disable()) // cross site request forgery (disabled because of use of JWT token)
                .authorizeHttpRequests(auth -> auth // Handles which path are authenticated and how.
                        .requestMatchers("/h2/**").permitAll() // Permit's access on path "/h2/**"
                        .requestMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll() // Permits acces
                                                                                                       // on
                                                                                                       // registration
                                                                                                       // path saved at
                                                                                                       // SecurityConstants
                        .anyRequest().authenticated()) // any other request, except the ones noted at requestMatchers
                                                       // nedds to be authenticated
                .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class) // First filter checking for
                                                                                           // exceptions
                .addFilter(authenticationFilter) // This filter handles authentication of user
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Preventing
                                                                                                               // of
                                                                                                               // creating
                                                                                                               // Http
                                                                                                               // session

        return http.build();

    }
}
