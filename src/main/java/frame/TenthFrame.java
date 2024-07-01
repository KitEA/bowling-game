package frame;

public final class TenthFrame extends Frame {
    private final int[] rolls = new int[3];
    @Override
    public void roll(int pinsKnockedDown) {
        rolls[++rollN] = pinsKnockedDown;

        checkIfRollsExceeded();
        checkIfSpare();
        checkIfStrike();

        if (isSpare() || isStrike()) {
            score += 10;
        }

        score += pinsKnockedDown;
    }
}
