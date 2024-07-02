package frame;

public final class TenthFrame extends Frame {
    @Override
    public void roll(int pinsKnockedDown) {
        rollN++;
        rolls.add(pinsKnockedDown);

        checkIfRollsExceeded();
        checkIfStrike();
        checkIfSpare();

        if (rollN < 2) {
            score += pinsKnockedDown;
        } else if (isStrike() || isSpare()) {
            score += pinsKnockedDown;
        }
    }
}
