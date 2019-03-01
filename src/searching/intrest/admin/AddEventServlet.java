package shopping.cart.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ap")
public class AddEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		String proid=request.getParameter("pid");
		String proname=request.getParameter("pname");
		String proprice=request.getParameter("pprice");
		int price=Integer.parseInt(proprice);
		String probrand=request.getParameter("pbrand");
		String procate=request.getParameter("pcate");
	
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "12345");
			PreparedStatement ps=con.prepareStatement("insert into product values(?,?,?,?,?)");
			ps.setString(1, proid);
			ps.setString(2, proname);
			ps.setInt(3, price);
			ps.setString(4, probrand);
			ps.setString(5, procate);
			int i=0;
			i=ps.executeUpdate();
			if(i!=0)
			{
				response.sendRedirect("addpro.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}







