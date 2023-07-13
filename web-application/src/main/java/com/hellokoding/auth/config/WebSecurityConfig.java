package com.hellokoding.auth.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MD5Encoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        http
                .authorizeRequests()
                .antMatchers("/resources/**","/district/**","/thana/**","/registration","/application/**","/download/**","/","/welcome","/downloadReceipt").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();

         */

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/resources/**","/district/**","/thana/**","/registration","/application/**","/download/**","/","/welcome","/downloadReceipt").permitAll()
                .antMatchers(HttpMethod.POST,"/registration").permitAll()
                .antMatchers(HttpMethod.GET, "/admin/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/user/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/user/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/login").permitAll()
                .and().logout().permitAll()
                .disable();
    }
    /*
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.POST,"/registration","/downloadReceipt","/admin/**");
    }

     */

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}