

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/us")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out=response.getWriter();
		response.setContentType("application/vnd");
		
		String userid=request.getParameter("uid");
		String npassword=request.getParameter("newpass");
		String nname=request.getParameter("newname");
		String age=request.getParameter("newage");
		int x=Integer.parseInt(age);
		String ncity=request.getParameter("newcity");

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "12345");
			PreparedStatement ps=con.prepareStatement("update user set password=?, name=?, age=?, city=? where userid=?");
			ps.setString(1, npassword);
			ps.setString(2, nname);
			ps.setInt(3, x);
			ps.setString(4, ncity);
			ps.setString(5, userid);
			
			int i=0;
			i=ps.executeUpdate();
			if(i!=0)
			{
				RequestDispatcher rd=request.getRequestDispatcher("home.html");
				rd.forward(request, response);
			}
			else
			{
				response.sendRedirect("edit.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}






