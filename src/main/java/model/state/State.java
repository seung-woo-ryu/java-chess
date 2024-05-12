package model.state;

import model.chessboard.ChessBoardWrapper;

public interface State {
    void move(String command);
    void status();
    void start();
    void end();
    boolean isEnd();
}
