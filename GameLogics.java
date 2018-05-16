public class GameLogics {
            public static boolean checkWinningCondition(GameBoard gameBoard){
                for(int i = 0; i < gameBoard.getBoardHeight(); i++){
                    for(int j = 0; j < gameBoard.getBoardWidth(); j++){
                        if(gameBoard.getButtonBoard()[i][j].getTileType().equals(FieldType.MINED)||
                                gameBoard.getButtonBoard()[i][j].getTileType().equals(FieldType.EMPTY_FLAGGED)||
                                (gameBoard.getButtonBoard()[i][j].getTileType().equals(FieldType.EMPTY)&&
                                        !gameBoard.getButtonBoard()[i][j].isEnabled())){
                            return false;
                        }
                        }
                    }
                    return true;
                }

                public static boolean checkLosingCondition(GameBoard gameBoard){
                    for(int i = 0; i < gameBoard.getBoardHeight(); i++){
                        for(int j = 0; j < gameBoard.getBoardWidth(); j++){
                            if(gameBoard.getButtonBoard()[i][j].getTileType().equals(FieldType.MINED_REVEALED)){
                                return true;
                            }
                        }
                    }
                    return false;
                }
}
