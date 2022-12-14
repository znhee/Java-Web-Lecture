package ch07;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PlayerServlet
 */
@WebServlet("/ch07/playerList")
public class PlayerServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PlayerDao dao = new PlayerDao();
		List<Player> list = dao.getPlayers();
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html>");
		out.print("<html lang=\"ko\">");
		out.print("<head>");
		out.print("    <meta charset=\"UTF-8\">");
		out.print("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
		out.print("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.print("    <title>선수 리스트</title>");
		out.print("</head>");
		out.print("<body style=\"margin: 40px;\">");
		out.print("    <h1>선수 리스트</h1>");
		out.print("    <hr>");
		out.print("	  <table border=\"1\">");
		out.print("	    <tr>");
		out.print("	      <th>등 번호</th><th>이름</th><th>포지션</th><th>생년월일</th><th>신장</th><th>액션</th>");
		out.print("	    </tr>");

		for (Player p: list) {
			out.print("		<tr>");
			out.print("			<td>" + p.getBackNum() + "</td>");
			out.print("			<td>" + p.getName() + "</td>");
			out.print("			<td>" + p.getPosition() + "</td>");
			out.print("			<td>" + p.getBirthday().toString() + "</td>");
			out.print("			<td>" + p.getHeight() + "</td>");
			out.print("			<td><a href=\"/jw/ch07/updatePlayer?backNum=" + p.getBackNum() + "\">수정</a>&nbsp;&nbsp;");
			out.print(		"<a href=\"/jw/ch07/deletePlayer?backNum=" + p.getBackNum() + "\">삭제</a>" + "</td>");
			out.print("		</tr>");
		}
		
		out.print("</table>");
		out.print("	<br>");
		out.print("<a href=\"/jw/ch07/registerPlayer.html\">선수 등록</a>");
		out.print("</body>");
		out.print("</html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
