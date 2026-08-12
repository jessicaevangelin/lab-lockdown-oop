class SequencePuzzle extends Puzzle {
    private String[] correctSequence;
    private String requiredItemName;

    public SequencePuzzle(String hint, int maxAttempts, String[] correctSequence, String requiredItemName) {
        super(hint, maxAttempts);
        this.correctSequence = correctSequence;
        this.requiredItemName = requiredItemName;
    }

    public String getRequiredItemName() {
        return requiredItemName;
    }

    @Override
    public boolean attempt(String input) {
        attemptsUsed++;
        String[] parts = input.trim().split("\\s*,\\s*");

        if (parts.length != correctSequence.length) {
            return false;
        }

        boolean match = true;
        for (int i = 0; i < correctSequence.length; i++) {
            if (!parts[i].equalsIgnoreCase(correctSequence[i])) {
                match = false;
                break;
            }
        }

        solved = match;
        return solved;
    }
}