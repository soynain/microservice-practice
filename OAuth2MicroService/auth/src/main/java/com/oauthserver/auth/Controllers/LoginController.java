package com.oauthserver.auth.Controllers;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class LoginController {
    
    @GetMapping
    public String prueba(){
        return "Autorizado mijo";
    }


    
}
