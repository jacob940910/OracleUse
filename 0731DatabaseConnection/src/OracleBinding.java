import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class OracleBinding {
	public static void main(String[] args) {
		//1.정수를 입력받기
		Scanner sc = new Scanner(System.in);
		System.out.print("부서번호 : ");
		int deptno = sc.nextInt();
		//System.out.println("부서번호 : " + deptno);
		
		Connection con = null;
		PreparedStatement pstmt= null;
		
		try {
			//드라이버 클래스를 로드 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("로드 성공!");
			
			//데이터베이스 연결
			con = DriverManager.getConnection("jdbc:oracle:thin:@10.211.55.3:1521:XE","scott", "tiger");
			System.out.println("연결 성공!");
			
			//SQL실행객체 생성 
			pstmt =con.prepareStatement("delete from dept where deptno = ?");
			pstmt.setInt(1, deptno);
			//SQL 실행 
			//실행하고 나면 영향받은 행의 개수를 리턴합니다.
			//조건에 맞는 데이터가 없으면 실패하는게 아니고
			//삭제하는 행의 개수가 0 입니다.
			//실패하면 예외가 발생하므로 catch로 갑니다.
			int r = pstmt.executeUpdate();
			if(r > 0)
				System.out.println("삭제 성공!");
			else
				System.out.println("조건에 맞는 데이터가 없습니다.");
			
		}catch(Exception e) {
			//예외 내용을 출력 
			System.out.println(e.getMessage());
			//예외가 발생한 지점을 역추적
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				if(con != null)con.close();
			
			}catch(Exception e) {
				
			}
		}
		
	}

}
