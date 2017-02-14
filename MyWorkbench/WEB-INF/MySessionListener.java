							
							
/*  This Class "MySessionListener" is responsible for keeping tracks of session.
	*/

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebListener;
import javax.servlet.RequestDispatcher;

import java.util.Enumeration;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.io.IOException;



@WebListener
public class MySessionListener implements HttpSessionListener
{
	private HttpSession session;
	
	
	/*sessionCreated: 	This method is automatically invoked whenever a new session is created.
						Currently it only prints newly created session Id with its maxInactiveInteval time (60). */
	public void sessionCreated(HttpSessionEvent e)
	{
		session=e.getSession();
		session.setMaxInactiveInterval(3);	
		System.out.println("Created : "+session.getId()+" with " +session.getMaxInactiveInterval());
	}

	
	
	/*sessionDestroyed:	This method also automatically invoked whenever a session is destroyed.
						Currently it only prints newly created session Id with its maxInactiveInteval time (60). */
	public void sessionDestroyed(HttpSessionEvent e)
	{
				System.out.println("Destroyed: "+e.getSession().getId()+" with " +e.getSession().getMaxInactiveInterval());	
				session.invalidate();
	}

	
	
	/*checkSession:     This method checks whether the session for the passed request is still available or not.
						If the there is not session available,then it forward the request to error page "seesionOut.html" otherwise
						it prints sessionId and its maxInactiveInterval.
						(NOTE: this method must be called from each and every servlet at the beginning before doing anything to make sure
						the session is alive.)		 */
	public static void checkSession(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		HttpSession ses=request.getSession(false);
		if(ses!=null)
		{
			System.out.println("Session:"+ses.getId());
			System.out.println("Session:"+ses.getMaxInactiveInterval());
		} 
		else 
		{
			System.out.println("Invalid Sesson");
			request.getServletContext().getRequestDispatcher("/sessionOut.html").forward(request,response);	
		}
	}
	
}
