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
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;



/*��ǰ �߰�/���� ������*/
public class ProductFrame extends JFrame{
   JPanel adPa1=new JPanel();//��ǰ�߰��Ҷ� �ؽ�Ʈ�ʵ�,�󺧵���
   JPanel adPa2=new JPanel();//adPa1�� ��ư�߰��� ���гο� ������
   JPanel adPa3=new JPanel();//��ǰ�����Ҷ� �ؽ�Ʈ�ʵ�,�󺧵���
   JPanel adPa4=new JPanel();//adPa2�� ��ư�߰��� ���гο� ������
   JLabel la1=new JLabel("��ǰ��ȣ");//���⼭����
   JLabel la2=new JLabel("��ǰ��");
   JLabel la3=new JLabel("�ǸŰ���");
   JLabel la4=new JLabel("����");
   JLabel la5=new JLabel("������(��ȣ)");
   JLabel la6=new JLabel("��ǰ����");
   JTextField t1=new JTextField(10);
   JTextField t2=new JTextField(10);
   JTextField t3=new JTextField(10);
   JTextField t4=new JTextField(10);
   JTextField t5=new JTextField(10);
   JTextField t6=new JTextField(10);
   JButton adPaBtn1=new JButton("�߰�");//��������� ��ǰ�߰� �ǿ� ����
   JButton adPaBtn2=new JButton("����");//���⼭����
   JLabel ta1=new JLabel("��ǰ��ȣ");
   JTextField tt1=new JTextField(10);//��������� ��ǰ���� �ǿ� ����
   JTabbedPane adTab1=new JTabbedPane();//��ǰ�߰�/����ȭ�鿡�� ��ȭ��
   JDialog dia=new JDialog(this,"�߰�",false);//���̾Ʒα�
   JLabel diaLa=new JLabel("�߰��Ǿ����ϴ�.");
   JButton diaBtn=new JButton("Ȯ��");//���̾�α� ��,��ư��
   public ProductFrame()
   {
      setSize(300,300);
      adPa1.setLayout(new GridLayout(6,2,5,5));
      adPa2.setLayout(new FlowLayout());
      adPa1.setSize(300,250);
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
      adPaBtn1.setSize(100,30);
      adPa2.add(adPaBtn1);
      adTab1.addTab("��ǰ�߰�",adPa2);//���гο� �г��߰�
      adTab1.setSize(300,250);
      getContentPane().add(adTab1);
      adPa3.setLayout(new GridLayout(1,2,5,15));
      adPa3.setSize(300,200);
      adPa3.add(ta1);
      adPa3.add(tt1);
      adPa4.setLayout(new FlowLayout());
      adPaBtn2.setSize(100,30);
      adPa4.add(adPa3);
      adPa4.add(adPaBtn2);
      adTab1.addTab("��ǰ����",adPa4);
      this.setVisible(true);
      adPaBtn1.addActionListener(new myListener());
      adPaBtn2.addActionListener(new myListener());
      dia.getContentPane().setLayout(new FlowLayout());
      dia.setSize(160,120);
      dia.setLocation(75,90);
      dia.getContentPane().add(diaLa);
      dia.getContentPane().add(diaBtn);
      diaBtn.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            dia.setVisible(false);
         }
      });//���̾�α׹�ư�� ���� �͸���Ŭ����
   }
   
   //��ǰ�߰� ��ư ����


   class myListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         if(e.getSource() == adPaBtn1){
            try {
               productAddr.productAdd(t1.getText(),t2.getText(),t3.getText(),t4.getText(),t5.getText(),t6.getText());
               diaLa.setText("�߰��Ǿ����ϴ�.");
               dia.setVisible(true);
            } catch (SQLException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
         if(e.getSource() == adPaBtn2){
            try {
               productDelr.productDel(tt1.getText());
               diaLa.setText("���ŵǾ����ϴ�.");
               dia.setVisible(true);
            } catch (SQLException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
      }
   }
}


class productAddr
{
   public static void productAdd (String a, String b, String c, String d, String e, String f) throws SQLException{
      Connection con = ConnectDatabase.makeConnection();
      Statement stmt = con.createStatement();
      String sql = "INSERT INTO producttable VALUES("+a+",'"+b+"',"+c+","+d+","+e+",'"+f+"')";
      String sql2 = "INSERT INTO SALESSTOCKTABLE VALUES("+a+",0,0,0,0)";
      ResultSet rs = stmt.executeQuery(sql);
      ResultSet rs2 = stmt.executeQuery(sql2);
   }
}
class productDelr
{
   public static void productDel (String a) throws SQLException{
      Connection con = ConnectDatabase.makeConnection();
      Statement stmt = con.createStatement();
      String sql = "delete from producttable where productnum="+a;
      String sql2 = "delete from SALESSTOCKTABLE where productnum="+a;
      ResultSet rs = stmt.executeQuery(sql);
      ResultSet rs2 = stmt.executeQuery(sql2);
   }
}   