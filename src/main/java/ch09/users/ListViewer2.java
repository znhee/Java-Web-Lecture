package ch09.users;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ListViewer2
 */
@WebServlet("/ch09/users/listView")
public class ListViewer2 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String sessionUid = (String)session.getAttribute("uid");
		
		List<User> list = (List<User>)request.getAttribute("userList");
		String data = "<!DOCTYPE html>\n"
				+ "<html lang=\"ko\">\n"
				+ "<head>\n"
				+ "    <meta charset=\"UTF-8\">\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
				+ "    <title>사용자 목록</title>\n"
				+ "    <style>\n"
				+ "        td { text-align: center; padding: 3px;}\n"
				+ "    </style>\n"
				+ "</head>\n"
				+ "<body style=\"margin: 40px;\">\n"
				+ "    <h1>사용자 목록</h1>\n";
		if (session.getAttribute("uid") != null) {		// login 되어 있는 상태
			data += "<button onclick=\"location.href='/jw/ch09/users/logout'\">로그아웃</button>";
			data += session.getAttribute("uname") + "님 환영합니다.";
		} else {
			data += "<button onclick=\"location.href='/jw/ch09/users/login.html'\">로그인</button>";
		}
		data +=	"    <hr>\n"
				+ "    <table border=\"1\">\n"
				+ "        <tr><th>UID</th><th>PWD</th><th>이름</th><th>email</th><th>등록일자</th><th>액션</th></tr>";
		for (User u: list) {
			data += "<tr>";
			data += "<td>" + u.getUid() + "</td>";
			data += "<td>" + u.getPwd() + "</td>";
			data += "<td>" + u.getUname() + "</td>";
			data += "<td>" + u.getEmail() + "</td>";
			data += "<td>" + u.getRegDate() + "</td>";
			data += "<td>";
			// 본인만이 수정 권한이 있음
			if (sessionUid == null || !sessionUid.equals(u.getUid()))
				data += "<button onclick=\"location.href='/jw/ch09/users/update?uid='\" disabled>수정</button>\n";
			else
				data += "<button onclick=\"location.href='/jw/ch09/users/update?uid='\">수정</button>\n";
			// 관리자(admin)만이 삭제 권한이 있음
			if (sessionUid == null || !sessionUid.equals("admin"))
				data += "<button onclick=\"location.href='/jw/ch09/users/delete?uid='\" disabled>삭제</button>";
			else
				data += "<button onclick=\"location.href='/jw/ch09/users/delete?uid='\">삭제</button>";
			data += "</td>";
			data += "</tr>";
		}
		data += "</table>\n"
				+ "    <br>\n"
				+ "    <a href=\"/jw/ch09/users/register.html\">회원 가입</a>\n"
				+ "</body>\n"
				+ "</html>";
		out.print(data);
	}

}
