package com.oauthserver.auth.Controllers;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oauthserver.auth.Components.JwtGenerator;
import com.oauthserver.auth.PojoSets.CredentialsRequest;
import com.oauthserver.auth.PojoSets.JwtTokenResponse;

@RestController
@RequestMapping("/")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private InMemoryUserDetailsManager userDetailsService;
    
    @PostMapping("/auth")
    public ResponseEntity<?> generateAndReturnTheJwtTokenToClient(@RequestBody CredentialsRequest userCredentials) throws Exception{
        log.info(userCredentials.toString());
        final String token=authenticate(userCredentials.getUsernameBinding(), userCredentials.getPasswordBinding());
        return ResponseEntity.ok(new JwtTokenResponse(token));
    }

   private String authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtGenerator.generateJwtToken(userDetailsService.loadUserByUsername(username));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
    
    @GetMapping("/redirect/done")
    public String jsonPrueba(){
        return "ay caramba";
    }
}
