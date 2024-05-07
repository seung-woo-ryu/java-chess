package view;

import java.util.concurrent.ScheduledExecutorService;
import model.chessboard.ChessBoardWrapper;
import model.piece.AbstractPiece;
import model.position.Column;
import model.position.Position;
import model.position.Row;

public class OutputView {

    public void printChessBoard(ChessBoardWrapper chessBoardWrapper) {
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
    }

}
