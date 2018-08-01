import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class OracleInsert {

	public static void main(String[] args) {
		//데이터베이스 연결 변수
		Connection con = null;
		//SQL실행변수
		PreparedStatement pstmt= null;
		try {
			//드라이버 클래스 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//데이터베이스 연결
			con = DriverManager.getConnection("jdbc:oracle:thin:@10.211.55.3:1521:XE","scott", "tiger");
			//SQL 실행 객체를 생성 : sql을 매개변수로 해서 생성
			//데이터 삽입 
			/*
			pstmt= con.prepareStatement("insert into dept(deptno, dname, loc)"+"values(80,'영업1','의정부')");
			*/
			/*
			pstmt = con.prepareStatement("delete from dept where deptno = 50");
			*/
			pstmt=con.prepareStatement("update dept set loc='만재도' where deptno = 60");
			//SQL을 실행 - select를 제외한 구문 실행
			//r에 저장되는 값은 영향받은 행의 개수
			int r = pstmt.executeUpdate();
			//성공여부 출력
			if(r>0) {
				System.out.println("삽입성공");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e) {
				
			}
		}
	}

}
