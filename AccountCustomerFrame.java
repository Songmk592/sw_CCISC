import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//��������, �ŷ�ó���� ����ȭ��
public class AccountCustomerFrame extends JFrame {
	private JButton accountManage = new JButton("��������");
	private JButton customerManage = new JButton("�ŷ�ó����");
	java.net.URL imageURL10 = getClass().getClassLoader().getResource("CCISCICON.png");
	ImageIcon cciscIcon = new ImageIcon(imageURL10);

	public AccountCustomerFrame() {
		this.setIconImage(cciscIcon.getImage());
		setSize(245, 100);
		setLayout(null);
		accountManage.setBounds(10, 10, 100, 30);
		customerManage.setBounds(120, 10, 100, 30);
		add(accountManage);
		add(customerManage);
		accountManage.addActionListener(new myListener());
		customerManage.addActionListener(new myListener());
		this.setVisible(true);
	}

	class myListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == accountManage) {
				AccountFrame fr4a = new AccountFrame();
				dispose();
			}
			if (e.getSource() == customerManage) {
				CustomerSearchFrame fr4b = new CustomerSearchFrame();
				dispose();
			}
		}
	}
}
