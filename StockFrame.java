import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

//재고추가, 폐기 프레임
public class StockFrame extends ConnectDatabase {
	JPanel adPa1 = new JPanel();// 재고추가할때 텍스트필드,라벨들어갈곳
	JPanel adPa2 = new JPanel();// adPa1에 버튼추가후 탭패널에 넣을거
	JPanel adPa3 = new JPanel();// 재고폐기할때 텍스트필드,라벨들어갈곳
	JPanel adPa4 = new JPanel();// adPa2에 버튼추가후 탭패널에 넣을거
	JLabel la1 = new JLabel("제품번호");// 여기서부터
	JLabel la2 = new JLabel("유통기한");
	JLabel la3 = new JLabel("수량");
	JTextField t1 = new JTextField(10);
	JTextField t2 = new JTextField(10);
	JTextField t3 = new JTextField(10);
	JButton adPaBtn1 = new JButton("추가");// 여기까지는 재고추가 탭에 들어갈거
	JButton adPaBtn2 = new JButton("폐기");// 여기서부터
	JLabel ta1 = new JLabel("제품번호");
	JLabel ta2 = new JLabel("유통기한");
	JLabel ta3 = new JLabel("수량");
	JTextField tt1 = new JTextField(10);
	JTextField tt2 = new JTextField(10);
	JTextField tt3 = new JTextField(10);// 여기까지는 상품제거 탭에 들어갈거
	JTabbedPane adTab1 = new JTabbedPane();// 상품추가/제거화면에서 탭화면
	JDialog dia = new JDialog(this, "추가", false);// 다이아로그
	static JLabel diaLa = new JLabel("추가되었습니다.");
	JButton diaBtn = new JButton("확인");// 다이어로그 라벨,버튼들
	java.net.URL imageURL10 = getClass().getClassLoader().getResource("CCISCICON.png");
	ImageIcon cciscIcon = new ImageIcon(imageURL10);

