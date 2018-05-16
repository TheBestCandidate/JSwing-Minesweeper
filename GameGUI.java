import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI extends JFrame{

    private GameBoard gameBoard;

    private Dimension screenSize;

    private JButton startButton;

    private JPanel boardPanel;
    private JPanel boardLayoutPanel;
    private JPanel settingsPanel;
    private JPanel informationPanel;

    private static JLabel gameStatusInfo;
    private JLabel boardWidthLabel;
    private JLabel boardHeightLabel;
    private JLabel difficultyLevelLabel;

    private JComboBox<Integer> boardWidthBox;
    private JComboBox<Integer> boardHeightBox;
    private JComboBox<String> difficultyLevelBox;

    private JSplitPane topSplitPane;
    private JSplitPane bottomSplitPane;


    public GameGUI(){
        setGameWindow();
    }

    public void setGameWindow(){
        setTitle("Minesweeper");
        setLayout(new GridBagLayout());
        setWindowLocation();
        createSettingsPanel();

        boardLayoutPanel = new JPanel(new GridBagLayout());

        gameStatusInfo = new JLabel();
        informationPanel = new JPanel();
        informationPanel.add(gameStatusInfo);

        topSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, settingsPanel, boardLayoutPanel);
        topSplitPane.setDividerSize(0);
        add(topSplitPane);

        bottomSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topSplitPane, informationPanel);
        bottomSplitPane.setDividerSize(0);
        add(bottomSplitPane);


        pack();
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void drawGameBoard(GameBoard gameBoard){
        boardLayoutPanel.removeAll();
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(gameBoard.getBoardHeight(), gameBoard.getBoardWidth()));
        for (int i = 0; i< gameBoard.getBoardHeight(); i++) {
            for (int j = 0; j < gameBoard.getBoardWidth(); j++) {
                boardPanel.add(gameBoard.getButtonBoard()[i][j]);
            }
        }
        boardLayoutPanel.add(boardPanel);
        setGameStatusInfo("Game running...", Color.black);
        pack();
    }

    private void setWindowLocation(){
        screenSize = getToolkit().getScreenSize();
        int windowVerticalPos = (int)screenSize.getHeight()/2-300;
        int windowHorizontalPos = (int)screenSize.getWidth()/2-300;
        setLocation(windowHorizontalPos,windowVerticalPos);
    }

    private void createSettingsPanel(){
        settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.LINE_AXIS));

        settingsPanel.add(Box.createRigidArea(new Dimension(10,50)));

        boardWidthLabel = new JLabel();
        boardWidthLabel.setText("Board width: ");
        boardWidthLabel.setPreferredSize(new Dimension(80,30));
        settingsPanel.add(boardWidthLabel);

        settingsPanel.add(Box.createRigidArea(new Dimension(10,50)));

        boardWidthBox = new JComboBox<>();
        boardWidthBox.addItem(10);
        boardWidthBox.addItem(15);
        boardWidthBox.addItem(20);
        boardWidthBox.setPreferredSize(new Dimension(60,30));
        boardWidthBox.setMaximumSize(new Dimension(60,30));
        settingsPanel.add(boardWidthBox);

        settingsPanel.add(Box.createRigidArea(new Dimension(30,50)));

        boardHeightLabel = new JLabel();
        boardHeightLabel.setText("Board height: ");
        boardHeightLabel.setPreferredSize(new Dimension(80,30));
        settingsPanel.add(boardHeightLabel);

        settingsPanel.add(Box.createRigidArea(new Dimension(10,50)));

        boardHeightBox = new JComboBox<>();
        boardHeightBox.addItem(10);
        boardHeightBox.addItem(15);
        boardHeightBox.addItem(20);
        boardHeightBox.setPreferredSize(new Dimension(60,30));
        boardHeightBox.setMaximumSize(new Dimension(60,30));
        settingsPanel.add(boardHeightBox);

        settingsPanel.add(Box.createRigidArea(new Dimension(30,50)));

        difficultyLevelLabel = new JLabel();
        difficultyLevelLabel.setText("Difficulty level: ");
        difficultyLevelLabel.setPreferredSize(new Dimension(90,30));
        settingsPanel.add(difficultyLevelLabel);

        settingsPanel.add(Box.createRigidArea(new Dimension(10,50)));

        difficultyLevelBox = new JComboBox<>();
        difficultyLevelBox.addItem(DifficultyLevel.EASY.getDifficultyLevel());
        difficultyLevelBox.addItem(DifficultyLevel.MEDIUM.getDifficultyLevel());
        difficultyLevelBox.addItem(DifficultyLevel.HARD.getDifficultyLevel());
        difficultyLevelBox.setMaximumSize(new Dimension(80,30));
        settingsPanel.add(difficultyLevelBox);

        settingsPanel.add(Box.createRigidArea(new Dimension(30,50)));

        startButton = new JButton();
        startButton.setMinimumSize(new Dimension(80,40));
        startButton.setMaximumSize(new Dimension(80,40));
        startButton.setText("START!");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGameBoard();
            }
        });
        settingsPanel.add(startButton);
    }

    private void setGameBoard(){
        String difficultyLevel = (String)difficultyLevelBox.getSelectedItem();
        int difficultyLevelNumber = DifficultyLevel.getDifficultyNumberForString(difficultyLevel);
        gameBoard = new GameBoard((int)boardHeightBox.getSelectedItem(), (int)boardWidthBox.getSelectedItem(), difficultyLevelNumber);
        drawGameBoard(gameBoard);
    }

    public static void setGameStatusInfo(String message, Color color){
        gameStatusInfo.setText(message);
        gameStatusInfo.setForeground(color);
    }


}
