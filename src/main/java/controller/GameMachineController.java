package controller;

import model.ChessBoard;
import model.Team;
import model.command.Command;
import model.dto.StatusDto;
import model.position.Position;
import model.state.EndState;
import model.state.NotStartState;
import model.state.StartState;
import model.state.State;
import model.validator.Validator;
import view.InputView;
import view.OutputView;

public class GameMachineController {

    private final State startState;
    private final State notStartState;
    private final State endState;
    private State state;
    private Team turn;
    private ChessBoard chessBoard;
    public GameMachineController() {
        this.startState = new StartState(this);
        this.notStartState = new NotStartState(this);
        this.endState = new EndState();
        this.state = this.notStartState;
        this.turn = Team.WHITE;
    }
    public void lazyInit() {
        this.chessBoard = new ChessBoard();
        this.state = startState;

        OutputView.printInit();
    }
    public void run() {
        InputView.inputStart();
        state.start();

        do{
            runInternal();
        } while(!state.isEnd() && !isCheckmate());

        OutputView.printEnd();
    }

    private void runInternal() {
        try{
            OutputView.printChessBoard(chessBoard);
            Command command = InputView.inputCommand(this,turn);
            command.execute(this.state);
        } catch (IllegalArgumentException e){
            OutputView.printError(e);
        }
    }

    private boolean isCheckmate() {
        if (chessBoard != null && chessBoard.isCheckmate(turn)) {
            OutputView.printCheckMate();
            return true;
        }

        return false;
    }
    public void move(Position fromPosition, Position toPosition) {
        Validator.validateTurnRight(chessBoard, fromPosition, this.turn);

        chessBoard.move(fromPosition, toPosition);

        this.turn = turn.changeTurn();

        if (chessBoard.isChecked(turn)) {
            OutputView.printCheck();
        }
    }
    public void status() {
        StatusDto statusDto = chessBoard.getStatus();
        OutputView.printStatus(statusDto);
    }
    public void end() {
        this.state = endState;
    }
}
