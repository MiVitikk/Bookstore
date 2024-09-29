package backend24.bookstore;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

import backend24.bookstore.web.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

        @Autowired
        private UserDetailServiceImpl userDetailsService;

        private static final AntPathRequestMatcher[] WHITE_LIST_URLS = { 
			new AntPathRequestMatcher("/api/books**"),
			new AntPathRequestMatcher("/h2-console/**") };

        @Bean
        public SecurityFilterChain configure(HttpSecurity http) throws Exception {
                http
                        .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/", "/index").permitAll()
                                .requestMatchers(antMatcher("/css/**")).permitAll()
                                .requestMatchers(WHITE_LIST_URLS).permitAll()
                                .anyRequest().authenticated())
                        .headers(headers -> 
				headers.frameOptions(frameOptions -> frameOptions 
					.disable()))
                        .formLogin(formlogin -> formlogin
                                .loginPage("/login")
                                .defaultSuccessUrl("/booklist", true)
                                .permitAll())
                        .logout(logout -> logout
                                .permitAll())
                        .csrf(csrf -> csrf.disable());
                return http.build();
        }

        @Bean
        public UserDetailsService userDetailsService() {

                List<UserDetails> users = new ArrayList<UserDetails>();

                PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

                UserDetails user1 = User.withUsername("user").password(passwordEncoder.encode("user")).roles("USER")
                                .build();

                users.add(user1);

                UserDetails user2 = User.withUsername("admin").password(passwordEncoder.encode("admin")).roles("ADMIN")
                                .build();

                users.add(user2);

                return new InMemoryUserDetailsManager(users);
        }

        @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

}
