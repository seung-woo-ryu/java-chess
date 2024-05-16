package model.command;

import model.state.State;

public class StartCommand implements Command{

    @Override
    public void execute(State state) {
        state.start();
    }
}
