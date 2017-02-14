/* This class is responsible for handling the LogIn.
	Correct Information to login:
						username:anything (it doesn't matter).
						password:qwerty.
						
	Once the information has been verified,it will create a new session from createSession() method and add the "username" and "password"
	attribute the the created session.
	*/

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

@WebServlet("/LogInHandler")
public class LogInHandler extends HttpServlet
{
		
public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
		{
		try { 
			response.setIntHeader("Refresh",1);
			response.setContentType("text/html");
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			System.out.println("username: "+username+"     " +"password: "+password);
			
			PrintWriter pw=response.getWriter();
			if(password!=null && password.equals("qwerty"))
				{
						createSession(username,password,request);
						getServletContext().getRequestDispatcher("/index2.html").include(request,response);
						pw.println("<br><center><h1> Helloo "+username+"!!</h1>"+"</center><br>");
						pw.println("</div></div></body></html>");
						//System.out.println("RequestForwarded successfully.");
				}
			else
			{ getServletContext().getRequestDispatcher("/index.html").forward(request,response);}
		} catch(Exception e){ System.out.println(e);}
		}
		
		
		/* This method is meant to create a new Session upon successful logIn. */
		private  void createSession(String username,String password,HttpServletRequest request)
		{
			//System.out.println(1);
			HttpSession session=request.getSession(true);
			//System.out.println(2);
			session.setAttribute("username",username);
			session.setAttribute("password",password);
			//System.out.println(3);

			//System.out.println(4);
			System.out.println(session.getId()+" with " +session.getMaxInactiveInterval());
		}
		
		
		public static String getDateTime()
		{
			 // Get current time
			Calendar calendar = new GregorianCalendar();
			String am_pm;
			int hour = calendar.get(Calendar.HOUR);
			int minute = calendar.get(Calendar.MINUTE);
			int second = calendar.get(Calendar.SECOND);
		
			if(calendar.get(Calendar.AM_PM) == 0)
					am_pm = "AM";
			else
					am_pm = "PM";
 
			String CT = hour+":"+ minute +":"+ second +" "+ am_pm;	
					return CT;
		}
}