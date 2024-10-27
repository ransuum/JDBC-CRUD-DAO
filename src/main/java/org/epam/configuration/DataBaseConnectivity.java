package org.epam.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnectivity {
    private Properties properties;
    private String url;

    public DataBaseConnectivity(String host, String db, String username, String password) {
        this.properties = new Properties();
        this.properties.put("user", username);
        this.properties.put("password", password);
        this.url = "jdbc:postgresql://" + host + "/" + db;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url, this.properties);
    }
}
