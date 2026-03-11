package onlineexam;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class myregisterclass extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.print(e);
		}
		try {
			Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/onlineexam", "root", "9359");
			Statement s = c.createStatement();
			String user = req.getParameter("user");
			String email = req.getParameter("remail");
			String pass = req.getParameter("rpass");
			String checkque = String.format("SELECT email,password FROM register WHERE email = '%s'", email);
			PrintWriter out = res.getWriter();
			ResultSet rs = s.executeQuery(checkque);
			 String curr = ".......";
			if(rs.next()) {
				curr = rs.getString("email");
			}
			if (curr.equals(email)) {
				out.print("You have alredy registered. Please log in");
			} else {

				String que = String.format("INSERT INTO register(username,email,password) VALUES('%s','%s','%s')", user,
						email, pass);
				s.executeUpdate(que);
				out.print("Registration Successful......");
			}

		} catch (SQLException e) {
			System.out.print(e);
		}
	}
}
