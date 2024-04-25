package Model.position;

public enum Row {
    Eight(8,"8"),
    Seven(7,"7"),
    Six(6,"6"),
    Five(5,"5"),
    Four(4,"4"),
    Three(3,"3"),
    Two(2,"2"),
    One(1,"1");

    private int value;
    private String name;

    Row(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
