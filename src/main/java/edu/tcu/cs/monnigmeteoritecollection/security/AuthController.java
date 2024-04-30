package edu.tcu.cs.monnigmeteoritecollection.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.tcu.cs.monnigmeteoritecollection.system.*;

@RestController
@RequestMapping("${api.endpoint.base-url}/users")
@CrossOrigin(origins = "*") // Allow requests from any origin
public class AuthController {
    private final AuthService authService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    /**
     * Generate a JSON web token if username and password has been authenticated by the BasicAuthenticationFilter.
     * In summary, this filter is responsible for processing any request that has an HTTP request header of Authorization
     * with an authentication scheme of Basic and a Base64-encoded username:password token.
     * <p>
     * BasicAuthenticationFilter will prepare the Authentication object for this login method.
     * Note: before this login method gets called, Spring Security already authenticated the username and password through Basic Auth.
     * Only successful authentication can make it to this method.
     *
     * @return User information and JSON web token
     */

    @PostMapping("/login")
    public Result getLoginInfo(Authentication authentication){
        LOGGER.debug("Authenticated user: '{}'", authentication.getName());
        return new Result(true, StatusCode.SUCCESS, "User Info and JSON Web Token", this.authService.createLoginInfo(authentication));
    }
}
