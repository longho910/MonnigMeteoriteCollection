package edu.tcu.cs.monnigmeteoritecollection.user.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import edu.tcu.cs.monnigmeteoritecollection.user.User;
import edu.tcu.cs.monnigmeteoritecollection.user.dto.UserDto;

@Component
public class UserDtoToUserConverter implements Converter<UserDto, User> {

    @SuppressWarnings("null")
    @Override
    public User convert(UserDto source) {
        User User = new User();
        User.setUsername(source.username());
        User.setEnabled(source.enabled());
        User.setRoles(source.roles());
        return User;
    }

}