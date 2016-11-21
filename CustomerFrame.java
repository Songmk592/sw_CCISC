import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.swing.*;


//거래처 추가, 삭제 화면
public class CustomerFrame extends JFrame{
   private JLabel customerName = new JLabel("이름");//추가탭에 들어갈 라벨
   private JLabel customerPhone = new JLabel("전화번호");
   private JLabel customerCompanyNum = new JLabel("거래회사(번호)");
   private JLabel customerCompany = new JLabel("거래회사");
   private JLabel customerName2 = new JLabel("이름");//삭제탭에 들어갈 라벨
   private JTextField customerNameTF = new JTextField(12);
   private JTextField customerPhoneTF = new JTextField(12);
   private JTextField customerCompanyNumTF = new JTextField(12);
   private JTextField customerCompanyTF = new JTextField(12);
   private JTextField customerNameTF2 = new JTextField(12);
   private JButton customerAdd = new JButton("추가");
   private JButton customerDel = new JButton("삭제");
   private JPanel pa1 = new JPanel();//추가탭에서의 라벨과 텍스트필드를 담을 패널
   private JPanel pa2 = new JPanel();
   private JPanel pa3 = new JPanel();//삭제탭에서의 라벨과 텍스트필드를 담을 패널
   private JPanel pa4 = new JPanel();
   private JTabbedPane tabPa1=new JTabbedPane();//탭패널
   JDialog dia=new JDialog(this,"거래처관리",false);//다이아로그
   JLabel diaLa=new JLabel("추가되었습니다.");
   JButton diaBtn=new JButton("확인");//다이어로그 라벨,버튼들
   public CustomerFrame(){
      setSize(300,210);
      pa1.setLayout(new GridLayout(4,2,5,5));
      pa2.setLayout(new FlowLayout());
      pa2.setSize(300,180);
      pa3.setLayout(new GridLayout(1,2,5,5));
      pa4.setLayout(new FlowLayout());
      pa4.setSize(300,180);
      pa1.add(customerName);
      pa1.add(customerNameTF);
      pa1.add(customerPhone);
      pa1.add(customerPhoneTF);
      pa1.add(customerCompanyNum);
      pa1.add(customerCompanyNumTF);
      pa1.add(customerCompany);
      pa1.add(customerCompanyTF);
      pa2.setSize(300,160);
      pa2.add(pa1);
      pa2.add(customerAdd);
      pa3.add(customerName2);
      pa3.add(customerNameTF2);
      pa4.add(pa3);
      pa4.add(customerDel);
      tabPa1.setSize(300,200);
      tabPa1.addTab("추가",pa2);
      tabPa1.addTab("삭제",pa4);
      add(tabPa1);
      customerAdd.addActionListener(new myListener());
      customerDel.addActionListener(new myListener());
      this.setVisible(true);
      dia.setLayout(new FlowLayout());
      dia.setSize(160,120);
      dia.setLocation(400,400);
      dia.add(diaLa);
      dia.add(diaBtn);
      diaBtn.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            dia.setVisible(false);
         }
      });//다이어로그버튼을 위한 익명내부클래스
   }
   class myListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         if(e.getSource() == customerAdd){
            try {
               customerAddr.customerAdd(customerNameTF.getText(),customerPhoneTF.getText(),customerCompanyNumTF.getText(),customerCompanyTF.getText());
               diaLa.setText("추가되었습니다.");
               dia.setVisible(true);
            } catch (SQLException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
         if(e.getSource() == customerDel){
            try {
               customerDelr.customerDel(customerNameTF2.getText());
               diaLa.setText("삭제되었습니다.");
               dia.setVisible(true);
            } catch (SQLException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }      
      }
   }
}


class customerAddr
{
   public static void customerAdd(String a, String b, String c, String d) throws SQLException
   {
      Connection con = ConnectDatabase.makeConnection();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("insert into MANUFACTURERTABLE values"
                              +"('"+c+"','"+d+"','"+a+"','"+b+"')");
   }
}

class customerDelr
{
   public static void customerDel(String a) throws SQLException
   {
      Connection con = ConnectDatabase.makeConnection();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("delete from MANUFACTURERTABLE where PERSONINCHARGER='"+a+"'");
   }
}