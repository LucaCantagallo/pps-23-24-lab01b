package e2;

public interface Cell {

    String textValue();
    boolean hasValue();
    void setValue(int value);
    boolean isBomb();
    void activeBomb();

}
