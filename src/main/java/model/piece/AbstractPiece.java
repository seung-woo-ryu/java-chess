package model.piece;

import java.util.Optional;
import model.chessboard.ChessBoardWrapper;
import model.position.Column;
import model.position.Position;
import model.position.Row;
import model.team.Team;

public abstract class AbstractPiece implements Piece{

    protected final String name;
    protected final int point;
    protected final Team team;

    protected Position position;
    protected ChessBoardWrapper chessBoardWrapper;

    public AbstractPiece(String name, int point, Team team, Position position, ChessBoardWrapper chessBoardWrapper) {
        this.name = name;
        this.point = point;
        this.team = team;
        this.position = position;
        this.chessBoardWrapper = chessBoardWrapper;
    }
    public boolean isWhite() {
        return team.isWhite();
    }
    public String getName() {
        return this.name;
    }
    public Team getTeam() {return this.team;}
    public Position getPosition() {return this.position;}
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
                AbstractPiece eliminatedPiece = chessBoardWrapper.eliminateIfEnemyExist(nextPosition, team);
                chessBoardWrapper.putHistory(this.position, nextPosition, eliminatedPiece);
                this.position = nextPosition;
                return;
            }
        }

        throw new IllegalArgumentException("이동 할 수 없는 위치입니다.");
    }

    @Override
    public void forceMove(Position position) {
        this.position = position;
    }
}
