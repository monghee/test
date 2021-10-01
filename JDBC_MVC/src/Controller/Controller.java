package Controller;

import java.util.ArrayList;

import Model.Model_DAO;
import Model.Model_VO;

//사용자가 입력한 정보를 Model에 전달 하는 역할
	//사용자가 전달하려면 정보를 알고 있어야 하기 때문에 view
public class Controller {
//필드
	// view에서 넘겨 받은 정보 vo를 가지고 있어야하기 때문에 vo를 필드에서 정의해서 저장
	private Model_VO vo;
	//컨트롤러가 저장을 할 수 있도록 필드생성하고 초기화 할 수 있게 해준다
	private String name;
	
	
//생성자	
	// insert실행시 호출할 생성자 view에서 넘겨받겠고, 내가 그 정보를 저장하겠다.
	public Controller(Model_VO vo) {
		this.vo = vo; // view에서 넘겨받은 정보 vo를 vo로 초기화 하겠다
	}
	// (기본생성자) select실행시 호출할 생성자 : 넘겨줄 정보가 없으니 
	public Controller() {
		
	}
	// selectone 실행시 호출할 생성자
	public Controller(String name) {
		this.name = name;
	}
	
	
	
	
	
//method
	public int insert() { //DAO에게 전달하는 기능의 method

		//DAO에 insert할 메소드
		Model_DAO dao = new Model_DAO();
			int cnt = dao.insertStd(vo);
		return cnt;
	}
	public ArrayList<Model_VO> select() {
		Model_DAO dao = new Model_DAO();
		ArrayList<Model_VO> al = dao.selectStd(); //학생들의 정보를 모두 한꺼번에 가지고 와서 출력해줄 수있는 기능 읽기만 하니 매개변수 필요없음
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
