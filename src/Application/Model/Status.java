package Application.Model;

public enum Status {
    WHISKY("Whisky"),
    DESTILLAT("Destillat"),
    TOM("Tom");

    private final String name;

    /**
     *
     * @param s navnet for status
     */
    Status(String s){
        name = s;
    }
}
