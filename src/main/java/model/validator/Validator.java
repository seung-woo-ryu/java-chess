package model.validator;

import model.ChessBoard;
import model.Team;
import model.piece.AbstractPiece;
import model.position.Position;

public class Validator {
    private static final String START = "start";
    private static final String STATUS = "status";
    private static final String MOVE = "move";
    private static final String END = "end";
    private Validator() {
    }

    public static void validateCommand(String[] split) {
        if (split.length != 3) {
            throw new IllegalArgumentException("ex) move 2f 3f. 예시와 같이 입력해주세요");
        }
    }

    public static void validatePosition(String rowColumn) {
        if (rowColumn.length() != 2) {
            throw new IllegalArgumentException("좌표는 2글자(ex:2f)여야 합니다!");
        }
    }
    public static void validateTurnRight(ChessBoard chessBoard, Position fromPosition, Team currentTeam) {
        AbstractPiece piece = chessBoard.getPiece(fromPosition);

        if (!piece.isSameTeam(currentTeam)) {
            throw new IllegalArgumentException(String.format("잘못된 명령입니다. 현재 %s이 움직일 차례입니다.", currentTeam));
        }
    }

    public static void validatePossibleCommand(String[] command) {
        validateCommandSplit(command);

        if (!(STATUS.equals(command[0]) || END.equals(command[0]) || MOVE.equals(command[0]))) {
            throw new IllegalArgumentException("가능한 명령이 아닙니다!");
        }
    }

    private static void validateCommandSplit(String[] command) {
        if (!(command.length == 1 || command.length == 3)) {
            throw new IllegalArgumentException("가능한 명령이 아닙니다");
        }
    }

    public static void validateCommandIsStart(String command) {
        if (!START.equals(command)) {
            throw new IllegalArgumentException("start 명령만 가능합니다");
        }
    }
}
