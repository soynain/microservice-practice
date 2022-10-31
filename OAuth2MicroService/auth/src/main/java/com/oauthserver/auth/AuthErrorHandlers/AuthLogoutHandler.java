package com.oauthserver.auth.AuthErrorHandlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestKey;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthLogoutHandler implements LogoutSuccessHandler{

    public static final Logger log=LoggerFactory.getLogger(AuthLogoutHandler.class);

    @Override
    public void onLogoutSuccess(
        HttpServletRequest request, 
        HttpServletResponse response, 
        Authentication authentication)
        throws IOException, ServletException 
    {
        log.info("Getting logout request from: "+ request.getHeader("Referer"));
        response.sendRedirect("/login/form");
    }
    
}
