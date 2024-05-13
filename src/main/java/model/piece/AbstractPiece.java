package model.piece;

import java.util.ArrayList;
import java.util.List;
import model.chessboard.ChessBoardWrapper;
import model.position.Position;
import model.Team;

public abstract class AbstractPiece implements Piece{

    protected final String name;
    protected final int point;
    protected final Team team;
    protected final ChessBoardWrapper chessBoardWrapper;
    protected Position position;
    protected int moveCnt;

    public AbstractPiece(String name, int point, Team team, Position position, ChessBoardWrapper chessBoardWrapper) {
        this.name = name;
        this.point = point;
        this.team = team;
        this.position = position;
        this.chessBoardWrapper = chessBoardWrapper;
        this.moveCnt = 0;
    }
    public void increaseCnt() {
        moveCnt++;
    }
    public void decreaseCnt() {
        moveCnt--;
    }
    public boolean isWhite() {
        return team.isWhite();
    }
    public String getName() {
        return this.name;
    }
    public Team getTeam() {
        return this.team;
    }
    public int getPoint() {
        return point;
    }
    public Position getPosition() {
        return this.position;
    }
    public boolean isSamePosition(Position position) {
        return this.position.equals(position);
    }
    public boolean isSameTeam(Team team) {
        return this.team.equals(team);
    }
    @Override
    public void move(Position nextPosition) {
        for (Position possiblePosition : getAllNextPosition()) {
            if (nextPosition.equals(possiblePosition)) {
                AbstractPiece eliminatedPiece = chessBoardWrapper.eliminateIfEnemyExist(nextPosition, team);
                chessBoardWrapper.putHistory(this.position, nextPosition, eliminatedPiece);
                this.position = nextPosition;
                increaseCnt();
                return;
            }
        }

        throw new IllegalArgumentException("이동 할 수 없는 위치입니다.");
    }

    protected void addPossiblePositionRepeatedly(int[] direction, List<Position> possiblePosition) {
        final int rowStep = direction[0];
        final int columnStep = direction[1];

        Position currentPosition = this.position;
        do {
            if (!chessBoardWrapper.isPossibleStep(currentPosition, rowStep, columnStep)) {
                break;
            }
            Position nextPosition = currentPosition.getNextPosition(rowStep, columnStep);
            if (chessBoardWrapper.isTeamHere(nextPosition, this.team)) {
                break;
            }
            possiblePosition.add(nextPosition);
            if (chessBoardWrapper.isEnemyHere(nextPosition, this.team)) {
                break;
            }

            currentPosition = nextPosition;
        } while (true);
    }

    protected void addPossiblePosition(int[] move, List<Position> possiblePosition) {
        final int rowStep = move[0];
        final int columnStep = move[1];

        Position currentPosition = this.position;
        if (!chessBoardWrapper.isPossibleStep(currentPosition, rowStep, columnStep)) {
            return;
        }

        Position nextPosition = currentPosition.getNextPosition(rowStep, columnStep);
        if (chessBoardWrapper.isTeamHere(nextPosition, this.team)) {
            return;
        }
        possiblePosition.add(nextPosition);
    }

    public void undoMove(Position currentPosition) {
        this.position = currentPosition;
        decreaseCnt();
    }

    protected List<Position> getPositionListRepeatedly(int[][] directions) {
        List<Position> possiblePosition = new ArrayList<>();

        for (int[] direction : directions) {
            addPossiblePositionRepeatedly(direction, possiblePosition);
        }

        return possiblePosition;
    }

    protected List<Position> getPosition(int[][] possibleMove) {
        List<Position> possiblePosition = new ArrayList<>();

        for (int[] move : possibleMove) {
            addPossiblePosition(move, possiblePosition);
        }

        return possiblePosition;
    }
}
