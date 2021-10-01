package Controller;

import java.util.ArrayList;

import Model.Model_DAO;
import Model.Model_VO;

//����ڰ� �Է��� ������ Model�� ���� �ϴ� ����
	//����ڰ� �����Ϸ��� ������ �˰� �־�� �ϱ� ������ view
public class Controller {
//�ʵ�
	// view���� �Ѱ� ���� ���� vo�� ������ �־���ϱ� ������ vo�� �ʵ忡�� �����ؼ� ����
	private Model_VO vo;
	//��Ʈ�ѷ��� ������ �� �� �ֵ��� �ʵ�����ϰ� �ʱ�ȭ �� �� �ְ� ���ش�
	private String name;
	
	
//������	
	// insert����� ȣ���� ������ view���� �Ѱܹްڰ�, ���� �� ������ �����ϰڴ�.
	public Controller(Model_VO vo) {
		this.vo = vo; // view���� �Ѱܹ��� ���� vo�� vo�� �ʱ�ȭ �ϰڴ�
	}
	// (�⺻������) select����� ȣ���� ������ : �Ѱ��� ������ ������ 
	public Controller() {
		
	}
	// selectone ����� ȣ���� ������
	public Controller(String name) {
		this.name = name;
	}
	
	
	
	
	
//method
	public int insert() { //DAO���� �����ϴ� ����� method

		//DAO�� insert�� �޼ҵ�
		Model_DAO dao = new Model_DAO();
			int cnt = dao.insertStd(vo);
		return cnt;
	}
	public ArrayList<Model_VO> select() {
		Model_DAO dao = new Model_DAO();
		ArrayList<Model_VO> al = dao.selectStd(); //�л����� ������ ��� �Ѳ����� ������ �ͼ� ������� ���ִ� ��� �б⸸ �ϴ� �Ű����� �ʿ����
		return al;
	}
	
	public Model_VO selectOne() {
		
		Model_DAO dao = new Model_DAO();
		Model_VO vo = dao.selectOneStd(name);
		
		return vo;
	}	
	public int update(String address) {
		Model_DAO dao = new Model_DAO();
		
		int cnt = dao.updateStd(name,address);
		
		return cnt;
	}
	public int delete() {
		Model_DAO dao = new Model_DAO();
		int cnt = dao.deleteStd(name);
		return cnt;
	}
	
}
