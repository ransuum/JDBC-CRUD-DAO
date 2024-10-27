package org.epam.repository;

import org.epam.configuration.DataBaseConnectivity;
import org.epam.databaseRequest.UserSql;
import org.epam.models.dto.UserDto;
import org.epam.models.entity.User;
import org.epam.models.request.UpdateRequestUser;
import org.epam.repository.dao.GenericDao;
import org.epam.util.mapper.UserMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepository implements GenericDao {
    private final Connection connection;
    private final UserMapper userMapper;

    public UserRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
        DataBaseConnectivity dataBaseConnectivity = new DataBaseConnectivity("localhost", "postgres",
                "postgres", "82232");
        try {
            this.connection = dataBaseConnectivity.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto create(User user) {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(UserSql.INSERT_USER.getSqlQuery())) {
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getFirstName());
            preparedStatement.setString(6, user.getLastName());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto findById(String id) {
        UserDto userDto = new UserDto();
        try(PreparedStatement statement = this.connection.prepareStatement(UserSql.GET_USER_BY_ID.getSqlQuery())) {
            statement.setString(5, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                userDto.setId(resultSet.getString("id"));
                userDto.setUsername(resultSet.getString("username"));
                userDto.setEmail(resultSet.getString("email"));
                userDto.setUsername(resultSet.getString("username"));
                userDto.setFirstName(resultSet.getString("first_name"));
                userDto.setLastName(resultSet.getString("last_name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(userDto).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserDto findByEmail(String email) {
        UserDto userDto = new UserDto();
        try(PreparedStatement statement = this.connection.prepareStatement(UserSql.GET_USER_BY_EMAIL.getSqlQuery())) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                userDto.setId(resultSet.getString("id"));
                userDto.setUsername(resultSet.getString("username"));
                userDto.setEmail(resultSet.getString("email"));
                userDto.setUsername(resultSet.getString("username"));
                userDto.setFirstName(resultSet.getString("first_name"));
                userDto.setLastName(resultSet.getString("last_name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(userDto).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserDto update(String id, UpdateRequestUser updateRequestUser) {
        UserDto userDto = null;
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(UserSql.UPDATE_USER.getSqlQuery())) {
            userDto = findById(id);
            if (userDto != null) {
                preparedStatement.setString(1, updateRequestUser.getUsername());
                preparedStatement.setString(2, updateRequestUser.getEmail());
                preparedStatement.setString(3, updateRequestUser.getPassword());
                preparedStatement.setString(4, updateRequestUser.getFirstName());
                preparedStatement.setString(5, updateRequestUser.getLastName());
                preparedStatement.setString(6, id);
                preparedStatement.execute();
                userDto = findById(id);
            } else {
                throw new RuntimeException("User not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userDto;
    }

    @Override
    public void deleteById(String id) {
        try(PreparedStatement statement = this.connection.prepareStatement(UserSql.DELETE_USER.getSqlQuery())) {
            statement.setString(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
