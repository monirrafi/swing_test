import java.sql.*;
import java.util.*;

import javax.swing.*;    
public class TableExample {    
    JFrame f;    
    TableExample() throws SQLException{    
    f=new JFrame();  
    ArrayList<String> listeDept = liste();
    System.out.println(listeDept);
    String data[][] = new String[listeDept.size()][listeDept.size()];

    for(int i=0; i<listeDept.size();i++){
       // ArrayList<String> unListe = listeDept.get(i);
        String str = "";
       //System.out.println(unListe);
        for(int j=0;j<3;j++){
            str = (listeDept).get(i);
            data[i][j]= str;
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
public static ArrayList<String> liste() throws SQLException {
        
    LaResultset laRs = new LaResultset("select * from dept","3306");
    ResultSet rs = laRs.getRs();
   // ArrayList<ArrayList<String>> names = new ArrayList<>();
    ArrayList<String> item = new ArrayList<>();

    while(rs.next()){
        item.add(rs.getString("deptno"));
        item.add(rs.getString("dname"));
        item.add(rs.getString("loc"));
        //names.add(item);
    }
    return item;
}

public static void main(String[] args) throws SQLException {    
    new TableExample();  
    /*  
    String data[][]={ {"101","Amit","670000"},    
{"102","Jai","780000"},    
{"101","Sachin","700000"}};   
    System.out.println(data[0][1]);*/
}         


}  