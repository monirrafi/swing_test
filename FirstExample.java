import java.sql.*;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class FirstExample {


    public static void main(String[] args) throws SQLException {
      // Open a connection
       LaResultset laRs = new LaResultset("SELECT * FROM dept");
       ResultSet rs = laRs.getRs();
      JTextArea sortie = new JTextArea(20,40);
      sortie.append("ID\tNom\t\tLocation\n");
      while (rs.next()) {
         sortie.append(String.valueOf(rs.getInt("deptno"))+"\t"+rs.getString("dname")+"\t\t"+rs.getString("loc")+"\n");
      }
      JOptionPane.showMessageDialog(null, sortie, "title", JOptionPane.PLAIN_MESSAGE);
   }
}