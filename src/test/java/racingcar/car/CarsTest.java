package racingcar.car;

import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import racingcar.errors.RacingCarException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarsTest {

    @ParameterizedTest
    @MethodSource("validCarNames")
    void 자동차들의이름을입력성공(final String[] input) {
        // given

        // when
        final Cars result = new Cars(input);

        // then
        assertThat(result).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("invalidCarNames")
    void 자동차들의이름을입력실패_잘못된입력(final String[] input) {
        // given

        // when
        final AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> new Cars(input));

        // then
        result.isInstanceOf(IllegalArgumentException.class).hasMessageContaining(RacingCarException.PREFIX);
    }

    @Test
    void 자동차들의위치조회() {
        // given
        final Cars cars = new Cars(new String[]{"a", "ab", "abc"});

        // when
        final CarsPosition result = cars.getCarsPosition();

        // then
        assertThat(result).isNotNull();
    }

    @Test
    void 자동차들우승자조회() {
        // given
        final Cars cars = new Cars(new String[]{"a", "ab", "abc"});

        // when
        final CarWinners result = cars.findWinners();

        // then
        assertThat(result).isNotNull();
    }

    private static Stream<Arguments> validCarNames() {
        return Stream.of(
                Arguments.of((Object) new String[]{"a"}),
                Arguments.of((Object) new String[]{"ab", "abc"}),
                Arguments.of((Object) new String[]{"a", "ab", "abc"}),
                Arguments.of((Object) new String[]{"a", "ab", "abc", "abcd"})
        );
    }

    private static Stream<Arguments> invalidCarNames() {
        return Stream.of(
                Arguments.of((Object) new String[]{""}),
                Arguments.of((Object) new String[]{"abcdef", "abc"}),
                Arguments.of((Object) new String[]{"abc", "abcdef"}),
                Arguments.of((Object) new String[]{"a", "a"})
        );
    }

}