import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Main extends JFrame {
  public Main() throws HeadlessException {
    setSize(400, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new FlowLayout(FlowLayout.LEFT));

    JLabel usernameLabel = new JLabel("Username: ");
    JLabel passwordLabel = new JLabel("Password: ");
    JTextField usernameField = new JTextField(20);
    JPasswordField passwordField = new JPasswordField(20);

    usernameLabel.setDisplayedMnemonic(KeyEvent.VK_U);
    usernameLabel.setLabelFor(usernameField);
    passwordLabel.setDisplayedMnemonic(KeyEvent.VK_P);
    passwordLabel.setLabelFor(passwordField);
    JPanel paneUser = new JPanel();
    paneUser.add(usernameLabel);
    paneUser.add(usernameField);
    JPanel panePword = new JPanel();
    panePword.add(passwordLabel);
    panePword.add(passwordField);

    super.add(paneUser);
    super.add(panePword);
  }

  public static void main(String[] args) {
    new Main().setVisible(true);
  }
}
 