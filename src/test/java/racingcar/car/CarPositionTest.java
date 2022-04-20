package racingcar.car;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CarPositionTest {

    private final String carName = "MK";

    @Test
    void 출력용메소드테스트() {
        // given
        final Car car = new Car(carName);
        final CarPosition carPosition = car.toCarPosition();

        // when
        final String result = carPosition.toViewString();

        // then
        assertThat(result).contains(carName);
    }

}