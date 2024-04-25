package edu.tcu.cs.monnigmeteoritecollection.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import edu.tcu.cs.monnigmeteoritecollection.curator.Curator;
import edu.tcu.cs.monnigmeteoritecollection.curator.MyCuratorPrincipal;
import edu.tcu.cs.monnigmeteoritecollection.curator.dto.CuratorDto;
import edu.tcu.cs.monnigmeteoritecollection.curator.converter.CuratorToCuratorDtoConverter;


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
        Curator curator = principal.getCurator();
        CuratorDto curatorDto = this.curatorToCuratorDtoConverter.convert(curator);
        // Create a JWT.
        String token = this.jwtProvider.createToken(authentication);

        Map<String, Object> loginResultMap = new HashMap<>();

        loginResultMap.put("curatorInfo", curatorDto);
        loginResultMap.put("token", token);

        return loginResultMap;
    }
}
