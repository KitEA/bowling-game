import frame.Frame;
import frame.TenthFrame;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int currentFrameIndex = -1;
    private final List<Frame> frames = new ArrayList<>(10);
    private static final int MAX_NUMBER_OF_FRAMES = 10;

    public void roll(int pinsKnockedDown) {
        checkIfShouldMoveToTheNextFrame();
        frames.get(currentFrameIndex).roll(pinsKnockedDown);
        addBonusToPreviousFrameIfSpare(pinsKnockedDown);
        addBonusToPrevFrameIfStrike();
    }

    public int score() {
        int score = 0;
        for (Frame frame : frames) {
            score += frame.score();
        }

        return score;
    }

    private void checkIfShouldMoveToTheNextFrame() {
        if (frames.isEmpty() || (checkIfRollsAreExceeded() && frames.size() < MAX_NUMBER_OF_FRAMES)) {
            moveToTheNextFrame();
        }
    }

    private boolean checkIfRollsAreExceeded() {
        return frames.get(currentFrameIndex).isExceeded();
    }

    private void moveToTheNextFrame() {
        Frame f = currentFrameIndex == 8
                ? new TenthFrame()
                : new Frame();
        frames.add(f);
        currentFrameIndex++;
    }

    private void addBonusToPreviousFrameIfSpare(int pinsKnockedDown) {
        if (isTherePreviousFrame() && getPreviousFrame().isSpare()) {
            getPreviousFrame().addBonus(pinsKnockedDown);
            getPreviousFrame().setSpare(false);
        }
    }

    private void addBonusToPrevFrameIfStrike() {
        if (isTherePreviousFrame() && frames.get(currentFrameIndex).isExceeded() && getPreviousFrame().isStrike()) {
            getPreviousFrame().addBonus(frames.get(currentFrameIndex).score());
            getPreviousFrame().setStrike(false);
        }
    }

    private boolean isTherePreviousFrame() {
        return frames.size() > 1;
    }

    private Frame getPreviousFrame() {
        return frames.get(currentFrameIndex - 1);
    }
}
