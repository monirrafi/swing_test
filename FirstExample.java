import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

public class FirstExample extends JFrame{
  // private JPanel pane =new JPanel();
   String[] entete = {"No","Nom","Location"};
   static JPanel panePrincipal = new JPanel(new GridLayout(4,1));
   static JPanel paneNo;

    public FirstExample() {
      JPanel pane = new JPanel();
      
      for(int i=0;i<entete.length;i++){

      JLabel labelNo = new JLabel(entete[i]);
      labelNo.setName("label"+entete[i]);
      JTextField no = new JTextField(20);
      no.setName("field"+entete[i]);
      //System.out.println(no.getName());

      labelNo.setLabelFor(no);
      paneNo = new JPanel();
      paneNo.add(labelNo);
      paneNo.add(no);
      panePrincipal.add(paneNo);
      
      }
      JButton btn = new JButton("Mise A Jour");
      btn.addActionListener(new ActionListener(){

         @Override
         public void actionPerformed(ActionEvent e) {
            try {
               listeDesFields();
               Afficher aff = new Afficher("SELECT * FROM DEPT", "3306");
               
            } catch (SQLException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }            
         }
         
      });
      panePrincipal.add(btn);
      pane.add(panePrincipal);
      super.add(pane);
      super.setTitle("title");
      super.setSize(new Dimension(800,600));
      super.setVisible(true);
   }
   public void listeDesFields() throws SQLException {
      ArrayList<JTextField> liste = new ArrayList<>();
      for(Component cmp:panePrincipal.getComponents()){
         
         for(Component cm:((Container) cmp).getComponents()){
            String nom =cm.getName().substring(0,5);
           if((nom).equals("field")){
             
               liste.add((JTextField)cm);
            }
         }
      }
         LaResultset laRs = new LaResultset("SELECT * FROM DEPT WHERE DEPTNO = " + liste.get(0).getText() ,"3306");
         ResultSet rs = laRs.getRs();
   
         while(rs.next()){
            rs.updateString("dname", liste.get(1).getText());
            rs.updateString("loc", liste.get(2).getText());
            rs.updateRow();

          }
        for(JTextField jtxt:liste){
         jtxt.setText("");
        }
   }
   



   public static void main(String[] args) throws SQLException {
      ArrayList<String> newValeurs = new ArrayList<>(){{add("");add("Marwa");add("Agadir");}};

      LaResultset laRs = new LaResultset("SELECT * FROM DEPT WHERE DEPTNO=10","3306");
      ResultSet rs = laRs.getRs();
      ResultSetTableModel rtm = new ResultSetTableModel( rs);
      while(rs.next()){

      for(int i=1;i<newValeurs.size();i++){
         String colName = rtm.getColumnName(i),nouveau = newValeurs.get(i);
         rs.updateString(colName,nouveau);
      }
         rs.updateRow();

       }
      //FirstExample ft = new FirstExample();
      Afficher aff = new Afficher("SELECT * FROM DEPT WHERE DEPTNO=10","3306");
      

      
}
}