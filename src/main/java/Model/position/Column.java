package Model.position;

public enum Column {
    A(1,"a"),
    B(2,"b"),
    C(3,"c"),
    D(4,"d"),
    E(5,"e"),
    F(6,"f"),
    G(7,"g"),
    H(8,"h");

    private final int value;
    private final String name;

    Column(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
