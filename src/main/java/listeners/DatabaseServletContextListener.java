package listeners;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import utils.DBConnection;

@WebListener
public class DatabaseServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();

		try {
			DBConnection dbConnection = initializeDatabaseConnection();
		context.setAttribute("dbConnection", dbConnection);
		System.out.println("Show!");
		} catch (SQLException e) {
			handleDatabaseConnectionError(e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		DBConnection dbConnection = DBConnection.getInstance();

		closeDatabaseConnection(dbConnection);
		deregisterJDBCDriver();
		stopAbandonedConnectionCleanupThread();

		context.removeAttribute("dbConnection");
	}
	
	private DBConnection initializeDatabaseConnection() throws SQLException {
		DBConnection dbConnection = DBConnection.getInstance();
		if (dbConnection == null || dbConnection.getConnection() == null) {
			throw new SQLException("Falha ao estabelecer conexão com o banco de dados.");
		}
		return dbConnection;
	}

	private void handleDatabaseConnectionError(SQLException e) {
		System.err.println("Erro ao estabelecer conexão com o banco de dados: " + e.getMessage());
	}

	private void closeDatabaseConnection(DBConnection dbConnection) {
		if (dbConnection != null) {
			dbConnection.close();
			System.out.println("Conexão com o banco de dados fechada com sucesso.");
		}
	}

	private void deregisterJDBCDriver() {
		try {
			DriverManager.deregisterDriver(DriverManager.getDriver("jdbc:mysql://"));
			System.out.println("Driver JDBC desregistrado com sucesso.");
		} catch (SQLException e) {
			System.err.println("Erro ao desregistrar o driver JDBC: " + e.getMessage());
		}
	}

	private void stopAbandonedConnectionCleanupThread() {
		try {
			AbandonedConnectionCleanupThread.uncheckedShutdown();
			System.out.println("Cleanup thread de conexões abandonadas interrompido com sucesso.");
		} catch (Exception e) {
			System.err.println("Erro ao interromper o cleanup thread de conexões abandonadas: " + e.getMessage());
		}
	}

	
	
}
