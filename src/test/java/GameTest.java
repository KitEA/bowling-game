import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {

    private Game g;

    @BeforeEach
    void setUp() {
        g = new Game();
    }

    public void rollMany(int n, int pins) {
        for (int i = 0; i < n; i++) {
            g.roll(pins);
        }
    }

    @Test
    void testGutterGame() {
        rollMany(20, 0);
        assertThat(g.score()).isEqualTo(0);
    }

    @Test
    void allOne() {
        rollMany(20, 1);
        assertThat(g.score()).isEqualTo(20);
    }

    @Test
    void testSpare() {
        g.roll(5);
        g.roll(5);
        g.roll(3);
        rollMany(17, 0);
        assertThat(g.score()).isEqualTo(16);
    }

    @Test
    void testStrike() {
        g.roll(10);
        g.roll(3);
        g.roll(6);
        rollMany(17, 0);
        assertThat(g.score()).isEqualTo(28);
    }
}