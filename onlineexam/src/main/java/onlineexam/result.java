package onlineexam;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/result")
public class result extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String email = req.getParameter("checkemail");
		String pass = req.getParameter("checkpass");
		PrintWriter pw = res.getWriter();
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("mysql://root:BLxszxszNtRQoshBrKkeXbZqkRAWjVbW@crossover.proxy.rlwy.net:44741/railway", "root", "BLxszxszNtRQoshBrKkeXbZqkRAWjVbW");
			Statement s = c.createStatement();
			String que = String.format("Select * from result Where email = '%s'", email);
			ResultSet rs = s.executeQuery(que);
			String name = "", Uemail = "", Upass = "";
			int marks = 0;
			if (rs.next()) {
				name = rs.getString("name");
				Uemail = rs.getString("email");
				marks = rs.getInt("marks");
				Upass = rs.getString("password");
			}
			if (Uemail.equals(email) && Upass.equals(pass)) {
				pw.println("Your Name : "+name);
				pw.println("Marks : "+marks);
				if(marks >= 4) {
					pw.println("Result : Pass");					
				}else {
					pw.println("Result : Fail");
				}
			} else {
				pw.print("invalid emial or password");
			}
			
		} catch (Exception e) {
			System.out.print(e);
		}

	}
}
