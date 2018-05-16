import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseActionHandler implements MouseListener {
    private GameBoard gameBoard;
    private int verticalPos;
    private int horizontalPos;

    public MouseActionHandler(int verticalPos, int horizontalPos, GameBoard gameBoard) {
        super();
        this.gameBoard = gameBoard;
        this.verticalPos = verticalPos;
        this.horizontalPos = horizontalPos;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
            if(!GameLogics.checkWinningCondition(gameBoard)&&!GameLogics.checkLosingCondition(gameBoard)){
                if (SwingUtilities.isLeftMouseButton(e)) {
                    gameBoard.leftMouseButtonAction(verticalPos, horizontalPos);
                }
                if (SwingUtilities.isRightMouseButton(e)) {
                    gameBoard.rightMouseButtonAction(verticalPos, horizontalPos);
                }
                if (SwingUtilities.isMiddleMouseButton(e)) {
                    gameBoard.middleMouseButtonAction(verticalPos, horizontalPos);
                }
                if(GameLogics.checkWinningCondition(gameBoard)){
                    GameGUI.setGameStatusInfo("You won!", new Color(33, 150, 49));
                }
                if(GameLogics.checkLosingCondition(gameBoard)){
                    GameGUI.setGameStatusInfo("You lost!", Color.red);
                    gameBoard.revealAllMinedTiles();
                }
            }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
