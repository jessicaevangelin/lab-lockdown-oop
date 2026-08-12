abstract class Puzzle implements Solvable {
    protected String hint;
    protected int maxAttempts;
    protected int attemptsUsed;
    protected boolean solved;

    public Puzzle(String hint, int maxAttempts) {
        this.hint = hint;
        this.maxAttempts = maxAttempts;
        this.attemptsUsed = 0;
        this.solved = false;
    }

    public boolean isSolved() {
        return solved;
    }

    public String getHint() {
        return hint;
    }

    public int getAttemptsRemaining() {
        return maxAttempts - attemptsUsed;
    }

    public boolean attemptsExhausted() {
        return attemptsUsed >= maxAttempts;
    }
}