package Model;
	//vo���·� ������ �����ϱ� ���� ������ Model_VO
public class Model_VO {
	// �Ӽ�(�ʵ�)����
		// �ܺο� �������� ���ϵ��� ���ֱ�
	private String name;
	private int age;
	private String tel;
	private String address;
	private String updateName;
	
	// ������ - ������ �ʵ尪 �ڵ� �Է»���
	public Model_VO(String name, int age, String tel, String address) {
		this.name = name;
		this.age = age;
		this.tel = tel;
		this.address = address;
	}
	

	
	
	//get method (���� ��ü�� �ʵ尪�� ��ȯ���ִ� ���)
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
	
