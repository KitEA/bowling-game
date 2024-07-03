import frame.Frame;
import frame.TenthFrame;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static final int MAX_NUMBER_OF_FRAMES = 10;
    private static final int LAST_REGULAR_FRAME_INDEX = 8;
    private final List<Frame> frames = new ArrayList<>(10);
    private int currentFrameIndex = -1;

    public void roll(int pinsKnockedDown) {
        checkIfShouldMoveToTheNextFrame();
        frames.get(currentFrameIndex).roll(pinsKnockedDown);
    }

    public int score() {
        int score = 0;

        for (int i = 0; i < MAX_NUMBER_OF_FRAMES; i++) {
            if (isTenthFrame(i)) {
                score += frames.get(i).score();
            } else if (frames.get(i).isStrike()) {
                score = calculateStrikeBonus(i, score);
            } else if (frames.get(i).isSpare()) {
                score += frames.get(i).score() + frames.get(i + 1).getFirstRole();
            } else {
                score += frames.get(i).score();
            }
        }

        return score;
    }

    private int calculateStrikeBonus(int frameIndex, int score) {
        if (isTenthFrame(frameIndex + 1)) {
            score += frames.get(frameIndex).score()
                    + frames.get(frameIndex + 1).getTwoRollsResult();
        }
        // covers both cases: one when both following frames are strikes
        // and one following is strike and another one is not
        else if (nextFrameIsStrike(frameIndex)) {
            score += frames.get(frameIndex).score()
                    + frames.get(frameIndex + 1).score()
                    + frames.get(frameIndex + 2).getFirstRole();
        }
        // covers the case when following frame is not a strike
        else {
            score += frames.get(frameIndex).score() + frames.get(frameIndex + 1).score();
        }
        return score;
    }

    private boolean isTenthFrame(int frameIndex) {
        return frames.get(frameIndex) instanceof TenthFrame;
    }

    private boolean nextFrameIsStrike(int frameIndex) {
        return frames.get(frameIndex + 1).isStrike();
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
        Frame f = currentFrameIndex == LAST_REGULAR_FRAME_INDEX
                ? new TenthFrame()
                : new Frame();
        frames.add(f);
        currentFrameIndex++;
    }
}
