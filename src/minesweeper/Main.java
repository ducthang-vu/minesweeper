package minesweeper;

import minesweeper.game.GameController;
import minesweeper.game.IGameController;

public class Main {

    public static void main(String[] args) {
        IGameController game = new GameController();
        game.run();
    }
}