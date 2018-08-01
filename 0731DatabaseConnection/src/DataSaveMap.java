import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataSaveMap {

	public static void main(String[] args) {
		//데이터베이스에서 데이터읽기를 위한 변수
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//읽어온 데이터 저장을 위한 자료구조
		ArrayList<Dept> list = new ArrayList<>();
		
		try {
			//데이터베이스 드라이버를 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//데이터베이스 접속
			con = DriverManager.getConnection("jdbc:oracle:thin:@10.211.55.3:1521:XE","scott", "tiger");
			pstmt = con.prepareStatement("select deptno, dname, loc from dept");
			// sql실행
			rs = pstmt.executeQuery();
			//데이터 읽기
			//행단위로 읽기
			while(rs.next()) {
				//하나의 행을 저장할 DTO 객체 생성 
				Dept dept = new Dept();
				dept.setDeptno(rs.getInt("deptno"));
				dept.setDname(rs.getString("dname"));
				dept.setLoc(rs.getString("loc"));
				
				//읽은 하나의 행을 리스트에 저장
				list.add(dept);
			}
			//System.out.println(list);
			
			System.out.printf("%10s", "부서번호");
			System.out.printf("%20s", "부서이름");
			System.out.printf("%30s", "부서위치");
			System.out.println();
			for(Dept dept : list) {
				System.out.printf("%10s", dept.getDeptno());
				System.out.printf("%20s", dept.getDname());
				System.out.printf("%30s", dept.getLoc());
				System.out.println();
			}
			
		}catch(Exception e) {
			//예외내용을 출력 
			System.out.println(e.getMessage());
			//예외 내용을 추적  
			e.printStackTrace();
		}finally {
			//사용을 다한경우 닫기
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(con != null)con.close();
				
			}catch(Exception e) {
				
			}
			
		}

	}


}
