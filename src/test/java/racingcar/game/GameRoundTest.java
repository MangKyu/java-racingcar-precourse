package racingcar.game;

import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.errors.RacingCarException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameRoundTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -2, -3 -4})
    void 이동할횟수입력성공_1보다작은입력(final int input) {
        // given

        // when
        final AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> new GameRound(input));

        // then
        result.isInstanceOf(IllegalArgumentException.class).hasMessageContaining(RacingCarException.PREFIX);
    }

}