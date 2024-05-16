package model.command;

import model.state.State;

public interface Command {
    void execute(State state);
}
