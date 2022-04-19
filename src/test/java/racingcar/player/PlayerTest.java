package racingcar.player;

import camp.nextstep.edu.missionutils.Console;
import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;

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
        final List<String> result = player.inputCarNames();

        // then
        assertThat(result.size()).isNotZero();
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
        result.isInstanceOf(IllegalArgumentException.class).hasMessageContaining("[ERROR]");
    }

}