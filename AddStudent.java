package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import com.mysql.cj.jdbc.Driver;

@WebServlet("/studentdata")
public class AddStudent extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		Random r = new Random();
		int id = 10+r.nextInt(99);
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String yop = req.getParameter("yop");
		String gender = req.getParameter("gender");
		String branch = req.getParameter("branch");
		
		
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagment","root","saurabh@123");
			PreparedStatement ps = con.prepareStatement("insert into student_table(id,name,email,yop,gender,branch) values(?,?,?,?,?,?)");
			
			
			System.out.println("id : "+id);
			System.out.println("Student name : "+name);
			System.out.println("email : "+email);
			System.out.println("yop : "+yop);
			System.out.println("gender : "+gender);
			System.out.println("branch : "+branch);
			
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setString(4, yop);
			ps.setString(5, gender);
			ps.setString(6, branch);
			
			ps.execute();
			
			PrintWriter pr = res.getWriter();
			res.setContentType("text/html"); 
			
			pr.println("<html lang=\"en\">\r\n"
					+ "  <head>\r\n"
					+ "    <title>Submited</title>\r\n"
					+ "    <style>\r\n"
					+ "      table,\r\n"
					+ "      th,\r\n"
					+ "      td {\r\n"
					+ "        border: 1px solid black;\r\n"
					+ "        text-align: center;\r\n"
					+ "        padding: 1rem;\r\n"
					+ "        margin: auto;\r\n"
					+ "      }\r\n"
					+ "      button {\r\n"
					+ "        display: flex;\r\n"
					+ "        margin: auto;\r\n"
					+ "        margin-top: 1rem;\r\n"
					+ "        height: 3rem;\r\n"
					+ "        width: 6rem;\r\n"
					+ "        border-radius: 0.5rem;\r\n"
					+ "        justify-content: center;\r\n"
					+ "        align-items: center;\r\n"
					+ "      }\r\n"
					+ "    </style>\r\n"
					+ "  </head>\r\n"
					+ "  <body>\r\n"
					+ "    <table>\r\n"
					+ "      <tr>\r\n"
					+ "        <th>ID</th>\r\n"
					+ "        <th>Name</th>\r\n"
					+ "        <th>Email</th>\r\n"
					+ "        <th>Year Of Passing</th>\r\n"
					+ "        <th>Gender</th>\r\n"
					+ "        <th>Branch</th>\r\n"
					+ "      </tr>\r\n"
					+ "      <tr>\r\n"
					+ "        <td>"+id+"</td>\r\n"
					+ "        <td>"+name+"</td>\r\n"
					+ "        <td>"+email+"</td>\r\n"
					+ "        <td>"+yop+"</td>\r\n"
					+ "        <td>"+gender+"</td>\r\n"
					+ "        <td>"+branch+"</td>\r\n"
					+ "      </tr>\r\n"
					+ "    </table>\r\n"
					+ "    <button onclick=\"window.location.href='index.html'\">Home</button>\r\n"
					+ "  </body>\r\n"
					+ "</html>");
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con!=null)
			{
				try {
					con.close();
					System.out.println("connection close");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
