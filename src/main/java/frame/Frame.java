package frame;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private boolean exceededRolls = false;
    private boolean isSpare = false;
    private boolean isStrike = false;
    final List<Integer> rolls = new ArrayList<>(2);
    int rollN = -1;
    int score;

    public void roll(int pinsKnockedDown) {
        rollN++;
        rolls.add(pinsKnockedDown);

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
        }
    }

    private boolean hasSpentAllRolls() {
        return hasPerformedBothRolls() || rolls.get(0) == 10;
    }

    private boolean hasPerformedBothRolls() {
        return rollN == 1;
    }

    private boolean isTheFirstRoll() {
        return rollN == 0;
    }

    private boolean isSpareCondition() {
        return rolls.get(0) + rolls.get(1) == 10;
    }

    private boolean isStrikeCondition() {
        return rolls.get(0) == 10;
    }
}
