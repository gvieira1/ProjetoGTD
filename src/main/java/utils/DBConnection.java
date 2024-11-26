package utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBConnection {

    private String host;
    private String port;
    private String schema;
    private String user;
    private String password;
    
    private static DBConnection instance;
    private static HikariDataSource dataSource;

    private DBConnection(String host, String port, String schema, String user, String password) {
        this.host = host;
        this.port = port;
        this.schema = schema;
        this.user = user;
        this.password = password;
        initializeDataSource();
    }

    private DBConnection() {
        this("localhost", "3306", "tarefa", "root", "senha");
    }

    public static DBConnection getInstance(String host, String port, String schema, String user, String password) {
        if (instance == null) {
            instance = new DBConnection(host, port, schema, user, password);
        }
        return instance;
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    private void initializeDataSource() {
        if (dataSource == null) {
            HikariConfig config = new HikariConfig();
            String jdbcUrl = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.schema + "?useTimezone=true&serverTimezone=UTC";
            config.setJdbcUrl(jdbcUrl);
            config.setUsername(this.user);
            config.setPassword(this.password);
            config.setDriverClassName("com.mysql.cj.jdbc.Driver");
            config.setMaximumPoolSize(10); 
            config.setConnectionTimeout(30000); //(30 segundos)
            config.setIdleTimeout(600000); // (10 minutos)
            
            dataSource = new HikariDataSource(config);
        }
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection(); 
    }

    public void close() {
        if (dataSource != null) {
            dataSource.close(); 
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = (host.isEmpty() ? "localhost" : host);
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = (port.isEmpty() ? "3306" : port);
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
