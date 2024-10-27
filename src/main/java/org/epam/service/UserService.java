package org.epam.service;

import org.epam.models.dto.UserDto;
import org.epam.models.entity.User;
import org.epam.models.request.UpdateRequestUser;
import org.epam.repository.UserRepository;
import org.epam.repository.dao.GenericDao;

public class UserService {
    private final GenericDao genericDao;

    public UserService(UserRepository genericDao) {
        this.genericDao = genericDao;
    }

    public UserDto createUser(User user) {
        return genericDao.create(user);
    }

    public UserDto updateUser(String id, UpdateRequestUser updateRequestUser) {
        return genericDao.update(id, updateRequestUser);
    }

    public UserDto getUser(String id) {
        return genericDao.findById(id);
    }

    public void deleteUser(String id) {
        genericDao.deleteById(id);
    }

    public UserDto getUserByEmail(String email) {
        return genericDao.findByEmail(email);
    }
}
