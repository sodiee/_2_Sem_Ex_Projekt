package Application.Model;

public enum Status {
    WHISKY("Whisky"),
    DESTILLAT("Destillat"),
    TOM("Tom");

    private final String name;

    Status(String s){
        name = s;
    }
}
