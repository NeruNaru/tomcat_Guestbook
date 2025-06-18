package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import com.javaex.vo.GuestbookVO;

public class GuestbookDAO {
	// field
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/guestbook_db";
	private String id = "guestbook";
	private String pw = "guestbook";

	// editor
	public GuestbookDAO() {
		super();
	}

	// method normal
	// DB연결 메소드-공통
	private void connect() { // 메인에서는 사용하지 못함

		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			this.conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	// 자원정리 메소드-공통
	private void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// insert
	public int guestInsert(GuestbookVO guestbookvo) {
		System.out.println("GuestbookDAO_guestInsert() ready");
		int count = -1;

		try

		{
			// 1. JDBC 드라이버 (MySQL) 로딩
			// 2. Connection 얻어오기
			this.connect();

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " insert into guestbook ";
			query += " values(null, ?, ?, ?, ?) ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, guestbookvo.getName());
			pstmt.setString(2, guestbookvo.getPassword());
			pstmt.setString(3, guestbookvo.getContent());
			pstmt.setTimestamp(4, Timestamp.valueOf(guestbookvo.getRegDate()));

			// 실행
			count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println(count + "건 등록완료");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		System.out.println("GuestbookDAO_guestInsert() Progress completed");
		this.close();

		return count;
	} // insert end

	// delete
	public int guestDelete(int no) {
		System.out.println("GuestbookDAO_guestDelete() ready");
		int count = -1;

		try

		{
			// 1. JDBC 드라이버 (MySQL) 로딩
			// 2. Connection 얻어오기
			this.connect();

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " delete from guestbook ";
			query += " where no = ? ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			// 실행
			count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println(count + "건 삭제완료");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		System.out.println("GeustbookDAO_guestDelete() Progress completed");
		this.close();

		return count;
	} // delete end

	// selectall
	public List<GuestbookVO> guestSelectAll() {
		System.out.println("GuestbookDAO_guestSelectAll() ready");

		List<GuestbookVO> guestList = new ArrayList<GuestbookVO>();

		this.connect();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " select no, ";
			query += " 		  name, ";
			query += "		  password, ";
			query += "		  content, ";
			query += " 		  reg_date ";
			query += " from guestbook ";
			query += " order by no desc ";
			System.out.println(query);

			// 바인딩
			pstmt = conn.prepareStatement(query);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리

			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");			
				LocalDateTime regDate = rs.getTimestamp("reg_date").toLocalDateTime();
				GuestbookVO guestbookvo = new GuestbookVO(no, name, password, content, regDate);

				guestList.add(guestbookvo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		System.out.println("GuestbookDAO_guestSelectAll() Progress completed");
		this.close();

		return guestList;
	} // selectAll end

	// selectOne
	public GuestbookVO guestSelectOne(int no) {
		System.out.println("GuestbookDAO_guestSelectOne() ready");
		GuestbookVO guestbookvo = null;

		this.connect();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " select no, ";
			query += " 		  name, ";
			query += "		  password, ";
			query += "		  content, ";
			query += " 		  reg_date ";
			query += " from guestbook ";
			query += " where no = ? ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리 (java 리스트로 만든다)
			if (rs.next()) {
				guestbookvo = new GuestbookVO(rs.getInt("no"),
						rs.getString("name"),
						rs.getString("password"),
						rs.getString("content"),
						rs.getTimestamp("reg_date").toLocalDateTime()
						);
			}

		} catch (

		SQLException e) {
			System.out.println("error:" + e);
		}
		System.out.println("GuestbookDAO_guestSelectOne() Progress completed");
		// 5. 자원정리
		this.close();

		return guestbookvo;
	}
}
