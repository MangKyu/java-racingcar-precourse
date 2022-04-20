package racingcar.car;

import racingcar.errors.RacingCarException;

import java.util.*;

public class Cars implements Movable {

    private final Set<Car> carSet;

    public Cars(final String[] carNames) {
        if (isEmpty(carNames)) {
            throw RacingCarException.invalidInput("Invalid input: " + Arrays.toString(carNames));
        }

        carSet = new LinkedHashSet<>();

        for (final String carName : carNames) {
            add(new Car(carName));
        }
    }

    private boolean isEmpty(final String[] carNames) {
        return carNames.length == 0;
    }

    private void add(final Car car) {
        if (carSet.contains(car)) {
            throw RacingCarException.invalidInput("Duplicated input: " + car);
        }

        carSet.add(car);
    }

    @Override
    public void moveRandomly() {
        for (final Car car : carSet) {
            car.moveRandomly();
        }
    }

    public CarsPosition getCarsPosition() {
        return new CarsPosition(carSet);
    }

    public CarWinners findWinners() {
        final Car firstWinner = findFirstWinner();
        return findWinners(firstWinner);
    }

    private Car findFirstWinner() {
        return Collections.max(this.carSet);
    }

    private CarWinners findWinners(final Car firstWinner) {
        final List<Car> winnerList = new ArrayList<>();
        for (final Car car : carSet) {
            addSamePositionOnly(winnerList, firstWinner, car);
        }
        return new CarWinners(winnerList);
    }

    private void addSamePositionOnly(final List<Car> winnerList, final Car target, final Car source) {
        if (target.inSamePosition(source)) {
            winnerList.add(source);
        }
    }

}