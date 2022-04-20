package racingcar.car;

import camp.nextstep.edu.missionutils.Randoms;
import racingcar.errors.RacingCarException;

import java.util.Objects;

public class Car implements Movable {

    private static final int MIN_CAR_NAME_LENGTH = 1;
    private static final int MAX_CAR_NAME_LENGTH = 5;
    private final String carName;
    private final Position position;

    public Car(final String carName) {
        if (notInLength(carName)) {
            throw RacingCarException.invalidInput("Invalid carName: " + carName);
        }

        this.carName = carName;
        this.position = new Position();
    }


    private boolean notInLength(final String carName) {
        return carName.length() < MIN_CAR_NAME_LENGTH || carName.length() > MAX_CAR_NAME_LENGTH;
    }

    @Override
    public void moveRandomly() {
        position.move(Randoms.pickNumberInRange(Position.MIN_POSITION, Position.MAX_POSITION));
    }

    public int getCurrentPosition() {
        return position.value;
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

    static class Position {

        private static final int MIN_POSITION = 1;
        private static final int MOVE_LOWER_BOUNDARY = 4;
        private static final int MAX_POSITION = 9;
        private int value;

        void move(final int input) {
            if (notInRange(input)) {
                throw RacingCarException.invalidState("Random value not in range: " + input);
            }

            if (doMove(input)) {
                move();
            }
        }

        private boolean notInRange(final int input) {
            return input < MIN_POSITION || input > MAX_POSITION;
        }

        private boolean doMove(final int result) {
            return MOVE_LOWER_BOUNDARY <= result && result <= MAX_POSITION;
        }

        private void move() {
            value++;
        }
    }

}