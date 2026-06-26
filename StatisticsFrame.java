import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class StatisticsFrame extends JFrame {
    private Player currentPlayer;
    private PlayerService playerService;

    public StatisticsFrame(Player player) {
        this.currentPlayer = player;
        this.playerService = new PlayerService();

        Player latestPlayer = playerService.getPlayerById(currentPlayer.getId());
        if (latestPlayer != null) {
            currentPlayer = latestPlayer;
        }

        setTitle("My Statistics Window");
        setSize(360, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("My Statistics", JLabel.CENTER);
        add(lblTitle, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        panel.add(new JLabel("Username"));
        panel.add(new JLabel(currentPlayer.getUsername()));

        panel.add(new JLabel("Wins"));
        panel.add(new JLabel(String.valueOf(currentPlayer.getWins())));

        panel.add(new JLabel("Losses"));
        panel.add(new JLabel(String.valueOf(currentPlayer.getLosses())));

        panel.add(new JLabel("Draws"));
        panel.add(new JLabel(String.valueOf(currentPlayer.getDraws())));

        panel.add(new JLabel("Score"));
        panel.add(new JLabel(String.valueOf(currentPlayer.getScore())));

        add(panel, BorderLayout.CENTER);

        JButton btnClose = new JButton("Back to Main Menu");
        btnClose.addActionListener(e -> this.dispose());
        add(btnClose, BorderLayout.SOUTH);
    }
}
