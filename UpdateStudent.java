package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/update")
public class UpdateStudent extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		
		int id1 = Integer.parseInt(id); 
		
		
		Connection con =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagment","root","saurabh@123");
			Statement stmt = con.createStatement();
			String sql = ("SELECT * FROM student_table WHERE id='"+id+"'");
			ResultSet rs = stmt.executeQuery(sql);
			PrintWriter pw= res.getWriter();
			res.setContentType("text/html");
			
			if(rs.next())
			{
				PreparedStatement pr = con.prepareStatement("update student_table set name=? where id=?");	
				pr.setInt(2, id1);
				pr.setString(1, name);
				pr.execute();
				
				pw.println("<html lang=\"en\">\r\n"
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
						+ "      </tr>");
					pw.print("<tr>\r\n"
							+ "        <td>"+rs.getInt(1)+"</td>\r\n"
							+ "        <td>"+name+"</td>\r\n"
							+ "        <td>"+rs.getString(3)+"</td>\r\n"
							+ "        <td>"+rs.getString(4)+"</td>\r\n"
							+ "        <td>"+rs.getString(5)+"</td>\r\n"
							+ "        <td>"+rs.getString(6)+"</td>\r\n"
							+ "      </tr>");
				pw.println("</table>\r\n"
						+ "    <button onclick=\"window.location.href='index.html'\">Home</button>\r\n"
						+ "  </body>\r\n"
						+ "</html>");
			}
			else
			{
				pw.println("<h3>NO = "+id+" Id Found In Data Base<h3>");
			}
			
			System.out.println("id :"+id1);
			System.out.println("name :"+name);
			
					
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
