

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

@WebServlet("/ds")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		HttpSession s=request.getSession();
		String x=(String)s.getAttribute("xyz");
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "12345");
			PreparedStatement ps=con.prepareStatement("delete from user where userid=?");
			ps.setString(1, x);
			int i=0;
			i=ps.executeUpdate();
			if(i!=0)
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









