import java.sql.*;
import java.util.*;

import javax.swing.*;    
public class TableExample {    
    JFrame f;    
    TableExample() throws SQLException{    
    f=new JFrame();  
    ArrayList<ArrayList<String>> listeDept = liste();
    String data[][] = new String[listeDept.size()][];
    for(int i=0; i<listeDept.size();i++){
        for(int j=0;j<3;j++){
            data[i][j]= listeDept.get(i);
        }

    }
    /*    
    String data[][]={ {"101","Amit","670000"},    
                          {"102","Jai","780000"},    
                          {"101","Sachin","700000"}};    */
    String column[]={"ID","NAME","Location"};         
    JTable jt=new JTable(data,column);    
    jt.setBounds(30,40,200,300);          
    JScrollPane sp=new JScrollPane(jt);    
    f.add(sp);          
    f.setSize(300,400);    
    f.setVisible(true);    
}  
public static ArrayList<ArrayList<String>> liste() throws SQLException {
        
    LaResultset laRs = new LaResultset("select * from dept");
    ResultSet rs = laRs.getRs();
    ArrayList<ArrayList<String>> names = new ArrayList<>();
    ArrayList<String> item = new ArrayList<>();

    while(rs.next()){
        item.add(rs.getString("deptno"));
        item.add(rs.getString("dname"));
        item.add(rs.getString("loc"));
        names.add(item);
    }
    return names;
}

public static void main(String[] args) throws SQLException {    
    new TableExample();    
}    
}  