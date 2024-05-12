package view;

import model.dto.StatusDto;
import model.chessboard.ChessBoardWrapper;
import model.piece.AbstractPiece;
import model.position.Column;
import model.position.Position;
import model.position.Row;

public class OutputView {

    private static final String INIT_CHESSBOARD = "체스판이 초기화 되었습니다.";
    private static final String WRONG_COMMAND = "잘못된 이동 명령입니다!";
    private static final String CHECK = "체크!";
    private static final String CHECKMATE = "체크메이트!";
    private static final String END = "게임을 종료합니다.";
    public void printChessBoard(ChessBoardWrapper chessBoardWrapper) {
        if (chessBoardWrapper != null) {
            System.out.println(" |A|B|C|D|E|F|G|H|");
            int col = 8;
            for (Row row : Row.values()) {
                StringBuilder sb = new StringBuilder();
                sb.append(col--);
                for (Column column : Column.values()) {
                    Position position = Position.unmodifiablePosition(row, column);
                    AbstractPiece piece = chessBoardWrapper.getPiece(position);
                    String name = piece.isWhite() ? piece.getName() : piece.getName().toUpperCase();
                    sb.append("|").append(name);
                }
                sb.append("|");
                System.out.println(sb);
            }
            System.out.println();
        }
    }

    public void printCheck() {
        System.out.println(CHECK);
    }
    public void printInit() {
        System.out.println();
        System.out.println(INIT_CHESSBOARD);
        System.out.println();
    }

    public void printError(Exception e) {
        System.out.println();
        System.out.println(e.getMessage());
        System.out.println();
    }

    public void printStatus(StatusDto status) {
        System.out.println();
        System.out.println(status);
        System.out.println();
    }

    public void printCheckMate() {
        System.out.println();
        System.out.println(CHECKMATE);
        System.out.println();
    }

    public void printEnd() {
        System.out.println();
        System.out.println(END);
        System.out.println();
    }
}
