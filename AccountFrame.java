import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

//�����߰�, �����ϴ� ������
public class AccountFrame extends JFrame {
   private JLabel getID = new JLabel("ID");
   private JLabel getPW = new JLabel("��й�ȣ");
   private JLabel checkPW = new JLabel("��й�ȣ Ȯ��");
   private JLabel getID2 = new JLabel("ID");
   private JLabel getPW2 = new JLabel("��й�ȣ");
   private JLabel checkPW2 = new JLabel("��й�ȣ Ȯ��");
   private JTextField getIDTF = new JTextField(12);
   private JPasswordField getPWTF = new JPasswordField(12);
   private JPasswordField checkPWTF = new JPasswordField(12);
   private JTextField getIDTF2 = new JTextField(12);
   private JPasswordField getPWTF2 = new JPasswordField(12);
   private JPasswordField checkPWTF2 = new JPasswordField(12);
   private JButton createAccount = new JButton("��������");
   private JButton deleteAccount = new JButton("��������");
   private JTabbedPane fr4aTap = new JTabbedPane();// ���г�
   private JPanel pa1 = new JPanel();
   private JPanel pa2 = new JPanel();
   private JPanel pa3 = new JPanel();
   private JPanel pa4 = new JPanel();
   JDialog dia = new JDialog(this, "��������", false);// ���̾Ʒα�
   JLabel diaLa = new JLabel("�����Ǿ����ϴ�.");
   JButton diaBtn = new JButton("Ȯ��");// ���̾�α� ��,��ư��

   ConnectDatabase c1 = new ConnectDatabase();// DB��ü ����

