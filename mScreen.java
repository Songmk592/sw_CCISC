import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

import java.sql.*;
import java.util.HashMap;

class MainFrame extends JFrame {
	private JPanel chan = new home();// 배경화면용 패널
	private JPanel pageback = new page2();
	private JPanel searchMe = new JPanel();// 조회조건 패널
	private JPanel salesSearchPanel = new JPanel();// 매출조회화면의 조회조건 담는 패널
	private JPanel salesScreenPanel = new JPanel();// 탭 패널에 담기 위한 매출조회화면 패널
	private JPanel salesTypeScreenPanel = new JPanel();// 탭 패널에 담기 위한 품목별 매출조회화면
	private JPanel salesEmptyPanel = new JPanel();// 품목별 매출조회화면에서 레이아웃 정렬을 위한
													// 빈패널
	private JPanel salesAllScreenPanel = new JPanel();// 전체매출조회하는 패널
	private JPanel salesAllLabelPanel = new JPanel();// 매출현황을 표시할 6개의 라벨을 담을 라벨
	private JPanel salesAllBtnPanel = new JPanel();// 전체매출을 검색할 버튼을 담을 패널
	private JPanel mainFramePanel = new JPanel();// 모든화면 적재하는 패널
	
	
	
	////////////////////////////////
	private JPanel userPanel = new JPanel();
	private JPanel userSearchPanel = new JPanel();
	private JPanel userHotItemPanel=new JPanel();//표시할 6개의 라벨을 담을 라벨
	private JButton userProductTypeSearch;
	private JButton userProductSearch;
	private JButton userHotItemSearch;
	private JLabel userHotItem1 = new JLabel("           ★ 오늘의  Hot Item");
	protected static JLabel userHotItem2 = new JLabel("?");
	private JLabel userHotItem3 = new JLabel("           ★ 이 주의 Hot Item");
	protected static JLabel userHotItem4 = new JLabel("?");
	private JLabel userHotItem5 = new JLabel("           ★ 이 달의 Hot Item");
	protected static JLabel userHotItem6 = new JLabel("?");//표시 할 라벨들
	
	
	
	////////////////////////////
	
	private JButton moveUserPageBtn;//
	
	
	ImageIcon productIcon = new ImageIcon("src/img/product1.png");// 상품관리버튼의 이미지
	ImageIcon productIcon2 = new ImageIcon("src/img/product2.png");// 상품관리버튼의
																	// 이미지
	ImageIcon saleIcon1 = new ImageIcon("src/img/sale.png");
	ImageIcon saleIcon2 = new ImageIcon("src/img/sale2.png");
	ImageIcon accountIcon1 = new ImageIcon("src/img/account.png");
	ImageIcon accountIcon2 = new ImageIcon("src/img/account2.png");
	ImageIcon login = new ImageIcon("src/img/login.png");
	ImageIcon userAccount = new ImageIcon("src/img/userAccount.png");
	ImageIcon logout1 = new ImageIcon("src/img/logout1.png");
	private JButton moveMainPageBtn;// 메인페이지으로 넘어가는 버튼
	private JButton moveMainAccountBtn;
	private JButton moveMainPageBtn2;// 로그인화면이 아닌곳에서 메인페이지로 넘어가는 버튼
	private JButton backHomeBtn;// 첫번째화면으로 넘어가는 버튼
	private JButton userbackHomeBtn;// 첫번째화면으로 넘어가는 버튼
	private JButton productManageBtn;// 상품관리화면으로 넘어가는 버튼
	private JButton salesManageBtn;// 매출관리화면으로 넘어가는 버튼
	private JButton manageBtn;// 관리화면으로 넘어가는 버튼
	private JButton stockManageBtn;// 재고관리화면으로 넘어가는 버튼
	private JButton productTypeSearch;// 품목별 조회
	private JButton choiceSearch;// 개별 상품 조회
	private JButton productSearchSales;// 매출관리화면의 상품명/번호별 검색
	private JButton typeSearchSales;// 매출관리화면의 품목별
	private JButton allSearchSales;// 매출관리화면의 전체 매출
	private JButton addDel1;// 상품추가의 삭제버튼
	private JLabel first;// 초기화면의 라벨
	private JLabel second;// 두번째화면의 라벨
	private JLabel typeScopeLabel = new JLabel("품목검색");// 품목 범위 라벨
	private JLabel searchScopeLabel = new JLabel("조회조건");// 상품검색 라벨
	private JLabel salesSearchScopeLabel = new JLabel("검색조건");// 매출조회화면에서 검색조건
	private JLabel salesEmptyLabel1 = new JLabel("  ");// 품목별 매출조회화면에서 레이아웃 정렬을
														// 위한 빈라벨
	private JLabel salesEmptyLabel2 = new JLabel("  ");// 품목별 매출조회화면에서 레이아웃 정렬을
														// 위한 빈라벨
	private JLabel salesEmptyLabel3 = new JLabel("  ");// 품목별 매출조회화면에서 레이아웃 정렬을
														// 위한 빈라벨
	private JLabel salesAllEmptyLabel1 = new JLabel("  ");// 전체 매출조회화면에서 레이아웃
															// 정렬을 위한 빈라벨
	private JLabel salesAllEmptyLabel2 = new JLabel("  ");// 전체 매출조회화면에서 레이아웃
															// 정렬을 위한 빈라벨
	private JLabel salesAllEmptyLabel3 = new JLabel("  ");// 전체 매출조회화면에서 레이아웃
															// 정렬을 위한 빈라벨
	private JLabel saleAllSearchLabel1 = new JLabel("일일 매출");
	protected static JLabel saleAllSearchLabel2 = new JLabel("0원");
	private JLabel saleAllSearchLabel3 = new JLabel("주간 매출");
	protected static JLabel saleAllSearchLabel4 = new JLabel("0원");
	private JLabel saleAllSearchLabel5 = new JLabel("월간 매출");
	protected static JLabel saleAllSearchLabel6 = new JLabel("0원");// 전체
																	// 매출조회화면에서
																	// 표시할 라벨들
	private JLabel loginID = new JLabel("ID");// 초기화면에서의 로그인ID 라벨
	private JLabel loginPW = new JLabel("비밀번호");// 초기화면에서의 로그인password 라벨
	private JComboBox typeScopeCbox = new JComboBox();// 품목범위지정을 위한 콤보박스
	private JComboBox searchScopeCbox = new JComboBox();// 조회조건지정을 위한 콤보박스
	private JComboBox salesSearchScopeCbox = new JComboBox();// 상품조회조건지정을 위한
																// 콤보박스
	private JTextField searchScopeTextField = new JTextField(10);// 상품조회화면에서
																	// 상품명,검색을
																	// 위한 텍스트박스
	private JTextField salesSearchScopeTextField = new JTextField(10);// 매출 조회
																		// 화면에서
																		// 상품명,검색을
																		// 위한
																		// 텍스트박스
	private JTextField loginIDTF = new JTextField(12);// 로그인 아이디 적는 텍스트필드
	private JPasswordField loginPWTF = new JPasswordField(12);// 로그인 비밀번호 적는
																// 텍스트필드
	JTabbedPane salesTab1 = new JTabbedPane();// 매출관리 화면에서 탭화면
	Font f = new Font("Dialog", Font.BOLD, 40);// 초기페이지 폰트 지정
	Font f1 = new Font("Dialog", Font.BOLD, 18);// 전체매출조회페이지 폰트 지정
	static String head[] = { "상품번호", "상품명", "가격", "품목", "제조사", "재고" };// 상품조회화면의
																		// 테이블
																		// 스키마
	static String content[][] = {};// 상품조회화면의 테이블
	static DefaultTableModel model = new DefaultTableModel(content, head);// 테이블
																			// 정보
																			// 수정을
																			// 위한
																			// 리모콘
	static JTable ta1 = new JTable(model);// 상품조회화면 테이블
	static JScrollPane jp = new JScrollPane(ta1);// 테이블을 스크롤 패널에 적재
	static String head2[] = { "상품번호", "상품명", "품목", "일일매출", "주간매출", "월간매출" };// 매출조회화면화면의
																			// 테이블
																			// 스키마
	static String content2[][] = {};// 매출조회화면의 테이블
	static DefaultTableModel model2 = new DefaultTableModel(content2, head2);// 매출조회테이블
																				// 정보
																				// 수정을
																				// 위한
																				// 리모콘
	static JTable salesSearchTable = new JTable(model2);//
	static JScrollPane salesSearchSpanel = new JScrollPane(salesSearchTable);// 매출조회테이블을
																				// 스크롤패널에
																				// 적재
	static String head3[] = { "품목", "일일매출", "주간매출", "월간매출" };// 매출조회화면화면의 테이블
																// 스키마
	static String content3[][] = {};// 매출조회화면의 테이블
	static DefaultTableModel model3 = new DefaultTableModel(content3, head3);// 품목별
																				// 매출조회테이블
																				// 정보
																				// 수정을
																				// 위한
																				// 리모콘
	static JTable salesTypeSearchTable = new JTable(model3);//
	static JScrollPane salesTypeSearchSpanel = new JScrollPane(salesTypeSearchTable);// 품목별매출조회테이블을
																						// 스크롤패널에
																						// 적재
	ConnectDatabase c1 = new ConnectDatabase();// DB객체 생성
	JDialog dia = new JDialog(this, "로그인오류", false);// 로그인 오류 다이아로그
	JLabel diaLa = new JLabel();
	JButton diaBtn = new JButton("확인");// 다이어로그 라벨,버튼들

