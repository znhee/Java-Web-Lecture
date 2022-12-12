package ch07;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PlayerInsert
 */
@WebServlet("/ch07/registerPlayer")
public class PlayerInsert extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String backNum_ = request.getParameter("backNum");
		int backNum = Integer.parseInt(backNum_);
		String name = request.getParameter("name");
		String position = request.getParameter("position");
		String birthday = request.getParameter("birthday");
		String height_ = request.getParameter("height");
		int height = Integer.parseInt(height_);
		
		PlayerDao dao = new PlayerDao();
		
		// 유효성 검증 
		Player p = dao.getPlayer(backNum);
		if (p.getName() != null)
			response.sendRedirect("/jw/ch07/registerPlayer.html");
		else {
			dao = new PlayerDao();
			p = new Player(backNum, name, position, birthday, height);
			dao.insertPlayer(p);
			
			response.sendRedirect("/jw/ch07/playerList");
			
		}
		
	}

}
