package model.piece;

import static org.junit.jupiter.api.Assertions.*;

import model.position.Column;
import model.position.Position;
import model.position.Row;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest extends AbstractTest{
    @Test
    @DisplayName("기물이 막지 않는 위치까지 이동 가능")
    void 이동_가능_01() {
        // given
        Position pawn2A = Position.unmodifiablePosition(Row.TWO, Column.A);
        Position pawn4A = Position.unmodifiablePosition(Row.FOUR, Column.A);

        Position rook1A = Position.unmodifiablePosition(Row.ONE, Column.A);
        Position rook3A = Position.unmodifiablePosition(Row.THREE, Column.A);

        chessBoardWrapper.move(pawn2A, pawn4A);
        // when & then
        Assertions.assertDoesNotThrow(() ->
            chessBoardWrapper.move(rook1A, rook3A));
    }
    @Test
    @DisplayName("적 기물이 존재하는 위치에 이동 가능")
    void 이동_가능_02() {
        // given
        Position whitePawn2A = Position.unmodifiablePosition(Row.TWO, Column.A);
        Position whitePawn4A = Position.unmodifiablePosition(Row.FOUR, Column.A);
        Position whitePawn5B = Position.unmodifiablePosition(Row.FIVE, Column.B);

        Position blackPawn7B = Position.unmodifiablePosition(Row.SEVEN, Column.B);
        Position blackPawn5B = Position.unmodifiablePosition(Row.FIVE, Column.B);

        Position rook1A = Position.unmodifiablePosition(Row.ONE, Column.A);
        Position rook7A = Position.unmodifiablePosition(Row.SEVEN, Column.A);

        chessBoardWrapper.move(whitePawn2A, whitePawn4A);
        chessBoardWrapper.move(blackPawn7B, blackPawn5B);
        chessBoardWrapper.move(whitePawn4A, whitePawn5B);

        // when & then
        Assertions.assertDoesNotThrow(() ->
            chessBoardWrapper.move(rook1A, rook7A));
    }

    @Test
    @DisplayName("우리팀 기물을 건너서는 위치, 우리팀 기물이 있는 위치까지 불가능")
    void 이동_불가능_01() {
        Position rook1A = Position.unmodifiablePosition(Row.ONE, Column.A);
        Position rook7A = Position.unmodifiablePosition(Row.SEVEN, Column.A);

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class,() ->
            chessBoardWrapper.move(rook1A, rook7A));
    }
    @Test
    @DisplayName("직진 이동이 아닌 움직임 불가능.")
    void 이동_불가능_02() {
        Position rook1A = Position.unmodifiablePosition(Row.ONE, Column.A);
        Position rook2B = Position.unmodifiablePosition(Row.SEVEN, Column.A);

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class,() ->
            chessBoardWrapper.move(rook1A, rook2B));
    }
}