package racingcar;

import racingcar.game.Game;
import racingcar.game.GameView;
import racingcar.game.Playable;
import racingcar.player.Player;

public class Application {

    public static void main(String[] args) {
        final Playable game = playable();
        game.play();
    }

    private static Playable playable() {
        return new Game(new Player(), new GameView());
    }

}
