package View;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.Controller;
import Model.Model_VO;



public class View {
	// view�� ����ڿ��� ���̴� �κ�
	// ����ڰ� ���� �Է��ϴ� ���, ��� ���
	public static void main(String[] args) {

		// ����ڰ� �޴� �Է�, ���, ����ڰ� �����Ҷ� ���� �޴� ��� ����
		Scanner sc = new Scanner(System.in);
		
		System.out.println("== Bigdata�� �л����� ���α׷�==");
		
		while(true) {
			System.out.println("1.�л������Է�  2.�л�������  3.Ư���л��������  4.�л���������  5.�л���������  6.����");
			int menu = sc.nextInt();
			
			if(menu==1) {
				//Bigdata table�� ���� ������ �Է� -> vo�� �����ϱ�
				//������ ������ 
				System.out.println("����� �л��� ������ �Է��Ͻÿ�");
				System.out.print("�̸� : ");
				String name = sc.next();
				System.out.print("���� : ");
				int age = sc.nextInt();
				System.out.print("��ȭ��ȣ : ");
				String tel = sc.next();
				System.out.print("�ּ� : ");
				String address = sc.next();
				
				//vo ��ü ���� : ����ڰ� �Է��� �� �л��� ���� ������ �ϳ��� �����ִ� ����
				Model_VO vo = new Model_VO(name,age,tel,address);
				
				//controller ��ü ���� : ����ڰ� �Է��� ������ �𵨴ܿ� �����ϴ� ����
					// view�� ������ model�� �������� �ҰŸ� view���� ������ controller���� �ָ鼭 �����ش޶�� �ؾ��Ѵ�.
					// view�� vo�� controller���� �Ű������� �������ش�.
				Controller con = new Controller(vo);
				
				// controller�� ���޹��� �����͸� ������ ����� �������� ����
				int cnt = con.insert(); // insert();�� �����ϰ�
				// ����ڿ��� ��� ���
				 if (cnt >0) {
					 System.out.println("�Է¼���");
				 }else {
					 System.out.println("�Է� ����");
				 }
				
			}else if(menu ==2) {
				
				//controller��ü ����
				Controller con = new Controller();
				//select��� ȣ��
				ArrayList<Model_VO> al = con.select();
				
			//	System.out.println(al.size());
				for(Model_VO vo:al) {
					System.out.println("�̸�: "+vo.getName());
					System.out.println("����: "+vo.getAge());
					System.out.println("��ȭ��ȣ: "+vo.getTel());
					System.out.println("�ּ�: "+vo.getAddress());
					System.out.println("========================");
				}
				
			}else if(menu ==3) {
				System.out.println("�˻��� �л� �̸�: ");
				String name = sc.next();
				
				Controller con = new Controller(name);
				 
				Model_VO vo = con.selectOne();
				System.out.println("�̸�: "+vo.getName());
				System.out.println("����: "+vo.getAge());
				System.out.println("��ȭ��ȣ: "+vo.getTel());
				System.out.println("�ּ�: "+vo.getAddress());
				System.out.println("========================");
				
			}else if(menu ==4) {
				int cnt =0;
				System.out.print("������ ������ �л� �˻�>> ");
				String name = sc.next();
				System.out.println("���ο� �ּ� �Է�>> ");
				String address = sc.next();
				
				Controller con = new Controller(name);
				cnt = con.update(address);
				if(cnt>0) {
					System.out.println("��������!");
				}
				
			}else if(menu ==5) {
				System.out.println("������ �л� �̸�: ");
				String name = sc.next();
				
				Controller con = new Controller(name);
				int cnt = con.delete();
				
				if(cnt >0) {
					System.out.println("��������!");
				}
			
				
			}else if(menu ==6) {
				System.out.println("���α׷� ����");
				break;
			}
		}
		
	}

}
