import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/*상품 추가/제거 프레임*/
public class ProductFrame extends JFrame {
	JPanel adPa1 = new JPanel();// 상품추가할때 텍스트필드,라벨들어갈곳
	JPanel adPa2 = new JPanel();// adPa1에 버튼추가후 탭패널에 넣을거
	JPanel adPa3 = new JPanel();// 상품제거할때 텍스트필드,라벨들어갈곳
	JPanel adPa4 = new JPanel();// adPa2에 버튼추가후 탭패널에 넣을거
	JLabel la1 = new JLabel("제품번호");// 여기서부터
	JLabel la2 = new JLabel("제품명");
	JLabel la3 = new JLabel("판매가격");
	JLabel la4 = new JLabel("원가");
	JLabel la5 = new JLabel("제조사(번호)");
	JLabel la6 = new JLabel("제품유형");
	JTextField t1 = new JTextField(10);
	JTextField t2 = new JTextField(10);
	JTextField t3 = new JTextField(10);
	JTextField t4 = new JTextField(10);
	JTextField t5 = new JTextField(10);
	JTextField t6 = new JTextField(10);
	JButton adPaBtn1 = new JButton("추가");// 여기까지는 상품추가 탭에 들어갈거
	JButton adPaBtn2 = new JButton("제거");// 여기서부터
	JLabel ta1 = new JLabel("제품번호");
	JTextField tt1 = new JTextField(10);// 여기까지는 상품제거 탭에 들어갈거
	JTabbedPane adTab1 = new JTabbedPane();// 상품추가/제거화면에서 탭화면
	JDialog dia = new JDialog(this, "추가", false);// 다이아로그
	JLabel diaLa = new JLabel("추가되었습니다.");
	JButton diaBtn = new JButton("확인");// 다이어로그 라벨,버튼들

