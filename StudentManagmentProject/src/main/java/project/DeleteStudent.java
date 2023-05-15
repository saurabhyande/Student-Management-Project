package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/delete")
public class DeleteStudent extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagment","root","saurabh@123");
			PreparedStatement ps = con.prepareStatement("delete from student_table where id=?");
			ps.setString(1, id);
			
			int i = ps.executeUpdate();
			
			PrintWriter pr = res.getWriter();
			if(i>0)
			{
				pr.println("User Is Successfully removed");
			}
			else
			{
				pr.println("Student Id Is Not Found In DataBase");
			}
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
