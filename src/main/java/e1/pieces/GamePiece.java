package e1.pieces;

import e1.Pair;

public interface GamePiece {

    boolean move(int row, int col);

    Pair<Integer, Integer> position();

}
