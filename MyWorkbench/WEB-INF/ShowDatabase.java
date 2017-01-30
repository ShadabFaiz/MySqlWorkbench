
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
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		response.setContentType("text/html");
		pw=response.getWriter();
		RDispatcher=getServletContext().getRequestDispatcher("/index.html");
		RDispatcher.include(request,response);
		
		String databaseName=request.getParameter("name");
		try
		{	
			pw.println("<h2>"+databaseName+"</h2><br>");
			useDatabase=connection.prepareStatement(" use "+databaseName+";");
			useDatabase.executeQuery();
			System.out.println("Database:"+databaseName+" is in use");
			showtables=connection.prepareStatement("show tables;");
			listofTables=showtables.executeQuery();
			while(listofTables.next())
				pw.println("<a href='./ShowTable?tableName="+listofTables.getString(1)+"'>"+listofTables.getString(1)+"<br>");
		
	}
	catch(SQLException e){ pw.print(e);}

}
}
