package Model.team;

public enum Team {
    WHITE(1, "흰색"),
    BLACK(-1, "검정");
    private final int direction;
    private final String name;
    Team(int direction, String name) {
        this.direction = direction;
        this.name = name;
    }
}