	public ProductFrame() {
		setSize(300, 300);
		adPa1.setLayout(new GridLayout(6, 2, 5, 5));
		adPa2.setLayout(new FlowLayout());
		adPa1.setSize(300, 250);
		adPa1.add(la1);
		adPa1.add(t1);
		adPa1.add(la2);
		adPa1.add(t2);
		adPa1.add(la3);
		adPa1.add(t3);
		adPa1.add(la4);
		adPa1.add(t4);
		adPa1.add(la5);
		adPa1.add(t5);
		adPa1.add(la6);
		adPa1.add(t6);
		adPa2.add(adPa1);
		adPaBtn1.setSize(100, 30);
		adPa2.add(adPaBtn1);
		adTab1.addTab("상품추가", adPa2);// 탭패널에 패널추가
		adTab1.setSize(300, 250);
		getContentPane().add(adTab1);
		adPa3.setLayout(new GridLayout(1, 2, 5, 15));
		adPa3.setSize(300, 200);
		adPa3.add(ta1);
		adPa3.add(tt1);
		adPa4.setLayout(new FlowLayout());
		adPaBtn2.setSize(100, 30);
		adPa4.add(adPa3);
		adPa4.add(adPaBtn2);
		adTab1.addTab("상품제거", adPa4);
		this.setVisible(true);
		adPaBtn1.addActionListener(new myListener());
		adPaBtn2.addActionListener(new myListener());
		dia.getContentPane().setLayout(new FlowLayout());
		dia.setSize(160, 120);
		dia.setLocation(75, 90);
		dia.getContentPane().add(diaLa);
		dia.getContentPane().add(diaBtn);
		diaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dia.setVisible(false);
			}
		});// 다이어로그버튼을 위한 익명내부클래스
	}

	// 상품추가 버튼 구현

	class myListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			Connection con = ConnectDatabase.makeConnection();

			if (e.getSource() == adPaBtn1) {

				try {
					Statement stmt = con.createStatement();
					String sql = "select productname, productnum from producttable";
					ResultSet rs = stmt.executeQuery(sql);
					JOptionPane temp = new JOptionPane();

					if (t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals("")
							|| t4.getText().equals("") || t5.getText().equals("") || t6.getText().equals("")) {
						temp.showMessageDialog(null, "빠진 정보가 있습니다.");
					}

					int pCheck1 = Integer.parseInt(t1.getText());
					int pCheck3 = Integer.parseInt(t3.getText());
					int pCheck4 = Integer.parseInt(t4.getText());
					int pCheck5 = Integer.parseInt(t5.getText());
					
					while (rs.next()) {

						String pName = rs.getString("productname");
						String pNum = rs.getString("productnum");
						pName = pName.trim();

						if (pName.equals(t2.getText())) {
							temp.showMessageDialog(null, "이미 존재하는 상품입니다.");
							return;
						}
						
						if(pNum.equals(t1.getText())) {
							temp.showMessageDialog(null, "이미 존재하는 제품번호입니다.");
							return;
						}
							

					}
					
					

					if (pCheck1 < 0  || pCheck3 < 0 || pCheck4 < 0 || pCheck5 < 0) {
						temp.showMessageDialog(null, "잘못된 값을 입력하셨습니다. 다시 입력하세요.");
					} else {
						productAddr.productAdd(t1.getText(), t2.getText(), t3.getText(), t4.getText(), t5.getText(),
								t6.getText());
						diaLa.setText("추가되었습니다.");
						dia.setVisible(true);
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// dispose();
			}
			if (e.getSource() == adPaBtn2) {
				try {
					Statement stmt = con.createStatement();
					String sql = "select productnum from producttable";
					ResultSet rs = stmt.executeQuery(sql);
					int num, cnum, error = 0;
					JOptionPane temp2 = new JOptionPane();
					cnum = Integer.parseInt(tt1.getText());
					while (rs.next()) {
						num = rs.getInt("productnum");
						if (num == cnum)
							error++;
					}
					int dCheck1 = Integer.parseInt(tt1.getText());
					if (dCheck1 < 0) {
						temp2.showMessageDialog(null, "잘못된 값을 입력하셨습니다. 다시 입력하세요.");
					} else {
						if (error == 0)
							temp2.showMessageDialog(null, "이미 삭제된 제품이거나 존재하지 않습니다..");
						else {
							productDelr.productDel(tt1.getText());
							diaLa.setText("제거되었습니다.");
							dia.setVisible(true);
						}
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// dispose();
			}
		}
	}
}
class Product {
	protected static Connection con = null;
	protected static Statement stmt = null;
	protected static String sql = null;
	protected static String sql2 = null;
	protected static ResultSet rs = null;
	protected static ResultSet rs2 = null;
	protected static ResultSet rs3 = null;
	protected static ResultSet rs4 = null;
	
}
class productAddr extends Product {
	public static void productAdd(String a, String b, String c, String d, String e, String f) throws SQLException {
		con = ConnectDatabase.makeConnection();
		stmt = con.createStatement();
		sql = "INSERT INTO producttable VALUES(" + a + ",'" + b + "'," + c + "," + d + "," + e + ",'" + f + "')";
		sql2 = "INSERT INTO SALESSTOCKTABLE VALUES(" + a + ",0,0,0,0)";
		rs = stmt.executeQuery(sql);
		rs2 = stmt.executeQuery(sql2);
	}
}

class productDelr extends Product {
	public static void productDel(String a) throws SQLException {
		con = ConnectDatabase.makeConnection();
		stmt = con.createStatement();
		sql = "delete from producttable where productnum=" + a;
		sql2 = "delete from SALESSTOCKTABLE where productnum=" + a;
		String stockVal = null;
		rs3 = stmt.executeQuery("select * FROM EXPIRATIONTABLE" + " WHERE productnum=" + a);
		while (rs3.next()) {
			stockVal = rs3.getString("stock");
		}
		rs4 = stmt.executeQuery("DELETE FROM EXPIRATIONTABLE " + "WHERE productnum=" + a);
		// 수량값이 0이면 해당 유통기한행의 전체 재고수 추출후 stockVal에 저장. 그후 그 행 전체 삭제

		rs = stmt.executeQuery(sql);
		rs2 = stmt.executeQuery(sql2);
	}
}