	// searchProductType sPT = new searchProductType();
	// searchProductName sPN = new searchProductName();
	// searchSaleName sSN = new searchSaleName();
	// searchSaleType sST = new searchSaleType();
	// searchSaleAll sSA = new searchSaleAll();

	public MainFrame() {
		this.setSize(1000, 550);// 프로그램 화면 크기
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("CCISC");
		moveMainPageBtn = new JButton(login);// 로그인 화면에서 기능선택 화면으로 넘어가는 버튼
		moveMainPageBtn.setBounds(450, 330, 130, 35);// 버튼 크기, 위치 지정
		moveMainAccountBtn = new JButton(userAccount);// 로그인 화면에서 기능선택 화면으로 넘어가는
														// 버튼
		moveMainAccountBtn.setBounds(450, 380, 130, 35);// 버튼 크기, 위치 지정
		moveMainPageBtn2 = new JButton("Back");// 로그인화면이 아닌곳에 기능선택 화면으로 넘어가는 버튼
		moveMainPageBtn2.setBounds(770, 400, 100, 30);// 버튼 크기, 위치 지정
		backHomeBtn = new JButton(logout1);// 초기화면으로 넘어가는 버튼
		backHomeBtn.setBounds(770, 400, 130, 50);// 버튼크기, 위치지정
		userbackHomeBtn = new JButton("로그아웃");
		productManageBtn = new JButton(productIcon);// 상품조회화면으로 넘어가는 버튼
		productManageBtn.setPressedIcon(productIcon2);// 눌렸을때 버튼이미지 바뀜
		productManageBtn.setRolloverIcon(productIcon2);// 커서가 위에 있으면 이미지 바뀜
		productManageBtn.setBounds(80, 250, 250, 100);
		salesManageBtn = new JButton(saleIcon1);// 매출조회화면으로 넘어가는 버튼
		salesManageBtn.setPressedIcon(saleIcon2);// 눌렸을때 버튼이미지 바뀜
		salesManageBtn.setRolloverIcon(saleIcon2);// 커서가 위에 있으면 이미지 바뀜
		salesManageBtn.setBounds(370, 250, 250, 100);
		manageBtn = new JButton(accountIcon1);// 관리화면으로 넘어가는 버튼
		manageBtn.setPressedIcon(accountIcon2);// 눌렸을때 버튼이미지 바뀜
		manageBtn.setRolloverIcon(accountIcon2);// 커서가 위에 있으면 이미지 바뀜
		manageBtn.setBounds(660, 250, 250, 100);
		productTypeSearch = new JButton("조회");// 품목별 조회
		productTypeSearch.setBounds(570, 30, 60, 25);
		choiceSearch = new JButton("조회");// 상품번호,이름별 조회
		choiceSearch.setBounds(570, 60, 60, 25);
		productSearchSales = new JButton("조회");// 매출조회화면의 품목별 조회
		typeSearchSales = new JButton("조회");
		allSearchSales = new JButton("조회");
		///////////////////////
		userHotItemSearch=new JButton("조회");
		userProductSearch=new JButton("상품 조회");
		//userHotItemSearch.setBounds(30, 40, 100, 30);
		userHotItemSearch.addActionListener(new myListener());
		userProductSearch.addActionListener(new myListener());
	
		userSearchPanel.setLayout(new GridLayout(1, 4, 5, 5));// 1행4열의 그리드
		userSearchPanel.add(userHotItemSearch);
		userSearchPanel.add(userProductSearch);
		userSearchPanel.add(salesEmptyLabel3);
		userSearchPanel.add(userbackHomeBtn);
		///////////////////////
		
		
		
//////////
	moveUserPageBtn = new JButton("Back");
	moveUserPageBtn.setBounds(770, 400, 100, 30);
	moveUserPageBtn.addActionListener(new myListener());
		addDel1 = new JButton("상품추가/삭제");// 상품추가/삭제 버튼
		addDel1.setBounds(170, 400, 120, 30);
		stockManageBtn = new JButton("재고관리");
		stockManageBtn.setBounds(300, 400, 100, 30);
		moveMainPageBtn.addActionListener(new myListener());// 리스너등록
		moveMainAccountBtn.addActionListener(new myListener());
		moveMainPageBtn2.addActionListener(new myListener());
		backHomeBtn.addActionListener(new myListener());
		userbackHomeBtn.addActionListener(new myListener());
		productManageBtn.addActionListener(new myListener());
		salesManageBtn.addActionListener(new myListener());
		manageBtn.addActionListener(new myListener());
		addDel1.addActionListener(new myListener());
		stockManageBtn.addActionListener(new myListener());
		productTypeSearch.addActionListener(new myListener());
		choiceSearch.addActionListener(new myListener());
		productSearchSales.addActionListener(new myListener());
		typeSearchSales.addActionListener(new myListener());
		allSearchSales.addActionListener(new myListener());
		first = new JLabel("재고 관리 프로그램");// 초기화면 텍스트 띄울라벨
		first.setFont(f);// 초기화면 라벨 폰트설정
		first.setBounds(310, 100, 500, 60);// 라벨위치, 크기 설정
		second = new JLabel("");
		second.setBounds(100, 135, 100, 30);
		jp.setBounds(180, 120, 600, 250);
		salesSearchSpanel.setBounds(180, 120, 600, 250);
		// searchMe.setSize(350,60);
		searchMe.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		typeScopeCbox.addItem("전체");
		typeScopeCbox.addItem("음료수");
		typeScopeCbox.addItem("우유");
		typeScopeCbox.addItem("즉석식품");
		typeScopeCbox.addItem("라면");
		typeScopeCbox.addItem("주류");
		typeScopeCbox.addItem("죽");
		typeScopeCbox.addItem("통조림");
		typeScopeCbox.addItem("소스");
		typeScopeCbox.setSelectedIndex(0);
		searchScopeCbox.addItem("선택안함");
		searchScopeCbox.addItem("상품명");
		searchScopeCbox.addItem("상품번호");
		searchScopeCbox.setSelectedIndex(0);
		c.gridx = 0;// 행번호
		c.gridy = 0;// 열번호
		c.gridheight = 1;// 컴포넌트가 차지하는 세로넓이
		c.gridwidth = 1;// 컴포넌트가 차지하는 가로넓이
		c.weightx = 1.0;// x축의 컴포넌트간 여유 공간의 배치 방법
		c.weighty = 3.0;// y축의 컴포넌트간 여유 공간의 배치 방법
		c.fill = GridBagConstraints.NONE;// 남은 공간 채우지 않는다.
		searchMe.add(typeScopeLabel, c);
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 3.0;
		c.fill = GridBagConstraints.NONE;
		searchMe.add(typeScopeCbox, c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 3.0;
		c.fill = GridBagConstraints.NONE;
		searchMe.add(searchScopeLabel, c);
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 3.0;
		c.fill = GridBagConstraints.NONE;
		searchMe.add(searchScopeCbox, c);
		c.gridx = 2;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 3.0;
		c.fill = GridBagConstraints.HORIZONTAL;// 남은공간을 수평방향으로 채운다.
		searchMe.add(searchScopeTextField, c);
		
		
		salesSearchPanel.setLayout(new GridLayout(1, 4, 5, 5));// 1행4열의 그리드
																// 레이아웃/* 여기서부터
		salesSearchPanel.add(salesSearchScopeLabel);
		salesSearchScopeCbox.addItem("전체조회");
		salesSearchScopeCbox.addItem("상품명");
		salesSearchScopeCbox.addItem("상품번호");
		searchScopeCbox.setSelectedIndex(0);
		salesSearchPanel.add(salesSearchScopeCbox);
		salesSearchPanel.add(salesSearchScopeTextField);
		salesSearchPanel.add(productSearchSales);
		salesScreenPanel.setLayout(new BorderLayout());
		salesScreenPanel.add(salesSearchPanel, BorderLayout.NORTH);
		salesScreenPanel.add(salesSearchSpanel, BorderLayout.CENTER);
		salesTab1.setBounds(50, 50, 650, 400);
		salesTab1.addTab("상품별조회", salesScreenPanel);
		salesTypeScreenPanel.setLayout(new BorderLayout());
		salesEmptyPanel.setLayout(new GridLayout(1, 4, 5, 5));
		salesEmptyPanel.add(salesEmptyLabel1);
		salesEmptyPanel.add(salesEmptyLabel2);
		salesEmptyPanel.add(salesEmptyLabel3);
		salesEmptyPanel.add(typeSearchSales);
		salesTypeScreenPanel.add(salesEmptyPanel, BorderLayout.NORTH);
		salesTypeScreenPanel.add(salesTypeSearchSpanel, BorderLayout.CENTER);
		salesTab1.addTab("품목별조회", salesTypeScreenPanel);
		salesAllBtnPanel.setLayout(new GridLayout(1, 4, 5, 5));
		salesAllBtnPanel.add(salesAllEmptyLabel1);
		salesAllBtnPanel.add(salesAllEmptyLabel2);
		salesAllBtnPanel.add(salesAllEmptyLabel3);
		salesAllBtnPanel.add(allSearchSales);
		salesAllLabelPanel.setLayout(new GridLayout(3, 2, 5, 5));
		saleAllSearchLabel1.setFont(f1);
		saleAllSearchLabel2.setFont(f1);
		saleAllSearchLabel3.setFont(f1);
		saleAllSearchLabel4.setFont(f1);
		saleAllSearchLabel5.setFont(f1);
		saleAllSearchLabel6.setFont(f1);
		salesAllLabelPanel.add(saleAllSearchLabel1);
		salesAllLabelPanel.add(saleAllSearchLabel2);
		salesAllLabelPanel.add(saleAllSearchLabel3);
		salesAllLabelPanel.add(saleAllSearchLabel4);
		salesAllLabelPanel.add(saleAllSearchLabel5);
		salesAllLabelPanel.add(saleAllSearchLabel6);
		salesAllScreenPanel.setLayout(new BorderLayout());
		salesAllScreenPanel.add(salesAllBtnPanel, BorderLayout.NORTH);
		salesAllScreenPanel.add(salesAllLabelPanel, BorderLayout.CENTER);
		salesTab1.addTab("전체매출조회", salesAllScreenPanel);// 여기까지 매출조회화면 컴포넌트
														// 삽입코드*/
		mainFramePanel.setLayout(null);// 레이아웃배치는 자유
		mainFramePanel.add(moveMainPageBtn);
		mainFramePanel.add(moveMainAccountBtn);
		// mainFramePanel.add(first);
		loginID.setBounds(370, 240, 100, 30);
		loginPW.setBounds(370, 265, 100, 30);
		loginIDTF.setBounds(440, 240, 150, 20);
		loginPWTF.setBounds(440, 270, 150, 20);
		mainFramePanel.add(loginID);
		mainFramePanel.add(loginPW);
		mainFramePanel.add(loginIDTF);
		mainFramePanel.add(loginPWTF);
		chan.setBounds(0, 0, 1000, 550);
		chan.setOpaque(false);
		pageback.setBounds(0, 0, 1000, 550);
		pageback.setOpaque(false);
		mainFramePanel.add(chan);
		add(mainFramePanel);
		this.setVisible(true);
		dia.setLayout(new FlowLayout());// 다이아로그 프레임의 정렬방식
		dia.setSize(150, 120);// 크기
		dia.setLocation(470, 250);// 위치
		dia.add(diaLa);// 라벨 삽입
		dia.add(diaBtn);// 버튼
		diaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dia.setVisible(false);
			}
		});// 다이어로그버튼을 위한 익명내부클래스
	}

	public void loginCMP(String a, String b) throws SQLException {
		Connection con = ConnectDatabase.makeConnection();
		Statement stmt = con.createStatement();
		String id = null;
		String pw = null;
		int flag = 0;// 아이디 비교 부분이 실행되었는지 확인하기 위함
		int flag2 = 0;// 비밀번호 비교 부분이 실행되었는지 확인하기 위함
		String sql = "select * from logininfo where accid='" + a + "'";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			id = rs.getString("ACCID");
			pw = rs.getString("PASSOWORD");
			int compare = Integer.parseInt(id);

			if (a.equals(id)) {
				flag = 1;
				if (b.equals(pw)) {
					flag2 = 1;

					if (compare >= 0 && compare <= 99999999 && id.length() == 8) {
						
						mainFramePanel.removeAll();
						chan.setBounds(0, 0, 1000, 550);
						chan.setOpaque(false);// 이렇게 해놔야 컴포넌트들이 보임
						
						
						userHotItem2.setText("?");
						userHotItem4.setText("?");
						userHotItem6.setText("?");
						userHotItemPanel.setLayout(new GridLayout(3,2,5,5));
						userHotItem1.setFont(f1);
						userHotItem2.setFont(f1);
						userHotItem3.setFont(f1);
						userHotItem4.setFont(f1);
						userHotItem5.setFont(f1);
						userHotItem6.setFont(f1);
						userHotItemPanel.add(userHotItem1);
						userHotItemPanel.add(userHotItem2);
						userHotItemPanel.add(userHotItem3);
						userHotItemPanel.add(userHotItem4);
						userHotItemPanel.add(userHotItem5);
						userHotItemPanel.add(userHotItem6);
						mainFramePanel.setLayout(new BorderLayout());
						mainFramePanel.add(userSearchPanel, BorderLayout.AFTER_LAST_LINE);
						
						mainFramePanel.add(userHotItemPanel, BorderLayout.CENTER);
						
					
						mainFramePanel.updateUI();
						//UserFrame uf = new UserFrame();
					} else {

						mainFramePanel.removeAll();
						chan.setBounds(0, 0, 1000, 550);
						chan.setOpaque(false);// 이렇게 해놔야 컴포넌트들이 보임
						mainFramePanel.add(backHomeBtn);
						// mainFramePanel.add(second);
						mainFramePanel.add(productManageBtn);
						mainFramePanel.add(salesManageBtn);
						mainFramePanel.add(manageBtn);
						mainFramePanel.add(pageback);
						mainFramePanel.setLayout(null);/////////////////이걸 넣어야 사용자로 로그인했다가 관리자로 로그인할때 오류안남 ㅠㅠㅠ
						mainFramePanel.updateUI();// 화면업데이트

					}
				}
			}
		}
		if (flag != 1) {
			diaLa.setText("  ID가 틀립니다.  ");
			dia.setVisible(true);
			System.out.println("id");
		} // flag가 바뀌지 않았으면 출력
		else if (flag2 != 1) {
			diaLa.setText("비밀번호가 틀립니다.");
			dia.setVisible(true);
			System.out.println("pw");
		} // flag2가 바뀌지 않았으면 출력
	}// 로그인 구현 메소드

	class myListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == moveMainPageBtn) {
				try {
					loginCMP(loginIDTF.getText(), loginPWTF.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				loginIDTF.setText("");
				loginPWTF.setText("");// 로그인 메소드 실행이 끝나면 초기화
			} // 로그인 화면에서 기능선택화면으로 넘어감
			if (e.getSource() == moveMainAccountBtn) {
				UserAccountFrame f4u = new UserAccountFrame();

			} // 로그인 화면에서 계정추가,삭제화면으로 넘어감
			if (e.getSource() == moveMainPageBtn2) {

				mainFramePanel.removeAll();
				chan.setBounds(0, 0, 1000, 550);
				chan.setOpaque(false);// 이렇게 해놔야 컴포넌트들이 보임
				mainFramePanel.add(backHomeBtn);
				// mainFramePanel.add(second);
				mainFramePanel.add(productManageBtn);
				mainFramePanel.add(salesManageBtn);
				mainFramePanel.add(manageBtn);
				mainFramePanel.add(pageback);
				 model = new DefaultTableModel(content, head); // 여기부터
				 ta1 = new JTable(model);
				 jp = new JScrollPane(ta1);
				 jp.setBounds(180, 120, 600, 250); // 여기까지 새로 창 띄었을때 pannel NULL 값으로 초기화
				mainFramePanel.updateUI();// 화면업데이트
			} // 로그인 화면이 아닌곳에서 기능선택 페이지로 넘어감
			if (e.getSource() == backHomeBtn) {
				mainFramePanel.removeAll();
				mainFramePanel.add(moveMainPageBtn);
				mainFramePanel.add(moveMainAccountBtn);
				mainFramePanel.add(loginID);
				mainFramePanel.add(loginPW);
				mainFramePanel.add(loginIDTF);
				mainFramePanel.add(loginPWTF);
				mainFramePanel.add(chan);
				mainFramePanel.updateUI();
			} // 초기화면으로 넘어감?
			if (e.getSource() == productManageBtn) {
				mainFramePanel.removeAll();
				mainFramePanel.add(moveMainPageBtn2);
				mainFramePanel.add(jp);
				mainFramePanel.add(searchMe);
				mainFramePanel.add(productTypeSearch);
				mainFramePanel.add(choiceSearch);
				searchMe.setBounds(200, 30, 350, 60);
				// mainFramePanel.add(moveMainPageBtn);
				mainFramePanel.add(addDel1);
				mainFramePanel.add(stockManageBtn);
				mainFramePanel.updateUI();
			} // 상품관리화면으로 넘어감?
			if (e.getSource() == salesManageBtn) {
				mainFramePanel.removeAll();
				mainFramePanel.add(moveMainPageBtn2);
				mainFramePanel.add(salesTab1);
				
				mainFramePanel.updateUI();
			} // 매출관리화면으로 넘어감
			if (e.getSource() == manageBtn) {
				AccountCustomerFrame fr4 = new AccountCustomerFrame();
			}
			if (e.getSource() == stockManageBtn) {
				StockConfirmFrame fr3 = new StockConfirmFrame();

			} // 재고관리?
			if (e.getSource() == addDel1) {
				ProductFrame fr2 = new ProductFrame();
			} // 상품추가/삭제
			if (e.getSource() == productTypeSearch) {
				int index = typeScopeCbox.getSelectedIndex();
				try {
					searchProductType.searchProductTypep(index);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} // 상품품목별조회
			if (e.getSource() == choiceSearch) {
				int index1 = searchScopeCbox.getSelectedIndex();
				try {
					searchProductName.searchProductNamep(index1, searchScopeTextField.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} // 상품번호/제목별 조회
			if (e.getSource() == productSearchSales) {
				int index2 = salesSearchScopeCbox.getSelectedIndex();
				try {
					searchSaleName.searchSaleNamep(index2, salesSearchScopeTextField.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} // 상품별 매출 조회
			if (e.getSource() == typeSearchSales) {
				try {
					searchSaleType.searchSaleTypep();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (e.getSource() == allSearchSales) {
				try {
					searchSaleAll.searchSaleAllp();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (e.getSource() == userHotItemSearch) {
				try {
					userHotItem.userHotItemp();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				
				}
			}
			if (e.getSource() == moveUserPageBtn) {
				
				mainFramePanel.removeAll();
				chan.setBounds(0, 0, 1000, 550);
				chan.setOpaque(false);// 이렇게 해놔야 컴포넌트들이 보임
				userHotItemPanel.setLayout(new GridLayout(3,2,5,5));
				userHotItem1.setFont(f1);
				userHotItem2.setFont(f1);
				userHotItem3.setFont(f1);
				userHotItem4.setFont(f1);
				userHotItem5.setFont(f1);
				userHotItem6.setFont(f1);
				userHotItemPanel.add(userHotItem1);
				userHotItemPanel.add(userHotItem2);
				userHotItemPanel.add(userHotItem3);
				userHotItemPanel.add(userHotItem4);
				userHotItemPanel.add(userHotItem5);
				userHotItemPanel.add(userHotItem6);
				
				
				 model = new DefaultTableModel(content, head); // 여기부터
				 ta1 = new JTable(model);
				 jp = new JScrollPane(ta1);
				 jp.setBounds(180, 120, 600, 250); // 여기까지 새로 창 띄었을때 pannel NULL 값으로 초기화
				
				
				mainFramePanel.setLayout(new BorderLayout());
				mainFramePanel.add(userSearchPanel, BorderLayout.AFTER_LAST_LINE);
				
				mainFramePanel.add(userHotItemPanel, BorderLayout.CENTER);
				
			
				mainFramePanel.updateUI();
			}
			if (e.getSource() == userProductSearch) {
				/////////////////////////////////////////////////////////////////////////////////////////////////////
				
				 mainFramePanel.removeAll();
				 chan.setBounds(0, 0, 1000, 550);
					chan.setOpaque(false);// 이렇게 해놔야 컴포넌트들이 보임
					//userHotItem2.setText("?"); // 상품조회후 다시 돌아왔을때 물음표가 떠야하나? 같이 물어보고 고치기
					//userHotItem4.setText("?");
					//userHotItem6.setText("?");
					mainFramePanel.add(moveUserPageBtn);
					mainFramePanel.add(jp);
					mainFramePanel.add(searchMe);
					mainFramePanel.add(productTypeSearch);
					mainFramePanel.add(choiceSearch);
					searchMe.setBounds(200, 30, 350, 60);
			
					
					mainFramePanel.setLayout(null); /////////////////이걸 넣어야 오류안남 ㅠㅠㅠ 왜?ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ
					mainFramePanel.updateUI();
						
				
				
				/*
				userJp.setBounds(180, 120, 600, 250);
					mainFramePanel.removeAll();
					chan.setBounds(0, 0, 1000, 550);
					chan.setOpaque(false);// 이렇게 해놔야 컴포넌트들이 보임
					
					
					
					
					userProductPanel.add(moveMainPageBtn2);
					userProductPanel.add(userJp);
					userProductPanel.add(userSearch);
					userProductPanel.add(productTypeSearch);
					userProductPanel.add(choiceSearch);
					userSearch.setBounds(200, 30, 350, 60);
					// userProductPanel.add(moveMainPageBtn);
					// userProductPanel.add(addDel1);
					//userProductPanel.add(stockManageBtn);
					mainFramePanel.setLayout(new BorderLayout());
					mainFramePanel.add(userProductPanel);
					mainFramePanel.updateUI();
					//userHotItem.userHotItemp();
					/*
					 
					 mainFramePanel.removeAll();
				mainFramePanel.add(moveMainPageBtn2);
				mainFramePanel.add(jp);
				mainFramePanel.add(searchMe);
				mainFramePanel.add(productTypeSearch);
				mainFramePanel.add(choiceSearch);
				searchMe.setBounds(200, 30, 350, 60);
				// mainFramePanel.add(moveMainPageBtn);
				mainFramePanel.add(addDel1);
				mainFramePanel.add(stockManageBtn);
				mainFramePanel.updateUI();
					
					
					*/
				
			}
			if (e.getSource() == userbackHomeBtn) {
				mainFramePanel.removeAll();
				mainFramePanel.add(moveMainPageBtn);
				mainFramePanel.add(moveMainAccountBtn);
				mainFramePanel.add(loginID);
				mainFramePanel.add(loginPW);
				mainFramePanel.add(loginIDTF);
				mainFramePanel.add(loginPWTF);
				mainFramePanel.add(chan);
				mainFramePanel.updateUI();
			} // 초기화면으로 넘어감?
		}
	}
}

class userProduct extends MainFrame {
	public static void userProductp() throws SQLException {
		
	}
}

class userHotItem extends MainFrame {
	public static void userHotItemp() throws SQLException {
		Connection con = ConnectDatabase.makeConnection();
	      Statement stmt = con.createStatement();
	      String arr[]=new String[3];
	      
	      ResultSet rs = stmt.executeQuery("select p.PRODUCTNAME as dayproductname"
	            +" from salesstocktable s,producttable p"
	            +" where s.PRODUCTNUM=p.PRODUCTNUM "
	            + "and s.DAYSALES = (select max(DAYSALES) from salesstocktable)");
	      while (rs.next()) {
	         arr[0]=rs.getString("DAYPRODUCTNAME");
	      }
	      
	      rs = stmt.executeQuery("select p.PRODUCTNAME as weekproductname"
		            +" from salesstocktable s,producttable p"
		            +" where s.PRODUCTNUM=p.PRODUCTNUM "
		            + "and s.WEEKSALES = (select max(WEEKSALES) from salesstocktable)");
		  while (rs.next()) {
			  arr[1]=rs.getString("WEEKPRODUCTNAME");
		  }
		      
		  rs = stmt.executeQuery("select p.PRODUCTNAME as monthproductname"
			        +" from salesstocktable s,producttable p"
			        +" where s.PRODUCTNUM=p.PRODUCTNUM "
			        + "and s.MONTHSALES = (select max(MONTHSALES) from salesstocktable)");
		  while (rs.next()) {
			  arr[2]=rs.getString("MONTHPRODUCTNAME");
		  }
	      userHotItem2.setText(arr[0]);
	      userHotItem4.setText(arr[1]);
	      userHotItem6.setText(arr[2]);
	}
}

class ConnectDatabase {
	public static Connection makeConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:XE";// SID 적을것
		String id = "my_test_id";// sqldeveloper에서 만든 유저 ID
		String password = "my_test_pw";// 비밀번호
		Connection con = null;
		try {
			// Class.forName("com.mysql.jdbc.Driver");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 적재 성공");
			con = DriverManager.getConnection(url, id, password);
			System.out.println("데이터베이스 연결 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
		} catch (SQLException e) {
			System.out.println("연결에 실패하였습니다.");
		}
		return con;
	}
}// 데이터베이스 연결

class home extends JPanel {
	Image homeBG;

	public home() {
		try {
			homeBG = ImageIO.read(new File("src/img/CCISC.png"));
			System.out.println("그림 try");
		} catch (IOException e) {
			System.out.println(e.getMessage() + "\nPic Not Load..");
			System.exit(2);
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(homeBG, 0, 0, this);
		repaint();
	}
}// 로그인화면 배경화면 설정

class page2 extends JPanel {
	Image homeBG;

	public page2() {
		try {
			homeBG = ImageIO.read(new File("src/img/YU.png"));
			System.out.println("그림 try");
		} catch (IOException e) {
			System.out.println(e.getMessage() + "\nPic Not Load..");
			System.exit(2);
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(homeBG, 0, 0, this);
		repaint();
	}
}// 기능선택 화면 배경화면 설정

public class mScreen {
	public static void main(String[] args) {
		MainFrame t = new MainFrame();
	}
}
// 테스트1

class searchProductType extends MainFrame {

	static void searchProductTypep(int a) throws SQLException {
		for (int i = ta1.getRowCount(); i > 0; i--) {
			model.removeRow(i - 1);
		} // for으로 table 초기화
		Connection con = ConnectDatabase.makeConnection();
		Statement stmt = con.createStatement();

		if (a == 0) {
			ResultSet rs = stmt.executeQuery(
					"select p.productnum, p.PRODUCTNAME, p.SALESPRICE, p.PRODUCTTYPE, m.MANUFACTURERNAME, s.stock"
							+ " from producttable p, manufacturertable m, salesstocktable s"
							+ " where p.PRODUCTNUM=s.productnum(+) and m.MANUFACTURERNUM(+)=p.MANUNUM");
			for (int i = ta1.getRowCount(); i > 0; i--) {
				model.removeRow(i - 1);
			} // for으로 table 초기화
			String arr[] = new String[6];
			while (rs.next()) {
				arr[0] = rs.getString("PRODUCTNUM");
				arr[1] = rs.getString("PRODUCTNAME");
				arr[2] = rs.getString("SALESPRICE");
				arr[3] = rs.getString("PRODUCTTYPE");
				arr[4] = rs.getString("MANUFACTURERNAME");
				arr[5] = rs.getString("STOCK");
				model.addRow(arr);// arr의 내용을 테이블에 채운다.
			}
		} else if (a == 1) {
			searchProductTypeQuery(ta1, "음료수");
		} else if (a == 2) {
			searchProductTypeQuery(ta1, "우유");
		} else if (a == 3) {
			searchProductTypeQuery(ta1, "즉석식품");
		} else if (a == 4) {
			searchProductTypeQuery(ta1, "라면");
		} else if (a == 5) {
			searchProductTypeQuery(ta1, "주류");
		} else if (a == 6) {
			searchProductTypeQuery(ta1, "죽");
		} else {
			searchProductTypeQuery(ta1, "통조림");
		}
	}

	static void searchProductTypeQuery(JTable ta1, String in) {
		try {
			for (int i = ta1.getRowCount(); i > 0; i--) {
				model.removeRow(i - 1);
			} // for문 돌려서 table 초기화
			Connection con = ConnectDatabase.makeConnection();

			Statement stmt = con.createStatement();
			//ResultSet rset = null;
			//PreparedStatement query = con.prepareStatement
			ResultSet rset = stmt.executeQuery(
					"select p.productnum, p.PRODUCTNAME, p.SALESPRICE, p.PRODUCTTYPE, m.MANUFACTURERNAME, s.stock"
							+ " from producttable p, manufacturertable m, salesstocktable s"
							+ " where p.PRODUCTNUM=s.productnum(+) and m.MANUFACTURERNUM(+)=p.MANUNUM and productType= '"+ in +"' ");
			//query.setString(1, in);
			//rset = query.executeQuery();

			String arr[] = new String[6];
			while (rset.next()) {
				arr[0] = rset.getString("PRODUCTNUM");
				arr[1] = rset.getString("PRODUCTNAME");
				arr[2] = rset.getString("SALESPRICE");
				arr[3] = rset.getString("PRODUCTTYPE");
				arr[4] = rset.getString("MANUFACTURERNAME");
				arr[5] = rset.getString("STOCK");
				model.addRow(arr);// arr의 내용을 테이블에 채운다.
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}// (productTypeSearch 버튼구현)(품목별 검색)

class searchProductName extends MainFrame {

	public static void searchProductNamep(int a, String b) throws SQLException {
		for (int i = ta1.getRowCount(); i > 0; i--) {
			model.removeRow(i - 1);
		} // for으로 table 초기화

		Connection con = ConnectDatabase.makeConnection();
		Statement stmt = con.createStatement();

		if (a == 1) {
			for (int i = ta1.getRowCount(); i > 0; i--) {
				model.removeRow(i - 1);
			} // for문 돌려서 table 초기화
			ResultSet rs = stmt.executeQuery(
					"select p.productnum, p.PRODUCTNAME, p.SALESPRICE, p.PRODUCTTYPE, m.MANUFACTURERNAME, s.stock"
							+ " from producttable p, manufacturertable m, salesstocktable s"
							+ " where p.PRODUCTNUM=s.productnum(+) and m.MANUFACTURERNUM(+)=p.MANUNUM and productname LIKE '%"
							+ b + "%'");
			String arr[] = new String[6];
			while (rs.next()) {
				arr[0] = rs.getString("PRODUCTNUM");
				arr[1] = rs.getString("PRODUCTNAME");
				arr[2] = rs.getString("SALESPRICE");
				arr[3] = rs.getString("PRODUCTTYPE");
				arr[4] = rs.getString("MANUFACTURERNAME");
				arr[5] = rs.getString("STOCK");
				model.addRow(arr);// arr의 내용을 테이블에 채운다.
			}
		} // 상품명 검색
		else if (a == 2) {
			for (int i = ta1.getRowCount(); i > 0; i--) {
				model.removeRow(i - 1);
			} // for문 돌려서 table 초기화
			ResultSet rs = stmt.executeQuery(
					"select p.productnum, p.PRODUCTNAME, p.SALESPRICE, p.PRODUCTTYPE, m.MANUFACTURERNAME, s.stock"
							+ " from producttable p, manufacturertable m, salesstocktable s"
							+ " where p.PRODUCTNUM=s.productnum(+) and m.MANUFACTURERNUM(+)=p.MANUNUM and p.productnum="
							+ b);
			String arr[] = new String[6];
			while (rs.next()) {
				arr[0] = rs.getString("PRODUCTNUM");
				arr[1] = rs.getString("PRODUCTNAME");
				arr[2] = rs.getString("SALESPRICE");
				arr[3] = rs.getString("PRODUCTTYPE");
				arr[4] = rs.getString("MANUFACTURERNAME");
				arr[5] = rs.getString("STOCK");
				model.addRow(arr);// arr의 내용을 테이블에 채운다.
			}
		} // 상품번호 검색
		else {
		}
	}
}// choiceSearch버튼 구현 (상품명,상품번호별 검색)

class searchSaleName extends MainFrame {

	static void searchSaleNamep(int a, String b) throws SQLException {
		for (int i = salesSearchTable.getRowCount(); i > 0; i--) {
			model2.removeRow(i - 1);
		} // for으로 table 초기화
		Connection con = ConnectDatabase.makeConnection();
		Statement stmt = con.createStatement();
		if (a == 0) {
			for (int i = salesSearchTable.getRowCount(); i > 0; i--) {
				model2.removeRow(i - 1);
			}
			ResultSet rs = stmt.executeQuery(
					"select distinct p.productnum, p.productname,p.producttype,s.DAYSALES*p.SALESPRICE as daysales,"
							+ " s.WEEKSALES*p.SALESPRICE as weeksales,s.MONTHSALES*p.SALESPRICE as monthsales"
							+ " from PRODUCTTABLE p, SALESSTOCKTABLE s" + " where s.PRODUCTNUM = p.PRODUCTNUM"
							+ " order by p.productnum");
			String arr[] = new String[6];
			while (rs.next()) {
				arr[0] = rs.getString("PRODUCTNUM");
				arr[1] = rs.getString("PRODUCTNAME");
				arr[2] = rs.getString("PRODUCTTYPE");
				arr[3] = rs.getString("DAYSALES");
				arr[4] = rs.getString("WEEKSALES");
				arr[5] = rs.getString("MONTHSALES");
				model2.addRow(arr);// arr의 내용을 테이블에 채운다.
			}
		}
		if (a == 1) {
			for (int i = salesSearchTable.getRowCount(); i > 0; i--) {
				model2.removeRow(i - 1);
			}
			ResultSet rs = stmt.executeQuery(
					"select distinct p.productnum, p.productname,p.producttype,s.DAYSALES*p.SALESPRICE as daysales,"
							+ " s.WEEKSALES*p.SALESPRICE as weeksales,s.MONTHSALES*p.SALESPRICE as monthsales"
							+ " from PRODUCTTABLE p, SALESSTOCKTABLE s"
							+ " where s.PRODUCTNUM = p.PRODUCTNUM and productname='" + b + "'"
							+ " order by p.productnum");
			String arr[] = new String[6];
			while (rs.next()) {
				arr[0] = rs.getString("PRODUCTNUM");
				arr[1] = rs.getString("PRODUCTNAME");
				arr[2] = rs.getString("PRODUCTTYPE");
				arr[3] = rs.getString("DAYSALES");
				arr[4] = rs.getString("WEEKSALES");
				arr[5] = rs.getString("MONTHSALES");
				model2.addRow(arr);// arr의 내용을 테이블에 채운다.
			}
		}
		if (a == 2) {
			for (int i = salesSearchTable.getRowCount(); i > 0; i--) {
				model2.removeRow(i - 1);
			}
			ResultSet rs = stmt.executeQuery(
					"select distinct p.productnum, p.productname,p.producttype,s.DAYSALES*p.SALESPRICE as daysales,"
							+ " s.WEEKSALES*p.SALESPRICE as weeksales,s.MONTHSALES*p.SALESPRICE as monthsales"
							+ " from PRODUCTTABLE p, SALESSTOCKTABLE s"
							+ " where s.PRODUCTNUM = p.PRODUCTNUM and p.productnum=" + b + " order by p.productnum");
			String arr[] = new String[6];
			while (rs.next()) {
				arr[0] = rs.getString("PRODUCTNUM");
				arr[1] = rs.getString("PRODUCTNAME");
				arr[2] = rs.getString("PRODUCTTYPE");
				arr[3] = rs.getString("DAYSALES");
				arr[4] = rs.getString("WEEKSALES");
				arr[5] = rs.getString("MONTHSALES");
				model2.addRow(arr);// arr의 내용을 테이블에 채운다.
			}
		}
	}
}// 매출조회 화면의 상품이름/상품번호별 매출

class searchSaleType extends MainFrame {

	static void searchSaleTypep() throws SQLException {
		Connection con = ConnectDatabase.makeConnection();
		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery("select p.producttype, sum(s.DAYSALES*p.SALESPRICE)as daysales,"
				+ " sum(s.WEEKSALES*p.SALESPRICE) as weeksales, sum(s.MONTHSALES*p.SALESPRICE) as monthsales"
				+ " from producttable p, salesstocktable s" + " where p.productnum = s.productnum"
				+ " group by p.producttype");
		String arr[] = new String[4];
		while (rs.next()) {
			arr[0] = rs.getString("PRODUCTTYPE");
			arr[1] = rs.getString("DAYSALES");
			arr[2] = rs.getString("WEEKSALES");
			arr[3] = rs.getString("MONTHSALES");
			model3.addRow(arr);// arr의 내용을 테이블에 채운다.
		}
	}
}// 매출조회 화면의 품목별 매출

class searchSaleAll extends MainFrame {

	static void searchSaleAllp() throws SQLException {
		Connection con = ConnectDatabase.makeConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select sum(s.DAYSALES*p.SALESPRICE) as totaldaysales,"
				+ " sum(s.WEEKSALES*p.SALESPRICE) as totalweeksales,"
				+ " sum(s.MONTHSALES*p.SALESPRICE) as totalmonthsales" + " from salesstocktable s,producttable p"
				+ " where s.PRODUCTNUM=p.PRODUCTNUM");
		String arr[] = new String[3];
		while (rs.next()) {
			arr[0] = rs.getString("TOTALDAYSALES");
			arr[1] = rs.getString("TOTALWEEKSALES");
			arr[2] = rs.getString("TOTALMONTHSALES");
		}
		saleAllSearchLabel2.setText(arr[0] + "원");
		saleAllSearchLabel4.setText(arr[1] + "원");
		saleAllSearchLabel6.setText(arr[2] + "원");
	}
}
