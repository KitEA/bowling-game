import frame.Frame;
import frame.TenthFrame;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Game {
    private static final int MAX_NUMBER_OF_FRAMES = 10;
    private static final int LAST_REGULAR_FRAME_INDEX = 8;
    private final List<Frame> frames = new LinkedList<>();
    private int currentFrameIndex = -1;

    public void roll(int pinsKnockedDown) {
        checkIfShouldMoveToTheNextFrame();
        frames.get(currentFrameIndex).roll(pinsKnockedDown);
    }

    public int score() {
        int score = 0;

        ListIterator<Frame> framesIterator = frames.listIterator();

        while (framesIterator.hasNext()) {
            Frame currentFrame = framesIterator.next();

            if (currentFrame instanceof TenthFrame) {
                score += currentFrame.score();
            }
            // strike
            else if (currentFrame.isStrike() && framesIterator.hasNext()) {
                score = calcStrikeBonus(framesIterator, score, currentFrame);
            }
            else if (currentFrame.isSpare() && framesIterator.hasNext()) {
                int currentFrameScore = currentFrame.score();
                int nextFrameScore = framesIterator.next().getFirstRole();

                score += currentFrameScore + nextFrameScore;

                framesIterator.previous();
            } else {
                score += currentFrame.score();
            }
        }

        return score;
    }

    private static int calcStrikeBonus(ListIterator<Frame> framesIterator, int score, Frame currentFrame) {
        Frame nextFrame = framesIterator.next();

        if (nextFrame instanceof TenthFrame) {
            score += currentFrame.score()
                    + nextFrame.getTwoRollsResult();

            framesIterator.previous();
        }
        // covers both cases: one when both following frames are strikes
        // and one following is strike and another one is not
        else if (nextFrame.isStrike() && framesIterator.hasNext()) {
            Frame afterNextFrame = framesIterator.next();

            score += currentFrame.score()
                    + nextFrame.score()
                    + afterNextFrame.getFirstRole();

            resetCursorToCurrentFrame(framesIterator);
        }
        // covers the case when following frame is not a strike
        else {
            score += currentFrame.score();

            framesIterator.previous();
        }
        return score;
    }

    private static void resetCursorToCurrentFrame(ListIterator<Frame> framesIterator) {
        framesIterator.previous();
        framesIterator.previous();
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
