package com.oauthserver.auth.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public ModelAndView getLoginForm(){
        ModelAndView c1=new ModelAndView("userLogin");
        c1.addObject("profObject", "hola carnal");
        return c1;
    }

    

    @GetMapping("/another")
    public String prueba2() {
        return "holaMundo";
    }

    
}
