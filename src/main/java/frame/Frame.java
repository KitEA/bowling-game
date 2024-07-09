package frame;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private final static int SECOND_ROLL = 2;
    private final static int MAX_FRAME_SCORE_EXCLUDING_BONUSES = 10;
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

    public int getFirstRole() {
        return rolls.get(0);
    }

    public int getTwoRollsResult() {
        return rolls.get(0) + rolls.get(1);
    }

    public int score() {
        return score;
    }

    public boolean isTenthFrame() {
        return this instanceof TenthFrame;
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
        return rolls.get(0) + rolls.get(1) == MAX_FRAME_SCORE_EXCLUDING_BONUSES;
    }

    private boolean isStrikeCondition() {
        return rolls.get(0) == MAX_FRAME_SCORE_EXCLUDING_BONUSES;
    }
}
