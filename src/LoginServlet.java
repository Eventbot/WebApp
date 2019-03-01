

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ls")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		String id=request.getParameter("unm");
		String y=request.getParameter("pass");
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "12345");
			PreparedStatement ps=con.prepareStatement("select * from user where userid=? and password=?");
			ps.setString(1, id);
			ps.setString(2, y);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				
				//Create Session
				HttpSession s=request.getSession();
				s.setAttribute("xyz", id);
				//s.setMaxInactiveInterval(25);
				response.sendRedirect("home.html");
			}
			else
			{
				response.sendRedirect("index.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		
	}

}









