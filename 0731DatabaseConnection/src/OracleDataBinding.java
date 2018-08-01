import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class OracleDataBinding {

	public static void main(String[] args) {
		//데이터베이스 연결 변수
		Connection con = null;
		//SQL실행변수
		PreparedStatement pstmt= null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("부서번호 : ");
		int deptno = sc.nextInt();
		System.out.print("부서이름 : ");
		//이전에남아있던 Enter를 제거하기 위한 코드입니다.
		sc.nextLine();
		String dname = sc.nextLine();
		
		System.out.print("지역 : ");
		String loc = sc.nextLine();
		try {		
					//드라이버 클래스 로드
					Class.forName("oracle.jdbc.driver.OracleDriver");
					//데이터베이스 연결
					con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.100:1521:XE","scott", "tiger");
					
					String sql = ("insert into dept(deptno, dname, loc)"+"values(?,?,?)");
					//pstmt 생성 - 다른데이터를 바인딩할 수 있는 PreparedStatement
					pstmt = con.prepareStatement(sql);
					//데이터 바인딩
					pstmt.setInt(1, deptno);
					pstmt.setString(2, dname);
					pstmt.setString(3, loc);
		
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
