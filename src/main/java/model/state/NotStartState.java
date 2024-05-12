package model.state;

import controller.GameMachine;

public class NotStartState implements State{
    private static final String ONLY_START_PLZ = "start를 입력해주세요!";
    private final GameMachine gameMachine;

    public NotStartState(GameMachine gameMachine) {
        this.gameMachine = gameMachine;
    }

    @Override
    public void move(String command) {
        throw new IllegalArgumentException(ONLY_START_PLZ);
    }

    @Override
    public void status() {
        throw new IllegalArgumentException(ONLY_START_PLZ);
    }

    @Override
    public void start() {
        gameMachine.init();
        gameMachine.startGame();
    }

    @Override
    public void end() {
        throw new IllegalArgumentException(ONLY_START_PLZ);
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
