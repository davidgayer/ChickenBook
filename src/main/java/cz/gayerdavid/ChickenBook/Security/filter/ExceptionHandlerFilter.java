package cz.gayerdavid.ChickenBook.security.filter;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import cz.gayerdavid.ChickenBook.exception.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//First filter hitted with request, handless exceptions and errors
public class ExceptionHandlerFilter extends OncePerRequestFilter { // used once per request

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response); // if everything is ok, move to the next filter (authentication)
        } catch (EntityNotFoundException exception) { // if user is not found in the database, response with 404 Not
                                                      // Found
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("User with this email doesn't exists in our database.");
            response.getWriter().flush();
        } catch (RuntimeException exception) { // if other Runtime exception is catched response with 400 Bad Request
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("BAD REQUEST");
            response.getWriter().flush();
        }
    }

}
