package edu.tcu.cs.monnigmeteoritecollection.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import edu.tcu.cs.monnigmeteoritecollection.user.User;
import edu.tcu.cs.monnigmeteoritecollection.user.MyUserPrincipal;
import edu.tcu.cs.monnigmeteoritecollection.user.dto.UserDto;
import edu.tcu.cs.monnigmeteoritecollection.user.converter.UserToUserDtoConverter;


import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private final JwtProvider jwtProvider;
    private final UserToUserDtoConverter userToUserDtoConverter;

    public AuthService(JwtProvider jwtProvider, UserToUserDtoConverter userToUserDtoConverter) {
        this.jwtProvider = jwtProvider;
        this.userToUserDtoConverter = userToUserDtoConverter;
    }

    public Map<String, Object> createLoginInfo(Authentication authentication) {
        // Create curator info.
        MyUserPrincipal principal = (MyUserPrincipal)authentication.getPrincipal();
        User user = principal.getUser();
        UserDto userDto = this.userToUserDtoConverter.convert(user);

        // Create a JWT.
        String token = this.jwtProvider.createToken(authentication);

        Map<String, Object> loginResultMap = new HashMap<>();

        loginResultMap.put("userInfo", userDto);
        loginResultMap.put("token", token);

        return loginResultMap;
    }
}
