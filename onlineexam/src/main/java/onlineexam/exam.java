package onlineexam;

import java.io.IOException;
import java.sql.Statement;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.Connection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/exam")
public class exam extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// b1 a2 c3 a4 c5 a6 a7 b8 c9 a10
		String first,second,third,four,five,six,seven,eight,nine,ten;
		 first = req.getParameter("first");
		 second = req.getParameter("second");
		 third = req.getParameter("third");
		 four = req.getParameter("four");
		 five = req.getParameter("five");
		 six = req.getParameter("six");
		 seven = req.getParameter("seven");
		 eight = req.getParameter("eight");
		 nine = req.getParameter("nine");
		 ten = req.getParameter("ten");
		String ar[] = { first, second, third, four, five, six, seven, eight, nine, ten };
		String optionar[] = { "b1", "a2", "c3", "a4", "c5", "a6", "a7", "b8", "c9", "a10" };
		int marks = 0;
		for (int i = 0; i < ar.length; i++) {
			if (optionar[i].equals(ar[i])) {
				marks++;
			}

		}
		HttpSession ht1 = req.getSession();
		String email = (String) ht1.getAttribute("sendemail");
		String user = (String) ht1.getAttribute("senduser");
		String pass = (String) ht1.getAttribute("sendmypass");
		res.setContentType("text/html");
		 PrintWriter pw = res.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
Connection c = DriverManager.getConnection("mysql://root:BLxszxszNtRQoshBrKkeXbZqkRAWjVbW@crossover.proxy.rlwy.net:44741/railway", "root", "BLxszxszNtRQoshBrKkeXbZqkRAWjVbW");
			Statement st = c.createStatement();
			String que = String.format("Insert Into result Value('%s','%s','%d','%s')",user,email,marks,pass);
			st.executeUpdate(que);

			pw.println("<html>");
			pw.println("<body>");
			pw.println("<b>Your exam has been Submitted.<br> Kindly go back and view the result</b>");
			pw.println("<br><br><a href='index.html'> <button>Go Back</button></a>");
			pw.println("</body>");
			pw.println("</html>");
		} catch (Exception ex) {
			System.out.print(ex);
		}
	}
}
























