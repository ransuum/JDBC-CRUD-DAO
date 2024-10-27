package org.epam.repository.dao;

import org.epam.models.dto.UserDto;
import org.epam.models.entity.User;
import org.epam.models.request.UpdateRequestUser;

public interface GenericDao {
    UserDto create(User user);
    UserDto findById(String id);
    UserDto update(String id, UpdateRequestUser updateRequestUser);
    void deleteById(String id);
    UserDto findByEmail(String email);
}
