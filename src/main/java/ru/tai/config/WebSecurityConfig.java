package ru.tai.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import ru.tai.service.UserService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private DataSource dataSource;
    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/",   // Пазрешаем полный доступ к главной транице
                            "/registration",        // Разрешаем полный доступ к страниуе регистрации
                            "/system/**", "/css/**", "/js/**")           // Разрешаем полный доступ к статике
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .usernameParameter("login") // Попле добавлено дополнительно
                    .passwordParameter("password") // Попле добавлено дополнительно
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    // указываем URL при удачном логауте
//                    .logoutSuccessUrl("/login?logout")
                    // делаем не валидной текущую сессию
//                    .invalidateHttpSession(true)
                    .permitAll();
                //.and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(NoOpPasswordEncoder.getInstance())
//                .usersByUsernameQuery("select login, password, active from usr where login=?")
//                .authoritiesByUsernameQuery("select u.login, r.role from usr u " +
//                        "inner join user_role ur " +
//                        "on u.id = ur.user_id " +
//                        "inner join role r " +
//                        "on ur.role_id = r.id " +
//                        "where u.login = ?");
//        System.out.println("auth");
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}

