import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

public class GameFrame extends JFrame {
    private Player currentPlayer;
    private PlayerService playerService;
    private GameLogic gameLogic;
    private JButton[] buttons;
    private JLabel lblStatus;

    public GameFrame(Player player) {
        this.currentPlayer = player;
        this.playerService = new PlayerService();
        this.gameLogic = new GameLogic();

        setTitle("Game Window");
        setSize(420, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        lblStatus = new JLabel("Your turn. You are X.", JLabel.CENTER);
        lblStatus.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblStatus, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        buttons = new JButton[9];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 48));
            final int index = i;
            buttons[i].addActionListener(e -> handlePlayerMove(index));
            boardPanel.add(buttons[i]);
        }

        add(boardPanel, BorderLayout.CENTER);
    }

    private void handlePlayerMove(int index) {
        boolean validMove = gameLogic.makeMove(index, 'X');

        if (!validMove) {
            JOptionPane.showMessageDialog(this, "Invalid move!");
            return;
        }

        buttons[index].setText("X");
        buttons[index].setEnabled(false);

        if (gameLogic.checkWinner('X')) {
            finishGame("WIN");
            return;
        }

        if (gameLogic.isDraw()) {
            finishGame("DRAW");
            return;
        }

        int computerIndex = gameLogic.computerMove();
        if (computerIndex >= 0) {
            buttons[computerIndex].setText("O");
            buttons[computerIndex].setEnabled(false);
        }

        if (gameLogic.checkWinner('O')) {
            finishGame("LOSE");
            return;
        }

        if (gameLogic.isDraw()) {
            finishGame("DRAW");
            return;
        }

        lblStatus.setText("Your turn. You are X.");
    }

    private void finishGame(String result) {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setEnabled(false);
        }

        playerService.updateStatistics(currentPlayer, result);
        JOptionPane.showMessageDialog(this, "Game result: " + result);

        Player updatedPlayer = playerService.getPlayerById(currentPlayer.getId());
        if (updatedPlayer == null) {
            updatedPlayer = currentPlayer;
        }

        MainMenuFrame menuFrame = new MainMenuFrame(updatedPlayer);
        menuFrame.setVisible(true);
        this.dispose();
    }
}
