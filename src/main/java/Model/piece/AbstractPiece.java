package Model.piece;

import Model.chessboard.ChessBoardWrapper;
import Model.position.Position;
import Model.team.Team;

public abstract class AbstractPiece implements Piece{

    private final String name;
    private final int point;
    protected final Team team;
    private Position position;
    protected ChessBoardWrapper chessBoardWrapper;

    public AbstractPiece(String name, int point, Team team, Position position, ChessBoardWrapper chessBoardWrapper) {
        this.name = name;
        this.point = point;
        this.team = team;
        this.position = position;
        this.chessBoardWrapper = chessBoardWrapper;
    }
    public Position getPosition() {
        return position;
    }
    public boolean isSamePosition(Position position) {
        return this.position.equals(position);
    }
    public boolean isSameTeam(Team team) {
        return this.team.equals(team);
    }
    @Override
    public void move(Position nextPosition) {
        for (Position canPosition : getAllNextPosition()) {
            if (nextPosition.equals(canPosition)) {
                chessBoardWrapper.eliminateIfEnemyExist(nextPosition,team);
                this.position = nextPosition;
                return;
            }
        }

        throw new IllegalArgumentException("이동 할 수 없는 위치입니다.");
    }
}
