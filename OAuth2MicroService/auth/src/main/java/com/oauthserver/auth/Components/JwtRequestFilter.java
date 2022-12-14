package com.oauthserver.auth.Components;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
    private static final org.slf4j.Logger log=LoggerFactory.getLogger(JwtRequestFilter.class);

    /*From our configuration class, we are retrieving our "singleton"
     * bean with the users "repository"
    */
    @Autowired
    private InMemoryUserDetailsManager jwtUserDetailsService;

    /*Declaring our component bean to generate or verify the Jwt payload in our request*/
    @Autowired 
    private JwtGenerator jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                final String requestTokenHeader = request.getHeader("Authorization");
                log.info(requestTokenHeader+" que es lo que trae el requestheader valido para setearlo en vue 3");
                String username = null;
                String jwtToken = null;
                // JWT Token is in the form "Bearer token". Remove Bearer word and get
                // only the Token
                if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                    
                    jwtToken = requestTokenHeader.substring(7);
                    try {
                        username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Unable to get JWT Token");
                    } catch (ExpiredJwtException e) {
                        System.out.println("JWT Token has expired");
                    }
                } else {
                    log.info(requestTokenHeader+" a ver el header");
                    logger.warn("JWT Token does not begin with Bearer String");
                }
        
                // Once we get the token validate it.
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        
                    UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
        
                    // if token is valid configure Spring Security to manually set
                    // authentication
                    if (jwtTokenUtil.validateJwtToken(jwtToken, userDetails)) {
        
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        usernamePasswordAuthenticationToken
                                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        // After setting the Authentication in the context, we specify
                        // that the current user is authenticated. So it passes the
                        // Spring Security Configurations successfully.
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
                filterChain.doFilter(request, response);
        
    }
    
}
