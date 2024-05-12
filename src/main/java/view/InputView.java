package view;


import java.util.Scanner;
import controller.GameMachine;
import model.Team;
import model.state.NotStartState;
import model.state.State;

public class InputView {
    private final Scanner scanner;
    private static final String START = """ 
        체스 게임에 오신걸 환영합니다. 
        시작하시려면 "start"를 입력해주세요.
        """;
    private static final String INPUT_COMMAND = """
        %s 차례입니다!
        명령어를 입력해주세요("move row(1~8)column(a~h)", "status", "end")
        """;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String inputCommand(GameMachine gameMachine) {
        if (gameMachine.isNotStartedState()) {
            System.out.println(START);
            String command = scanner.nextLine();
            return getOnlyStart(command);
        }

        System.out.println(String.format(INPUT_COMMAND, gameMachine.getTurn()));
        String command = scanner.nextLine();
        return command;
    }

    private String getOnlyStart(String command) {
        if ("start".equals(command)) {
            return command;
        }
        throw new IllegalArgumentException("start만 가능합니다");
    }
}
