							
							
/*  This Class "MyservletRequestListener" is responsible for keeping tracks of session.
	*/

import javax.servlet.ServletException;
import javax.servlet.ServletRequestListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebListener;

import java.io.IOException;


@WebListener
 public class MyServletRequestListener implements ServletRequestListener
{

	public void requestInitialized(ServletRequestEvent e)
	{
		
			System.out.println("request Initailized with "+e.getServletRequest());
	}

	
	public void requestDestroyed(ServletRequestEvent e)
	{
		System.out.println("reqiest Destroyed with "+e.getServletRequest());
	}
}