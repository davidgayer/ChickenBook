package cz.gayerdavid.ChickenBook.security.manager;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import cz.gayerdavid.ChickenBook.model.User;
import cz.gayerdavid.ChickenBook.service.UserService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = userService.getUser(authentication.getName()); // Found user in db by email
        if (!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) { // Check
                                                                                                              // if
                                                                                                              // password
                                                                                                              // for
                                                                                                              // that
                                                                                                              // user is
                                                                                                              // right
                                                                                                              // if not
                                                                                                              // throws
                                                                                                              // BadCredentialsException
            throw new BadCredentialsException("Wrong Password");
        }
        return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials()); // If
                                                                                                                        // password
                                                                                                                        // is
                                                                                                                        // right
                                                                                                                        // returns
                                                                                                                        // Token
                                                                                                                        // with
                                                                                                                        // email
                                                                                                                        // and
                                                                                                                        // password
                                                                                                                        // fields
    }

}
