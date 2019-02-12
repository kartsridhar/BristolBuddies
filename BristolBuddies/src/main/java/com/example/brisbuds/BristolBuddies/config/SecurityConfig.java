//package com.example.brisbuds.BristolBuddies.config;
//
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private static final Logger log = LogManager.getLogger();
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // Note:
//        // Use this to enable the tomcat basic authentication (tomcat popup rather than spring login page)
//        // Note that the CSRf token is disabled for all requests
//        log.info("Disabling CSRF, enabling basic authentication...");
//        http
//                .authorizeRequests()
//                .antMatchers("/**").authenticated() // These urls are allowed by any authenticated user
//                .and()
//                .httpBasic();
//        http.csrf().disable();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        String username = "username";
//        String password = "password";
//
////        System.out.println("\nPlease set the admin credentials for this web application (will be required when browsing to the web application)");
////        Console console = System.console();
//
//        // Read the credentials from the user console:
//        // Note:
//        // Console supports password masking, but is not supported in IDEs such as eclipse;
//        // thus if in IDE (where console == null) use scanner instead:
//
//
//        // Set the inMemoryAuthentication object with the given credentials:
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        if (username != null && password != null) {
//            String encodedPassword = passwordEncoder().encode(password);
//            manager.createUser(User.withUsername(username).password(encodedPassword).roles("USER").build());
//        }
//        return manager;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}