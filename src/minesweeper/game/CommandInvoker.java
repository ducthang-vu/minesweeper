package minesweeper.game;

public class CommandInvoker implements IInvoker {
    Command command;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        this.command.execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSuccess() {
        return command.isSuccess();
    }
}
