package e1.piecesFactory;

import e1.Pair;
import e1.checker.KnightMoveChecker;
import e1.grid.GridProperties;
import e1.pieces.IdlePawn;
import e1.pieces.Knight;

public class PieceFactoryImpl implements PiecesFactory{

    private GridProperties gridProperties;

    public PieceFactoryImpl(GridProperties gridProperties){
        this.gridProperties = gridProperties;
    }

    @Override
    public IdlePawn createIdlePawn() {
        return new IdlePawn(new Pair<Integer, Integer>(this.gridProperties.getSize()-1, this.gridProperties.getSize()-1));
    }

    @Override
    public Knight createKnight() {
        return new Knight(new Pair<Integer,Integer>(0, 0), new KnightMoveChecker(gridProperties));
    }

}
