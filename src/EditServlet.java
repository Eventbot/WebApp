

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

@WebServlet("/es")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		HttpSession s=request.getSession();
	
		String id=null;
		id=(String) s.getAttribute("xyz");
		if(id==null)
		{
			response.sendRedirect("login.html");
		}
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "12345");
			PreparedStatement ps=con.prepareStatement("select * from user where userid=?");
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				out.println("<form action=us>");
					out.println("<table align=center>");
					out.println("<tr>");
						out.println("<td>ID</td>");
						out.println("<td><input type=text value="+rs.getString(1)+" name=uid></td>");
					out.println("</tr>");


					out.println("<tr>");
						out.println("<td>PASSWORD</td>");
						out.println("<td><input type=text value="+rs.getString(2)+" name=newpass></td>");
					out.println("</tr>");

					out.println("<tr>");
						out.println("<td>NAME</td>");
						out.println("<td><input type=text value="+rs.getString(3)+" name=newname></td>");
					out.println("</tr>");

					out.println("<tr>");
						out.println("<td>AGE</td>");
						out.println("<td><input type=text value="+rs.getInt(4)+" name=newage></td>");
					out.println("</tr>");

					out.println("<tr>");
						out.println("<td>CITY</td>");
						out.println("<td><input type=text value="+rs.getString(5)+" name=newcity></td>");
					out.println("</tr>");

					out.println("<tr>");
						out.println("<td>GENDER</td>");
						out.println("<td>"+rs.getString(6)+"</td>");
					out.println("</tr>");

					out.println("<tr>");
						out.println("<td></td>");
						out.println("<td><input type=submit value=SAVE></td>");
					out.println("</tr>");
				
					out.println("</table>");
					out.println("</form>");

			}
			else
			{
				out.println("<h1>No Record Found for this ID</h1>");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		
	}

}









