import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.swing.*;
import java.sql.*;

//�ŷ�ó �߰�, ���� ȭ��
public class CustomerFrame extends JFrame {
	private JLabel customerName = new JLabel("�̸�");// �߰��ǿ� �� ��
	private JLabel customerPhone = new JLabel("��ȭ��ȣ");
	private JLabel customerCompanyNum = new JLabel("�ŷ�ȸ��(��ȣ)");
	private JLabel customerCompany = new JLabel("�ŷ�ȸ��");
	private JLabel customerName2 = new JLabel("�̸�");// �����ǿ� �� ��
	private JTextField customerNameTF = new JTextField(12);
	private JTextField customerPhoneTF = new JTextField(12);
	private JTextField customerCompanyNumTF = new JTextField(12);
	private JTextField customerCompanyTF = new JTextField(12);
	private JTextField customerNameTF2 = new JTextField(12);
	private JButton customerAdd = new JButton("�߰�");
	private JButton customerDel = new JButton("����");
	private JPanel pa1 = new JPanel();// �߰��ǿ����� �󺧰� �ؽ�Ʈ�ʵ带 ���� �г�
	private JPanel pa2 = new JPanel();
	private JPanel pa3 = new JPanel();// �����ǿ����� �󺧰� �ؽ�Ʈ�ʵ带 ���� �г�
	private JPanel pa4 = new JPanel();
	private JTabbedPane tabPa1 = new JTabbedPane();// ���г�
	JDialog dia = new JDialog(this, "�ŷ�ó����", false);// ���̾Ʒα�
	JLabel diaLa = new JLabel("�߰��Ǿ����ϴ�.");
	JButton diaBtn = new JButton("Ȯ��");// ���̾�α� ��,��ư��

	public CustomerFrame() {
		setSize(300, 210);
		pa1.setLayout(new GridLayout(4, 2, 5, 5));
		pa2.setLayout(new FlowLayout());
		pa2.setSize(300, 180);
		pa3.setLayout(new GridLayout(1, 2, 5, 5));
		pa4.setLayout(new FlowLayout());
		pa4.setSize(300, 180);
		pa1.add(customerName);
		pa1.add(customerNameTF);
		pa1.add(customerPhone);
		pa1.add(customerPhoneTF);
		pa1.add(customerCompanyNum);
		pa1.add(customerCompanyNumTF);
		pa1.add(customerCompany);
		pa1.add(customerCompanyTF);
		pa2.setSize(300, 160);
		pa2.add(pa1);
		pa2.add(customerAdd);
		pa3.add(customerName2);
		pa3.add(customerNameTF2);
		pa4.add(pa3);
		pa4.add(customerDel);
		tabPa1.setSize(300, 200);
		tabPa1.addTab("�߰�", pa2);
		tabPa1.addTab("����", pa4);
		add(tabPa1);
		customerAdd.addActionListener(new myListener());
		customerDel.addActionListener(new myListener());
		this.setVisible(true);
		dia.setLayout(new FlowLayout());
		dia.setSize(160, 120);
		dia.setLocation(400, 400);
		dia.add(diaLa);
		dia.add(diaBtn);
		diaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dia.setVisible(false);
			}
		});// ���̾�α׹�ư�� ���� �͸���Ŭ����
	}

	class myListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Connection con = ConnectDatabase.makeConnection();
			if (e.getSource() == customerAdd) {
				try {
					Statement stmt = con.createStatement();
					String sql = "select MANUFACTURERNAME from MANUFACTURERTABLE";
					JOptionPane temp = new JOptionPane();
					
					int cCheck3 = Integer.parseInt(customerCompanyNumTF.getText());
					ResultSet rs = stmt.executeQuery(sql);
					int cnt=0;

					while (rs.next()) {
						String personName = rs.getString("MANUFACTURERNAME");
						personName = personName.trim();

						if (personName.equals(customerCompanyTF.getText())) {
							cnt = 0;
							temp.showMessageDialog(null, "�̹� �����ϴ� �ŷ�ó�Դϴ�.");
							return;
						} else
							cnt++;
					}

					if (cCheck3 < 0) {
						temp.showMessageDialog(null, "�߸��� ���� �Է� �ϼ̽��ϴ�. �ٽ� �Է��ϼ���.");
					}

					else {
							customerAddr.customerAdd(customerNameTF.getText(), customerPhoneTF.getText(),
									customerCompanyNumTF.getText(), customerCompanyTF.getText());
							diaLa.setText("�߰��Ǿ����ϴ�.");
							dia.setVisible(true);
						
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			if (e.getSource() == customerDel) {
				try {
					Statement stmt = con.createStatement();
					String sql = "select PERSONINCHARGER from MANUFACTURERTABLE";
					ResultSet rs = stmt.executeQuery(sql);
					JOptionPane temp2 = new JOptionPane();
					
					String name, cname;
					int error = 0;
					//cname = customerNameTF2.getText();
					//cname.trim();
					while (rs.next()) {
						
						name = rs.getString("PERSONINCHARGER");
						name = name.trim();
						if (name.equals( customerNameTF2.getText()))
							error++;
					}
					if (error == 0) {
						temp2.showMessageDialog(null, "�������� �ʴ� �ŷ�ó�Դϴ�.");
					} else {
						customerDelr.customerDel(customerNameTF2.getText());
						diaLa.setText("�����Ǿ����ϴ�.");
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
class Customer extends CustomerFrame {
	protected static Connection con;
	protected static Statement stmt;
	protected static ResultSet rs;
}

class customerAddr extends Customer {
	public static void customerAdd(String a, String b, String c, String d) throws SQLException {
		con = ConnectDatabase.makeConnection();
		stmt = con.createStatement();
		rs = stmt.executeQuery(
				"insert into MANUFACTURERTABLE values" + "('" + c + "','" + d + "','" + a + "','" + b + "')");
	}
}

class customerDelr extends Customer{
	public static void customerDel(String a) throws SQLException {
		con = ConnectDatabase.makeConnection();
		stmt = con.createStatement();
		rs = stmt.executeQuery("delete from MANUFACTURERTABLE where PERSONINCHARGER='" + a + "'");
	}
}