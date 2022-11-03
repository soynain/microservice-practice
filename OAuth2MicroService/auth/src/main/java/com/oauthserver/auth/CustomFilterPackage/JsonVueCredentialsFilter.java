package com.oauthserver.auth.CustomFilterPackage;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/*Custom filter snippet extracted from this source https://ckinan.com/blog/spring-security-credentials-from-json-request/
 * pretty helful resource
 */

public class JsonVueCredentialsFilter extends AbstractAuthenticationProcessingFilter {
    public static final Logger log = LoggerFactory.getLogger(JsonVueCredentialsFilter.class);

    public JsonVueCredentialsFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        String username, password;

        Map<String, String> requestMap = new ObjectMapper().readValue(request.getInputStream(), Map.class);
        username = requestMap.get("usernameBinding");
        password = requestMap.get("passwordBinding");
        log.info(username + " " + password + " -> credenciales del usuario desde el json");

        log.info("afuera del try catch, el error está afuera?");
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        log.info(authRequest.getCredentials().toString() + " credenciales en la instancia -> " + authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

}
