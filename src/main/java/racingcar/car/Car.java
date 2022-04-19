package racingcar.car;

import racingcar.errors.RacingCarException;

import java.util.Objects;

public class Car {

    private static final int MIN_CAR_NAME_LENGTH = 1;
    private static final int MAX_CAR_NAME_LENGTH = 5;
    private final String carName;

    public Car(final String carName) {
        if (notInLength(carName)) {
            throw RacingCarException.invalidInput("Invalid carName: " + carName);
        }

        this.carName = carName;
    }


    private boolean notInLength(final String carName) {
        return carName.length() < MIN_CAR_NAME_LENGTH || carName.length() > MAX_CAR_NAME_LENGTH;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Car car = (Car) o;
        return Objects.equals(carName, car.carName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carName);
    }

    @Override
    public String toString() {
        return "Car{" +
                "carName='" + carName + '\'' +
                '}';
    }
}