package com.khmersolution.moduler.configure.util;

import com.khmersolution.moduler.domain.User;
import com.khmersolution.moduler.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/*
 * Created by cheasocheat
 * On 08, May, 2018
 */
public class AsvaAuthenticationProvider extends DaoAuthenticationProvider {

    private UserRepository userRepository;

    public AsvaAuthenticationProvider() {
        super();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //return super.authenticate(authentication);
        User user = null;
        String usernane = authentication.getName();
        if (usernane != null) {
            if (usernane.contains("@")) {
                user = userRepository.findByEmail(authentication.getName());
            } else {
                user = userRepository.findByUsername(authentication.getName());
            }
        }
        if (user == null) {
            throw new BadCredentialsException("Invalid username or password");
        }
        final Authentication result = super.authenticate(authentication);
        return new UsernamePasswordAuthenticationToken(user, result.getCredentials(), result.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
