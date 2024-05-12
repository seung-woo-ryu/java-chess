package model.state;

import controller.GameMachine;

public class EndState implements State{

    private static final String GAME_OVER = "게임이 종료된 상태입니다";
    private final GameMachine gameMachine;

    public EndState(GameMachine gameMachine) {
        this.gameMachine = gameMachine;
    }

    @Override
    public void move(String command) {
        throw new IllegalArgumentException(GAME_OVER);
    }

    @Override
    public void status() {
        throw new IllegalArgumentException(GAME_OVER);
    }

    @Override
    public void start() {
        throw new IllegalArgumentException(GAME_OVER);
    }

    @Override
    public void end() {
        throw new IllegalArgumentException(GAME_OVER);
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
