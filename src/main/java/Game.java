public class Game {
    private int score;

    public void roll(int pinsKnockedDown) {
        score += pinsKnockedDown;
    }

    public int score() {
        return score;
    }
}
