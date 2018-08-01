import java.sql.Connection;
import java.sql.DriverManager;



public class DBConectMain {

	public static void main(String[] args) {
		//사용하고자 하는 드라이버 클래스를 로드 
		//데이터베이스 종류에 따라 다름
		
		//테이터베이스연결 변수 선언
		//반드시 close()를 호출해야 하므로 finally에서 close()를 호출 
		Connection con = null;
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.234:1521:XE","scott", "tiger");
			System.out.println(con);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			//연결이 되어 있으면 반드시 연결 해제
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					
				}
			}
		}

	}

}
