import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

//계정추가, 삭제하는 프레임
public class AccountFrame extends JFrame {
   private JLabel getID = new JLabel("ID");
   private JLabel getPW = new JLabel("비밀번호");
   private JLabel checkPW = new JLabel("비밀번호 확인");
   private JLabel getID2 = new JLabel("ID");
   private JLabel getPW2 = new JLabel("비밀번호");
   private JLabel checkPW2 = new JLabel("비밀번호 확인");
   private JTextField getIDTF = new JTextField(12);
   private JPasswordField getPWTF = new JPasswordField(12);
   private JPasswordField checkPWTF = new JPasswordField(12);
   private JTextField getIDTF2 = new JTextField(12);
   private JPasswordField getPWTF2 = new JPasswordField(12);
   private JPasswordField checkPWTF2 = new JPasswordField(12);
   private JButton createAccount = new JButton("계정생성");
   private JButton deleteAccount = new JButton("계정삭제");
   private JTabbedPane fr4aTap = new JTabbedPane();// 탭패널
   private JPanel pa1 = new JPanel();
   private JPanel pa2 = new JPanel();
   private JPanel pa3 = new JPanel();
   private JPanel pa4 = new JPanel();
   JDialog dia = new JDialog(this, "계정관리", false);// 다이아로그
   JLabel diaLa = new JLabel("생성되었습니다.");
   JButton diaBtn = new JButton("확인");// 다이어로그 라벨,버튼들

   ConnectDatabase c1 = new ConnectDatabase();// DB객체 생성

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
      fr4aTap.addTab("계정추가", pa2);
      pa3.add(getID2);
      pa3.add(getIDTF2);
      pa3.add(getPW2);
      pa3.add(getPWTF2);
      pa3.add(checkPW2);
      pa3.add(checkPWTF2);
      pa4.add(pa3);
      pa4.add(deleteAccount);
      fr4aTap.addTab("계정삭제", pa4);
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
      });// 다이어로그버튼을 위한 익명내부클래스
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
   private JLabel getPW = new JLabel("비밀번호");
   private JLabel checkPW = new JLabel("비밀번호 확인");
   private JLabel getID2 = new JLabel("ID");
   private JLabel getPW2 = new JLabel("비밀번호");
   private JLabel checkPW2 = new JLabel("비밀번호 확인");
   private JTextField getIDTF = new JTextField(12);
   private JPasswordField getPWTF = new JPasswordField(12);
   private JPasswordField checkPWTF = new JPasswordField(12);
   private JTextField getIDTF2 = new JTextField(12);
   private JPasswordField getPWTF2 = new JPasswordField(12);
   private JPasswordField checkPWTF2 = new JPasswordField(12);
   private JButton createAccount = new JButton("계정생성");
   private JButton deleteAccount = new JButton("계정삭제");
   private JTabbedPane fr4aTap = new JTabbedPane();// 탭패널
   private JPanel pa1 = new JPanel();
   private JPanel pa2 = new JPanel();
   private JPanel pa3 = new JPanel();
   private JPanel pa4 = new JPanel();
   JDialog dia = new JDialog(this, "계정관리", false);// 다이아로그
   JLabel diaLa = new JLabel("생성되었습니다.");
   JButton diaBtn = new JButton("확인");// 다이어로그 라벨,버튼들

   ConnectDatabase c1 = new ConnectDatabase();// DB객체 생성

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
      fr4aTap.addTab("계정추가", pa2);
      pa3.add(getID2);
      pa3.add(getIDTF2);
      pa3.add(getPW2);
      pa3.add(getPWTF2);
      pa3.add(checkPW2);
      pa3.add(checkPWTF2);
      pa4.add(pa3);
      pa4.add(deleteAccount);
      fr4aTap.addTab("계정삭제", pa4);
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
      });// 다이어로그버튼을 위한 익명내부클래스
   }

   class myListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         if (e.getSource() == createAccount) {
            try {
               if(confirmID(getIDTF.getText())) {
               createAccount ca = new createAccount(getIDTF.getText(), getPWTF.getText(), checkPWTF.getText());
               }
               else {
                  diaLa.setText(" ID를 학번 8자리로 입력해주세요. " );
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
                  diaLa.setText(" ID를 학번 8자리로 입력해주세요. " );
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
         diaLa.setText("    생성되었습니다.    ");
         dia.setVisible(true);
      } else {
         diaLa.setText("<html>비밀번호가 맞지 않습니다.<br/>비밀번호를 다시 확인하세요.</html>");
         dia.setVisible(true);
      }
   }

}// 계정생성 메소드

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
         diaLa.setText("    삭제되었습니다.    ");
         dia.setVisible(true);
      } else if (!a.equals(id)) {
         diaLa.setText("    ID가 맞지않습니다.    ");
         dia.setVisible(true);
      } else if (!b.equals(pw)) {
         diaLa.setText("    비밀번호가 틀립니다.    ");
         dia.setVisible(true);
      } else if (!b.equals(c)) {
         diaLa.setText("<html>비밀번호가 맞지 않습니다.<br/>비밀번호를 다시 확인하세요.</html>");
         dia.setVisible(true);
      }
   }
}