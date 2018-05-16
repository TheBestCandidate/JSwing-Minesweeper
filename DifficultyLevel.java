public enum DifficultyLevel {
    EASY(1, "Easy"), MEDIUM(2, "Medium"), HARD(3, "Hard");

    private final int difficultyLevelNumber;
    private final String difficultyLevel;
    DifficultyLevel(int difficultyLevelNumber, String difficultyLevel){
        this.difficultyLevelNumber = difficultyLevelNumber;
        this.difficultyLevel = difficultyLevel;
    }

    public int getDifficultyLevelNumber() {
        return difficultyLevelNumber;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public static int getDifficultyNumberForString(String difficultyLevelString){
        for(DifficultyLevel difficultyLevel : values()){
            if(difficultyLevelString.equals(difficultyLevel.getDifficultyLevel())){
                return difficultyLevel.getDifficultyLevelNumber();
            }
        }
        throw new NullPointerException();
    }

}
