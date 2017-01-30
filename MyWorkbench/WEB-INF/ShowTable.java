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


@WebServlet("/ShowTable")
public class ShowTable extends HttpServlet 
{
	
		public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
		{
			PreparedStatement statement;
			Connection connection=MyContextListener.connection;
			PrintWriter pw;
			response.setContentType("text/html");
			String tableName=request.getParameter("tableName");
			pw=response.getWriter();
			pw.print("<center>");
			try{
				statement=connection.prepareStatement("select * from "+tableName+";");
				//statement.setString(1,tableName);
				System.out.println(statement);
								System.out.println(tableName);

				ResultSet result=statement.executeQuery();
				System.out.println("Table:"+tableName+" is in use");
				ResultSetMetaData ResultMD=result.getMetaData();
				int noOfColumns=ResultMD.getColumnCount();
				
				while(result.next())
				{
					for(int i=1;i<=noOfColumns;i++)
						pw.print(result.getString(i)+"&nbsp;&nbsp;&nbsp;&nbsp");
				
					
					pw.print("<br>");
			
				}
				
			} catch(SQLException e)
			{  		tableName=tableName.toLowerCase();
					if( tableName.indexOf("select")!=-1 || tableName.indexOf("show")!=-1 || tableName.indexOf("update")!=-1 || tableName.indexOf("delete")!=-1)
						pw.print("<h1>Nice try My BOOUUYYHHH</h1>");
					else
						pw.print("Is that even a table?");
				
				}
			pw.print("</center>");
		}
}		
