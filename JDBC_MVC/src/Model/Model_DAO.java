package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Model_DAO {
	
	// ���������� ���� ���������� -> ���������� �������ֱ�
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	int cnt = 0;
	Model_VO vo = null;

	ArrayList<Model_VO> al = new ArrayList<Model_VO>();
	
	//db���� �޼��� �����ϱ�
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

// close method ����	
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

	//insertStd method���� - ������ ������ �����ϴ� ���
	public int insertStd(Model_VO vo) { 
		//���������� �̵�
//		Connection conn = null;
//		PreparedStatement psmt = null;
		
		
		//>> return���� ��ȯ���� ����� ���ֵ��� try �ۿ��� ���� ����!
//		int cnt = 0; 
		
//		
			try {
				
					conn(); // -> ������ ��������� ������ ������ ���� �ҷ����⸸
					//���������� �̵�
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
						// -->> ���⼭ view���� ������ ��� ���� �����ֱ� ����	
					if(cnt>0) { // �𵨴ܿ��� ��µǴ� �κ� -> ����ڿ��Դ� ������ ����!
						System.out.println("Model�� : ������ ���� ����!");
							}

// ������ �޼ҵ�� �����߱� ������ ����
//				} catch (ClassNotFoundException e) { 
//					System.out.println("class�� ã�� ���Ͽ����ϴ�.");
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
	
	//selectStd method���� - �л����� ������ �о ��ȯ���ش�.
		// al��� �迭����Ʈ�� ������ �о �����Ͽ��� ������ ��ȯŸ���� ArrayList
	public ArrayList<Model_VO> selectStd() {
//		���������� �̵�
//		Connection conn = null;
//		PreparedStatement psmt = null;
//		ResultSet rs = null;
		
		// ����ڿ��� ���� ����� ���� ���� ����
		// ����� ������ �ϳ��� ��� ���� - ArrayList(�������� ������ ��������)
//		ArrayList<Model_VO> al = new ArrayList<Model_VO>(); -> ���������� �̵�
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
			// ����ڿ��� ����� ����ϱ� ������ ���⼭ ���� ������ ����� �� �ְ� �����ش�.
			while(rs.next()) { 
				String name = rs.getString("name");
				int age = rs.getInt(2);
				String tel = rs.getString("tel");
				String address = rs.getString(4);
				
				// �ѻ���� ���� ������ ����
				Model_VO vo = new Model_VO(name,age,tel,address);
				// ����� ������ �ϳ��� ��� ���� ������ ����
					//(�ݺ������� ����� ������ �ݺ������� �����ϱ�����)
				al.add(vo);
			}
				
				
		}catch(Exception e) { 
			e.printStackTrace();
		}finally {
			close();
// method�� �����ؼ� ����ϱ� ���� ����
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

		// ���������� �̵�
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
			String sql ="select * from bigdata where name=?"; // sql�� where���ǹ����� ��������� ����!ȿ��!!
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
				System.out.println("�˻��� �̸��� �����ϴ�.");
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
		//���������� �̵�
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
		//���������� �̵�
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

