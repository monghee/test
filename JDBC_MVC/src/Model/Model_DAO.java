package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Model_DAO {
	
	// 공통적으로 들어가는 지역변수를 -> 전역변수로 정의해주기
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	int cnt = 0;
	Model_VO vo = null;

	ArrayList<Model_VO> al = new ArrayList<Model_VO>();
	
	//db연결 메서드 생성하기
	public void conn() {
		try {

			
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
				String user = "hr"; 
				String password = "hr"; 
				 conn = DriverManager.getConnection(url,user,password); 
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

// close method 생성	
	public void close() {
		try {
			if (rs != null) {
			rs.close();
			}if(psmt != null) {
			psmt.close();
			}if(conn != null) {
			conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//insertStd method생성 - 가져온 정보를 삽입하는 기능
	public int insertStd(Model_VO vo) { 
		//전역변수로 이동
//		Connection conn = null;
//		PreparedStatement psmt = null;
		
		
		//>> return에서 반환값을 담아줄 수있도록 try 밖에서 변수 선언!
//		int cnt = 0; 
		
//		
			try {
				
					conn(); // -> 위에서 정의해줬기 때문에 실행을 위해 불러오기만
					//전역변수로 이동
//					Class.forName("oracle.jdbc.driver.OracleDriver");
//					
//					String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
//					String user = "hr"; 
//					String password = "hr"; 
//					 conn = DriverManager.getConnection(url,user,password); 
					String sql = "insert into bigdata values(?,?,?,?)";
					psmt = conn.prepareStatement(sql);
					
					psmt.setString(1,vo.getName()); 
					psmt.setInt(2,vo.getAge());  
					psmt.setString(3, vo.getTel());
					psmt.setString(4, vo.getAddress());
					
					cnt = psmt.executeUpdate(); 
						// -->> 여기서 view에게 수행의 결과 값을 보여주기 위해	
					if(cnt>0) { // 모델단에서 출력되는 부분 -> 사용자에게는 보이지 않음!
						System.out.println("Model단 : 데이터 삽입 성공!");
							}

// 위에서 메소드로 정의했기 때문에 삭제
//				} catch (ClassNotFoundException e) { 
//					System.out.println("class를 찾지 못하였습니다.");
//					e.printStackTrace(); 
					
				} catch (SQLException e) { 
					e.printStackTrace();
				}finally { 
					close();
//					try {
//						psmt.close();
//						conn.close();
//					}catch (SQLException e) {
//						e.printStackTrace();
//					}
//		
				}
				return cnt;
	}
	
	//selectStd method생성 - 학생들의 정보를 읽어서 반환해준다.
		// al라는 배열리스트에 정보를 읽어서 저장하였기 때문에 반환타입은 ArrayList
	public ArrayList<Model_VO> selectStd() {
//		전역변수로 이동
//		Connection conn = null;
//		PreparedStatement psmt = null;
//		ResultSet rs = null;
		
		// 사용자에게 정보 출력을 위한 변수 생성
		// 도출된 정보를 하나로 묶어서 관리 - ArrayList(가변적인 정보를 담으려고)
//		ArrayList<Model_VO> al = new ArrayList<Model_VO>(); -> 전역변수로 이동
		try {
			conn();
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			String user = "hr";
//			String password = "hr";
//			
//			conn = DriverManager.getConnection(url, user, password);
			
			psmt = conn.prepareStatement("select * from bigdata"); 
			rs = psmt.executeQuery();
			// 사용자에게 결과를 출력하기 때문에 여기서 읽은 정보를 출력할 수 있게 묶어준다.
			while(rs.next()) { 
				String name = rs.getString("name");
				int age = rs.getInt(2);
				String tel = rs.getString("tel");
				String address = rs.getString(4);
				
				// 한사람의 대한 정보를 도출
				Model_VO vo = new Model_VO(name,age,tel,address);
				// 도출된 정보를 하나로 묶어서 관리 변수에 저장
					//(반복적으로 도출된 정보를 반복적으로 저장하기위해)
				al.add(vo);
			}
				
				
		}catch(Exception e) { 
			e.printStackTrace();
		}finally {
			close();
// method로 생성해서 사용하기 위해 삭제
//				try {
//					rs.close();
//					psmt.close();
//					conn.close();
//				}catch(Exception e) {
//					e.printStackTrace();
//				}
			
		}
		return al;
		
	}

	public Model_VO selectOneStd(String name) {

		// 전역변수로 이동
//		Connection conn = null;
//		PreparedStatement psmt = null;
//		ResultSet rs = null;
//		Model_VO vo = null;
		
		
		try {
			conn();
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			String user = "hr";
//			String password = "hr";
//			conn = DriverManager.getConnection(url, user, password);
			String sql ="select * from bigdata where name=?"; // sql에 where조건문으로 실행로직이 간단!효율!!
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, name);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				String getName = rs.getString("name");
				int age = rs.getInt("age");
				String tel = rs.getString("tel");
				String address = rs.getString("address");
				
				vo = new Model_VO(getName, age, tel, address);
			}else {
				System.out.println("검색한 이름이 없습니다.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
//			try {
//				rs.close();
//				psmt.close();
//				conn.close();
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
		
		}
		return vo;
	}
	public int updateStd(String name, String address) {
		//전역변수로 이동
//		Connection conn = null;
//		PreparedStatement psmt = null; 
//		Scanner sc = new Scanner(System.in);
//		int cnt = 0;
		
		try { 
			conn();
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			String user = "hr";
//			String password = "hr";
//			conn = DriverManager.getConnection(url, user, password);
			
			String sql = "update bigdata set address = ? where name = ?";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, address);
			psmt.setString(2, name);
			
			cnt = psmt.executeUpdate();
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			close();
//			try {
//				psmt.close();
//				conn.close();
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
				
		}
		return cnt;
	
	}

	public int deleteStd (String name) {
		//전역변수로 이동
//		Connection conn =null;
//		PreparedStatement psmt = null;
//		Scanner sc = new Scanner(System.in);
//		int cnt = 0;
		
		try {
			conn();
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			String user = "hr";
//			String password = "hr";
//			conn = DriverManager.getConnection(url,user,password);
			
			String sql = "delete from bigdata where name =?";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1,name);
			
			 cnt = psmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			close();
//			try {
//				psmt.close();
//				conn.close();
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
			
		}
		return cnt;
	}
}

