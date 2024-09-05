package gls;

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

/**
 * Servlet implementation class insertUser
 */
@WebServlet("/insertUser")
public class insertUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			String uname = request.getParameter("uname");
			String upass = request.getParameter("upass");
			String umail = request.getParameter("umail");
			Long unum = Long.parseLong(request.getParameter("unum"));
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jaymin","root","admin");
			
			PreparedStatement pst = con.prepareStatement("INSERT INTO users(uname,upass,unum,umail) VALUES(?,?,?,?)");
			pst.setString(1, uname);
			pst.setString(2, upass);
			pst.setLong(3, unum);
			pst.setString(4, umail);
			pst.executeUpdate();
			
			out.println("<html><body><h1>Data Inserted In Table Successfully.</h1>");
			out.println("<a href='index.html'>Back To Home</a>");
			out.println("</body></html>");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
