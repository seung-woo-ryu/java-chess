package model.command;

import model.state.State;

public class MoveCommand implements Command {

    private String[] command;

    public MoveCommand(String[] command) {
        this.command = command;
    }

    @Override
    public void execute(State state) {
        state.move(command);
    }
}