	public StockFrame() {
		this.setIconImage(cciscIcon.getImage());
		setSize(300, 200);
		adPa1.setLayout(new GridLayout(3, 2, 5, 5));
		adPa2.setLayout(new FlowLayout());
		adPa1.setSize(250, 250);
		adPa1.add(la1);
		adPa1.add(t1);
		adPa1.add(la2);
		adPa1.add(t2);
		adPa1.add(la3);
		adPa1.add(t3);
		adPa2.add(adPa1);
		adPaBtn1.setSize(100, 30);
		adPa2.add(adPaBtn1);
		adTab1.addTab("재고추가", adPa2);// 탭패널에 패널추가
		adTab1.setSize(250, 250);
		adPa3.setLayout(new GridLayout(3, 2, 5, 5));
		adPa4.setLayout(new FlowLayout());
		adPa3.setSize(250, 250);
		adPa3.add(ta1);
		adPa3.add(tt1);
		adPa3.add(ta2);
		adPa3.add(tt2);
		adPa3.add(ta3);
		tt3.setText("");
		adPa3.add(tt3);
		adPa4.add(adPa3);
		adPaBtn2.setSize(100, 30);
		adPa4.add(adPaBtn2);
		adTab1.addTab("재고폐기", adPa4);// 탭패널에 패널추가
		adTab1.setSize(250, 250);
		add(adTab1);
		adPaBtn1.addActionListener(new myListener());
		adPaBtn2.addActionListener(new myListener());
		dia.setLayout(new FlowLayout());
		dia.setSize(160, 120);
		dia.setLocation(75, 90);
		dia.add(diaLa);
		dia.add(diaBtn);
		diaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dia.setVisible(false);
			}
		});// 다이어로그버튼을 위한 익명내부클래스
		this.setVisible(true);
	}

	class myListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String productNum = null;
			if (e.getSource() == adPaBtn1) {
				try {
					String sql = "select productnum from producttable";
					ResultSet rs = stmt.executeQuery(sql);
					JOptionPane temp = new JOptionPane();
					int mCheck1 = Integer.parseInt(t1.getText());
					String mCheck2 = t2.getText();
					int mCheck3 = Integer.parseInt(t3.getText());
					int error = 0;

					while (rs.next()) {
						productNum = rs.getString(1);
						if (productNum.equals(null) || !productNum.equals(t1.getText()))
							error = 0;
						else
							error++;
					}
					System.out.println(t2.getText());
					if (mCheck1 < 0 || mCheck3 < 0) {
						temp.showMessageDialog(null, "잘못된 값을 입력하셨습니다. 다시 입력하세요.");
					} else if (error == 0)
						temp.showMessageDialog(null, "제품이 없습니다. 제품부터 추가하세요.");
					else {
						dispose();
						stockAddr.stockAdd(t1.getText(), t2.getText(), t3.getText());
						diaLa.setText("추가되었습니다.");
						dia.setVisible(true);
						stockAddr.stockAdd2(t1.getText(), t3.getText());
						diaLa.setText("추가되었습니다.");
						dia.setVisible(true);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (e.getSource() == adPaBtn2) {
				try {
					String sql = "select STOCK,productnum from EXPIRATIONTABLE";
					ResultSet rs = stmt.executeQuery(sql);
					JOptionPane temp2 = new JOptionPane();
					int count = 0, cnt = 0;
					int nCheck1 = Integer.parseInt(tt1.getText());
					int nCheck2 = Integer.parseInt(tt1.getText());
					int nCheck3 = Integer.parseInt(tt1.getText());

					if (nCheck1 < 0 || nCheck2 < 0 || nCheck3 < 0) {
						temp2.showMessageDialog(null, "잘못된 값을 입력하셨습니다. 다시 입력하세요.");
					}

					while (rs.next()) {
						int pStock = rs.getInt("stock");
						String pNum = rs.getString("productnum");
						if (pNum.equals(tt1.getText()) && nCheck3 > pStock && nCheck3 > 0) {
							temp2.showMessageDialog(null, "제거 수량이 존재하는 수량보다 많습니다.");
							count++;
						} else if (!pNum.equals(tt1.getText())) {
							temp2.showMessageDialog(null, "존재하지 않는 제품입니다.");
							count++;
						}
					}
					if (count == 0) {
						dispose();
						String val = stockDelr.stockDel(tt1.getText(), tt2.getText(), tt3.getText());
						stockDelr.stockDel2(tt1.getText(), val);
						diaLa.setText("폐기되었습니다.");
						dia.setVisible(true);

					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}

class Stock extends StockFrame {

}

class stockAddr extends Stock {
	public static void stockAdd(String a, String b, String c) throws SQLException {
		String sql = "select productnum from EXPIRATIONTABLE";
		ResultSet rs = null;
		ResultSet rs2 = stmt.executeQuery(sql);
		while (rs2.next()) {
			String pNum = rs2.getString("productnum");
			if (pNum.equals(a)) {
				rs = stmt.executeQuery("update EXPIRATIONTABLE" + " set stock = stock+" + c + " where productnum=" + a);
				return;
			}
		}
		rs = stmt.executeQuery("INSERT INTO EXPIRATIONTABLE VALUES(" + a + ",'" + b + "'," + c + ")");
	}

	public static void stockAdd2(String a, String b) throws SQLException {
		ResultSet rs = stmt
				.executeQuery("update SALESSTOCKTABLE" + " set stock = stock+" + b + " where productnum=" + a);
	}
}

class stockDelr extends Stock {
	public static String stockDel(String a, String b, String c) throws SQLException {
		String num = "0";// 들어온 수량값이 0인지 확인용
		String stockVal = null;// 수량을 얼마나 제거 했는지 저장. 끝나고 반환
		if (c.equals(num)) {
			ResultSet rs = stmt.executeQuery(
					"select * FROM EXPIRATIONTABLE" + " WHERE productnum=" + a + " and EXPIRYDATE='" + b + "'");
			while (rs.next()) {
				stockVal = rs.getString("stock");
			}
			ResultSet rs2 = stmt.executeQuery(
					"DELETE FROM EXPIRATIONTABLE " + "WHERE productnum=" + a + " and EXPIRYDATE='" + b + "'");
		} // 수량값이 0이면 해당 유통기한행의 전체 재고수 추출후 stockVal에 저장. 그후 그 행 전체 삭제
		else {
			ResultSet rs2 = stmt.executeQuery("update EXPIRATIONTABLE " + "set stock=stock-" + c + " where productnum="
					+ a + " and EXPIRYDATE='" + b + "'");
			stockVal = c;
		} // 0이 아니면 들어론 수량만큼 재고수 감수
		return stockVal;
	}

	public static void stockDel2(String a, String b) throws SQLException {
		ResultSet rs = stmt.executeQuery("update SALESSTOCKTABLE " + "set stock=stock-" + b + " where productnum=" + a);
	}
}