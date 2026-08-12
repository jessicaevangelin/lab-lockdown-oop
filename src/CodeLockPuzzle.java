class CodeLockPuzzle extends Puzzle {
    private String correctCode;
    private String requiredItemName;

    public CodeLockPuzzle(String hint, int maxAttempts, String correctCode, String requiredItemName) {
        super(hint, maxAttempts);
        this.correctCode = correctCode;
        this.requiredItemName = requiredItemName;
    }

    public String getRequiredItemName() {
        return requiredItemName;
    }

    @Override
    public boolean attempt(String input) {
        attemptsUsed++;
        if (input.trim().equals(correctCode)) {
            solved = true;
        }
        return solved;
    }
}