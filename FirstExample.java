import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

public class FirstExample extends JFrame implements ajouterEcouteur{

//======================= Declaration ======================================================

   private String strSql;
   private String port;
   private LaResultset laRs;
   private ResultSet rs;
   private ResultSetTableModel rtm;

   static JPanel pane;
   static JPanel panePrincipal;
   static JPanel paneNo;
   static JButton btnQuitter = new JButton("Quitter");
   static JButton btnAjouter = new JButton("Ajouter");
   static JButton btnSuprimer = new JButton("Suprimer");
   static JButton btnMAJ = new JButton("Mise A Jour");
   static ArrayList<JTextField> listeChamps;


   public FirstExample(String strSql, String port) {
      this.strSql=strSql;
      this.port=port;
      laRs = new LaResultset(strSql,port);
      rs = laRs.getRs();
      rtm = new ResultSetTableModel(rs);
      this.cliquer();
   }
	public static int menuGeneral() {
		String contenu = "1-Afficher\n2-Ajouter\n3-suprimer\n4-modifier\n5-Terminer\n";
		contenu += "Entrez votre choix parmis[1-5] : ";
		return Integer
				.parseInt(JOptionPane.showInputDialog(null, contenu, "MENU ", JOptionPane.PLAIN_MESSAGE));
	}
   public void affichage() {
      JOptionPane.showMessageDialog(null, new TablePanel(rtm), "Affichage de la table", JOptionPane.PLAIN_MESSAGE);
      
   }

   public void intefaceSaisir(ArrayList<String> listeField) {
      panePrincipal = new JPanel(new GridLayout(rtm.getColumnCount(),1));
      pane = new JPanel(new GridLayout(2,1));
      listeChamps = new ArrayList<>();
      for(int i=0;i<rtm.getColumnCount();i++){

         JLabel labelNo = new JLabel(rtm.getColumnName(i));
         labelNo.setBounds(0, 0, 200, 20);
         JTextField no = new JTextField(20);
         listeChamps.add(no);
         no.setBounds(0, 0, 200, 20);;
         labelNo.setLabelFor(no);
         paneNo = new JPanel();
         paneNo.add(labelNo);
         paneNo.add(no);
         panePrincipal.add(paneNo);
      }
      for(int i=0;i<listeField.size();i++){
         listeChamps.get(i).setText(listeField.get(i));
      }
/*
      JPanel paneButtons = new JPanel();
      paneButtons.add(btnAjouter);
      paneButtons.add(btnSuprimer);
      paneButtons.add(btnMAJ);
      paneButtons.add(btnQuitter);*/
      pane.add(panePrincipal);
      //pane.add(paneButtons);
      JOptionPane.showMessageDialog(null, pane, "Saisir", JOptionPane.PLAIN_MESSAGE);
   }

