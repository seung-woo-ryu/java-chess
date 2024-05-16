package model.state;

import controller.GameMachineController;

public class NotStartState implements State{
    private static final String ONLY_START_PLZ = "start를 입력해주세요!";
    private final GameMachineController gameMachineController;
    public NotStartState(GameMachineController gameMachineController) {
        this.gameMachineController = gameMachineController;
    }
    @Override
    public void move(String[] command) {
        throw new IllegalArgumentException(ONLY_START_PLZ);
    }

    @Override
    public void status() {
        throw new IllegalArgumentException(ONLY_START_PLZ);
    }

    @Override
    public void start() {
        gameMachineController.lazyInit();
    }

    @Override
    public void end() {
        throw new IllegalArgumentException(ONLY_START_PLZ);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean isNotStart() {
        return true;
    }
}
