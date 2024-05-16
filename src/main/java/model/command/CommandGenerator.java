package model.command;

public class CommandGenerator {
    private static final String START = "start";
    private static final String STATUS = "status";
    private static final String MOVE = "move";
    private static final String END = "end";
    public static Command createCommand(String[] commandInfo) {
        String command = commandInfo[0];

        if (START.equals(command)) {
            return new StartCommand();
        }
        if (STATUS.equals(command)) {
            return new StatusCommand();
        }
        if (MOVE.equals(command)) {
            return new MoveCommand(commandInfo);
        }
        if (END.equals(command)) {
            return new EndCommand();
        }

        throw new IllegalArgumentException("가능한 명령이 아닙니다");
    }
}
