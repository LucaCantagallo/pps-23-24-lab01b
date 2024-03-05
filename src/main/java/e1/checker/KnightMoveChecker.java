package e1.checker;

import e1.Pair;
import e1.grid.GridProperties;

public class KnightMoveChecker extends MoveCheckerImpl{

    public KnightMoveChecker(GridProperties gridProperties) {
        super(gridProperties);
    }  

    private boolean knightCanMove(Pair<Integer, Integer> knightPosition, Pair<Integer, Integer> newPosition){
        int x = this.computeShift(knightPosition.getX(), newPosition.getX());
		int y = this.computeShift(knightPosition.getY(), newPosition.getY());
		return x!=0 && y!=0 && Math.abs(x)+Math.abs(y)==3;
    }

    private int computeShift(int knightPosition, int newPosition){
        return knightPosition-newPosition;
    }

    public boolean checkKnightMove(Pair<Integer, Integer> knightPosition, Pair<Integer, Integer> newPosition){
        return !(super.checkIsOutsideGrid(newPosition.getX(), newPosition.getY())) && this.knightCanMove(knightPosition, newPosition);
    }

}
