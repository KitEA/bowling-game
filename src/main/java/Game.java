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
        ListIterator<Frame> framesIterator = frames.listIterator();
        int score = 0;

        while (framesIterator.hasNext()) {
            Frame currentFrame = framesIterator.next();

            if (currentFrame.isTenthFrame()) {
                score += currentFrame.score();
            } else if (isStrikeAndFrameFollows(currentFrame, framesIterator)) {
                score = calcStrikeBonus(framesIterator, score, currentFrame);
            } else if (currentFrame.isSpare() && framesIterator.hasNext()) {
                score += currentFrame.score()
                        + framesIterator.next().getFirstRole();

                reverseCursorTimes(framesIterator, 1);
            } else {
                score += currentFrame.score();
            }
        }

        return score;
    }

    private static int calcStrikeBonus(ListIterator<Frame> framesIterator, int score, Frame currentFrame) {
        Frame nextFrame = framesIterator.next();

        if (nextFrame.isTenthFrame()) {
            score += currentFrame.score()
                    + nextFrame.getTwoRollsResult();

            reverseCursorTimes(framesIterator, 1);
        }
        // covers both cases: one when both following frames are strikes
        // and one following is strike and another one is not
        else if (isStrikeAndFrameFollows(nextFrame, framesIterator)) {
            score += currentFrame.score()
                    + nextFrame.score()
                    + framesIterator.next().getFirstRole();

            reverseCursorTimes(framesIterator, 2);
        }
        // covers the case when following frame is not a strike
        else {
            score += currentFrame.score();

            reverseCursorTimes(framesIterator, 1);
        }
        return score;
    }

    private static boolean isStrikeAndFrameFollows(Frame currentFrame, ListIterator<Frame> framesIterator) {
        return currentFrame.isStrike() && framesIterator.hasNext();
    }

    private static void reverseCursorTimes(ListIterator<Frame> framesIterator, int times) {
        for (int i = 0; i < times; i++) {
            framesIterator.previous();
        }
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
