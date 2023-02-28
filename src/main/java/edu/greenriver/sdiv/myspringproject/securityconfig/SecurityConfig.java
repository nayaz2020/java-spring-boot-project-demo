package edu.greenriver.sdiv.myspringproject.securityconfig;

import edu.greenriver.sdiv.myspringproject.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author nemat
 * @version 1.2
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    private UserService userService;

    /**
     * @param userService requested userService
     */
    public SecurityConfig(UserService userService)
    {
        this.userService = userService;
    }

    /**
     * a password hash algorithm.
     * @return encrypted version of password
     */

    @Bean
    public BCryptPasswordEncoder encoder()
    {
        return new BCryptPasswordEncoder();
    }

    /**
     * We configure the mechanism for authentication in this method
     * (how we determine who a user is
     * @param auth type of authority as user have
     * @throws Exception if password or user is incorrect
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        BCryptPasswordEncoder encoder = encoder();

       auth
                .userDetailsService(userService)
                .passwordEncoder(encoder);

    }

    /**
     * Configure which file and folders are publicly available in the app
     * @param web site which should be ignored
     *
     */
    @Override
    public void configure(WebSecurity web)
    {

        web
                .ignoring().antMatchers("/resources/**")
                .and()
                .ignoring().antMatchers("/h2-console/**");
    }

    /**
     * Configures permissions (authorization) and the login/logout
     * routines on the app.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                    .authorizeRequests()
                    .antMatchers("/admin","/resume/**")
                    .hasAnyAuthority("admin")

                    .antMatchers("/resume/**")
                    .hasAnyAuthority("user")
                    .antMatchers("/index")
                .permitAll()

                .and()
                    .formLogin()
                    .defaultSuccessUrl("/resume")
                    .loginPage("/login")
                    .failureForwardUrl("/login")
                .failureUrl("/login?error=true")
                .and()
                    .logout()
                    .permitAll()
                    .logoutUrl("/logout")
                   .logoutSuccessUrl("/login?logout=true");
    }

    @Override
    public String toString() {
        return "SecurityConfig{" +
                "userService=" + userService +
                '}';
    }
}