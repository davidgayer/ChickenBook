package cz.gayerdavid.ChickenBook.security.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import cz.gayerdavid.ChickenBook.model.User;
import cz.gayerdavid.ChickenBook.security.manager.CustomAuthenticationManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final CustomAuthenticationManager customAuthenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
                try {
                    User user = new ObjectMapper().readValue(request.getInputStream(), User.class);//Reads user input and save the informations into new user object
                    Authentication authenticationObject = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());//Create new authentication token which will be send to authentication check
                    return customAuthenticationManager.authenticate(authenticationObject); //returns authentication token to Authentication manager
                } catch (IOException exception) {
                    throw new RuntimeException();
                }
    }

}
