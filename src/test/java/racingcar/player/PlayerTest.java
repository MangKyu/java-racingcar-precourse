package racingcar.player;

import camp.nextstep.edu.missionutils.Console;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
    void 자동차들의이름을입력받는다(final String input) {
        // given
        console.when(Console::readLine)
                .thenReturn(input);

        // when
        final List<String> result = player.inputCarNames();

        // then
        assertThat(result.size()).isNotZero();
    }

}