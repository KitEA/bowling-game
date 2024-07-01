public class Frame {
    private int score;
    private int rollN = -1;
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

    private void checkIfRollsExceeded() {
        if (rollN == 1) {
            exceededRolls = true;
        }
    }

    private void checkIfSpare() {
        if (rollN == 1 && rolls[0] + rolls[1] == 10) {
            isSpare = true;
        }
    }

    private void checkIfStrike() {
        if (rollN == 0 && rolls[0] == 10) {
            isStrike = true;
            exceededRolls = true;
        }
    }
}
