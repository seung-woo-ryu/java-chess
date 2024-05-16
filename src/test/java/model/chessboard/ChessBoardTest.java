package model.chessboard;

import model.piece.AbstractTest;
import model.position.Column;
import model.position.Position;
import model.position.Row;
import model.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest extends AbstractTest {

    @Test
    @DisplayName("다음 수가 체크 상태를 유발하면 Exception 발생")
    void 체크_상태_판단_기능() {
        // given
        Position blackPawn7E = Position.of(Row.SEVEN, Column.E);
        Position blackPawn6E = Position.of(Row.SIX, Column.E);

        Position blackBishop8F = Position.of(Row.EIGHT, Column.F);
        Position blackBishop4B = Position.of(Row.FOUR, Column.B);

        Position whitePawn2D = Position.of(Row.TWO, Column.D);
        Position whitePawn3D = Position.of(Row.THREE, Column.D);
        // when
        chessBoard.move(blackPawn7E, blackPawn6E);
        chessBoard.move(blackBishop8F, blackBishop4B);
        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            chessBoard.move(whitePawn2D, whitePawn3D);
        });
    }

    @Test
    @DisplayName("내가 둘 수 있는 모든 수가 체크 상태를 유발하면 Exception 발생")
    void 체크메이트_상태_판단() {
        // given
        Position blackPawn7E = Position.of(Row.SEVEN, Column.E);
        Position blackPawn5E = Position.of(Row.FIVE, Column.E);

        Position whitePawn2F = Position.of(Row.TWO, Column.F);
        Position whitePawn3F = Position.of(Row.THREE, Column.F);

        Position whitePawn2G = Position.of(Row.TWO, Column.G);
        Position whitePawn4G = Position.of(Row.FOUR, Column.G);

        Position blackQueen8D = Position.of(Row.EIGHT, Column.D);
        Position blackQueen4H = Position.of(Row.FOUR, Column.H);
        // when
        chessBoard.move(blackPawn7E, blackPawn5E);
        chessBoard.move(whitePawn2F, whitePawn3F);
        chessBoard.move(whitePawn2G, whitePawn4G);
        chessBoard.move(blackQueen8D, blackQueen4H);
        // then
        Assertions.assertTrue(chessBoard.isCheckmate(Team.WHITE));
    }
}