package e1.logic;

import e1.Pair;
import e1.grid.GridProperties;
import e1.grid.GridPropertiesImpl;
import e1.pieces.GamePiece;
import e1.piecesFactory.PieceFactoryImpl;
import e1.piecesFactory.PiecesFactory;

public class CleanLogicsImpl implements Logics {

    private PiecesFactory pieceFactory;
    private GamePiece knight;
    private GamePiece idlePawn;

    private GridProperties gridProperties;

    public CleanLogicsImpl(int size){
    	this.gridProperties = new GridPropertiesImpl(size);
        this.pieceFactory = new PieceFactoryImpl(this.gridProperties);
        this.knight = this.pieceFactory.createKnight();
        this.idlePawn = this.pieceFactory.createIdlePawn();
    }
    
	@Override
	public boolean hit(int row, int col) {
		this.knight.move(row, col);
		return idlePawn.position().equals(knight.position());
	}

	@Override
	public boolean hasKnight(int row, int col) {
        return this.knight.position().equals(new Pair<Integer, Integer>(row, col));
	}

	@Override
	public boolean hasPawn(int row, int col) {
		return this.idlePawn.position().equals(new Pair<Integer, Integer>(row, col));
	}
}
