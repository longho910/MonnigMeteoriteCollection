package edu.tcu.cs.monnigmeteoritecollection.user.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import edu.tcu.cs.monnigmeteoritecollection.user.User;
import edu.tcu.cs.monnigmeteoritecollection.user.dto.CuratorDto;

@Component
public class CuratorToCuratorDtoConverter implements Converter<User, CuratorDto> {

    @SuppressWarnings("null")
    @Override
    public CuratorDto convert(User source) {
        // We are not setting password in DTO.
        final CuratorDto curatorDto = new CuratorDto(source.getId(),
                source.getUsername(),
                source.isEnabled(),
                source.getRoles());
        return curatorDto;
    }

}