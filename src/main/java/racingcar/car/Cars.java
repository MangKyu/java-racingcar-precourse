package racingcar.car;

import racingcar.errors.RacingCarException;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Cars {

    private final Set<Car> carSet;

    public Cars(String[] carNames) {
        if (carNames.length == 0) {
            throw RacingCarException.invalidInput("Invalid input: " + Arrays.toString(carNames));
        }

        carSet = new LinkedHashSet<>();

        for (final String carName : carNames) {
            add(new Car(carName));
        }
    }

    private void add(final Car car) {
        if (carSet.contains(car)) {
            throw RacingCarException.invalidInput("Duplicated input: " + car);
        }

        carSet.add(car);
    }

}