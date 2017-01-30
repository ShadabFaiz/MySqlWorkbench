


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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
			
			response.setContentType("text/html");
			PrintWriter pw=response.getWriter();
			RequestDispatcher requestDispatcher=getServletContext().getRequestDispatcher("/index.html");
			requestDispatcher.include(request,response);
			try{
			PreparedStatement statement=connection.prepareStatement("show databases;");
			ResultSet result=connection.getMetaData().getCatalogs();
			while(result.next())
			{	
					pw.println("<a href='./ShowDatabase?name="+result.getString(1)+"'>"+result.getString(1)+"</a><br>");
		
			}
			} 
			catch(SQLException e){ pw.print(e); }
			
	}
	
	public void destroyed()
	{
		System.out.println("/n/n/n ShowAllDatabase destroyed is running.../n /n /n /n");
		
	}

}