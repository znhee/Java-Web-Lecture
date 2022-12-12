package ch07;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PlayerDao {
	public static Connection getConnection() {
		Connection conn;
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/world");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}
	
	public Player getPlayer(int backNum) {
		Connection conn = getConnection();
		String sql = "SELECT * FROM baseballplayer WHERE backNum=?;";
		Player p = new Player();
		
		PreparedStatement pStmt;
		try {
			pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, backNum);
			
			// Select 실행
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				p.setBackNum(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setPosition(rs.getString(3));
				p.setBirthday(LocalDate.parse(rs.getString(4)));
				p.setHeight(rs.getInt(5));
				p.setIsDeleted(rs.getInt(6));
			}	
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public List<Player> getPlayers() {
		Connection conn = getConnection();
		Statement stmt = null;
		List<Player> list = new ArrayList<>();
		String sql = "SELECT * FROM baseballplayer WHERE isDeleted = 0;";
		try {
			stmt = conn.createStatement();
			
			// Select 실행
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Player p = new Player();
				p.setBackNum(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setPosition(rs.getString(3));
				p.setBirthday(LocalDate.parse(rs.getString(4)));
				p.setHeight(rs.getInt(5));
				p.setIsDeleted(rs.getInt(6));
				list.add(p);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public void insertPlayer(Player p) {
		Connection conn = getConnection();
		String sql = "INSERT INTO baseballplayer VALUES (?,?,?,?,?,DEFAULT);";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, p.getBackNum());
			pStmt.setString(2, p.getName());
			pStmt.setString(3, p.getPosition());
			pStmt.setString(4, p.getBirthday().toString());
			pStmt.setInt(5, p.getHeight());
			
			pStmt.executeUpdate();
			pStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updatePlayer(Player p) {
		Connection conn = getConnection();
		
		String sql = "UPDATE baseballplayer SET name=?, position=?, birthday=?, height=? WHERE backNum=?;";
		PreparedStatement pStmt;
		try {
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, p.getName());
			pStmt.setString(2, p.getPosition());
			pStmt.setString(3, p.getBirthday().toString());
			pStmt.setInt(4, p.getHeight());
			pStmt.setInt(5, p.getBackNum());
			
			// Update 실행
			pStmt.executeUpdate();
			pStmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deletePlayer(int backNum) {
		Connection conn = getConnection();
		String sql = "UPDATE baseballplayer SET isDeleted=1 WHERE backNum=?;";
		
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, backNum);
			
			// Delete 대신에 isDeleted 필드를 1로 변경
			pStmt.executeUpdate();
			pStmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
