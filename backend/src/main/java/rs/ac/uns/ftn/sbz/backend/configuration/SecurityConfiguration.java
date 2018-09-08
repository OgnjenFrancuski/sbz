package rs.ac.uns.ftn.sbz.backend.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Created by Ivana Zeljkovic on 29/05/2018.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${BCryptTimes}")
    private Integer bCryptTimes;


    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(bCryptTimes);
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        String[] swaggerUrls = {
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/v2/api-docs",
                "/webjars/**"
        };

        String[] angularUrls = {
                "/**.bundle.**",
                "/fontawesome**",
                "/favicon.ico",
                "/**.jpg",
                "/**.html"
        };

        httpSecurity
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .and()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers(swaggerUrls).permitAll()
                .antMatchers(angularUrls).permitAll();
    }

}
