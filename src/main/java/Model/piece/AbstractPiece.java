package Model.piece;

import Model.chessboard.ChessBoardWrapper;
import Model.position.Position;
import Model.team.Team;

public abstract class AbstractPiece implements Piece{

    private final String name;
    private final int point;
    protected final Team team;
    protected Position position;
    protected ChessBoardWrapper chessBoardWrapper;

    public AbstractPiece(String name, int point, Team team, Position position) {
        this.name = name;
        this.point = point;
        this.team = team;
        this.position = position;
    }

    @Override
    public void move(Position nextPosition) {
        for (Position canPosition : getAllNextPosition()) {
            if (nextPosition.equals(canPosition)) {
                chessBoardWrapper.eliminateIfEnemyExist(nextPosition,team);
                this.position = nextPosition;
                break;
            }
        }

        throw new IllegalArgumentException("이동 할 수 없는 위치입니다.");
    }
}
