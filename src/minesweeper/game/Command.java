package minesweeper.game;

/**
 * Interface for command objects, implementing the command pattern.
 */
public interface Command {
    /**
     * Execute command.
     */
    void execute();

    /**
     * Test whether command was executed correctly.
     *
     * @return - boolean
     */
    boolean isSuccess();
}
