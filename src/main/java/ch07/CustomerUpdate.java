package ch07;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerUpdate
 */
@WebServlet("/ch07/updateCustomer")
public class CustomerUpdate extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		
		CustomerDao dao = new CustomerDao();
		Customer c = dao.getCustomer(uid);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String data = "<!DOCTYPE html> "
				+ "<html lang=\"en\"> "
				+ "<head> "
				+ "    <meta charset=\"UTF-8\"> "
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> "
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> "
				+ "    <title>회원 정보 수정</title> "
				+ "    <style> "
				+ "        td { text-align: center; padding: 3px;} "
				+ "    </style> "
				+ "</head> "
				+ "<body style=\"margin: 40px;\"> "
				+ "    <h1>회원 정보 수정</h1> "
				+ "    <hr> "
				+ "    <form action=\"/jw/ch07/updateCustomer\" method=\"post\"> "
				+ "<input type=\"hidden\" name=\"uid\" value=\"" + c.getUid() + "\">"
				+ "        <table> "
				+ "            <tr> "
				+ "                <td>사용자 ID</td>";
		data += "<td><input type=\"text\" name=\"uid\" value=\"" + c.getUid() + "\" disabled></td>";
		data += "			</tr> "
				+ "            <tr> "
				+ "                <td>사용자명</td>";
		data += "<td><input type=\"text\" name=\"uname\" value=\"" + c.getUname() + "\"></td>";
		data += "			</tr> "
				+ "            <tr> "
				+ "                <td>가입일자</td>";
		data += "<td><input type=\"text\" name=\"regDate\" value=\"" + c.getRegDate() + "\" disabled></td>";
		data += "			</tr> "
				+ "            <tr> "
				+ "                <td colspan=\"2\"><input type=\"submit\" value=\"수정\"></td> "
				+ "            </tr> "
				+ "        </table> "
				+ "    </form> "
				+ "</body> "
				+ "</html>";
		out.print(data);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		String uname = request.getParameter("uname");
		
		Customer c = new Customer(uid, uname);
		CustomerDao dao = new CustomerDao();
		dao.updateCustomer(c);
		
		response.sendRedirect("/jw/ch07/customerList");
	}

}
