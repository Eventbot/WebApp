import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/nus")
public class NewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String name=request.getParameter("nm");
                                     String username=request.getParameter("unm");		
                                     String password=request.getParameter("pass");

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "12345");
			PreparedStatement ps1=con.prepareStatement("select userid from user");
			ResultSet rs=ps1.executeQuery();
			int v=0;
			String userid=null;
			if(rs.last())
			{
				String id=rs.getString(1);
				String str[]=id.split("/");
				v=Integer.parseInt(str[2]);
				v++;
				userid=str[0]+"/"+str[1]+"/"+`v;

			}
			PreparedStatement ps2=con.prepareStatement("insert into user values(?,?,?)");

			ps2.setString(1,name );
			ps2.setString(2, username);
			ps2.setString(3, password);
			
			
			
			int i=0;
			i=ps2.executeUpdate();
			if(i!=0)
			{
					out.println("<h1 align=center>Your ID is "+userid+"</h1>");
					out.println("<h2 align=center><a href=login.html>Now Login..</a></h2>");

			}
			else
			{
				response.sendRedirect("signup.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}






