import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
 
import javax.swing.JFrame;
public class Afficher extends JFrame
{
  Afficher(String strSql, String port){
    LaResultset laRs = new LaResultset(strSql,port);
    ResultSet rs = laRs.getRs();
    ResultSetTableModel rtm = new ResultSetTableModel( rs);
    
    TablePanel tablePanel = new TablePanel( rtm );
    
    super.setTitle( "Affiche table " );
    super.add( tablePanel, BorderLayout.CENTER );
    super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
    super.setSize( 640, 480 );
    super.setVisible( true );

  }

  
}
