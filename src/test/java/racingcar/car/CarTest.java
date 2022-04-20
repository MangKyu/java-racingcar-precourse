package racingcar.car;

import camp.nextstep.edu.missionutils.Randoms;
import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import racingcar.errors.RacingCarException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarTest {

    private MockedStatic<Randoms> randoms;

    @BeforeEach
    void init() {
        randoms = Mockito.mockStatic(Randoms.class);
    }

    @AfterEach
    void clear() {
        randoms.close();
    }

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
    @ValueSource(strings = {"a", "ab", "abc", "abcd", "abcde"})
    void equals와hashcode테스트(final String input) {
        // given
        final Car car = new Car(input);

        // when
        final Car result = new Car(input);

        // then
        assertThat(result).isEqualTo(car);
        assertThat(result.hashCode()).isEqualTo(car.hashCode());
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

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7, 8, 9})
    void 무작위이동_성공(final int input) {
        // given
        randoms.when(() -> Randoms.pickNumberInRange(1, 9))
                .thenReturn(input);

        final Car car = new Car("MK");
        final int defaultPosition = car.getCurrentPosition();

        // when
        car.moveRandomly();

        // then
        assertThat(car.getCurrentPosition()).isNotEqualTo(defaultPosition);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void 무작위이동_실패(final int input) {
        // given
        randoms.when(() -> Randoms.pickNumberInRange(1, 9))
                .thenReturn(input);

        final Car car = new Car("MK");
        final int defaultPosition = car.getCurrentPosition();

        // when
        car.moveRandomly();

        // then
        assertThat(car.getCurrentPosition()).isEqualTo(defaultPosition);
    }

    @ParameterizedTest
    @ValueSource(ints = {-3, -2, -1, 0, 10, 11, 12, 13, 14, 15})
    void 무작위이동_실패_잘못된값(final int input) {
        // given
        randoms.when(() -> Randoms.pickNumberInRange(1, 9))
                .thenReturn(input);

        final Car car = new Car("MK");

        // when
        final AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(car::moveRandomly);

        // then
        result.isInstanceOf(IllegalStateException.class).hasMessageContaining(RacingCarException.PREFIX);
    }

    @Test
    void 자동차위치정보조회() {
        // given
        final Car car = new Car("MK");

        // when
        final CarPosition result = car.toCarPosition();

        // then
        assertThat(result).isNotNull();
    }

    @Test
    void comparable구현() {
        // given
        final Car car1 = new Car("MK");
        final Car car2 = new Car("MK");

        // when
        final int result = car1.compareTo(car2);

        // then
        assertThat(result).isZero();
    }

    @Test
    void 같은위치에있는지검사_True() {
        // given
        final Car car1 = new Car("MK");
        final Car car2 = new Car("MK");

        // when
        final boolean result = car1.inSamePosition(car2);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void 같은위치에있는지검사_False() {
        // given
        randoms.when(() -> Randoms.pickNumberInRange(1, 9))
                .thenReturn(5);

        final Car car1 = new Car("MK");
        car1.moveRandomly();
        final Car car2 = new Car("MK");

        // when
        final boolean result = car1.inSamePosition(car2);

        // then
        assertThat(result).isFalse();
    }

}