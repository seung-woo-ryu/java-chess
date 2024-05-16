package model.piece;

import model.position.Column;
import model.position.Position;
import model.position.Row;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest extends AbstractTest{
    @Test
    @DisplayName("1칸 전진 가능")
    void _1칸_전진_가능() {
        // given
        Position pawn2A = Position.of(Row.TWO, Column.A);
        Position pawn3A = Position.of(Row.THREE, Column.A);

        // when && then
        Assertions.assertDoesNotThrow(
            () -> chessBoard.move(pawn2A, pawn3A));
    }

    @Test
    @DisplayName("우리팀 기물이 존재하여 움직임 불가능")
    void _1칸_전진_불가능() {
        // given
        Position knight1B = Position.of(Row.getWhiteMajorPieceStartRow(), Column.B);
        Position knight3A = Position.of(Row.THREE, Column.A);

        Position pawn2A = Position.of(Row.TWO, Column.A);
        Position pawn3A = Position.of(Row.THREE, Column.A);

        chessBoard.move(knight1B, knight3A);
        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            chessBoard.move(pawn2A, pawn3A)
        );
    }


    @Test
    @DisplayName("첫 이동 시 2칸 전진 가능")
    void _2칸_전진_가능() {
        // given
        Position pawn2A = Position.of(Row.TWO, Column.A);
        Position pawn4A = Position.of(Row.FOUR, Column.A);
        // when & then
        Assertions.assertDoesNotThrow(
            () -> chessBoard.move(pawn2A, pawn4A));
    }

    @Test
    @DisplayName("상대 기물이 존재하면 대각선 전진 공격 가능")
    void 대각선_전진_공격_가능() {
        // given
        Position pawn7A = Position.of(Row.getBlackPawnStartRow(), Column.A);
        Position pawn5A = Position.of(Row.getBlackPawnStartRow().move(-2), Column.A);

        Position pawn2B = Position.of(Row.getWhitePawnStartRow(), Column.B);
        Position pawn4B = Position.of(Row.getWhitePawnStartRow().move(2), Column.B);

        chessBoard.move(pawn7A, pawn5A);
        chessBoard.move(pawn2B, pawn4B);
        // when & then
        Assertions.assertDoesNotThrow(
            () -> chessBoard.move(pawn4B, pawn5A));

    }

    @Test
    @DisplayName("폰이 이동 가능한 위치가 아닌 경우 IllegalException")
    void 이동_불가능한_움직임_요청() {
        // given
        Position pawn2A = Position.of(Row.TWO, Column.A);
        Position impossiblePawnPosition = Position.of(Row.FIVE, Column.A);

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            chessBoard.move(pawn2A, impossiblePawnPosition)
        );
    }
}