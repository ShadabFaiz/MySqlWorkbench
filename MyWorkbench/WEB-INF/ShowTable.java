/* This class is used to show the details of the table selected.*/



import java.io.PrintWriter;
import java.io.IOException;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

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
	Connection connection;
	public PrintWriter pw;
	public RequestDispatcher requestDispatcher;
		
	public PreparedStatement statement;
	public String tableName;
	public ResultSet tableresult;
	public ResultSetMetaData ResultMD;
	
	
	/* 	This method is used to display all the details of the selected table.
		Using of the PreparedStatement    */
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
		{
			MySessionListener.checkSession(request,response);
			connection=MyContextListener.connection;
			response.setContentType("text/html");
			pw=response.getWriter();
			
			tableName=request.getParameter("tableName");
			requestDispatcher=getServletContext().getRequestDispatcher("/index2.html");
			requestDispatcher.include(request,response);
			try{
				statement=connection.prepareStatement("select * from "+tableName+";");
				System.out.println(statement);
				tableresult=statement.executeQuery();
				System.out.println("Table:"+tableName+" is in use");
				
				pw.println("<br> <h2> Table: "+tableName+"</h2></br>");
				
				displayResultSet(tableresult);
					
				} 
				catch(SQLException e)
				{  		tableName=tableName.toLowerCase();
						if( tableName.indexOf("select")!=-1 || tableName.indexOf("show")!=-1 || tableName.indexOf("update")!=-1 || tableName.indexOf("delete")!=-1)
							pw.print("<h1>Nice try My BOOUUYYHHH</h1>  ...."+e);
						else
							pw.print("Is that even a table? .......  "+e);
						System.out.println(e);
				}	
				pw.print("</div>");
				pw.print("</body>");
				pw.print("</html>");	
		}
		
		/* 	This method displays the table details.It accepts the ResultSet as its argument to display the table.
			To get the column names,it uses the MetaData class's getColumnCount() and getColumnName().*/
		private void displayResultSet(ResultSet tableresult)throws SQLException
		{
			ResultMD=tableresult.getMetaData();
			int noOfColumns=ResultMD.getColumnCount();
			List<String> columnNames=new ArrayList<>();
			pw.print("<table data-role='table' id='customers' class='ui-responsive ui-shadow'>");
			pw.print("<thead>");
			pw.print("<tr>");
			for(int i=1;i<=noOfColumns;i++)
			{	
				columnNames.add(ResultMD.getColumnName(i));
				pw.print("<th>"+ResultMD.getColumnName(i)+"</th>");
			}
			pw.print("</tr>");
			pw.print("</thead>");
			pw.print("<tbody>");
			while(tableresult.next())
				{
					pw.print("<tr>");
					for(int i=1;i<=noOfColumns;i++)
					{	
						pw.print("<td>"+tableresult.getString(i)+"</td>");
					}
					pw.print("</tr>");					
				}
				pw.print("</tbody>");
				pw.print("</table>");
		}	
}		
