public class Game {
    private int score;
    private int rollN = 0;
    private final int[] rolls = new int[21];

    public void roll(int pinsKnockedDown) {
        rollN++;
        rolls[rollN] = pinsKnockedDown;

        bonusIfSpare(pinsKnockedDown);
        bonusIfStrike(pinsKnockedDown);

        score += pinsKnockedDown;
    }

    private void bonusIfStrike(int pinsKnockedDown) {
        if (rollN > 2 && rolls[rollN - 2] == 10 && rollN % 2 != 0) {
            score += pinsKnockedDown + rolls[rollN - 1];
        }
    }

    private void bonusIfSpare(int pinsKnockedDown) {
        if (rollN > 2 && rolls[rollN - 2] + rolls[rollN - 1] == 10 && rollN % 2 != 0) {
            score += pinsKnockedDown;
        }
    }

    public int score() {
        return score;
    }
}
