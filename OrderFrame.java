import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.HashMap;
import org.json.simple.JSONObject;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

//��ǰ���� ������
public class OrderFrame extends JFrame{
   JPanel fr3bPa1=new JPanel();
   JPanel fr3bPa2=new JPanel();
   JPanel fr3bPa3=new JPanel();
   JLabel fr3bLa1=new JLabel("��ǰ��ȣ");
   JLabel fr3bLa2=new JLabel("��ǰ����");
   JTextField fr3bTF1=new JTextField(10);
   JTextField fr3bTF2=new JTextField(10);
   JButton fr3bBtn1=new JButton("�ڵ�����");
   JButton fr3bBtn2=new JButton("����");
   JDialog dia=new JDialog(this,"����",false);//���̾Ʒα�
   JLabel diaLa=new JLabel("��ǰ�����߽��ϴ�.");
   JButton diaBtn=new JButton("Ȯ��");//���̾�α� ��,��ư��
   public OrderFrame(){
      setSize(300,200);
      fr3bPa1.setLayout(new GridLayout(2,2,10,10));
      fr3bPa1.add(fr3bLa1);
      fr3bPa1.add(fr3bTF1);
      fr3bPa1.add(fr3bLa2);
      fr3bPa1.add(fr3bTF2);
      fr3bPa2.setLayout(new GridLayout(2,1,10,10));
      fr3bPa2.add(fr3bBtn1);
      fr3bPa2.add(fr3bBtn2);
      fr3bPa2.setLayout(new GridLayout(1,1,10,10));
      fr3bPa3.add(fr3bPa1);
      fr3bPa3.add(fr3bPa2);
      fr3bBtn1.addActionListener(new myListener());
      fr3bBtn2.addActionListener(new myListener());
      add(fr3bPa3);
      dia.setLayout(new FlowLayout());
      dia.setSize(160,120);
      dia.setLocation(75,90);
      dia.add(diaLa);
      dia.add(diaBtn);
      diaBtn.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            dia.setVisible(false);
         }
      });//���̾�α׹�ư�� ���� �͸���Ŭ����
      this.setVisible(true);
   }

   class myListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         if(e.getSource() == fr3bBtn1){
            try {
               autoOrderr.autoOrder();
               diaLa.setText("�ڵ������߽��ϴ�.");
               dia.setVisible(true);
            } catch (SQLException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
         if(e.getSource() == fr3bBtn2){
            try {
               orderr.order(fr3bTF1.getText(),fr3bTF2.getText());
               diaLa.setText("��ǰ�����߽��ϴ�.");
               dia.setVisible(true);
            } catch (SQLException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
      }
   }
}


class smsCallr
{
   public static void smsCall(String a, String b, String c){
       String api_key = "";//apiŰ(http://www.coolsms.co.kr/���� ȸ�������� APIŰ �߱� �޾Ƽ� ����Ұ�)
       String api_secret = "";//api��й�ȣ
       Message coolsms = new Message(api_key, api_secret);
       String item = a;//��ǰ�̸�
       String amount = b;//��ǰ ����
       String sms = c;//������ ��ȣ

       // 4 params(to, from, type, text) are mandatory. must be filled
       HashMap<String, String> params = new HashMap<String, String>();
       params.put("to", sms); // ���Ź�ȣ
       params.put("from", "01085617082"); // �߽Ź�ȣ
       params.put("type", "SMS"); // Message type ( SMS, LMS, MMS, ATA )
       params.put("text", a+"��ǰ "+ b +"�� �ֹ��մϴ�"); // ���ڳ���    
       params.put("app_version", "JAVA SDK v1.2"); // application name and version

       // Optional parameters for your own needs
       // params.put("image", "desert.jpg"); // image for MMS. type must be set as "MMS"
       // params.put("image_encoding", "binary"); // image encoding binary(default), base64 
       // params.put("mode", "test"); // 'test' ���. ������ �߼۵��� ������ ���۳����� 60 �����ڵ�� ��ϴ�. ������ ĳ���� ������ ������ ���� �˴ϴ�.
       // params.put("delay", "10"); // 0~20������ ������ �������� �ð��� �� �� �ֽ��ϴ�.
       // params.put("force_sms", "true"); // Ǫ�� �� �˸��� �̿�ÿ��� ������ SMS�� �߼۵ǵ��� �� �� �ֽ��ϴ�.
       // params.put("refname", ""); // Reference name
       // params.put("country", "KR"); // Korea(KR) Japan(JP) America(USA) China(CN) Default is Korea
       // params.put("sender_key", "5554025sa8e61072frrrd5d4cc2rrrr65e15bb64"); // �˸��� ����� ���� �ʿ��մϴ�. ��û��� : http://www.coolsms.co.kr/AboutAlimTalk
       // params.put("template_code", "C004"); // �˸��� template code �Դϴ�. �ڼ��� ������ http://www.coolsms.co.kr/AboutAlimTalk�� �������ּ���. 
       // params.put("datetime", "20140106153000"); // Format must be(YYYYMMDDHHMISS) 2014 01 06 15 30 00 (2014 Jan 06th 3pm 30 00)
       // params.put("mid", "mymsgid01"); // set message id. Server creates automatically if empty
       // params.put("gid", "mymsg_group_id01"); // set group id. Server creates automatically if empty
       // params.put("subject", "Message Title"); // set msg title for LMS and MMS
       // params.put("charset", "euckr"); // For Korean language, set euckr or utf-8
       // params.put("app_version", "Purplebook 4.1") // ���ø����̼� ����

       try {
         JSONObject obj = (JSONObject) coolsms.send(params);
         System.out.println(obj.toString());
       } catch (CoolsmsException e) {
         System.out.println(e.getMessage());
         System.out.println(e.getCode());
       }
   }
}
class autoOrderr extends smsCallr
{
   public static void autoOrder() throws SQLException
   {
      Connection con = ConnectDatabase.makeConnection();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select distinct p.productname,m.telephone, s.stock"
            +" from producttable p, manufacturertable m, SALESSTOCKTABLE s,EXPIRATIONTABLE e"
            +" where p.MANUNUM=m.MANUFACTURERNUM and p.productnum=s.PRODUCTNUM and s.stock<=5");
      String item=null;
      String sms=null;
      String num="10";
      while(rs.next()){
         item=rs.getString("PRODUCTNAME");
         sms=rs.getString("TELEPHONE");
         smsCall(item,num,sms);
      }
   }
}

class orderr extends smsCallr
{   
   public static void order(String a, String b) throws SQLException
   {
      Connection con = ConnectDatabase.makeConnection();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select distinct p.productname,m.telephone"
      +" from producttable p, manufacturertable m"
      +" where p.MANUNUM=m.MANUFACTURERNUM and p.productnum="+a);
      String item=null;
      String sms=null;
      while(rs.next()){
         item=rs.getString("PRODUCTNAME");
         sms=rs.getString("TELEPHONE");
      }
      smsCall(item,b,sms);
   }
}