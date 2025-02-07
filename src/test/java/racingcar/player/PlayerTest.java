package racingcar.player;

import camp.nextstep.edu.missionutils.Console;
import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import racingcar.car.Cars;
import racingcar.errors.RacingCarException;
import racingcar.game.GameRound;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    private Player player;
    private MockedStatic<Console> console;

    @BeforeEach
    void init() {
        player = new Player();
        console = Mockito.mockStatic(Console.class);
    }

    @AfterEach
    void destroy() {
        console.close();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "ab,abc", "a,ab,abc", "a,ab,abc,abcd"})
    void 자동차들의이름을입력성공(final String input) {
        // given
        console.when(Console::readLine)
                .thenReturn(input);

        // when
        final Cars result = player.inputCarNames();

        // then
        assertThat(result).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "abcdef,abc", "abc,abcdef", "a,a"})
    void 자동차들의이름을입력실패_잘못된입력(final String input) {
        // given
        console.when(Console::readLine)
                .thenReturn(input);

        // when
        final AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> player.inputCarNames());

        // then
        result.isInstanceOf(IllegalArgumentException.class).hasMessageContaining(RacingCarException.PREFIX);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void 이동할횟수입력성공(final int input) {
        // given
        console.when(Console::readLine)
                .thenReturn(String.valueOf(input));

        // when
        final GameRound result = player.inputGameRound();

        // then
        assertThat(result).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "+", "?", "MangKyu"})
    void 이동할횟수입력성공_정수가아닌입력(final String input) {
        // given
        console.when(Console::readLine)
                .thenReturn(input);

        // when
        final AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> player.inputGameRound());

        // then
        result.isInstanceOf(IllegalArgumentException.class).hasMessageContaining(RacingCarException.PREFIX);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -2, -3 -4})
    void 이동할횟수입력성공_1보다작은입력(final int input) {
        // given
        console.when(Console::readLine)
                .thenReturn(String.valueOf(input));

        // when
        final AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> player.inputGameRound());

        // then
        result.isInstanceOf(IllegalArgumentException.class).hasMessageContaining(RacingCarException.PREFIX);

    }
}