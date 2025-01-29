package com.io.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.io.scm.service.impl.SecurityCustomUserDetailsService;

@Configuration
public class SecurityConfig {
    /*
     * For in-memory user details
     * 
     * @Bean
     * public UserDetailsService userDetailsService()
     * {
     * UserDetails user1=User
     * .withUsername("Anant")
     * .password("{noop}Anant123")
     * .roles("USER","ADMIN")
     * .build();
     * 
     * UserDetails user2=User
     * .withUsername("Kanha")
     * .password("{noop}Kanha123")
     * .roles("USER")
     * .build();
     * 
     * 
     * var inMemoryUserDetailsManager=new InMemoryUserDetailsManager(user1,user2);
     * return inMemoryUserDetailsManager;
     * 
     * }
     */

    /*
     * Login when user login using username and password , loadUserByUserName method
     * of userDetailsService is called and pass the given username , it simply
     * return userDetails type object that contain its all details(username and
     * original password) and original password and given password is matcher by
     * specify password encoder in by authentication provider.
     */
    // Step -2 : Create CustomUserDetails Service
    @Autowired
    private SecurityCustomUserDetailsService userDetailsService;

    @Autowired
    private AuthFailureHandler authFailureHandler;

    // Step - 1 : Create AuthenticationProvider
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // user detail service ka object :
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        // password encoder ka object :
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    // Step -3 : Specify password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private OAuthAuthenticationSuccessHandler handler;

    // Implement Security Filter Chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // urls configure kiay hai ki kaun se public rahenge aur koun se private rahenge
        httpSecurity.authorizeHttpRequests(authorize -> {
            // authorize.requestMatchers("/home", "/register", "/services").permitAll();
            authorize.requestMatchers("/user/**").authenticated(); // authentication all request that is under /user
            authorize.anyRequest().permitAll(); // it by pass all this routes
        });

        // form default login
        // agar hame kuch bhi change karna hua to hame yaha ayenge : form login
        // Form default login
        // httpSecurity.formLogin(Customizer.withDefaults());

        // custom login configuration
        httpSecurity.formLogin(formLogin -> {

            formLogin.loginPage("/login"); // when login page functionalties execute
            formLogin.loginProcessingUrl("/authenticate");
            formLogin.successForwardUrl("/user/profile"); // where to forward given url
            // formLogin.failureForwardUrl("/login?error=true"); // to show exception in
            // login
            // formLogin.defaultSuccessUrl("/home");
            formLogin.usernameParameter("email"); // we take username as email
            formLogin.passwordParameter("password");

            // formLogin.failureHandler(new AuthenticationFailureHandler() {

            // @Override
            // public void onAuthenticationFailure(HttpServletRequest request,
            // HttpServletResponse response,
            // AuthenticationException exception) throws IOException, ServletException {
            // // TODO Auto-generated method stub
            // throw new UnsupportedOperationException("Unimplemented method
            // 'onAuthenticationFailure'");
            // }

            // });

            // formLogin.successHandler(new AuthenticationSuccessHandler() {

            // @Override
            // public void onAuthenticationSuccess(HttpServletRequest request,
            // HttpServletResponse response,
            // Authentication authentication) throws IOException, ServletException {
            // // TODO Auto-generated method stub
            // throw new UnsupportedOperationException("Unimplemented method
            // 'onAuthenticationSuccess'");
            // }

            // });
            formLogin.failureHandler(authFailureHandler);

        });

        // to diable csrf authencation
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        // logout functionalties implementation
        httpSecurity.logout(logoutForm -> {
            // when user click on do-logout url
            logoutForm.logoutUrl("/do-logout");
            // to show login again if logout is successfult
            logoutForm.logoutSuccessUrl("/login?logout=true");
        });

        // httpSecurity.oauth2Login(Customizer.withDefaults());

        httpSecurity.oauth2Login(oauth -> {
            oauth.loginPage("/login");
            oauth.successHandler(handler);
            // oauth.defaultSuccessUrl("/user/dashboard");
        });

        return httpSecurity.build();
    }

}
