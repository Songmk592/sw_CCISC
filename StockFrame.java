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

//����߰�, ��� ������
public class StockFrame extends ConnectDatabase {
	JPanel adPa1 = new JPanel();// ����߰��Ҷ� �ؽ�Ʈ�ʵ�,�󺧵���
	JPanel adPa2 = new JPanel();// adPa1�� ��ư�߰��� ���гο� ������
	JPanel adPa3 = new JPanel();// �������Ҷ� �ؽ�Ʈ�ʵ�,�󺧵���
	JPanel adPa4 = new JPanel();// adPa2�� ��ư�߰��� ���гο� ������
	JLabel la1 = new JLabel("��ǰ��ȣ");// ���⼭����
	JLabel la2 = new JLabel("�������");
	JLabel la3 = new JLabel("����");
	JTextField t1 = new JTextField(10);
	JTextField t2 = new JTextField(10);
	JTextField t3 = new JTextField(10);
	JButton adPaBtn1 = new JButton("�߰�");// ��������� ����߰� �ǿ� ����
	JButton adPaBtn2 = new JButton("���");// ���⼭����
	JLabel ta1 = new JLabel("��ǰ��ȣ");
	JLabel ta2 = new JLabel("�������");
	JLabel ta3 = new JLabel("����");
	JTextField tt1 = new JTextField(10);
	JTextField tt2 = new JTextField(10);
	JTextField tt3 = new JTextField(10);// ��������� ��ǰ���� �ǿ� ����
	JTabbedPane adTab1 = new JTabbedPane();// ��ǰ�߰�/����ȭ�鿡�� ��ȭ��
	JDialog dia = new JDialog(this, "�߰�", false);// ���̾Ʒα�
	static JLabel diaLa = new JLabel("�߰��Ǿ����ϴ�.");
	JButton diaBtn = new JButton("Ȯ��");// ���̾�α� ��,��ư��
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
		adTab1.addTab("����߰�", adPa2);// ���гο� �г��߰�
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
		adTab1.addTab("������", adPa4);// ���гο� �г��߰�
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
		});// ���̾�α׹�ư�� ���� �͸���Ŭ����
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
						temp.showMessageDialog(null, "�߸��� ���� �Է��ϼ̽��ϴ�. �ٽ� �Է��ϼ���.");
					} else if (error == 0)
						temp.showMessageDialog(null, "��ǰ�� �����ϴ�. ��ǰ���� �߰��ϼ���.");
					else {
						dispose();
						stockAddr.stockAdd(t1.getText(), t2.getText(), t3.getText());
						diaLa.setText("�߰��Ǿ����ϴ�.");
						dia.setVisible(true);
						stockAddr.stockAdd2(t1.getText(), t3.getText());
						diaLa.setText("�߰��Ǿ����ϴ�.");
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
						temp2.showMessageDialog(null, "�߸��� ���� �Է��ϼ̽��ϴ�. �ٽ� �Է��ϼ���.");
					}

					while (rs.next()) {
						int pStock = rs.getInt("stock");
						String pNum = rs.getString("productnum");
						if (pNum.equals(tt1.getText()) && nCheck3 > pStock && nCheck3 > 0) {
							temp2.showMessageDialog(null, "���� ������ �����ϴ� �������� �����ϴ�.");
							count++;
						} else if (!pNum.equals(tt1.getText())) {
							temp2.showMessageDialog(null, "�������� �ʴ� ��ǰ�Դϴ�.");
							count++;
						}
					}
					if (count == 0) {
						dispose();
						String val = stockDelr.stockDel(tt1.getText(), tt2.getText(), tt3.getText());
						stockDelr.stockDel2(tt1.getText(), val);
						diaLa.setText("���Ǿ����ϴ�.");
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
		String num = "0";// ���� �������� 0���� Ȯ�ο�
		String stockVal = null;// ������ �󸶳� ���� �ߴ��� ����. ������ ��ȯ
		if (c.equals(num)) {
			ResultSet rs = stmt.executeQuery(
					"select * FROM EXPIRATIONTABLE" + " WHERE productnum=" + a + " and EXPIRYDATE='" + b + "'");
			while (rs.next()) {
				stockVal = rs.getString("stock");
			}
			ResultSet rs2 = stmt.executeQuery(
					"DELETE FROM EXPIRATIONTABLE " + "WHERE productnum=" + a + " and EXPIRYDATE='" + b + "'");
		} // �������� 0�̸� �ش� ����������� ��ü ���� ������ stockVal�� ����. ���� �� �� ��ü ����
		else {
			ResultSet rs2 = stmt.executeQuery("update EXPIRATIONTABLE " + "set stock=stock-" + c + " where productnum="
					+ a + " and EXPIRYDATE='" + b + "'");
			stockVal = c;
		} // 0�� �ƴϸ� ���� ������ŭ ���� ����
		return stockVal;
	}

	public static void stockDel2(String a, String b) throws SQLException {
		ResultSet rs = stmt.executeQuery("update SALESSTOCKTABLE " + "set stock=stock-" + b + " where productnum=" + a);
	}
}