package racingcar.player;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;

public class Player {

    public List<String> inputCarNames() {
        final String input = Console.readLine();

        return Arrays.asList(input.split(","));
    }

}