   public static void afficherMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "MESSAGES", JOptionPane.PLAIN_MESSAGE);
	}


   public ArrayList<String> rechercheRow(String deptNO) {
      ArrayList<String> existeListe = new ArrayList<>();
      int rep=-1;
      for(int i=0;i<rtm.getColumnCount();i++){
         for(int j=0;j<rtm.getRowCount();j++){
            String val = String.valueOf(rtm.getValueAt(j,i));
            if(val.equals(deptNO) ){
               rep=j;
               break;
               
            }

         }
      }
      if(rep!=-1){
         for(int i=0;i<rtm.getColumnCount();i++){
            if(rtm.getValueAt(rep,i) instanceof Integer){
              existeListe.add(String.valueOf((Integer)rtm.getValueAt(rep,i)));
            }else {
              existeListe.add((String) rtm.getValueAt(rep,i)); 
            }
         }
      }else{
         existeListe.add(String.valueOf(rep));
      } 
      return existeListe;
   }
   public void suprimerRow() throws SQLException {
      String deptNo = JOptionPane.showInputDialog(null, "Entrez le departement");
      if(rechercheRow(deptNo).get(0).equals("-1")){
         afficherMessage("le departement "+ deptNo +" n'existe pas!");
      }else{
         rs.deleteRow();
         affichage();
      }
      
   }

   public void ajouterRow() throws SQLException {
      String deptNo = JOptionPane.showInputDialog(null, "Entrez le departement");
      if(!rechercheRow(deptNo).get(0).equals("-1")){
         afficherMessage("le departement "+ deptNo +" existe deja!");
      }else{
         //ArrayList<String> liste = 
         intefaceSaisir(new ArrayList<>(){{add(deptNo);}});
         rs.last();
         rs.moveToInsertRow();
         for(int i=0;i<listeChamps.size();i++){
            String colName = rtm.getColumnName(i),nouveau = listeChamps.get(i).getText();
            rs.updateString(colName,nouveau);
         }
         rs.insertRow();
         rs.beforeFirst();
         affichage();
      }
      
   }

   public void modifierRow() throws SQLException {
      String deptNo = JOptionPane.showInputDialog(null, "Entrez le departement");
      if(rechercheRow(deptNo).get(0).equals("-1")){
         afficherMessage("le departement "+ deptNo +" n'existe pas!");
      }else{
         intefaceSaisir(rechercheRow(deptNo));
         for(int i=0;i<listeChamps.size();i++){
            String colName = rtm.getColumnName(i),nouveau = listeChamps.get(i).getText();
            rs.updateString(colName,nouveau);
         }
         rs.updateRow();
         rs.beforeFirst();
         affichage();
      }
      
   }
   public void intefaceModifier() {
      String deptNo = JOptionPane.showInputDialog(null, "Entrez le departement");
      strSql = strSql + " WHERE DEPTNO = " + deptNo;
      FirstExample  ft = new FirstExample(strSql, port);
      
      panePrincipal = new JPanel(new GridLayout(rtm.getColumnCount(),1));
      pane = new JPanel(new GridLayout(2,1));
      
      for(int i=0;i<rtm.getColumnCount();i++){
         for(int j=0;j<rtm.getRowCount();j++){
      
         JLabel labelNo = new JLabel(rtm.getColumnName(i));
         labelNo.setBounds(0, 0, 200, 20);
         JTextField no = new JTextField(20);
            if(rtm.getValueAt(i, j) instanceof Integer){
               no.setText(String.valueOf((Integer) rtm.getValueAt(i, j)));

            }else if(rtm.getValueAt(i, j) instanceof String){
               no.setText((String) rtm.getValueAt(i, j));
            }
         
         no.setBounds(0, 0, 200, 20);;
         labelNo.setLabelFor(no);
         paneNo = new JPanel();
         paneNo.add(labelNo);
         paneNo.add(no);
         panePrincipal.add(paneNo);
         }

      }
      JPanel paneButtons = new JPanel();
      paneButtons.add(btnAjouter);
      paneButtons.add(btnSuprimer);
      paneButtons.add(btnMAJ);
      paneButtons.add(btnQuitter);
      pane.add(panePrincipal);
      pane.add(paneButtons);
      JOptionPane.showMessageDialog(null, pane, "Saisir", JOptionPane.PLAIN_MESSAGE);
   }

   public void actionBtns(ActionEvent e) {
      if(e.getSource() == btnQuitter){
         System.exit(0);
      }else if(e.getSource() == btnMAJ){
         affichage();

      }
      
   }
   @Override
   public void cliquer() {
      btnQuitter.addActionListener(this::actionBtns);
      btnMAJ.addActionListener(this::actionBtns);
      
   }

   public static void main(String[] args) throws SQLException {

      FirstExample ft = new FirstExample("SELECT * FROM EMP", "4000");
      int choix;
		
		do {
			choix = menuGeneral();
			switch (choix) {
				case 1:
					ft.affichage();
					break;
				case 2:
               ft.ajouterRow();
               break;
				case 3:
               ft.suprimerRow();
               break;
				case 4:
					ft.modifierRow();
					break;
				case 5:
               System.exit(0);
					break;
				default:
					JOptionPane.showMessageDialog(null,"Choix invalide. Les option sont [1-5] !");
					break;
			}
		} while (choix != 5);      
      //ft.intefaceSaisir();
      //ft.Affichage();
      

      
}
}