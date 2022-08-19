package com.hamitmizrak.security;

import com.hamitmizrak.bean.PasswordEncoderBean;
import com.hamitmizrak.bean.PersistentTokenRepositoryBean;
import com.hamitmizrak.business.service.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    //Sistemdeki kullanıcıyı göstermek
    @Autowired
    IUserServices iUserServices;

    //Login Encoder -Decoder
    //passwordEncoderBean: şifrelerimizi maskeleme yapmak için kullanıyoruz.
    @Autowired
    private PasswordEncoderBean passwordEncoderBean;

    //Remember Me -1
    @Autowired
    private UserDetailsService userDetailsService;

    //Remember Me -2
    @Autowired
    PersistentTokenRepositoryBean persistentTokenRepositoryBean;


    // sistemde hangi url'e izin vermek, logout,login v.s alanlara
    //login,logout,register,root,index izin vermek
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/","/index").permitAll()
                    .antMatchers("/static/**","/css/**","/js/**","/images/**").permitAll()
                    .antMatchers("/user/register").permitAll()
                   // .antMatchers("/send/mimemail","/send/simplemail").permitAll()
                    .anyRequest().authenticated()
                .and()
                   // .formLogin().loginPage("/login").permitAll()
                    .formLogin().loginPage("/login").loginProcessingUrl("/login")
                //eger login olursam beni hangi sayfaya redirect yapılacak gosterir ==> defaultSuccessUrl
                .defaultSuccessUrl("/admin",true).permitAll()
                .and()
                    .logout().invalidateHttpSession(true).clearAuthentication(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
                .and()
                .rememberMe()
                .key("uniqueAndSecret")
                // saniye cinsinden devam 1YIL :31.104.000
               // .tokenValiditySeconds(31104000)
                .tokenValiditySeconds(1*60*60*24*30*12) //1 YIL
                .tokenRepository(persistentTokenRepositoryBean.persistentTokenRepositoryBeanMethod())
                .userDetailsService(userDetailsService);;
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
