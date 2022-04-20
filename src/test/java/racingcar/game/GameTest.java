package racingcar.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import racingcar.player.Player;

class GameTest {

    private Game game;
    private Player player;

    @BeforeEach
    void init() {
        player = new Player();
        game = new Game(player, new GameView());
    }

    @Test
    void temp() {
        System.out.println("A");
    }

}