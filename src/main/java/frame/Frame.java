package frame;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private final static int SECOND_ROLL = 2;
    private boolean exceededRolls = false;
    private boolean isSpare = false;
    private boolean isStrike = false;
    final List<Integer> rolls = new ArrayList<>(2);
    int rollN = 0;
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
        if (isStrikeCondition()) {
            isStrike = true;
        }
    }

    private boolean hasSpentAllRolls() {
        return hasPerformedBothRolls() || isStrikeCondition();
    }

    private boolean hasPerformedBothRolls() {
        return rollN == SECOND_ROLL;
    }

    private boolean isSpareCondition() {
        return rolls.get(0) + rolls.get(1) == 10;
    }

    private boolean isStrikeCondition() {
        return rolls.get(0) == 10;
    }
}
