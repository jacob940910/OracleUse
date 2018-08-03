package oracleuse;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ContactView extends JFrame {
	//필요한 UI 변수 선언  
	
	
	//레이블 선언 
	JLabel lblName, lblPhone, lblEmail, lblBirthday;
	//입력 받을 텍스트 필드 선언
	JTextField tfName, tfPhone, tfEmail, tfBirthday;
	//버튼 변수
	JButton btnFirst, btnLast, btnPrev, btnNext;
	JButton btnInsert, btnUpdate, btnDelete, btnSearch, btnClear;
	
	//현재출력중인 데이터의 인덱스를 표시할 레이블
	JLabel lblIndex;
	
	//데이터베이스 작업을 위한 Dao변수
	ContactDAO dao = new ContactDaoImpl();
	//데이터베이스에서 가져온 결과를 저장하기 위한 List
	List<Contact> list;
	//현재 출력중인 데이터의 인덱스를 저장할 변수
	int idx;
	
	//idx 번째 데이터를 화면에 출력해주는 메소드
	//맨처음 한번호출하고 데이터에 작업이 발생하면 호출하는 메소드
	private void printData() {
		//읽을데이터가 없다면 메시지 박스를 출하고 return
		if(list.size() == 0) {
			JOptionPane.showMessageDialog(null, "읽을 데이터가 없습니다.", "데이터 없음", JOptionPane.ERROR_MESSAGE);
			return;
		}
		//List에서 idx 번째 데이터를 가져옵니다.
		Contact contact = list.get(idx);
		tfName.setText(contact.getName());
		tfPhone.setText(contact.getPhone());
		tfEmail.setText(contact.getEmail());
		//생일은 Date타입이므로 toString을 호출해서 String으로 변환해서 출력합니다.
		tfBirthday.setText(contact.getBirthday().toString());
		//레이블에 현재 인덱스를 출력
		//lblIndex.setText(String.format("%d", idx+1));
		//lblIndex.setText((idx+1)+"");
		lblIndex.setText((idx+1)+"/"+list.size());
	}
	
	
	//생성자
	//객체를 만들 때 초기화를 위해서 생성
	public ContactView() {
		//중앙에 배치할 패널 생성
		JPanel centerPanel = new JPanel();
		
		//레이블을 생성
		lblName = new JLabel("이름",JLabel.RIGHT);
		lblPhone = new JLabel("전화번호",JLabel.RIGHT);
		lblEmail = new JLabel("이메일",JLabel.RIGHT);
		lblBirthday = new JLabel("생년월일",JLabel.RIGHT);
		
		//텍스트필드 생성
		tfName = new JTextField();
		tfPhone = new JTextField();
		tfEmail = new JTextField();
		tfBirthday = new JTextField();
		
		
		centerPanel.setLayout(new GridLayout(4, 2, 3, 3));
		centerPanel.add(lblName);
		centerPanel.add(tfName);
		
		centerPanel.add(lblPhone);
		centerPanel.add(tfPhone);
		
		centerPanel.add(lblEmail);
		centerPanel.add(tfEmail);
		
		centerPanel.add(lblBirthday);
		centerPanel.add(tfBirthday);
		
		add("Center",centerPanel);
		
		
		display();
		
		//데이터 베이스에서 데이터를 가져옵니다.//앞뒤 출력속도가 다르다.
		list = dao.allContact();
		printData();
		
		setBounds(100, 100, 400, 400);
		setTitle("연락처");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		
		
	}
	private void display() {
		//버튼생성
				btnFirst = new JButton("처음으로");
				btnFirst.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(idx == 0) {
							JOptionPane.showMessageDialog(null, "첫번째 데이터 입니다.", "조회", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						idx = 0;
						printData();
					}
					
				});
				btnPrev = new JButton("이전으로");
				btnPrev.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(idx == 0) {
							JOptionPane.showMessageDialog(null, "첫번째 데이터 입니다.", "조회에러", JOptionPane.ERROR_MESSAGE);
							return;
						}
						idx = idx -1;
						printData();
						
					}
					
				});
				btnNext = new JButton("다음으로");
				btnNext.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						if(idx == list.size() -1) {
							idx = 0;
							
						}
						idx = idx +1;
						printData();
					}
					
				});
				btnLast = new JButton("마지막으로");
				btnLast.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(idx == 0) {
							JOptionPane.showMessageDialog(null, "마지막 데이터 입니다.", "조회", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						idx = list.size() - 1;
						printData();
					}
					
				});
				btnInsert = new JButton("삽입");
				btnInsert.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						//lblIndex의 텍스트가 삽입이 아니면 메시지 박스를 출력하고 함수의 수행을 종료
						//문자열의 동일성 여부는 == 가아니고 equals로 해야합니다.  //if(lblIndex.getText() != "삽입") 
						if(lblIndex.getText().equals("삽입") == false) {
							JOptionPane.showMessageDialog(null, "지우고 쓰세요", "삽입에러", JOptionPane.ERROR_MESSAGE);
							return;
						}
						//입력한 데이터를 가져오기
						String name = tfName.getText();
						String phone = tfPhone.getText();
						String email = tfEmail.getText();
						String birthday = tfBirthday.getText();
						
						//name은 필수 입력이고 3자 이상 입력했는지 검사
						if(name.trim().length() <3) {
							JOptionPane.showMessageDialog(null, "이름은 3자이상", "이름오류", JOptionPane.ERROR_MESSAGE);
							return;
						}
						//phone은 전부 숫자이고 11자리인지 검사  
						if(phone.trim().length() != 11) {
							JOptionPane.showMessageDialog(null, "전화번호는 11자리", "전화번호오류", JOptionPane.ERROR_MESSAGE);
							return;
						}
						int i = 0;
						while(i<phone.trim().length()) {
							char ch = phone.trim().charAt(i);
							if(ch < '0' || ch > '9') {
								JOptionPane.showMessageDialog(null, "전화번호는 숫자만", "전화번호 오류", JOptionPane.ERROR_MESSAGE);
								return;
								
							}
							i = i + 1;
						}
						//Dao의 메소드에 맞게 데이터를 생성
						Contact contact = new Contact();
						contact.setName(name);
						contact.setEmail(email);
						contact.setPhone(phone);
						//birthday가 yyyymmdd 형식으로 입력되었다고 가정하고 Date 타입으로 변경하기
						int year = Integer.parseInt(birthday.substring(0, 4));
						int month = Integer.parseInt(birthday.substring(4, 6));
						int day = Integer.parseInt(birthday.substring(6));
						
						Calendar cal = Calendar.getInstance();
						cal.set(Calendar.YEAR, year);
						cal.set(Calendar.MONTH, month-1);
						cal.set(Calendar.DAY_OF_MONTH, day);
						
						Date date = new Date(cal.getTimeInMillis());
						contact.setBirthday(date);
						//데이터베이스 작업 수행 
						dao.insertContact(contact);
						//작업 수행후 재출력을 위해서 데이터를 다시 가져오고 인덱스 조정 
						list = dao.allContact();
						idx = list.size() - 1;
						printData();
						//대화상자 출력하기
						JOptionPane.showMessageDialog(null, "삽입성공", "데이터 삽입", JOptionPane.INFORMATION_MESSAGE);
						
						
						
					}
				});
				btnUpdate = new JButton("수정");
				btnUpdate.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						//입력한 스트 전부 가져오기
						String name = tfName.getText();
						String phone = tfPhone.getText();
						String email = tfEmail.getText();
						String birthday = tfBirthday.getText();
						String message = "";
						//name의 데이터가 비어있으면 메시지 박스를 출력하고 리턴하기
						if(name.trim().length() < 1) {
							message = "이름은 필수입력입니다.";
						}
						//phone의 데이터가 비어 있으면 메시지 박스를 출력하고 
						//리턴하고 출력하기
						else if(phone.trim().length() < 1) {
							message = "전화번호는 필수입력입니다.";
						}
						if(message.length() >=1) {
							JOptionPane.showMessageDialog(null, message, "입력 오류", JOptionPane.ERROR_MESSAGE);
							return;
						}
						//날짜와 데이터가 년도 4자리- 월2자리- 일2자리 형식인지 조사
						int i = 0;
						if(birthday.length() != 10) {
							JOptionPane.showMessageDialog(null, "생일은 10자리로 만들어주세요", "입력오류", JOptionPane.ERROR_MESSAGE);
							return;
						}
						while(i<birthday.length()) {
							char ch = birthday.charAt(i);
							//첫번째부터 네번째 까지 숫자가 아니면 반복문은 종료
							if(i>=0 && i<=3) {
								if(ch < '0' || ch > '9') {
									break;
								}
							}
							if(i==4 || i==7) {
								if(ch != '-') {
									break;
								}
							}
							if(i==5) {
								if(ch != '0' && ch != '1') {
									break;
								}
							}
							if(i==6) {
								if(ch < '0' || ch > '9') {
									break;
								}
							}
							if(i>7) {
								if(ch < '0' || ch > '9') {
									break;
								}
							}
							
							i = i + 1;
							
						}
						//유효성검사에 실패하면 메시지 박스를 출력하고 리턴
						if(i != birthday.length()) {
							JOptionPane.showMessageDialog(null, "생일은 YYYY-MM-DD 형식", "입력오류", JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						Contact contact = new Contact();
						
						contact.setNum(list.get(idx).getNum());
						contact.setEmail(email);
						contact.setName(name);
						contact.setPhone(phone);
						
						//yyyy-mm-dd형식의 문자열을 가지고 Date만들기
						//0번째부터 4번째 앞까지
						int year = Integer.parseInt(birthday.substring(0, 4));
						int month = Integer.parseInt(birthday.substring(5, 7));
						int day = Integer.parseInt(birthday.substring(8));
						//Calendar 객체를만들어서 입력한 데이터를 가지고 Calendar를 수정 
						Calendar cal = Calendar.getInstance();
						cal.set(Calendar.YEAR, year);
						cal.set(Calendar.MONTH, month-1);
						cal.set(Calendar.DAY_OF_MONTH, day);
						//Calendar 객체를 이용해서 Date객체 만들기
						Date date = new Date(cal.getTimeInMillis());
						contact.setBirthday(date);
						System.out.println(contact);
						dao.updateContact(contact);
						JOptionPane.showMessageDialog(null, "수정성공", "데이터수정", JOptionPane.INFORMATION_MESSAGE);
						list = dao.allContact();
						printData();
						
					}
					
				});
				btnDelete = new JButton("삭제");
				btnDelete.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						int ret = JOptionPane.showConfirmDialog(null, "정말로 삭제 ?", "삭제", JOptionPane.YES_NO_OPTION);
						System.out.println(ret);
						if(0==ret) {
							dao.deleteContact(list.get(idx).getNum());
							list = dao.allContact();
							idx=0;
							printData();
							
							JOptionPane.showMessageDialog(null, "삭제성공", "삭제", JOptionPane.INFORMATION_MESSAGE);
						}
						
					}
					
				});
				btnSearch = new JButton("조회");
				btnSearch.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String name = JOptionPane.showInputDialog(null, "조회할 이름의 일부분을 입력하세요.", "이름으로 조회", JOptionPane.QUESTION_MESSAGE);
						tfName.setText("");
						tfPhone.setText("");
						tfEmail.setText("");
						tfBirthday.setText("");
						if(name != null) {
							list = dao.nameContact(name.trim());
							idx = 0;
							printData();
						}
						
					}
					
				});
				btnClear = new JButton("지움");
				btnClear.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						//텍스트 필드들의 텍스트를 전부 삭제
						tfName.setText("");
						tfEmail.setText("");
						tfPhone.setText("");
						tfBirthday.setText("");
						
						//lblIndex의 타이틀을 삽입으로 변경
						lblIndex.setText("삽입");
					}
					
				});
				
				lblIndex = new JLabel(" ",JLabel.CENTER);
				
				paneldisplay();
				
	}
	private void paneldisplay() {
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1, 5, 7, 7));
		panel1.setBorder(new TitledBorder("조회"));
		panel1.add(btnFirst);
		panel1.add(btnPrev);
		panel1.add(lblIndex);
		panel1.add(btnNext);
		panel1.add(btnLast);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1, 5, 7, 7));
		panel2.setBorder(new TitledBorder("작업"));
		panel2.add(btnInsert);
		panel2.add(btnUpdate);
		panel2.add(btnDelete);
		panel2.add(btnSearch);
		panel2.add(btnClear);
		
		JPanel p = new JPanel(new BorderLayout());
		p.add("Center", panel1);
		p.add("South", panel2);
		
		add("South",p);
	}

}
