package e1.pieces;

import e1.Pair;
import e1.checker.KnightMoveChecker;

public class Knight implements GamePiece {

    private Pair<Integer, Integer> position;
    private KnightMoveChecker knightMoveChecker;


    public Knight(Pair<Integer, Integer> position, KnightMoveChecker knightMoveChecker) {
        this.position = position;
        this.knightMoveChecker = knightMoveChecker;
    }

    @Override
    public boolean move(int row, int col) {
        boolean correctMove = this.checkMove(row, col);
		this.position = correctMove ? new Pair<>(row,col) : this.position;
        return correctMove;
    }

    private boolean checkMove(int row, int col){
        return knightMoveChecker.checkKnightMove(this.position, new Pair<Integer,Integer>(row, col));		
    }

    @Override
    public Pair<Integer, Integer> position() {
        return this.position;
    }

}
