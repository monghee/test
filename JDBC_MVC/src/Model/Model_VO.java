package Model;
	//vo형태로 데이터 관리하기 위해 생성된 Model_VO
public class Model_VO {
	// 속성(필드)정의
		// 외부에 접근하지 못하도록 해주기
	private String name;
	private int age;
	private String tel;
	private String address;
	private String updateName;
	
	// 생성자 - 생성시 필드값 자동 입력생성
	public Model_VO(String name, int age, String tel, String address) {
		this.name = name;
		this.age = age;
		this.tel = tel;
		this.address = address;
	}
	

	
	
	//get method (현재 객체의 필드값을 반환해주는 기능)
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public String getTel() {
		return tel;
	}
	public String getAddress() {
		return address;
	}
}
	
