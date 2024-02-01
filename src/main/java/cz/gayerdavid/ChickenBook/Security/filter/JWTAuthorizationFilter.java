package cz.gayerdavid.ChickenBook.security.filter;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import cz.gayerdavid.ChickenBook.security.SecurityConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

                String header = request.getHeader("Authentication"); // Takes header from request
                if (header == null || !header.startsWith(SecurityConstants.BEARER)) { // Checks if header is null, if so, request coming from registration and exit JWT Filter
                    filterChain.doFilter(request, response);
                    return;
                }
                String token = header.replace(SecurityConstants.BEARER, ""); // extracts secret key from header
                String user = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET_KEY)) // Compare request body secret key with saved secret key
                    .build()
                    .verify(token)
                    .getSubject(); 
                Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList()); 
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);


    }

}
