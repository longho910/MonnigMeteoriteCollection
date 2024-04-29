package edu.tcu.cs.monnigmeteoritecollection.user.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import edu.tcu.cs.monnigmeteoritecollection.user.User;
import edu.tcu.cs.monnigmeteoritecollection.user.dto.UserDto;

@Component
public class UserToUserDtoConverter implements Converter<User, UserDto> {

    @SuppressWarnings("null")
    @Override
    public UserDto convert(User source) {
        // We are not setting password in DTO.
        final UserDto userDto = new UserDto(source.getId(),
                source.getUsername(),
                source.isEnabled(),
                source.getRoles());
        return userDto;
    }

}