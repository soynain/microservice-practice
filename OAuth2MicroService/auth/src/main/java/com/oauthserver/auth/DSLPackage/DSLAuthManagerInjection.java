package com.oauthserver.auth.DSLPackage;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class DSLAuthManagerInjection extends AbstractHttpConfigurer<DSLAuthManagerInjection,HttpSecurity>{

    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        http.authenticationManager(authenticationManager);
        super.configure(http);
    }

    @Override
    public void init(HttpSecurity http) throws Exception {
        // TODO Auto-generated method stub
        super.init(http);
    }

    public static DSLAuthManagerInjection customDsl(){
        return new DSLAuthManagerInjection();
    }
    
}
