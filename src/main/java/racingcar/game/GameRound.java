package racingcar.game;

import racingcar.errors.RacingCarException;

public class GameRound {

    private static final int MIN_ROUND = 1;
    private final int round;

    public GameRound(final int round) {
        if (round < MIN_ROUND) {
            throw RacingCarException.invalidInput("Invalid round: " + round);
        }
        this.round = round;
    }



}
