package view;

import model.dto.StatusDto;
import model.chessboard.ChessBoardWrapper;
import model.piece.AbstractPiece;
import model.position.Column;
import model.position.Position;
import model.position.Row;

public class OutputView {

    private static final String INIT_CHESSBOARD = "체스판이 초기화 되었습니다.";
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
                    Position position = Position.of(row, column);
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
        printString(CHECK);
    }
    public void printInit() {
        printString(INIT_CHESSBOARD);
    }

    public void printError(Exception e) {
        printString(e.getMessage());
    }

    public void printStatus(StatusDto status) {
        printString(status.toString());
    }

    public void printCheckMate() {
        printString(CHECKMATE);
    }

    public void printEnd() {
        printString(END);
    }

    private static void printString(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println();
    }

}
