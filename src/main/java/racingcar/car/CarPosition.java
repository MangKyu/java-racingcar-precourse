package racingcar.car;

public class CarPosition {

    private static final String POSITION_PRINT_CHARACTER = "-";

    private final String carName;
    private final int position;

    public CarPosition(final String carName, final int position) {
        this.carName = carName;
        this.position = position;
    }

    @Override
    public String toString() {
        return carName + " : " + positionToChars();
    }

    private String positionToChars() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < position; i++) {
            stringBuilder.append(POSITION_PRINT_CHARACTER);
        }
        return stringBuilder.toString();
    }

}
