package com.khmersolution.moduler.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Vannaravuth Yo
 * Date : 8/28/2017, 2:48 PM
 * Email : ravuthz@gmail.com
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DaoAuthenticationProvider authProvider;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers(
                        "/manages/**",
                        "/webjars/**",
                        "/v2/api-docs",
                        "/swagger-resources/**",
                        "/ewebservice/api/v1/provinces/**", // ignore security for temporary
                        "/ewebservice/api/v1/documentgroups/**", // ignore security for temporary
                        "/ewebservice/api/v1/documents/**", // ignore security for temporary
                        "/ewebservice/api/v1/wayofknowings/**", // ignore security for temporary
                        "/ewebservice/api/v1/keywords/**", // ignore security for temporary
                        "/swagger-ui.html**");
    }


}
