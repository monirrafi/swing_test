import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;    
public class ComboBoxExample extends JFrame{   
    static JComboBox cb;
    ComboBoxExample() throws SQLException{    
        super.setTitle("ComboBox Example"); 
        String[] array = liste().toArray(new String[liste().size()]);   
        cb=new JComboBox(array);    
        cb.setBounds(50, 50,90,20);    
        super.add(cb);        
        super.setLayout(null);    
        super.setSize(400,500);    
        super.setVisible(true);  

        //cb.addActionListener(this::afficher);

        
        cb.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                afficher();
                
            }  
        });
        
    }    
    
    //String country[]={"India","Aus","U.S.A","England","Newzealand"};  A
    public static ArrayList<String> liste() throws SQLException {
         /*   
        LaResultset laRs1 = new LaResultset("select * from emp");
        ResultSet rs1 = laRs1.getRs();
        int size=0;
        while(rs1.next()){
            size++;
        }*/
        
        LaResultset laRs = new LaResultset("select * from emp");
        ResultSet rs = laRs.getRs();
        //String[] names = new String[size];
        ArrayList<String> names = new ArrayList<>();
        //int i=0;
        while(rs.next()){
            names.add(rs.getString("ename"));
           // i++;
        }
        return names;
    }
 
public void afficher() {
    JOptionPane.showMessageDialog(null, cb.getSelectedItem());
    
}
public static void main(String[] args) throws SQLException {    
    new ComboBoxExample();         
}    
}   