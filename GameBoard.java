import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBoard {
    private int boardHeight;
    private int boardWidth;
    private int difficultyLevel;
    private GameTile[][] buttonBoard;
    private Random randomNumber;

    public GameBoard(int boardHeight, int boardWidth, int difficultyLevel) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.difficultyLevel = difficultyLevel;
        createGameBoard(boardHeight, boardWidth, difficultyLevel);
    }

    public void createGameBoard(int boardHeight, int boardWidth, int difficultyLevel) {
        buttonBoard = new GameTile[boardHeight][boardWidth];
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                buttonBoard[i][j] = new GameTile();
                buttonBoard[i][j].setVerticalPos(i);
                buttonBoard[i][j].setHorizontalPos(j);
                setMines(i, j, difficultyLevel);
                buttonBoard[i][j].addMouseListener(new MouseActionHandler(i, j, this));
            }
        }

    }

    public GameTile[][] getButtonBoard() {
        return buttonBoard;
    }

    public int getBoardHeight() {
        return boardHeight;
    }


    public int getBoardWidth() {
        return boardWidth;
    }

    private void setMines(int horizontalPos, int verticalPos, int difficultyLevel) {
        randomNumber = new Random();
        if (difficultyLevel > randomNumber.nextInt(10)) {
            buttonBoard[horizontalPos][verticalPos].setTileType(FieldType.MINED);
        }
    }

    public int checkHowManyMinesAreAroundTile(int verticalPos, int horizontalPos) {
        int counter = 0;
        if (buttonBoard[verticalPos][horizontalPos].getTileType().equals(FieldType.EMPTY)) {
            for (int i = -1; i <= 1; i++) {
                try {
                    if (buttonBoard[verticalPos - 1][horizontalPos + i].getTileType().equals(FieldType.MINED) ||
                            buttonBoard[verticalPos - 1][horizontalPos + i].getTileType().equals(FieldType.MINED_FLAGGED) ||
                            buttonBoard[verticalPos - 1][horizontalPos + i].getTileType().equals(FieldType.MINED_REVEALED)) {
                        counter++;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {
                    if (buttonBoard[verticalPos + 1][horizontalPos + i].getTileType().equals(FieldType.MINED) ||
                            buttonBoard[verticalPos + 1][horizontalPos + i].getTileType().equals(FieldType.MINED_FLAGGED) ||
                            buttonBoard[verticalPos + 1][horizontalPos + i].getTileType().equals(FieldType.MINED_REVEALED)) {
                        counter++;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
            try {
                if (buttonBoard[verticalPos][horizontalPos + 1].getTileType().equals(FieldType.MINED) ||
                        buttonBoard[verticalPos][horizontalPos + 1].getTileType().equals(FieldType.MINED_FLAGGED) ||
                        buttonBoard[verticalPos][horizontalPos + 1].getTileType().equals(FieldType.MINED_REVEALED)) {
                    counter++;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            try {
                if (buttonBoard[verticalPos][horizontalPos - 1].getTileType().equals(FieldType.MINED) ||
                        buttonBoard[verticalPos][horizontalPos - 1].getTileType().equals(FieldType.MINED_FLAGGED) ||
                        buttonBoard[verticalPos][horizontalPos - 1].getTileType().equals(FieldType.MINED_REVEALED)) {
                    counter++;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
        return counter;
    }

    public int checkHowManyFlaggedTilesAreAroundTile(int verticalPos, int horizontalPos) {
        int counter = 0;
        if (buttonBoard[verticalPos][horizontalPos].getTileType().equals(FieldType.EMPTY)) {
            for (int i = -1; i <= 1; i++) {
                try {
                    if (buttonBoard[verticalPos - 1][horizontalPos + i].getTileType().equals(FieldType.MINED_FLAGGED) ||
                            buttonBoard[verticalPos - 1][horizontalPos + i].getTileType().equals(FieldType.EMPTY_FLAGGED)) {
                        counter++;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {
                    if (buttonBoard[verticalPos + 1][horizontalPos + i].getTileType().equals(FieldType.MINED_FLAGGED) ||
                            buttonBoard[verticalPos + 1][horizontalPos + i].getTileType().equals(FieldType.EMPTY_FLAGGED)) {
                        counter++;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
            try {
                if (buttonBoard[verticalPos][horizontalPos + 1].getTileType().equals(FieldType.MINED_FLAGGED) ||
                        buttonBoard[verticalPos][horizontalPos + 1].getTileType().equals(FieldType.EMPTY_FLAGGED)) {
                    counter++;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            try {
                if (buttonBoard[verticalPos][horizontalPos - 1].getTileType().equals(FieldType.MINED_FLAGGED) ||
                        buttonBoard[verticalPos][horizontalPos - 1].getTileType().equals(FieldType.EMPTY_FLAGGED)) {
                    counter++;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
        return counter;
    }

    public List<GameTile> revealAllTilesAroundTile(int verticalPos, int horizontalPos) {
        List<GameTile> listOfTiles = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            try {
                if (buttonBoard[verticalPos - 1][horizontalPos + i].getTileType().equals(FieldType.MINED) ||
                        buttonBoard[verticalPos - 1][horizontalPos + i].getTileType().equals(FieldType.EMPTY)) {
                    listOfTiles.add(buttonBoard[verticalPos - 1][horizontalPos + i]);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            try {
                if (buttonBoard[verticalPos + 1][horizontalPos + i].getTileType().equals(FieldType.MINED) ||
                        buttonBoard[verticalPos + 1][horizontalPos + i].getTileType().equals(FieldType.EMPTY)) {
                    listOfTiles.add(buttonBoard[verticalPos + 1][horizontalPos + i]);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
        try {
            if (buttonBoard[verticalPos][horizontalPos + 1].getTileType().equals(FieldType.MINED) ||
                    buttonBoard[verticalPos][horizontalPos + 1].getTileType().equals(FieldType.EMPTY)) {
                listOfTiles.add(buttonBoard[verticalPos][horizontalPos + 1]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (buttonBoard[verticalPos][horizontalPos - 1].getTileType().equals(FieldType.MINED) ||
                    buttonBoard[verticalPos][horizontalPos - 1].getTileType().equals(FieldType.EMPTY)) {
                listOfTiles.add(buttonBoard[verticalPos][horizontalPos - 1]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return listOfTiles;
    }

    public void leftMouseButtonAction(int verticalPos, int horizontalPos) {
        GameTile gameTile = buttonBoard[verticalPos][horizontalPos];
        int numberOfMinedFields = checkHowManyMinesAreAroundTile(verticalPos, horizontalPos);
        if (gameTile.getTileType().equals(FieldType.MINED)) {
            gameTile.setGameTileAsEnabled();
            gameTile.setTileAppearance(verticalPos, horizontalPos, numberOfMinedFields);
        } else if (gameTile.getTileType().equals(FieldType.EMPTY)) {
            if (checkHowManyMinesAreAroundTile(verticalPos, horizontalPos) == 0) {
                revealAllEmptyTilesAround(verticalPos, horizontalPos);
            } else {
                gameTile.setGameTileAsEnabled();
                gameTile.setTileAppearance(verticalPos, horizontalPos, numberOfMinedFields);
            }
        }
    }

    public void rightMouseButtonAction(int verticalPos, int horizontalPos) {
        GameTile gameTile = buttonBoard[verticalPos][horizontalPos];
        int numberOfMinedFields = checkHowManyMinesAreAroundTile(verticalPos, horizontalPos);
        gameTile.setTileAppearance(verticalPos, horizontalPos, numberOfMinedFields);
    }

    public void middleMouseButtonAction(int verticalPos, int horizontalPos) {
        GameTile gameTile = buttonBoard[verticalPos][horizontalPos];
        int numberOfMinedFields;
        if (gameTile.isEnabled() && gameTile.getTileType().equals(FieldType.EMPTY)) {
            if (checkHowManyMinesAreAroundTile(verticalPos, horizontalPos) <=
                    checkHowManyFlaggedTilesAreAroundTile(verticalPos, horizontalPos)) {
                for (GameTile tile : revealAllTilesAroundTile(verticalPos, horizontalPos)) {
                    tile.setGameTileAsEnabled();
                    numberOfMinedFields = checkHowManyMinesAreAroundTile(tile.getVerticalPos(), tile.getHorizontalPos());
                    tile.setTileAppearance(tile.getVerticalPos(), tile.getHorizontalPos(), numberOfMinedFields);
                }
            }
        }
    }

    private Thread revealAllEmptyTilesAround(int verticalPos, int horizontalPos) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                GameTile gameTile = buttonBoard[verticalPos][horizontalPos];
                int numberOfMinedFields = checkHowManyMinesAreAroundTile(verticalPos, horizontalPos);
                if (numberOfMinedFields == 0) {
                    gameTile.setGameTileAsEnabled();
                    gameTile.setTileAppearance(verticalPos, horizontalPos, numberOfMinedFields);

                    for (int i = -1; i < 2; i++) {
                        try {
                            if ((!buttonBoard[verticalPos - 1][horizontalPos + i].isEnabled()) &&
                                    (buttonBoard[verticalPos - 1][horizontalPos + i].getTileType().equals(FieldType.EMPTY))) {
                                Thread newThread = revealAllEmptyTilesAround(verticalPos - 1, horizontalPos + i);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                        }
                    }

                    for (int i = -1; i < 2; i++) {
                        try {
                            if ((!buttonBoard[verticalPos + 1][horizontalPos + i].isEnabled()) &&
                                    (buttonBoard[verticalPos + 1][horizontalPos + i].getTileType().equals(FieldType.EMPTY))) {
                                Thread newThread = revealAllEmptyTilesAround(verticalPos + 1, horizontalPos + i);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                        }
                    }
                    try {
                        if ((!buttonBoard[verticalPos][horizontalPos + 1].isEnabled()) &&
                                (buttonBoard[verticalPos][horizontalPos + 1].getTileType().equals(FieldType.EMPTY))) {
                            Thread newThread = revealAllEmptyTilesAround(verticalPos, horizontalPos + 1);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        if ((!buttonBoard[verticalPos][horizontalPos - 1].isEnabled()) &&
                                (buttonBoard[verticalPos][horizontalPos - 1].getTileType().equals(FieldType.EMPTY))) {
                            Thread newThread = revealAllEmptyTilesAround(verticalPos, horizontalPos - 1);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }

                } else {
                    gameTile.setGameTileAsEnabled();
                    gameTile.setTileAppearance(verticalPos, horizontalPos, numberOfMinedFields);
                }
            }
        });
        thread.start();
        return thread;
    }

    public void revealAllMinedTiles() {
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                if ((!buttonBoard[i][j].isEnabled() && buttonBoard[i][j].getTileType().equals(FieldType.MINED)) ||
                        (!buttonBoard[i][j].isEnabled() && buttonBoard[i][j].getTileType().equals(FieldType.MINED_FLAGGED))) {
                    buttonBoard[i][j].setGameTileAsEnabled();
                    buttonBoard[i][j].setText("M");
                    buttonBoard[i][j].setForeground(Color.red);
                    buttonBoard[i][j].setBackground(Color.darkGray);
                }
            }
        }
    }

}
