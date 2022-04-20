package racingcar.game;

import racingcar.car.CarWinners;
import racingcar.car.Cars;
import racingcar.player.Player;

public class Game implements Playable {

    private final Player player;
    private final GameView gameView;
    private Cars cars;
    private GameRound round;

    public Game(final Player player, final GameView gameView) {
        this.player = player;
        this.gameView = gameView;
    }

    @Override
    public void play() {
        init();
        start(cars, round);
        complete();
    }

    private void complete() {
        final CarWinners winners = cars.findWinners();
        gameView.printWinners(winners);
    }

    private void init() {
        inputCars();
        inputGameRound();
    }

    private void inputCars() {
        do {
            gameView.printInputCars();
        } while(!doInputCarSuccess());
    }

    private boolean doInputCarSuccess() {
        try {
            this.cars = player.inputCarNames();
            return true;
        } catch (final IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }

    private void inputGameRound() {
        do {
            gameView.printInputGameRound();
        } while(!doInputRoundSuccess());
    }

    private boolean doInputRoundSuccess() {
        try {
            this.round = player.inputGameRound();
            return true;
        } catch (final IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }

    private void start(final Cars cars, final GameRound round) {
        gameView.printStartGame();
        while (!round.isEnd()) {
            round.start();
            cars.moveRandomly();
            gameView.printCarPosition(cars.getCarsPosition().toViewString());
        }
    }

}
