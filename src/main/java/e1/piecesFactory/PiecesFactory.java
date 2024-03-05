package e1.piecesFactory;

import e1.pieces.IdlePawn;
import e1.pieces.Knight;

public interface PiecesFactory {

    IdlePawn createIdlePawn();

    Knight createKnight();

}
