package view;


import java.util.Scanner;
import controller.GameMachineController;
import model.Team;
import model.command.Command;
import model.command.CommandGenerator;
import model.state.State;
import model.validator.Validator;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);;

    private static final String INPUT_COMMAND = """
        %s 차례입니다!
        명령어를 입력해주세요("move row(1~8)column(a~h)", "status", "end")
        """;
    private static final String START = """ 
        체스 게임에 오신걸 환영합니다. 
        시작하시려면 "start"를 입력해주세요.
        """;


    public static Command inputCommand(GameMachineController gameMachineController, Team turn) {
        System.out.println(String.format(INPUT_COMMAND, turn));

        String[] command = scanner.nextLine().split(" ");

        Validator.validatePossibleCommand(command);

        return CommandGenerator.createCommand(command);
    }

    public static void inputStart() {
        try {
            System.out.println(START);

            String command = scanner.nextLine().trim();

            Validator.validateCommandIsStart(command);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            inputStart();
        }
    }
}
