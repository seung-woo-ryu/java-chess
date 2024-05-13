package state;

import controller.GameMachine;
import model.position.Position;
import validator.Validator;

public class StartState implements State{
    private static final String ALREADY_START = "이미 시작했습니다!";
    private final GameMachine gameMachine;

    public StartState(GameMachine gameMachine) {
        this.gameMachine = gameMachine;
    }

    @Override
    public void move(String command) {
        String[] split = command.split(" ");

        Validator.validateCommand(split);

        Position fromPosition = mapStringToPosition(split[1]);
        Position toPosition = mapStringToPosition(split[2]);

        gameMachine.move(fromPosition, toPosition);
    }

    private Position mapStringToPosition(String rowColumn) {
        Validator.validatePosition(rowColumn);

        final char rowChar = rowColumn.charAt(0);
        final char columnChar = rowColumn.charAt(1);

        return Position.of(rowChar, columnChar);
    }

    @Override
    public void status() {
        gameMachine.status();
    }

    @Override
    public void start() {
        throw new IllegalArgumentException(ALREADY_START);
    }

    @Override
    public void end() {
        gameMachine.end();
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
