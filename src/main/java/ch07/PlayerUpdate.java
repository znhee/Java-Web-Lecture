package ch07;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PlayerUpdate
 */
@WebServlet("/ch07/updatePlayer")
public class PlayerUpdate extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int backNum = Integer.parseInt(request.getParameter("backNum"));
		PlayerDao dao = new PlayerDao();
		Player p = dao.getPlayer(backNum);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String data = "<!DOCTYPE html>\n"
				+ "<html lang=\"en\">\n"
				+ "<head>\n"
				+ "    <meta charset=\"UTF-8\">\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
				+ "    <title>선수 정보 수정</title>\n"
				+ "    <style>\n"
				+ "        td { text-align: center; padding: 3px;}\n"
				+ "    </style>\n"
				+ "</head>\n"
				+ "<body style=\"margin: 40px;\">\n"
				+ "    <h1>선수 정보 수정</h1>\n"
				+ "    <hr>\n"
				+ "    <form action=\"/jw/ch07/updatePlayer\" method=\"post\">\n"
				+ "        <input type=\"hidden\" name=\"backNum\" value=\"" + p.getBackNum() + "\">"
				+ "        <table>\n"
				+ "            <tr>\n"
				+ "                <td>등 번호</td>";
		data += "<td><input type=\"text\" name=\"backNum\" value=\"" + p.getBackNum() + "\" disabled></td>";
		data += "</tr>\n"
				+ "            <tr>\n"
				+ "                <td>이름</td>";
		data += "<td><input type=\"text\" name=\"name\" value=\"" + p.getName() + "\"></td>";
		data += "</tr>\n"
				+ "            <tr>\n"
				+ "                <td>포지션</td>";
		data += "<td><input type=\"text\" name=\"position\" value=\"" + p.getPosition() + "\"></td>";
		
		data += "</tr>\n"
				+ "            <tr>\n"
				+ "                <td>생일</td>";
		data += "<td><input type=\"text\" name=\"birthday\" value=\"" + p.getBirthday() +"\"></td>";
		data += "</tr>\n"
				+ "            <tr>\n"
				+ "                <td>신장</td>";
		data += "<td><input type=\"text\" name=\"height\" value=\"" + p.getHeight() + "\"></td>";
		data += "</tr>\n"
				+ "            <tr>\n"
				+ "                <td colspan=\"2\"><input type=\"submit\" value=\"수정\"></td>\n"
				+ "            </tr>\n"
				+ "        </table>\n"
				+ "    </form>\n"
				+ "</body>\n"
				+ "</html>";
		out.print(data);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int backNum = Integer.parseInt(request.getParameter("backNum"));
		String name = request.getParameter("name");
		String position = request.getParameter("position");
		String birthday = request.getParameter("birthday");
		int height = Integer.parseInt(request.getParameter("height"));
		
		Player p = new Player(backNum, name, position, birthday, height);
		PlayerDao dao = new PlayerDao();
		dao.updatePlayer(p);
		
		response.sendRedirect("/jw/ch07/playerList");
		
	}

}
