package com.khmersolution.moduler.configure;

import com.khmersolution.moduler.configure.util.AsvaAuthenticationProvider;
import com.khmersolution.moduler.configure.util.AsvaPasswordEncoder;
import com.khmersolution.moduler.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

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

    @Bean
    public PasswordEncoder encoder() {
        return new AsvaPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        ReflectionSaltSource rss = new ReflectionSaltSource();
        rss.setUserPropertyToUse("passwordSalt");

        final AsvaAuthenticationProvider authProvider = new AsvaAuthenticationProvider();
        authProvider.setPasswordEncoder(encoder());
        authProvider.setSaltSource(rss);
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setUserRepository(userRepository);

        return authProvider;
    }


}
