package model.chessboard;

import model.piece.AbstractTest;
import model.position.Column;
import model.position.Position;
import model.position.Row;
import model.team.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardWrapperTest extends AbstractTest {

    @Test
    @DisplayName("다음 수가 체크 상태를 유발하면 Exception 발생")
    void 체크_상태_판단_기능() {
        // given
        Position blackPawn7E = Position.unmodifiablePosition(Row.SEVEN, Column.E);
        Position blackPawn6E = Position.unmodifiablePosition(Row.SIX, Column.E);

        Position blackBishop8F = Position.unmodifiablePosition(Row.EIGHT, Column.F);
        Position blackBishop4B = Position.unmodifiablePosition(Row.FOUR, Column.B);

        Position whitePawn2D = Position.unmodifiablePosition(Row.TWO, Column.D);
        Position whitePawn3D = Position.unmodifiablePosition(Row.THREE, Column.D);
        // when
        chessBoardWrapper.move(blackPawn7E, blackPawn6E);
        chessBoardWrapper.move(blackBishop8F, blackBishop4B);
        chessBoardWrapper.move(blackPawn7E, blackPawn6E);
        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            chessBoardWrapper.move(whitePawn2D, whitePawn3D);
        });
    }

    @Test
    @DisplayName("내가 둘 수 있는 모든 수가 체크 상태를 유발하면 Exception 발생")
    void 체크메이트_상태_판단() {
        // given
        Position blackPawn7E = Position.unmodifiablePosition(Row.SEVEN, Column.E);
        Position blackPawn5E = Position.unmodifiablePosition(Row.FIVE, Column.E);

        Position whitePawn2F = Position.unmodifiablePosition(Row.TWO, Column.F);
        Position whitePawn3F = Position.unmodifiablePosition(Row.THREE, Column.F);

        Position whitePawn2G = Position.unmodifiablePosition(Row.TWO, Column.G);
        Position whitePawn4G = Position.unmodifiablePosition(Row.FOUR, Column.G);

        Position blackQueen8D = Position.unmodifiablePosition(Row.EIGHT, Column.D);
        Position blackQueen4H = Position.unmodifiablePosition(Row.FOUR, Column.H);
        // when
        chessBoardWrapper.move(blackPawn7E, blackPawn5E);
        chessBoardWrapper.move(whitePawn2F, whitePawn3F);
        chessBoardWrapper.move(whitePawn2G, whitePawn4G);
        chessBoardWrapper.move(blackQueen8D, blackQueen4H);
        // then
        Assertions.assertTrue(chessBoardWrapper.isCheckmate(Team.WHITE));
    }
}