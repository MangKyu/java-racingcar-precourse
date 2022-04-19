package racingcar.car;

import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.errors.RacingCarException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarTest {

    @ParameterizedTest
    @ValueSource(strings = {"a", "ab", "abc", "abcd", "abcde"})
    void 자동차생성성공(final String input) {
        // given

        // when
        final Car result = new Car(input);

        // then
        assertThat(result).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "abcdef"})
    void 자동차생성실패_잘못된입력(final String input) {
        // given

        // when
        final AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> new Car(input));

        // then
        result.isInstanceOf(IllegalArgumentException.class).hasMessageContaining(RacingCarException.PREFIX);
    }

}