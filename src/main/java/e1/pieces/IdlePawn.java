package e1.pieces;

import e1.Pair;

public class IdlePawn implements GamePiece{

    private Pair<Integer, Integer> position;

    public IdlePawn(Pair<Integer, Integer> position) {
        this.position = position;
    }


    @Override
    public boolean move(int row, int col) {
        return false; //AOC
    }


    @Override
    public Pair<Integer, Integer> position() {
        return position;
    }

}
