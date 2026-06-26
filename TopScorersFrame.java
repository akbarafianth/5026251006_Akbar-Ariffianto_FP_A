import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.util.ArrayList;

public class TopScorersFrame extends JFrame {
    private JTable table;
    private PlayerService playerService;

    public TopScorersFrame() {
        playerService = new PlayerService();

        setTitle("Top 5 Scorers Window");
        setSize(560, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columns = {"Username", "Wins", "Losses", "Draws", "Score"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        ArrayList<Player> players = playerService.getTopFiveScorers();
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            Object[] row = {
                    player.getUsername(),
                    player.getWins(),
                    player.getLosses(),
                    player.getDraws(),
                    player.getScore()
            };
            model.addRow(row);
        }

        table = new JTable(model);
        table.setEnabled(false);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton btnClose = new JButton("Back to Main Menu");
        btnClose.addActionListener(e -> this.dispose());
        bottomPanel.add(btnClose);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
