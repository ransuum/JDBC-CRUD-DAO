package org.epam.util.mapper;

import org.epam.models.dto.UserDto;
import org.epam.models.entity.User;

public class UserMapper {

    public UserDto userToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail()
        );
    }
}
