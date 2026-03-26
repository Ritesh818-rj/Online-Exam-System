package onlineexam;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class MyloginClass extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.print(e);
		}
		try {
			Connection c = DriverManager.getConnection("mysql://root:BLxszxszNtRQoshBrKkeXbZqkRAWjVbW@crossover.proxy.rlwy.net:44741/railway", "root", "BLxszxszNtRQoshBrKkeXbZqkRAWjVbW");
			Statement s = c.createStatement();
			String myemail = req.getParameter("email");
			String mypass = req.getParameter("pass");
			String que = String.format("SELECT * FROM register WHERE email = '%s'", myemail);

			PrintWriter out = res.getWriter();
			ResultSet rs = s.executeQuery(que);
			String mye = "......", myp = "......", myu = ".......";
			if (rs.next()) {
				mye = rs.getString("email");
				myp = rs.getString("password");
				myu = rs.getString("username");
			}
			String que2 = String.format("Select email,password from result where email = '%s'", myemail);
			Statement s1 = c.createStatement();
			ResultSet rs1 = s1.executeQuery(que2);
			String aque = "",apass = "";
			if (rs1.next()) {
				aque = rs.getString("email");
				apass = rs.getString("password");
			}
			if (myemail.equals(aque) && mypass.equals(apass) ) {
				out.print("You have already taken the exam __ now go check your result");
			} else if (mye.equals(myemail) && myp.equals(mypass)) {
				HttpSession ht = req.getSession();
				ht.setAttribute("sendemail", myemail);
				ht.setAttribute("senduser", myu);
				ht.setAttribute("sendmypass", mypass);
				res.sendRedirect("exam.html");
			} else {
				out.print("Invalid email or Password");
				out.println("\n\nPlease registere before loging in.");
			}

		} catch (SQLException e) {
			System.out.print(e);
		}
	}

}
