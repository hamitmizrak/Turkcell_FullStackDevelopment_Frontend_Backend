package com.hamitmizrak.security;

import com.hamitmizrak.bean.PasswordEncoderBean;
import com.hamitmizrak.business.service.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    //Sistemdeki kullanıcıyı göstermek
    @Autowired
    IUserServices iUserServices;

    //passwordEncoderBean: şifrelerimizi maskeleme yapmak için kullanıyoruz.
    @Autowired
    private PasswordEncoderBean passwordEncoderBean;

    //Override yapmamız gereken method
    //alt+Ins


    // sistemde hangi url'e izin vermek, logout,login v.s alanlara
    //login,logout,register,root,index izin vermek
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/","/index").permitAll()
                    .antMatchers("/static/**","/css/**","/js/**","/images/**").permitAll()
                    .antMatchers("/user/register").permitAll()
                    .anyRequest().authenticated()
                .and()
                   // .formLogin().loginPage("/login").permitAll()
                    .formLogin().loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/secret",true).permitAll()
                .and()
                    .logout().invalidateHttpSession(true).clearAuthentication(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout");
    }

    //Ben olusturdum.
    //PasswordEncoder,kullanıcı kendisine atanan UserDetailsService ile yüklesin ve doğrulasın
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProviderMyBean(){
        DaoAuthenticationProvider authentication=new DaoAuthenticationProvider();
        authentication.setUserDetailsService(iUserServices);
        //maskeleme işlemleri
        authentication.setPasswordEncoder(passwordEncoderBean.passwordEncoderMethod());
        return authentication;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     auth.authenticationProvider(daoAuthenticationProviderMyBean());
    }
}
