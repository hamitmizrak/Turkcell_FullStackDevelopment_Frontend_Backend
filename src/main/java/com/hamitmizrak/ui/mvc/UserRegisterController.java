package com.hamitmizrak.ui.mvc;

import com.hamitmizrak.business.dto.UserRegisterDto;
import com.hamitmizrak.business.service.IUserServices;
import com.hamitmizrak.data.entity.UserRegisterEntity;
import com.hamitmizrak.data.repository.IUserRegisterRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@Log4j2
public class UserRegisterController {

    //@Autowired
    private IUserServices services;
    private IUserRegisterRepository repository;

    //org.apache.logging.log4j.Logger
    //@Autowired
    //Logger logger = (Logger) LoggerFactory.getLogger(UserRegisterController.class);

    //parametreli constructor
    @Autowired
    public UserRegisterController(IUserServices services) {
        this.services = services;
    }


    // home page
    // http://localhost:8080/
    // http://localhost:8080/index
    @GetMapping( {"/","/index"})
    public String homePage(@PathVariable(name = "index",required = false) String path) {
        return "index";
    }

    // login
    // http://localhost:8080/login
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/secret")
    public String adminPage(){
        return "login_page";
    }

    @GetMapping("/login-user")
    public String loginUser(UserRegisterEntity userRegisterEntity, HttpServletRequest request){
        //email ve şifre varsa
        if(repository.findByEmailAndPassword(userRegisterEntity.getEmail(),userRegisterEntity.getPassword()) !=null){
        }
//        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
//        if(authentication!=null){
//            return "login";
//        }
//        HttpSession session=request.getSession();
//        if(session!=null){
//            return "login_page";
//        }
        return "login_page";
    }


    // login
    // http://localhost:8080/login
   // @GetMapping("/login")
    //public String loginPage(HttpServletRequest request, HttpServletResponse response){
//        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
//        if(authentication!=null){
//            return "login";
//        }
//        HttpSession session=request.getSession();
//        if(session!=null){
//            return "login_page";
//        }
      //  return "login";
    //}







    // #####################################################
    //register ==> GetMapping
    // http://localhost:8080/user/register
    @GetMapping("/user/register")
    public String getRegisterForm(Model model){
        model.addAttribute("user",new UserRegisterDto());
        return "register";
    }

    //register ==> PostMapping
    // http://localhost:8080/user/register
    @PostMapping("/user/register")
    public String postRegisterForm(@Valid @ModelAttribute("user") UserRegisterDto userRegisterDto ,BindingResult bindingResult ){
        log.info("Log4j2:" +userRegisterDto);
        if (bindingResult.hasErrors()) {
            log.error("Kaydetme Hatası:" +userRegisterDto);
            return "register";
        }
       // logger.info("LoggerSlf: "+userRegisterDto);
        services.saveUserRegister(userRegisterDto);
        //return "redirect:/user/register?success";
        return "redirect:/login?success";
    }
}
