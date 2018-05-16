import javax.swing.*;
import java.awt.*;

public class GameTile extends JButton {
    private FieldType tileType;
    private boolean enabled = false;
    private int verticalPos;
    private int horizontalPos;
    private final int buttonSize = 45;

    public GameTile(){
        super();
        tileType = FieldType.EMPTY;
        setMinimumSize(new Dimension(buttonSize,buttonSize));
        setPreferredSize(new Dimension(buttonSize,buttonSize));
    }

    public FieldType getTileType() {
        return tileType;
    }

    public void setTileType(FieldType tileType) {
        this.tileType = tileType;
    }

    public void setGameTileAsEnabled(){
        this.setEnabled(true);
        enabled = true;
    }

    public int getVerticalPos() {
        return verticalPos;
    }

    public void setVerticalPos(int verticalPos) {
        this.verticalPos = verticalPos;
    }

    public int getHorizontalPos() {
        return horizontalPos;
    }

    public void setHorizontalPos(int horizontalPos) {
        this.horizontalPos = horizontalPos;
    }

    public void setTileAppearance(int verticalPos, int horizontalPos, int numberOfMinedFieldsAround) {
        if (isEnabled()) {
            if (getTileType().equals(FieldType.MINED)) {
                setTileType(FieldType.MINED_REVEALED);
                setBackground(Color.red);
                setText("M");
            } else if (getTileType().equals(FieldType.EMPTY)) {
                setBackground(Color.gray);
                if (numberOfMinedFieldsAround != 0) {
                    setText(numberOfMinedFieldsAround + "");
                }
            }
        } else {
            if (getTileType().equals(FieldType.MINED)) {
                setText("F");
                setTileType(FieldType.MINED_FLAGGED);
            } else if (getTileType().equals(FieldType.EMPTY)) {
                setText("F");
                setTileType(FieldType.EMPTY_FLAGGED);
            } else if (getTileType().equals(FieldType.MINED_FLAGGED)) {
                setText("");
                setTileType(FieldType.MINED);
            } else if (getTileType().equals(FieldType.EMPTY_FLAGGED)) {
                setText("");
                setTileType(FieldType.EMPTY);
            }
        }
    }

    @Override
    public boolean isEnabled(){
        return enabled;
    }

}
