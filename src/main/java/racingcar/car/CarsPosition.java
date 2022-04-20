package racingcar.car;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CarsPosition {

    private final List<CarPosition> carPositionList;

    public CarsPosition(final Set<Car> cars) {
        carPositionList = new ArrayList<>();

        for (final Car car : cars) {
            carPositionList.add(car.toCarPosition());
        }
    }

    public String toViewString() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final CarPosition position : carPositionList) {
            stringBuilder.append(position).append("\n");
        }
        return stringBuilder.toString();
    }
}
