package racingcar.player;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.HashSet;
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
        if (split.length == 0) {
            return false;
        }

        for (final String value : split) {
            if (value.length() < 1 || value.length() > 5) {
                return false;
            }
        }

        return new HashSet<>(Arrays.asList(split)).size() == split.length;
    }

}