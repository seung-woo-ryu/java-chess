import controller.GameMachine;
import view.InputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        GameMachine gameMachine = new GameMachine(inputView);

        gameMachine.run();
    }
}
