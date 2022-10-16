package org.pharmEasy.config;

import org.pharmEasy.authentication.MyAuthenticationEntryPoint;
import org.pharmEasy.authentication.filter.JwtRequestFilter;
import org.pharmEasy.constants.LoginMappings;
import org.pharmEasy.constants.UserMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // We don't need CSRF for this example
        httpSecurity.csrf().disable()
        // don't authenticate this particular
                 .authorizeRequests().antMatchers('/'+ LoginMappings.LOGIN, UserMappings.USERS + "/" + UserMappings.REGISTER).permitAll().
        				// all other requests need to be authenticated
        				anyRequest().authenticated().and().
        				// make sure we use stateless session; session won't be used to
        				// store user's state.
        				exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint).and().sessionManagement()
        				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        		// Add a filter to validate the tokens with every request
        		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
