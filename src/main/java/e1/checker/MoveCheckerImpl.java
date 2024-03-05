package e1.checker;

import e1.grid.GridProperties;

public class MoveCheckerImpl implements MoveChecker {

    private GridProperties gridProperties;

    public MoveCheckerImpl(GridProperties gridProperties){
        this.gridProperties = gridProperties;
    }

    @Override
    public boolean checkIsOutsideGrid(int row, int col){
        boolean check = row<0 || col<0 || row >= this.gridProperties.getSize() || col >= this.gridProperties.getSize();
        if(check){
            throwExceptionOutsideGrid();
        }
        return check;			
    }

    private void throwExceptionOutsideGrid() throws IndexOutOfBoundsException{
        throw new IndexOutOfBoundsException();      
    }
    
}
