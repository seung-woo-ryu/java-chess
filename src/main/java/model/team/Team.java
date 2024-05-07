package model.team;

public enum Team {
    WHITE(1, "흰색"),
    BLACK(-1, "검정"),
    NOTHING(0, "NULL");
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

    public Team getOpposite() {
        return this.isWhite() ? Team.BLACK : Team.WHITE;
    }

    public boolean isWhite() {
        return this.equals(WHITE);
    }
}