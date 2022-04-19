package racingcar.player;

import camp.nextstep.edu.missionutils.Console;
import racingcar.car.Cars;

public class Player {

    public Cars inputCarNames() {
        final String[] carNames = split(Console.readLine());
        return new Cars(carNames);
    }

    private String[] split(String input) {
        return input.split(",");
    }

    public int inputGameRound() {
        final String input = Console.readLine();
        return toInt(input);
    }

    private int toInt(final String input) {
        return Integer.parseInt(input);
    }

}