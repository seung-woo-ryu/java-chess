import controller.GameMachine;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        InputView inputView = new InputView();
        GameMachine gameMachine = new GameMachine(inputView, outputView);

        gameMachine.run();
    }
}
