package repository;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
    private String host;
    private String port;
    private String schema;
    private String user;
    private String password;
    private Connection connection = null;

    // Não abrir a conexão no construtor
    public DBConnection(String host, String port, String schema, String user, String password) {
        this.setHost(host);
        this.setPort(port);
        this.setSchema(schema);
        this.setUser(user);
        this.setPassword(password);
    }

    public DBConnection() {
        this("localhost", "3306", "tarefa", "root", "senha");
    }

    
    public Connection getConnection() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            String timezone = "&useTimezone=true&serverTimezone=UTC"; // ajustar timezone
            String url = "jdbc:mysql://" + this.host + ":" + port + "/" + this.schema + "?user=" + this.user + "&password=" + this.password + timezone;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                this.connection = DriverManager.getConnection(url);
            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
                throw new SQLException("Falha ao conectar ao banco de dados.");
            }
        }
        return this.connection;
    }

    // Método para fechar a conexão explicitamente
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	
	public String getHost() {
		return host;
	}
	
	public void setHost(String host) {
		this.host = ( host.isEmpty() ? "localhost" : host ) ;
	}
	
	public String getPort() {
		return port;
	}
	
	public void setPort(String port) {
		this.port = ( port.isEmpty() ? "3306" : port ) ;
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
