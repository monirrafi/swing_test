import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;    
public class ComboBoxExample extends JFrame{    
    static JComboBox cb;
ComboBoxExample(){    
    super.setTitle("ComboBox Example");    
    String country[]={"India","Aus","U.S.A","England","Newzealand"};        
    cb=new JComboBox(country);    
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
public void afficher() {
    JOptionPane.showMessageDialog(null, cb.getSelectedItem());
    
}
public static void main(String[] args) {    
    new ComboBoxExample();         
}    
}   