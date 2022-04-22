package racingcar.game;

import racingcar.car.CarWinners;
import racingcar.car.CarsPosition;

public final class GameView {

    public void printInputCars() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
    }

    public void printInputGameRound() {
        System.out.println("시도할 회수는 몇회인가요?");
    }

    public void printStartGame() {
        System.out.println("\n실행 결과");
    }

    public void printCarPositions(final CarsPosition positions) {
        System.out.println(positions);
    }

    public void printWinners(final CarWinners winners) {
        System.out.println("최종 우승자: " + winners);
    }
}