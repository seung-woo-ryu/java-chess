package Model.team;

import Model.position.Row;

public enum Team {
    WHITE(1, "흰색"),
    BLACK(-1, "검정");
    private final int direction;
    private final String name;

    public int getDirection() {
        return direction;
    }

    public String getName() {
        return name;
    }

    Team(int direction, String name) {
        this.direction = direction;
        this.name = name;
    }

    public boolean isWhite() {
        return this.equals(WHITE);
    }
}