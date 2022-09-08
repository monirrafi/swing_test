import java.sql.*;

public class LaResultset {
    private ResultSet rs=null;
    public LaResultset(String strSql){ 
    final String DB_URL = "jdbc:mysql://localhost:4000/officecenter";
    final String USER = "root";
    final String PASS = "";
    final String QUERY = strSql ;
    try{
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
         rs = stmt.executeQuery(QUERY);

         // Extract data from result set
      } catch (SQLException e) {
         e.printStackTrace();
      }
}
    public ResultSet getRs() {
        return rs;
    }
    public void setRs(ResultSet rs) {
        this.rs = rs;
    }


}
