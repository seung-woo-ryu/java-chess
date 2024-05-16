package model.command;

import model.state.State;

public class StatusCommand implements Command{

    @Override
    public void execute(State state) {
        state.status();
    }
}
