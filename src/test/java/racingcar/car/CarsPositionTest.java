package racingcar.car;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CarsPositionTest {

    private final String carName1 = "MK1";
    private final String carName2 = "MK2";

    @Test
    void 출력용메소드테스트() {
        // given
        final Cars cars = new Cars(new String[]{carName1, carName2});

        // when
        final CarsPosition result = cars.getCarsPosition();

        // then
        assertThat(result).isNotNull();
        assertThat(result.toViewString()).contains(carName1);
        assertThat(result.toViewString()).contains(carName2);
    }

}