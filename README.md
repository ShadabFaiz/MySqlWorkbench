# MySqlWorkbench
This project is about the creation of a web-based GUI for MySql database.
#Technologies Used:
  **Front-End:** Html,CSS.  
  **Back-End:** Java,Servlet,Apache-Tomcat-Server 8.  
#PREREQUISITES  
   Before you try to deploy this project on your system.make sure you have the following softwares installed and configured properly
    1. jdk ver1.8 or higher
    2. A Web/Application Server (Weblogic/Tomcat/Glassfish/etc).
    3. MySql ver5.7 or higher configured to port no:3306 and localhost (If it is configured to other port/ip address,then make sure to  
    change these from "servletContextListener" file.)  
#HOW TO START THE APLLICATION/PROJECT:
Now there is 2 ways to deploy this project:  

1. CREATING A .WAR FILE  
To create a .war file,open the terminal/command prompt.Navigate to the downloaded directory.Type the following command: 'jar -cvf MyWorkbench.war ' A "MyWorkbench.war file will created.Deploy it to the server.Check the respective Server's document on how to deploy a .war 
file on it.  

2. WITHOUT .WAR FILE  
Not every server support the deployment without .war file.Check whether the Server you are using has functionality to       
deploy it without a .war file.This method is for deploy on Tomcat ver-8 server only.Other Servers may have different       
method.Copy the download folder "MyWorkbench" to Tomcat/webapps" folder.Run your tomcat server.Visit the address           
htttp://localhost/MyWorkbench.You will be greeted with the Welcome Screen.

