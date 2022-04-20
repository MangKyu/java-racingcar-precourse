package racingcar.car;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class CarWinnersTest {

    private final String carName1 = "MK1";
    private final String carName2 = "MK2";

    @Test
    void 우승자가1인경우의출력() {
        // given
        final CarWinners carWinners = new CarWinners(Collections.singletonList(new Car(carName1)));

        // when
        final String result = carWinners.toString();

        // then
        assertThat(result).contains(carName1)
                .doesNotContain(CarWinners.DELIMITER);
    }

    @Test
    void 우승자가2인경우의출력() {
        // given
        final CarWinners carWinners = new CarWinners(Arrays.asList(new Car(carName1), new Car(carName2)));

        // when
        final String result = carWinners.toString();

        // then
        assertThat(result).contains(carName1)
                .contains(CarWinners.DELIMITER)
                .contains(carName2);
    }
}