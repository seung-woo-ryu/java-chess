package model.command;

import model.state.State;

public class EndCommand implements Command{

    @Override
    public void execute(State state) {
        state.end();
    }
}
