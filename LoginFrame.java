import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class LoginFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private PlayerService playerService;

    public LoginFrame() {
        playerService = new PlayerService();

        setTitle("Login Window");
        setSize(400, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("Tic-Tac-Toe Login", JLabel.CENTER);
        add(lblTitle, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        panel.add(new JLabel("Username"));
        txtUsername = new JTextField();
        panel.add(txtUsername);

        panel.add(new JLabel("Password"));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        panel.add(new JLabel(""));
        btnLogin = new JButton("Login");
        panel.add(btnLogin);

        add(panel, BorderLayout.CENTER);
        getRootPane().setDefaultButton(btnLogin);

        btnLogin.addActionListener(e -> handleLogin());
    }

    private void handleLogin() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        Player player = playerService.login(username, password);

        if (player != null) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            MainMenuFrame menuFrame = new MainMenuFrame(player);
            menuFrame.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password!");
        }
    }
}
