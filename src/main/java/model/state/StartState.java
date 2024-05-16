package model.state;

import controller.GameMachineController;
import model.position.Position;
import model.validator.Validator;

public class StartState implements State{
    private static final String ALREADY_START = "이미 시작했습니다!";
    private final GameMachineController gameMachineController;

    public StartState(GameMachineController gameMachineController) {
        this.gameMachineController = gameMachineController;
    }

    @Override
    public void move(String[] command) {
        Validator.validateCommand(command);

        Position fromPosition = mapStringToPosition(command[1]);
        Position toPosition = mapStringToPosition(command[2]);

        gameMachineController.move(fromPosition, toPosition);
    }

    private Position mapStringToPosition(String rowColumn) {
        Validator.validatePosition(rowColumn);

        final char rowChar = rowColumn.charAt(0);
        final char columnChar = rowColumn.charAt(1);

        return Position.of(rowChar, columnChar);
    }

    @Override
    public void status() {
        gameMachineController.status();
    }

    @Override
    public void start() {
        throw new IllegalArgumentException(ALREADY_START);
    }

    @Override
    public void end() {
        gameMachineController.end();
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean isNotStart() {
        return false;
    }
}