   public AccountFrame() {
      setSize(300, 200);
      pa2.setLayout(new FlowLayout());
      pa4.setLayout(new FlowLayout());
      pa1.setLayout(new GridLayout(3, 2, 5, 5));
      pa3.setLayout(new GridLayout(3, 2, 5, 5));
      pa1.setSize(300, 150);
      pa1.add(getID);
      pa1.add(getIDTF);
      pa1.add(getPW);
      pa1.add(getPWTF);
      pa1.add(checkPW);
      pa1.add(checkPWTF);
      pa2.add(pa1);
      pa2.add(createAccount);
      fr4aTap.setSize(300, 200);
      fr4aTap.addTab("�����߰�", pa2);
      pa3.add(getID2);
      pa3.add(getIDTF2);
      pa3.add(getPW2);
      pa3.add(getPWTF2);
      pa3.add(checkPW2);
      pa3.add(checkPWTF2);
      pa4.add(pa3);
      pa4.add(deleteAccount);
      fr4aTap.addTab("��������", pa4);
      add(fr4aTap);
      createAccount.addActionListener(new myListener());
      deleteAccount.addActionListener(new myListener());
      this.setVisible(true);
      dia.setLayout(new FlowLayout());
      dia.setSize(200, 120);
      dia.setLocation(470, 450);
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
         if (e.getSource() == createAccount) {
            try {
               createAccount ca = new createAccount(getIDTF.getText(), getPWTF.getText(), checkPWTF.getText());
            } catch (SQLException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
         if (e.getSource() == deleteAccount) {
            try {
               deleteAccount da = new deleteAccount(getIDTF2.getText(), getPWTF2.getText(), checkPWTF2.getText());
            } catch (SQLException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
      }
   }
}

class UserAccountFrame extends JFrame { //extends AccountFrame {
   private JLabel getID = new JLabel("ID");
   private JLabel getPW = new JLabel("��й�ȣ");
   private JLabel checkPW = new JLabel("��й�ȣ Ȯ��");
   private JLabel getID2 = new JLabel("ID");
   private JLabel getPW2 = new JLabel("��й�ȣ");
   private JLabel checkPW2 = new JLabel("��й�ȣ Ȯ��");
   private JTextField getIDTF = new JTextField(12);
   private JPasswordField getPWTF = new JPasswordField(12);
   private JPasswordField checkPWTF = new JPasswordField(12);
   private JTextField getIDTF2 = new JTextField(12);
   private JPasswordField getPWTF2 = new JPasswordField(12);
   private JPasswordField checkPWTF2 = new JPasswordField(12);
   private JButton createAccount = new JButton("��������");
   private JButton deleteAccount = new JButton("��������");
   private JTabbedPane fr4aTap = new JTabbedPane();// ���г�
   private JPanel pa1 = new JPanel();
   private JPanel pa2 = new JPanel();
   private JPanel pa3 = new JPanel();
   private JPanel pa4 = new JPanel();
   JDialog dia = new JDialog(this, "��������", false);// ���̾Ʒα�
   JLabel diaLa = new JLabel("�����Ǿ����ϴ�.");
   JButton diaBtn = new JButton("Ȯ��");// ���̾�α� ��,��ư��

   ConnectDatabase c1 = new ConnectDatabase();// DB��ü ����

   public UserAccountFrame() {
      setSize(300, 200);
      pa2.setLayout(new FlowLayout());
      pa4.setLayout(new FlowLayout());
      pa1.setLayout(new GridLayout(3, 2, 5, 5));
      pa3.setLayout(new GridLayout(3, 2, 5, 5));
      pa1.setSize(300, 150);
      pa1.add(getID);
      pa1.add(getIDTF);
      pa1.add(getPW);
      pa1.add(getPWTF);
      pa1.add(checkPW);
      pa1.add(checkPWTF);
      pa2.add(pa1);
      pa2.add(createAccount);
      fr4aTap.setSize(300, 200);
      fr4aTap.addTab("�����߰�", pa2);
      pa3.add(getID2);
      pa3.add(getIDTF2);
      pa3.add(getPW2);
      pa3.add(getPWTF2);
      pa3.add(checkPW2);
      pa3.add(checkPWTF2);
      pa4.add(pa3);
      pa4.add(deleteAccount);
      fr4aTap.addTab("��������", pa4);
      add(fr4aTap);
      createAccount.addActionListener(new myListener());
      deleteAccount.addActionListener(new myListener());
      this.setVisible(true);
      dia.setLayout(new FlowLayout());
      dia.setSize(250, 120);
      dia.setLocation(470, 450);
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
         if (e.getSource() == createAccount) {
            try {
               if(confirmID(getIDTF.getText())) {
               createAccount ca = new createAccount(getIDTF.getText(), getPWTF.getText(), checkPWTF.getText());
               }
               else {
                  diaLa.setText(" ID�� �й� 8�ڸ��� �Է����ּ���. " );
                  dia.setVisible(true);
               }
            } catch (SQLException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
         if (e.getSource() == deleteAccount) {
            try {
               if(confirmID(getIDTF.getText())) {
                  deleteAccount da = new deleteAccount(getIDTF2.getText(), getPWTF2.getText(), checkPWTF2.getText());
                  }
               else {
                  diaLa.setText(" ID�� �й� 8�ڸ��� �Է����ּ���. " );
                  dia.setVisible(true);
               }
               
            } catch (SQLException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
      }
   }
   
   Boolean confirmID(String a) {
      int compare = Integer.parseInt(a);
      if(compare>=0 && compare<=99999999 && a.length()==8) {
         System.out.println(a.length());
         return true;
      }
      else return false;
   }
}


class createAccount extends AccountFrame {

   createAccount(String a, String b, String c) throws SQLException {
      Connection con = ConnectDatabase.makeConnection();
      Statement stmt = con.createStatement();
      String id = a;
      String pw = b;
      String sql = "INSERT INTO LOGININFO VALUES('" + a + "','" + b + "')";
      if (b.equals(c)) {
         ResultSet rs = stmt.executeQuery(sql);
         diaLa.setText("    �����Ǿ����ϴ�.    ");
         dia.setVisible(true);
      } else {
         diaLa.setText("<html>��й�ȣ�� ���� �ʽ��ϴ�.<br/>��й�ȣ�� �ٽ� Ȯ���ϼ���.</html>");
         dia.setVisible(true);
      }
   }

}// �������� �޼ҵ�

class deleteAccount extends AccountFrame {

   deleteAccount(String a, String b, String c) throws SQLException {
      Connection con = ConnectDatabase.makeConnection();
      Statement stmt = con.createStatement();
      String id = null;
      String pw = null;
      String sql = "select * from logininfo where accid='" + a + "'";
      String sql2 = "delete from logininfo where accid='" + a + "'";
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
         id = rs.getString("ACCID");
         pw = rs.getString("PASSOWORD");
      }
      if (a.equals(id) && b.equals(pw) && b.equals(c)) {
         rs = stmt.executeQuery(sql2);
         diaLa.setText("    �����Ǿ����ϴ�.    ");
         dia.setVisible(true);
      } else if (!a.equals(id)) {
         diaLa.setText("    ID�� �����ʽ��ϴ�.    ");
         dia.setVisible(true);
      } else if (!b.equals(pw)) {
         diaLa.setText("    ��й�ȣ�� Ʋ���ϴ�.    ");
         dia.setVisible(true);
      } else if (!b.equals(c)) {
         diaLa.setText("<html>��й�ȣ�� ���� �ʽ��ϴ�.<br/>��й�ȣ�� �ٽ� Ȯ���ϼ���.</html>");
         dia.setVisible(true);
      }
   }
}