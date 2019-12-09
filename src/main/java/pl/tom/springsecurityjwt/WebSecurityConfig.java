package pl.tom.springsecurityjwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Bean
//    PasswordEncoder getPasswordEncokder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser(User.builder()
//                .username("test")
//                .password(getPasswordEncokder().encode("test"))
//                .roles("ADMIN"));
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/hello1").permitAll()
                .antMatchers("/hello2").hasRole("USER")
                .antMatchers("/hello3").hasRole("ADMIN")
                .and()
                .addFilter(new JwtFilter(authenticationManager()));
    }
}
