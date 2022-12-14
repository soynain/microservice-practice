package com.oauthserver.auth.Controllers;


//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.oauthserver.auth.AuthServerConfiguration.QueueComponentsConfig;
import com.oauthserver.auth.Components.JwtGenerator;
import com.oauthserver.auth.PojoSets.CredentialsRequest;
import com.oauthserver.auth.PojoSets.JwtTokenResponse;

@RestController
@RequestMapping("/auth")
public class LoginController {
    //private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private InMemoryUserDetailsManager userDetailsService;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    
    @PostMapping("/login")
    public ResponseEntity<?> generateAndReturnTheJwtTokenToClient(@RequestBody CredentialsRequest userCredentials) throws Exception{
        //log.info(userCredentials.toString()+" a ver las credenciales");
        String token=authenticate(userCredentials.getUsernameBinding(), userCredentials.getPasswordBinding());
        //log.info(token+" a ver el token");
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
    
    //@GetMapping("/products/get-all")
    @RequestMapping(method = RequestMethod.GET,value = "/products/get-all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getJsonResponseWithToken(){
        org.springframework.amqp.core.Message newMessage=MessageBuilder.withBody("".getBytes()).build();
        org.springframework.amqp.core.Message jsonResponse=this.rabbitTemplate.sendAndReceive(QueueComponentsConfig.TOPIC_EXCHANGE_NAME, QueueComponentsConfig.QUEUE_NAME, newMessage);
        //log.info(jsonResponse.getBody().toString());
        return ResponseEntity.ok().body(new String(jsonResponse.getBody()));
        //return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("http://localhost:8092/products/get-all")).build();
    }
}
