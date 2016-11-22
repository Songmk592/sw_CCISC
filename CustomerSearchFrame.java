import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//�ŷ�ó��ȸ ȭ��
public class CustomerSearchFrame extends JFrame{
   static String head[]={"�̸�","��ȭ��ȣ","�ŷ�ȸ��(��ȣ)","�ŷ�ȸ��"};//���̺� ��Ű��
   static String content[][]={};//���̺�����
   static DefaultTableModel model=new DefaultTableModel(content,head);//���̺� ������ ���� ������
   static JTable customerSearch = new JTable(model);//���̺�����
   JScrollPane customerSearchScrPa=new JScrollPane(customerSearch);//���̺��� ��ũ���гο� ����
   JPanel pa1=new JPanel();//���Ĺ�ư���� ���� �г�
   JPanel pa2=new JPanel();//���Ĺ�ư�� ��ũ���г��� ���� �г�
   JButton customerSearchBtn=new JButton("��ȸ");//�ŷ�ó ��ȸ��ư
   JButton customerModBtn=new JButton("�ŷ�ó �߰�/����");//�ŷ�ó ���� ���� ��ư
   public CustomerSearchFrame(){
      setSize(600,330);
      setLayout(null);
      pa1.setLayout(new FlowLayout());
      pa2.setLayout(new GridLayout(2,1,20,15));
      pa2.setSize(100,40);
      pa2.add(customerSearchBtn);
      pa2.add(customerModBtn);
      customerSearch.setPreferredScrollableViewportSize(new Dimension(400,200));//���̺� ����� ����
      pa1.setBounds(10,10,400,250);
      pa1.add(customerSearchScrPa);
      pa1.add(pa2);
      pa1.setBounds(10,10,590,340);
      add(pa1);
      this.setVisible(true);
      customerSearchBtn.addActionListener(new myListener());
      customerModBtn.addActionListener(new myListener());
   }
   
   class myListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         if(e.getSource() == customerSearchBtn){
            try {
               customerSearchr.customerSearch();
            } catch (SQLException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
         if(e.getSource() == customerModBtn){
            CustomerFrame fr = new CustomerFrame();
         }
      }
   }
}


class customerSearchr extends CustomerSearchFrame
{
   public static void customerSearch() throws SQLException{
      Connection con = ConnectDatabase.makeConnection();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select PERSONINCHARGER, telephone,MANUFACTURERNUM,MANUFACTURERNAME"
                              +" from manufacturertable");
      for(int i=customerSearch.getRowCount();i>0;i--){
         model.removeRow(i-1);
      }
      String arr[]=new String[4];
      while(rs.next()){
         arr[0]=rs.getString("PERSONINCHARGER");
         arr[1]=rs.getString("telephone");
         arr[2]=rs.getString("MANUFACTURERNUM");
         arr[3]=rs.getString("MANUFACTURERNAME");
         model.addRow(arr);//arr�� ������ ���̺��� ä���.
      }
   }
}