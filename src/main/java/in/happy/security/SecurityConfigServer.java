package in.happy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import in.happy.service.MyUserDetailsService;

@EnableWebSecurity
@Configuration
public class SecurityConfigServer {
	@Autowired
	private MyUserDetailsService userDtlsService;
	
	@Autowired
	  public void configureUsers(AuthenticationManagerBuilder auth) throws Exception {
	        
	        auth.userDetailsService(userDtlsService)
	        .passwordEncoder(NoOpPasswordEncoder.getInstance());
	    }

    @Bean
    public SecurityFilterChain securityconfig(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((req) -> req
                .antMatchers("/contect").permitAll()    // Allow all access to "/conteect"
                .anyRequest().authenticated()           // All other requests need to be authenticated
                 )
             .formLogin();                              // Enable form login

        return http.build();
    }
}
