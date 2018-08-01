import java.sql.Connection;
import java.sql.DriverManager;

public class OracleMain {

	public static void main(String[] args) {
		Connection con = null;
		
		try {
			//사용하는 데이터 베이스마다 로드하는 클래스 이름은 결정되어있습니다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//확인하기 위한 코드
			//이코드가 출력되지않으면 드라이버 이름을 확인해보고
			//드라이버의 이름이 틀리지 않았다면 ojdbc6.jar가 Referenced Libraries에 포함되어 있는지를 확인 
			System.out.println("오라클 드라이버 로드 성공!");
			//데이터베이스 연결 
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.234:1521:XE","scott", "tiger");
			System.out.println("데이터베이스 연결 성공!");
			// 이 메세지가 안 나올경우
			// 1.데이터베이스 실행 여부 확인
			// 2.데이터베이스 주소 입력 확인
			// 3.계정을 확인  
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				//데이터베이스가 열려있으면 데이터베이스를 닫기  
				if(con !=null) {
					con.close();
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
