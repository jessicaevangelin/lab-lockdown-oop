class RiddlePuzzle extends Puzzle {
    private String correctAnswer;

    public RiddlePuzzle(String hint, int maxAttempts, String correctAnswer) {
        super(hint, maxAttempts);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean attempt(String input) {
        attemptsUsed++;
        if (input.trim().equalsIgnoreCase(correctAnswer)) {
            solved = true;
        }
        return solved;
    }
}