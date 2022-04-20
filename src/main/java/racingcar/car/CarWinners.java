package racingcar.car;

import java.util.List;

public class CarWinners {

    public static final String DELIMITER = ", ";
    private final List<Car> carList;

    public CarWinners(final List<Car> carList) {
        this.carList = carList;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final Car car : carList) {
            stringBuilder.append(car).append(DELIMITER);
        }

        if (stringBuilder.length() != 0) {
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(DELIMITER));
        }

        return stringBuilder.toString();
    }

}
