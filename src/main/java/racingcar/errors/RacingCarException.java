package racingcar.errors;

public final class RacingCarException {

    public static final String PREFIX = "[ERROR] ";

    private RacingCarException() {
        throw new UnsupportedOperationException("Cannot create new Object");
    }

    public static RuntimeException invalidInput(final String message) {
        return new IllegalArgumentException(PREFIX + message);
    }

    public static RuntimeException invalidState(final String message) {
        return new IllegalStateException(PREFIX + message);
    }

}
