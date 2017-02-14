
/* This class is responsible for showing the list of all the databases available.
 */

import java.io.PrintWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/ShowAllDatabases")
public class ShowAllDatabases extends HttpServlet {

	Connection connection=MyContextListener.connection;
	
	/* 	This method will list all the database availble.
		The name of the selected database is send to "ShowDatabase" servlet in form of parameter.*/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
			MySessionListener.checkSession(request,response);
			response.setContentType("text/html");
			PrintWriter pw=response.getWriter();
			RequestDispatcher requestDispatcher=getServletContext().getRequestDispatcher("/index2.html");
			requestDispatcher.include(request,response);
			try{
			PreparedStatement statement=connection.prepareStatement("show databases;");
			ResultSet result=connection.getMetaData().getCatalogs();
			pw.print("<center><h2>DATABASES</h2><br>");
			while(result.next())
					pw.println("<a href='./ShowDatabase?name="+result.getString(1)+"'>"+result.getString(1)+"</a><br>");
			
			pw.print("</center>");
			} 
			catch(SQLException e){ pw.print(e); }
			pw.println("</div></div></body></html>");		
	}
	
	public void destroyed()
	{
		System.out.println("/n/n/n ShowAllDatabase destroyed is running.../n /n /n /n");
		
	}

}