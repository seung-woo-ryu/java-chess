package model.piece;

import java.util.ArrayList;
import java.util.List;
import model.ChessBoard;
import model.position.Position;
import model.Team;

public abstract class AbstractPiece implements Piece{

    protected final String name;
    protected final int point;
    protected final Team team;
    protected final ChessBoard chessBoard;
    protected Position position;
    protected int moveCnt;

    public AbstractPiece(String name, int point, Team team, Position position, ChessBoard chessBoard) {
        this.name = name;
        this.point = point;
        this.team = team;
        this.position = position;
        this.chessBoard = chessBoard;
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
                AbstractPiece eliminatedPiece = chessBoard.eliminateIfEnemyExist(nextPosition, team);
                chessBoard.putHistory(this.position, nextPosition, eliminatedPiece);
                this.position = nextPosition;
                increaseCnt();
                return;
            }
        }

        throw new IllegalArgumentException("이동 할 수 없는 위치입니다.");
    }
    public void undoMove(Position currentPosition) {
        this.position = currentPosition;
        decreaseCnt();
    }
    protected void addPossiblePositionRepeatedly(int[] direction, List<Position> possiblePosition) {
        final int rowStep = direction[0];
        final int columnStep = direction[1];

        Position currentPosition = this.position;
        do {
            // 체스 보드 좌표 안에서 움직이는 수인지 체크
            if (!chessBoard.isInBoardAfterMove(currentPosition, rowStep, columnStep)) {
                break;
            }
            Position nextPosition = currentPosition.getNextPosition(rowStep, columnStep);
            // 우리 기물이 존재하는 위치인지 확인
            if (chessBoard.isTeamHere(nextPosition, this.team)) {
                break;
            }
            possiblePosition.add(nextPosition);
            // 적 기물이 존재하면 중단
            if (chessBoard.isEnemyHere(nextPosition, this.team)) {
                break;
            }

            currentPosition = nextPosition;
        } while (true);
    }

    protected void addPossiblePosition(int[] move, List<Position> possiblePosition) {
        final int rowStep = move[0];
        final int columnStep = move[1];

        Position currentPosition = this.position;
        if (!chessBoard.isInBoardAfterMove(currentPosition, rowStep, columnStep)) {
            return;
        }

        Position nextPosition = currentPosition.getNextPosition(rowStep, columnStep);
        if (chessBoard.isTeamHere(nextPosition, this.team)) {
            return;
        }
        possiblePosition.add(nextPosition);
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
