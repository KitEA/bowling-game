import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void shouldCalculateScoreForStrike() {
        // when
        rollStrike();

        // then
        assertThat(game.score()).isEqualTo(10);
    }

    @Test
    void shouldCalculateScoreForStrikeAndRoll() {
        // given
        rollStrike();

        // when
        game.roll(4);

        // then
        assertThat(game.score()).isEqualTo(14);
    }

    @Test
    void shouldCalculateScoreForTwoStrikes() {
        // given
        rollStrike();

        // when
        rollStrike();

        // then
        assertThat(game.score()).isEqualTo(20);
    }

    @Test
    void shouldCalculateScoreForRollAfterTwoStrikes() {
        // given
        rollStrike();
        rollStrike();

        // when
        game.roll(5);

        // then
        assertThat(game.score()).isEqualTo(40);
    }

    @Test
    void shouldCalculateScoreForSpare() {
        // when
        rollSpare();

        // then
        assertThat(game.score()).isEqualTo(10);
    }

    @Test
    void shouldCalculateScoreForRollAfterSpare() {
        // given
        rollSpare();

        // when
        game.roll(4);

        // then
        assertThat(game.score()).isEqualTo(18);
    }

    @Test
    void shouldCalculateScoreForTwoRollsAfterSpare() {
        // given
        rollSpare();

        // when
        game.roll(4);
        game.roll(4);

        // then
        assertThat(game.score()).isEqualTo(22);
    }

    @Test
    void shouldReturnZeroScoreForAllZeroRolls() {
        rollMany(20, 0);
        assertThat(game.score()).isEqualTo(0);
    }

    @Test
    void shouldCalculateScoreForAllOnesRolls() {
        rollMany(20, 1);
        assertThat(game.score()).isEqualTo(20);
    }

    @Test
    void shouldCalculateScoreForPerfectGame() {
        rollMany(12, 10);
        assertThat(game.score()).isEqualTo(300);
    }

    private void rollMany(int n, int pins) {
        for (int i = 0; i < n; i++) {
            game.roll(pins);
        }
    }

    private void rollStrike() {
        game.roll(10);
    }

    private void rollSpare() {
        game.roll(5);
        game.roll(5);
    }
}