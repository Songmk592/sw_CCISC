import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*�������,��� �����ִ� ������*/
public class StockConfirmFrame extends ConnectDatabase {
	String head[] = { "��ǰ��ȣ", "��ǰ��", "�������", "���" };// ���̺� ��Ű��
	String content[][] = {};// ���̺���
	DefaultTableModel model = new DefaultTableModel(content, head);// ���̺� ������ ����
																	// ������
	JTable fr3ta1 = new JTable(model);// ���̺����
	JScrollPane fr3sp = new JScrollPane(fr3ta1);// ���̺� ��ũ���гο� ����
	JPanel fr3pa1 = new JPanel();// ���Ĺ�ư���� ���� �г�
	JPanel fr3pa2 = new JPanel();// ���Ĺ�ư�� ��ũ���г��� ���� �г�
	JPanel fr3pa3 = new JPanel();// ��ǰ�߰�/���� ��ư, ��ǰ���ֹ�ư ���� �г�
	JButton fr3Btn2 = new JButton("���� ����");
	JButton fr3Btn3 = new JButton("�߰�/����");
	ConnectDatabase c1 = new ConnectDatabase();// DB��ü ����
	java.net.URL imageURL10 = getClass().getClassLoader().getResource("CCISCICON.png");
	ImageIcon cciscIcon = new ImageIcon(imageURL10);
		
	public StockConfirmFrame() {
		this.setIconImage(cciscIcon.getImage());
		setSize(600, 400);
		setLayout(null);
		fr3pa1.setLayout(new GridLayout(2, 1, 5, 15));
		fr3pa1.add(fr3Btn2);
		fr3pa2.setLayout(new FlowLayout());
		fr3ta1.setPreferredScrollableViewportSize(new Dimension(400, 200));// ���̺�
																			// �����
																			// ����
		fr3pa2.add(fr3sp);
		fr3pa2.add(fr3pa1);
		fr3pa2.setLocation(10, 10);
		fr3pa2.setSize(550, 250);
		fr3pa3.setLayout(new GridLayout(1, 2, 10, 10));
		fr3pa3.add(fr3Btn3);
		fr3pa3.setBounds(20, 260, 200, 30);
		add(fr3pa3);
		add(fr3pa2);
		fr3Btn2.addActionListener(new myListener());
		fr3Btn3.addActionListener(new myListener());
		this.setVisible(true);
	}

	public void sellLifeArray() throws SQLException {
		for (int i = fr3ta1.getRowCount(); i > 0; i--) {
			model.removeRow(i - 1);
		}
		String arr[] = new String[4];
	}// ������Ѽ� ����

	public void stockArray() throws SQLException {
		int num = 0;
		ResultSet rs = stmt.executeQuery(
				"select p.productnum, p.productname, e.expirydate, e.stock from producttable p, EXPIRATIONTABLE e where p.productnum=e.PRODUCTNUM order by e.stock desc");
		for (int i = fr3ta1.getRowCount(); i > 0; i--) {
			model.removeRow(i - 1);
		}
		String arr[] = new String[4];
		while (rs.next()) {
			arr[0] = rs.getString("productnum");
			arr[1] = rs.getString("productname");
			arr[2] = rs.getString("expirydate");
			arr[3] = rs.getString("Stock");
			model.addRow(arr);// arr�� ������ ���̺� ä���.
		}
	}// ���� ����

	class myListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String productNum = null;

			if (e.getSource() == fr3Btn2) {
				try {
					String sql = "select productnum from producttable";
					ResultSet rs = stmt.executeQuery(sql);
					JOptionPane temp = new JOptionPane();
					int error = 0;

					while (rs.next()) {
						productNum = rs.getString(1);
						if (productNum.equals(null)) {
							error = 0;
						} else {
							error++;
						}
					}
					if (error == 0)
						temp.showMessageDialog(null, "������ ��ǰ�� �����ϴ�.");
					else {
						stockArray();

					}
					model.fireTableDataChanged();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (e.getSource() == fr3Btn3) {
				StockFrame aa = new StockFrame();
			}
		}
	}
}