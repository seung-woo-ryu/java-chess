package controller;

import model.Team;
import model.chessboard.ChessBoardWrapper;
import model.dto.StatusDto;
import model.piece.AbstractPiece;
import model.position.Position;
import model.state.EndState;
import model.state.NotStartState;
import model.state.StartState;
import model.state.State;
import view.InputView;
import view.OutputView;

public class GameMachine {

    private final InputView inputView;
    private final OutputView outputView;
    private static final String START = "start";
    private static final String STATUS = "status";
    private static final String MOVE = "move";
    private static final String END = "end";
    private final State startState;
    private final State notStartState;
    private final State endState;
    private State state;
    private Team turn;
    private ChessBoardWrapper chessBoardWrapper;
    public GameMachine(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;

        this.startState = new StartState(this);
        this.notStartState = new NotStartState(this);
        this.endState = new EndState(this);
        this.state = this.notStartState;

        this.turn = Team.WHITE;
    }
    public void init() {
        chessBoardWrapper = new ChessBoardWrapper();
    }
    public Team getTurn() {
        return turn;
    }
    public void run() {
        do{
            runInternal();
        } while(!state.isEnd() && !isCheckmate());

        outputView.printEnd();
    }

    private void runInternal() {
        try{
            outputView.printChessBoard(chessBoardWrapper);
            operate();
        } catch (IllegalArgumentException e){
            outputView.printError(e);
        }
    }

    private boolean isCheckmate() {
        if (chessBoardWrapper != null && chessBoardWrapper.isCheckmate(turn)) {
            outputView.printCheckMate();
            return true;
        }

        return false;
    }

    private void operate() {
        String command = inputView.inputCommand(this);

        if (START.equals(command)) {
            state.start();
        }
        else if (command!= null && command.startsWith(MOVE)) {
            state.move(command);
        }
        else if (STATUS.equals(command)) {
            state.status();
        }
        else if (END.equals(command)) {
            state.end();
        }
        else {
            throw new IllegalArgumentException("가능한 명령이 아닙니다!");
        }
    }
    public void start() {
        this.state = startState;
        outputView.printInit();
    }
    public void move(Position fromPosition, Position toPosition) {
        validateTurnIsRight(fromPosition);
        doMoveAndChangeTurn(fromPosition, toPosition);
        printCheckIfChecked();
    }

    private void doMoveAndChangeTurn(Position fromPosition, Position toPosition) {
        chessBoardWrapper.move(fromPosition, toPosition);
        this.turn = turn.changeTurn();
    }

    private void printCheckIfChecked() {
        if (chessBoardWrapper.isChecked(turn)) {
            outputView.printCheck();
        }
    }

    public void status() {
        StatusDto statusDto = chessBoardWrapper.getStatus();
        outputView.printStatus(statusDto);
    }

    public void end() {
        this.state = endState;
    }
    private void validateTurnIsRight(Position fromPosition) {
        if (!isSameTeam(fromPosition)) {
            throw new IllegalArgumentException(String.format("잘못된 명령입니다. 현재 %s이 움직일 차례입니다.", this.turn));
        }
    }
    private boolean isSameTeam(Position fromPosition) {
        AbstractPiece piece = chessBoardWrapper.getPiece(fromPosition);
        return piece.isSameTeam(this.turn);
    }

    public boolean isNotStartedState() {
        return this.state.equals(this.notStartState);
    }
}
