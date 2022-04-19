package racingcar.car;

import racingcar.errors.RacingCarException;

import java.util.Objects;

public class Car {

    private final String carName;

    public Car(final String carName) {
        if (carName.length() < 1 || carName.length() > 5) {
            throw RacingCarException.invalidInput("Invalid carName: " + carName);
        }

        this.carName = carName;
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