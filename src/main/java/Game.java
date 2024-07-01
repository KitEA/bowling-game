import java.util.ArrayList;
import java.util.List;

public class Game {
    private int currentFrame = -1;
    private final List<Frame> frames = new ArrayList<>(10);

    public void roll(int pinsKnockedDown) {
        checkIfShouldMoveToTheNextFrame();
        frames.get(currentFrame).roll(pinsKnockedDown);

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
        if (frames.isEmpty() || isCheckIfRollsAreExceeded()) {
            moveToTheNextFrame();
        }
    }

    private boolean isCheckIfRollsAreExceeded() {
        return frames.get(currentFrame).isExceeded();
    }

    private void moveToTheNextFrame() {
        Frame f = new Frame();
        frames.add(f);
        currentFrame++;
    }

    private void addBonusToPreviousFrameIfSpare(int pinsKnockedDown) {
        if (frames.size() > 1 && frames.get(currentFrame - 1).isSpare()) {
            frames.get(currentFrame - 1).addBonus(pinsKnockedDown);
            frames.get(currentFrame - 1).setSpare(false);
        }
    }

    private void addBonusToPrevFrameIfStrike() {
        if (frames.size() > 1 && frames.get(currentFrame).isExceeded() && frames.get(currentFrame - 1).isStrike()) {
            frames.get(currentFrame - 1).addBonus(frames.get(currentFrame).score());
            frames.get(currentFrame - 1).setStrike(false);
        }
    }
}
