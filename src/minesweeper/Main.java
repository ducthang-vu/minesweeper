package minesweeper;

import minesweeper.game.GameController;

public class Main {

    public static void main(String[] args) {
        Runnable game = new GameController();
        game.run();
    }
}