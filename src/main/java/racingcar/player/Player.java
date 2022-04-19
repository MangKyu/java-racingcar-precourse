package racingcar.player;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;

public class Player {

    public List<String> inputCarNames() {
        final String input = Console.readLine();
        final String[] split = input.split(",");

        if (!isValid(split)) {
            throw new IllegalArgumentException("[ERROR]");
        }

        return Arrays.asList(split);
    }

    private boolean isValid(String[] split) {
        for (final String value : split) {
            if (value.length() < 1 || value.length() > 5) {
                return false;
            }
        }
        return true;
    }

}