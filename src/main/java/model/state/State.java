package model.state;

public interface State {
    void move(String[] command);
    void status();
    void start();
    void end();
    boolean isEnd();
    boolean isNotStart();
}
