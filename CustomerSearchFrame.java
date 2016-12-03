import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//거래처조회 화면
public class CustomerSearchFrame extends ConnectDatabase {
	static String head[] = { "이름", "전화번호", "거래회사(번호)", "거래회사" };// 테이블 스키마
	static String content[][] = {};// 테이블내용
	static DefaultTableModel model = new DefaultTableModel(content, head);// 테이블
																			// 조작을
																			// 위한
																			// 리모콘
	static JTable customerSearch = new JTable(model);// 테이블생성
	JScrollPane customerSearchScrPa = new JScrollPane(customerSearch);// 테이블를
																		// 스크롤패널에
																		// 적재
	JPanel pa1 = new JPanel();// 정렬버튼들을 담을 패널
	JPanel pa2 = new JPanel();// 정렬버튼과 스크롤패널을 담을 패널
	JButton customerSearchBtn = new JButton("조회");// 거래처 조회버튼
	JButton customerModBtn = new JButton("거래처 추가/제거");// 거래처 정보 수정 버튼
	java.net.URL imageURL10 = getClass().getClassLoader().getResource("CCISCICON.png");
	ImageIcon cciscIcon = new ImageIcon(imageURL10);

	public CustomerSearchFrame() {
		this.setIconImage(cciscIcon.getImage());
		setSize(600, 330);
		setLayout(null);
		pa1.setLayout(new FlowLayout());
		pa2.setLayout(new GridLayout(2, 1, 20, 15));
		pa2.setSize(100, 40);
		pa2.add(customerSearchBtn);
		pa2.add(customerModBtn);
		customerSearch.setPreferredScrollableViewportSize(new Dimension(400, 200));// 테이블
																					// 사이즈를
																					// 조절
		pa1.setBounds(10, 10, 400, 250);
		pa1.add(customerSearchScrPa);
		pa1.add(pa2);
		pa1.setBounds(10, 10, 590, 340);
		add(pa1);
		this.setVisible(true);
		customerSearchBtn.addActionListener(new myListener());
		customerModBtn.addActionListener(new myListener());
	}

	class myListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String manuNum = null;

			if (e.getSource() == customerSearchBtn) {
				try {
					String sql = "select MANUFACTURERNUM from MANUFACTURERTABLE";
					ResultSet rs = stmt.executeQuery(sql);
					JOptionPane temp = new JOptionPane();
					int error = 0;

					while (rs.next()) {
						manuNum = rs.getString(1);
						if (manuNum.equals(null)) {
							error = 0;
						} else
							error++;
					}
					if (error == 0) {
						for (int i = customerSearch.getRowCount(); i > 0; i--) {
		                     model.removeRow(i - 1);
		                  }
						temp.showMessageDialog(null, "조회할 거래처가 없습니다.");
					} else
						customerSearchr.customerSearch();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (e.getSource() == customerModBtn) {
				CustomerFrame fr = new CustomerFrame();
			}
		}
	}
}

class customerSearchr extends CustomerSearchFrame {
	public static void customerSearch() throws SQLException {
		ResultSet rs = stmt.executeQuery(
				"select PERSONINCHARGER, telephone,MANUFACTURERNUM,MANUFACTURERNAME" + " from manufacturertable");
		for (int i = customerSearch.getRowCount(); i > 0; i--) {
			model.removeRow(i - 1);
		}
		String arr[] = new String[4];
		while (rs.next()) {
			arr[0] = rs.getString("PERSONINCHARGER");
			arr[1] = rs.getString("telephone");
			arr[2] = rs.getString("MANUFACTURERNUM");
			arr[3] = rs.getString("MANUFACTURERNAME");
			model.addRow(arr);// arr의 내용을 테이블에 채운다.
		}
	}
}