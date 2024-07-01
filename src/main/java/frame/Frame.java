package frame;

public class Frame {
    int score;
    int rollN = -1;
    private boolean exceededRolls = false;
    private boolean isSpare = false;
    private boolean isStrike = false;
    private final int[] rolls = new int[2];

    public void roll(int pinsKnockedDown) {
        rolls[++rollN] = pinsKnockedDown;

        checkIfRollsExceeded();

        checkIfSpare();
        checkIfStrike();

        score += pinsKnockedDown;
    }

    public boolean isExceeded() {
        return exceededRolls;
    }

    public boolean isSpare() {
        return isSpare;
    }

    public boolean isStrike() {
        return isStrike;
    }

    public void setSpare(boolean spare) {
        isSpare = spare;
    }

    public void setStrike(boolean strike) {
        isStrike = strike;
    }

    public void addBonus(int bonus) {
        score += bonus;
    }

    public int score() {
        return score;
    }

    void checkIfRollsExceeded() {
        if (hasSpentAllRolls()) {
            exceededRolls = true;
        }
    }

    void checkIfSpare() {
        if (hasPerformedBothRolls() && isSpareCondition()) {
            isSpare = true;
        }
    }

    void checkIfStrike() {
        if (isTheFirstRoll() && isStrikeCondition()) {
            isStrike = true;
            exceededRolls = true;
        }
    }

    private boolean hasSpentAllRolls() {
        return rollN >= 1;
    }

    private boolean hasPerformedBothRolls() {
        return rollN == 1;
    }

    private boolean isTheFirstRoll() {
        return rollN == 0;
    }

    private boolean isSpareCondition() {
        return rolls[0] + rolls[1] == 10;
    }

    private boolean isStrikeCondition() {
        return rolls[0] == 10;
    }
}
