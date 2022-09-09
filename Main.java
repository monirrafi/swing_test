import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
import javax.swing.JFrame;
 
 
public class Main
{
  public static void main(String[] args) throws SQLException
  {
    LaResultset laRs = new LaResultset("SELECT * FROM EMP");
    ResultSetTableModel rtm = new ResultSetTableModel( laRs.getRs() );
    
    TablePanel tablePanel = new TablePanel( rtm );
    
    JFrame mainFrame = new JFrame( "Affiche table " );
    mainFrame.add( tablePanel, BorderLayout.CENTER );
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
    mainFrame.setSize( 640, 480 );
    mainFrame.setVisible( true );
  }
 
  
}
