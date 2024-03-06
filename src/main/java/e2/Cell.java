package e2.cells;

import e2.Pair;

public interface Cell {

    Pair<Integer, Integer> position();
    int getX();
    int getY();
    String textValue();
    int value();
    boolean hasValue();
    boolean isBomb();

}
