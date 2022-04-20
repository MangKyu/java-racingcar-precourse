package racingcar.game;

import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.errors.RacingCarException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameRoundTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -2, -3 - 4})
    void 이동할횟수입력성공_1보다작은입력(final int input) {
        // given

        // when
        final AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> new GameRound(input));

        // then
        result.isInstanceOf(IllegalArgumentException.class).hasMessageContaining(RacingCarException.PREFIX);
    }

    @Test
    void 마지막라운드검사_맞음() {
        // given
        int totalRound = 3;
        final GameRound round = new GameRound(totalRound);

        for (int i = 0; i < totalRound; i++) {
            round.start();
        }

        // when
        final boolean result = round.isEnd();

        // then
        assertThat(result).isTrue();
    }

    @Test
    void 마지막라운드검사_아님() {
        // given
        int totalRound = 3;
        final GameRound round = new GameRound(totalRound);

        for (int i = 0; i < totalRound - 1; i++) {
            round.start();
        }

        // when
        final boolean result = round.isEnd();

        // then
        assertThat(result).isFalse();
    }

}