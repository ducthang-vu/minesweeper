package minesweeper.game;

/**
 * Interface of game command invoker.
 *
 * @see {@link Command}
 */
public interface IInvoker {
    /**
     * Execute command.
     */
    void execute();

    /**
     * Test whether command was executed correctly.
     *
     * @return boolean.
     */
    boolean isSuccess();

    /**
     * Set next command to be executed.
     *
     * @param command - the next command.
     */
    void setCommand(Command command);
}
