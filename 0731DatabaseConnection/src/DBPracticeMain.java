import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DBPracticeMain {

	public static void main(String[] args) {
		//데이터베이스 연결 변수
		Connection con = null;
		//SQL실행변수
		PreparedStatement pstmt= null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("부서번호 : ");
		int deptno = sc.nextInt();
	
		
		try {		
					//드라이버 클래스 로드
					Class.forName("oracle.jdbc.driver.OracleDriver");
					//데이터베이스 연결
					con = DriverManager.getConnection("jdbc:oracle:thin:@10.211.55.3:1521:XE","scott", "tiger");
					
					String sql = ("delete from dept where deptno = ?");
					//pstmt 생성 - 다른데이터를 바인딩할 수 있는 PreparedStatement
					pstmt = con.prepareStatement(sql);
					
				
					pstmt.setInt(1, deptno);
		
					int r = pstmt.executeUpdate();
					//성공여부 출력
					if(r>0) {
						System.out.println(deptno+"번 삭제성공");
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
