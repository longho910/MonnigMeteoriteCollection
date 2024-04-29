package edu.tcu.cs.monnigmeteoritecollection.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import edu.tcu.cs.monnigmeteoritecollection.user.User;
import edu.tcu.cs.monnigmeteoritecollection.user.MyCuratorPrincipal;
import edu.tcu.cs.monnigmeteoritecollection.user.dto.CuratorDto;
import edu.tcu.cs.monnigmeteoritecollection.user.converter.CuratorToCuratorDtoConverter;


import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private final JwtProvider jwtProvider;
    private final CuratorToCuratorDtoConverter curatorToCuratorDtoConverter;

    public AuthService(JwtProvider jwtProvider, CuratorToCuratorDtoConverter curatorToCuratorDtoConverter) {
        this.jwtProvider = jwtProvider;
        this.curatorToCuratorDtoConverter = curatorToCuratorDtoConverter;
    }

    public Map<String, Object> createLoginInfo(Authentication authentication) {
        // Create curator info.
        MyCuratorPrincipal principal = (MyCuratorPrincipal)authentication.getPrincipal();
        User user = principal.getCurator();
        CuratorDto curatorDto = this.curatorToCuratorDtoConverter.convert(user);
        // Create a JWT.
        String token = this.jwtProvider.createToken(authentication);

        Map<String, Object> loginResultMap = new HashMap<>();

        loginResultMap.put("curatorInfo", curatorDto);
        loginResultMap.put("token", token);

        return loginResultMap;
    }
}
