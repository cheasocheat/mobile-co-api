package com.khmersolution.moduler.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * Created by Vannaravuth Yo
 * Date : 8/29/17, 9:52 PM
 * Email : ravuthz@gmail.com
 */

@Configuration
@EnableResourceServer
@SuppressWarnings("SpringJavaAutowiringInspection")
public class ResourceConfig extends ResourceServerConfigurerAdapter {

    @Value("${security.oauth2.resourceId}")
    private String resourceId;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private DefaultTokenServices tokenServices;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .resourceId(resourceId)
                .tokenServices(tokenServices)
                .tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/manages/**").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().csrf().disable();
    }
}