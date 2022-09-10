import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class FirstExample extends JFrame{
  // private JPanel pane =new JPanel();
   String[] entete = {"No","Nom","Location"};
   static JPanel panePrincipal = new JPanel(new GridLayout(3,1));


    public FirstExample() {
      JPanel pane = new JPanel();
      for(String str:entete){

      JLabel labelNo = new JLabel(str);
      JTextField no = new JTextField(20);
      labelNo.setLabelFor(no);
      JPanel paneNo = new JPanel();
      paneNo.add(labelNo);
      paneNo.add(no);
      panePrincipal.add(paneNo);
      pane.add(panePrincipal);
      }
      super.add(pane);
      super.setTitle("title");
      super.setSize(new Dimension(800,600));
      super.setVisible(true);
   }


   public static void main(String[] args) throws SQLException {
      new FirstExample();
}
}