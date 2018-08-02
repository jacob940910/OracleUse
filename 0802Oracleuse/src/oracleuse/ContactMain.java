package oracleuse;

import java.util.Calendar;
import java.sql.Date;
 
public class ContactMain {

	public static void main(String[] args) {
		//인터페이스나 클래스를 상속한 경우에는 
		//상위 인터페이스나  클래스 이름으로 변수를 만들고
		//하위 클래스의 인스턴스를 생성해서 대입합니다.
		ContactDAO dao = new ContactDaoImpl();
		Contact contact = new Contact();
		contact.setNum(1);
		contact.setName("이승환");
		contact.setPhone("01094416351");
		contact.setEmail("dltmdghks94@gmail.com");
		
		//현재시간을 저장한 캘린더 생성
		Calendar cal = Calendar.getInstance();
		
		//데이터 삽입
		cal.set(Calendar.YEAR, 1994);
		//시작위치가 0부터시작 
		cal.set(Calendar.MONTH, 10);
		cal.set(Calendar.DAY_OF_MONTH,10);
		
		Date birthDay = new Date(cal.getTimeInMillis());
		contact.setBirthday(birthDay);
		/*
		//데이터를 업데이트하는 메소드
		boolean r = dao.updateContact(contact);
		if(r == true) {
			System.out.println("수정성공!");
		}else {
			System.out.println("수정실패!");
		}
		*/
		/*
		//데이터를 삽입하는 메소드 호출
		boolean result = dao.insertContact(contact);
		if(result == true) {
			System.out.println("삽입성공!");
		}else {
			System.out.println("삽입실패!");
		}
		*/
		//데이터를 삭제하는메소드 
		boolean r = dao.deleteContact(5);
		if(r == true) {
			System.out.println("삭제성공!");
		}else {
			System.out.println("삭제실패!");
		}

	}

}
