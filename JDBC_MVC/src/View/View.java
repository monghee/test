package View;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.Controller;
import Model.Model_VO;



public class View {
	// view는 사용자에게 보이는 부분
	// 사용자가 직접 입력하는 기능, 결과 출력
	public static void main(String[] args) {

		// 사용자가 메뉴 입력, 출력, 사용자가 종료할때 까지 메뉴 계속 선택
		Scanner sc = new Scanner(System.in);
		
		System.out.println("== Bigdata반 학생관리 프로그램==");
		
		while(true) {
			System.out.println("1.학생정보입력  2.학생명단출력  3.특정학생정보출력  4.학생정보수정  5.학생정보삭제  6.종료");
			int menu = sc.nextInt();
			
			if(menu==1) {
				//Bigdata table에 삽입 데이터 입력 -> vo로 관리하기
				//삽입할 데이터 
				System.out.println("등록할 학생의 정보를 입력하시오");
				System.out.print("이름 : ");
				String name = sc.next();
				System.out.print("나이 : ");
				int age = sc.nextInt();
				System.out.print("전화번호 : ");
				String tel = sc.next();
				System.out.print("주소 : ");
				String address = sc.next();
				
				//vo 객체 생성 : 사용자가 입력한 한 학생에 대한 정보를 하나로 묶어주는 역할
				Model_VO vo = new Model_VO(name,age,tel,address);
				
				//controller 객체 생성 : 사용자가 입력한 정보를 모델단에 전달하는 역할
					// view의 정보를 model에 절달해줘 할거면 view에서 정보를 controller에게 주면서 전달해달라고 해야한다.
					// view는 vo로 controller에게 매개변수로 전달해준다.
				Controller con = new Controller(vo);
				
				// controller는 전달받은 데이터를 수행할 기능이 무엇인지 정의
				int cnt = con.insert(); // insert();로 수행하고
				// 사용자에게 결과 출력
				 if (cnt >0) {
					 System.out.println("입력성공");
				 }else {
					 System.out.println("입력 실패");
				 }
				
			}else if(menu ==2) {
				
				//controller객체 생성
				Controller con = new Controller();
				//select기능 호출
				ArrayList<Model_VO> al = con.select();
				
			//	System.out.println(al.size());
				for(Model_VO vo:al) {
					System.out.println("이름: "+vo.getName());
					System.out.println("나이: "+vo.getAge());
					System.out.println("전화번호: "+vo.getTel());
					System.out.println("주소: "+vo.getAddress());
					System.out.println("========================");
				}
				
			}else if(menu ==3) {
				System.out.println("검색할 학생 이름: ");
				String name = sc.next();
				
				Controller con = new Controller(name);
				 
				Model_VO vo = con.selectOne();
				System.out.println("이름: "+vo.getName());
				System.out.println("나이: "+vo.getAge());
				System.out.println("전화번호: "+vo.getTel());
				System.out.println("주소: "+vo.getAddress());
				System.out.println("========================");
				
			}else if(menu ==4) {
				int cnt =0;
				System.out.print("정보를 수정할 학생 검색>> ");
				String name = sc.next();
				System.out.println("새로운 주소 입력>> ");
				String address = sc.next();
				
				Controller con = new Controller(name);
				cnt = con.update(address);
				if(cnt>0) {
					System.out.println("수정성공!");
				}
				
			}else if(menu ==5) {
				System.out.println("삭제할 학생 이름: ");
				String name = sc.next();
				
				Controller con = new Controller(name);
				int cnt = con.delete();
				
				if(cnt >0) {
					System.out.println("삭제성공!");
				}
			
				
			}else if(menu ==6) {
				System.out.println("프로그램 종료");
				break;
			}
		}
		
	}

}
