
public class TestMain { 
	public static void main(String []args) {
		/*
		// 생성자를 이용해서 객체를 생성 T obj1 = new T(); T obj2 = new T();

	    // 해시코드 출력 System.out.println(System.identityHashCode(obj1));
	    System.out.println(obj1.hashCode());
        System.out.println(System.identityHashCode(obj2));
        System.out.println(obj2.hashCode());

	    int a = 10;
	    int b = 10;
	    
		
        // 10을 저장하고 있는 같은 공간을 a,b로 표시 (해시코드 출력시 이용 코드)
	    System.out.println(System.identityHashCode(a));
	    System.out.println(System.identityHashCode(b));
		*/
		
		//싱글톤 패턴을 적용할 클래스의 객체만들
		T obj1 = T.getInstance();
	    T obj2 = T.getInstance();
	    
	    //해시코드출력
	    System.out.println(System.identityHashCode(obj1));
	    System.out.println(System.identityHashCode(obj2));
	    
	}

}
