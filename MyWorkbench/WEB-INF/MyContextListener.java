import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebListener;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.DriverManager;


@WebListener
public class MyContextListener implements ServletContextListener {

	static Connection connection;
	public void contextInitialized(ServletContextEvent event)
	{
		System.out.println("ServletContext is initializing.....");
		createDatabaseConnection();
	}
	
	public void contextDestroyed(ServletContextEvent event)
	{	
		System.out.println("Application is closing...");
		closeAllStream();
	}
	
	private void createDatabaseConnection()
	{
		System.out.println("Database connection is initiated.....");
		String dbUrl="jdbc:mysql://localhost:3306/?zeroDateTimeBehavior=convertToNull";
		try
		  {
			connection=DriverManager.getConnection(dbUrl,"root","MySql");
			System.out.println("\n Connection is  established");
	     } 
		catch(SQLException e){ System.out.println( e+"\n Connection is not established"); }		
	}
	
	
	public static void closeAllStream()
	{
		try{ 
				connection.close(); 
			} catch(SQLException e){ System.out.println(e);}
	}
	
}