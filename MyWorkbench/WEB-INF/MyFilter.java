/* Currently there is no use of this class. */


import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.annotation.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;  
 
@WebFilter( urlPatterns={"/*"} )
public class MyFilter implements Filter{  
  
public void init(FilterConfig filterConfig) throws ServletException {System.out.println("MyWorkbench filter \n \n");}  
      
public void doFilter(ServletRequest req, ServletResponse resp,  
    FilterChain chain) throws IOException, ServletException {  
     
    PrintWriter out=resp.getWriter();  
    //System.out.println("filter is invoked before");  
    //System.out.println("Filter before");  
	 
    chain.doFilter(req, resp);
	
	//System.out.println("Filter after");       
   // out.print("filter is invoked after");  
    }  
    public void destroy() {  }  
}  