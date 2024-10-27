package org.epam.databaseRequest;

public enum UserSql {
    GET_USER_BY_ID("SELECT id, first_name, last_name, username, email, password FROM users WHERE id = ?"),
    GET_USER_BY_EMAIL("SELECT id, first_name, last_name, username, email, password FROM users WHERE email = ?"),
    UPDATE_USER("UPDATE users SET username = ?, email = ?, password = ?, first_name = ?, last_name = ? WHERE id = ?"),
    DELETE_USER("DELETE FROM users WHERE id = ?"),
    INSERT_USER("INSERT INTO users(id, username, email, password, first_name, last_name) VALUES (?, ?, ?, ?, ?, ?)");

    private final String sqlQuery;

    UserSql(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    public String getSqlQuery() {
        return sqlQuery;
    }

}
