/* This class is used to show the list of taables available in the database. */



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
import java.sql.ResultSetMetaData;

@WebServlet("/ShowDatabase")
public class ShowDatabase extends HttpServlet
{
	Connection connection=MyContextListener.connection;
	PreparedStatement showtables;
	PreparedStatement useDatabase;
	ResultSet listofTables;
	
	RequestDispatcher RDispatcher;
	PrintWriter pw;
	
	/* 	To decide which database's tables to show,the database name is passed as the parameter "name" along with the 
		request.Upon retrieving the database name,it will execute the query to show all tables available in the database (in form of links).
		These links will create the table names as parameter "tableName".*/
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		MySessionListener.checkSession(request,response);
		response.setContentType("text/html");
		pw=response.getWriter();
		RDispatcher=getServletContext().getRequestDispatcher("/index2.html");
		RDispatcher.include(request,response);
		
		String databaseName=request.getParameter("name");
		try
		{	
			pw.println("<h2> Database: "+databaseName+"</h2>");
			useDatabase=connection.prepareStatement(" use "+databaseName+";");
			useDatabase.executeQuery();
			System.out.println("Database:"+databaseName+" is in use");
			showtables=connection.prepareStatement("show tables;");
			listofTables=showtables.executeQuery();
			while(listofTables.next())
				pw.println("<a href='./ShowTable?tableName="+listofTables.getString(1)+"'>"+listofTables.getString(1)+"<br>");
		
	    }
		catch(SQLException e){ pw.print(e);}
		pw.println("</div></div></body></html>");
	}
}
