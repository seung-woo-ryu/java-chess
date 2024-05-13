package validator;

import static constants.Constants.*;

import model.Team;
import model.piece.AbstractPiece;

public class Validator {
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
    public static void validateCommandOnlyStartPossible(String command) {
        if (!"start".equals(command.trim())) {
            throw new IllegalArgumentException("start만 가능합니다");
        }
    }

    public static void validateTurnRight(AbstractPiece piece, Team currentTeam) {
        if (!piece.isSameTeam(currentTeam)) {
            throw new IllegalArgumentException(String.format("잘못된 명령입니다. 현재 %s이 움직일 차례입니다.", currentTeam));
        }
    }

    public static void validatePossibleCommand(String command) {
        if (!(START.equals(command) || STATUS.equals(command) || END.equals(command) ||(command!= null && command.startsWith(MOVE)))) {
            throw new IllegalArgumentException("가능한 명령이 아닙니다!");
        }
    }
}
