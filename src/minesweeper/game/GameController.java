package minesweeper.game;

import minesweeper.cell.Coordinates;
import minesweeper.cell.ICoordinates;
import minesweeper.field.Field;
import minesweeper.field.FieldStatus;
import minesweeper.field.IField;

import java.util.Scanner;

public class GameController implements Runnable {

    IField field;
    IInvoker invoker = new CommandInvoker();
    Scanner scanner = new Scanner(System.in);

    private void makePlayerMove() {
        while (true) {
            System.out.println("Set/unset mines marks or claim a cell as free: ");
            String commandString = scanner.nextLine();
            ICoordinates coordinates = new Coordinates(commandString);

            String[] commandStrings = commandString.split(" ");
            String type = commandStrings[commandStrings.length - 1];

            Command command = switch (type) {
                case "free" -> new Explore(field, coordinates);
                case "mine" -> new ToggleMarkedCell(field, coordinates);
                default -> throw new IllegalStateException("Command not valid");
            };

            invoker.setCommand(command);
            invoker.execute();
            boolean result = invoker.isSuccess();
            if (result) {
                break;
            }
            System.out.println("There is a number here!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        System.out.println("How many mines do you want on the field?");
        int numberOfMines = scanner.nextInt();
        scanner.nextLine();

        field = new Field(numberOfMines);
        System.out.println(field);

        FieldStatus gameStatus = FieldStatus.PENDING;
        while (gameStatus == FieldStatus.PENDING) {
            makePlayerMove();
            System.out.println(field);
            gameStatus = field.getFieldStatus();
        }

        if (field.getFieldStatus() == FieldStatus.LOSS) {
            System.out.println("You stepped on a mine and failed!");
        } else {
            System.out.println("Congratulations! You found all the mines!");
        }
    }

}
