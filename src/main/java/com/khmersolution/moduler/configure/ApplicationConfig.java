package com.khmersolution.moduler.configure;

import com.khmersolution.moduler.configure.util.MobileAuthenticationProvider;
import com.khmersolution.moduler.repository.UserRepository;
import com.khmersolution.moduler.configure.util.MobilePasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * Created by cheasocheat
 * On 08, May, 2018
 */
@Configuration
public class ApplicationConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    /**
     * Password Encoder
     * @return
     */
    /*@Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }*/

    @Bean
    public PasswordEncoder encoder() {
        return new MobilePasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        final MobileAuthenticationProvider authProvider = new MobileAuthenticationProvider(userRepository, userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }


}
