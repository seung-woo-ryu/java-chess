package model.piece;

import model.position.Column;
import model.position.Position;
import model.position.Row;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest extends AbstractTest{


    @Test
    @DisplayName("기물이 막지 않는 위치까지 이동 가능")
    void 이동_가능_01() {
        // given
        Position pawn2B = Position.unmodifiablePosition(Row.TWO, Column.B);
        Position pawn3B = Position.unmodifiablePosition(Row.THREE, Column.B);

        Position bishop1C = Position.unmodifiablePosition(Row.ONE, Column.C);
        Position bishop2B = Position.unmodifiablePosition(Row.TWO, Column.B);

        chessBoardWrapper.move(pawn2B, pawn3B);
        // when & then
        Assertions.assertDoesNotThrow(() ->
            chessBoardWrapper.move(bishop1C, bishop2B));
    }

    @Test
    @DisplayName("적 기물이 존재하는 위치에 이동 가능")
    void 이동_가능_02() {
        // given
        // black
        Position pawn7H = Position.unmodifiablePosition(Row.SEVEN, Column.H);
        Position pawn6H = Position.unmodifiablePosition(Row.SIX, Column.H);
        // white
        Position pawn2D = Position.unmodifiablePosition(Row.TWO, Column.D);
        Position pawn3D = Position.unmodifiablePosition(Row.THREE, Column.D);
        // white
        Position bishop1C = Position.unmodifiablePosition(Row.ONE, Column.C);
        Position bishop6H = Position.unmodifiablePosition(Row.SIX, Column.H);

        chessBoardWrapper.move(pawn7H, pawn6H);
        chessBoardWrapper.move(pawn2D, pawn3D);
        // when & then
        Assertions.assertDoesNotThrow(() ->
            chessBoardWrapper.move(bishop1C, bishop6H));
    }

    @Test
    @DisplayName("우리팀 기물을 건너서는 위치, 우리팀 기물이 있는 위치까지 불가능")
    void 이동_불가능_01() {
        // given
        // white
        Position bishop1C = Position.unmodifiablePosition(Row.ONE, Column.C);
        Position bishop6H = Position.unmodifiablePosition(Row.SIX, Column.H);

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class,() ->
            chessBoardWrapper.move(bishop1C, bishop6H));
    }

    @Test
    @DisplayName("대각선 이동이 아닌 움직임 불가능.")
    void 이동_불가능_02() {
        // given
        // white
        Position bishop1C = Position.unmodifiablePosition(Row.ONE, Column.C);
        Position bishop2C = Position.unmodifiablePosition(Row.TWO, Column.C);
        // when & then
        Assertions.assertThrows(IllegalArgumentException.class,() ->
            chessBoardWrapper.move(bishop1C, bishop2C));
    }
}