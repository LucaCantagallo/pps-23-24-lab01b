package e1;
import org.junit.jupiter.api.*;

import e1.checker.KnightMoveChecker;
import e1.grid.GridProperties;
import e1.grid.GridPropertiesImpl;
import e1.logic.Logics;
import e1.logicFactory.LogicFactory;
import e1.logicFactory.LogicFactoryImpl;
import e1.pieces.GamePiece;
import e1.piecesFactory.PieceFactoryImpl;
import e1.piecesFactory.PiecesFactory;

import static org.junit.jupiter.api.Assertions.*;
public class LogicTest {

  LogicFactory logicFactory;
  Logics logic;
  int size;
  Pair<Integer, Integer> knightPosition;
  Pair<Integer, Integer> pawnPosition;
  PiecesFactory piecesFactory;
  KnightMoveChecker knightMoveChecker;
  GridProperties gridProperties;
  GamePiece knight;
  GamePiece idlePiece;

  @BeforeEach
  void beforeEach(){
    size = 5;
    logicFactory = new LogicFactoryImpl();
    logic = logicFactory.createCleanLogic(size);
    knightPosition = new Pair<>(0, 0);
    pawnPosition = new Pair<>(size-1, size-1);
    gridProperties = new GridPropertiesImpl(size);
    knightMoveChecker = new KnightMoveChecker(gridProperties);
    piecesFactory = new PieceFactoryImpl(gridProperties);
    knight = piecesFactory.createKnight();
}


  @Test
  public void test() {
    assert(true);
    // TODO: Add your test logic here
    // You can generate random inputs and assert the expected output
    // For example:
    // int result = Logic.someMethod(5, 10);
    // assertEquals(expectedResult, result);
  }

  @Test
  public void FirstPositionOfKnight(){
    assertTrue(logic.hasKnight(knightPosition.getX(), knightPosition.getY()));
  }

  @Test
  public void FirstPositionOfPawn(){
    assertTrue(logic.hasPawn(pawnPosition.getX(), pawnPosition.getY()));
  }

  @Test
  public void hasOneKnight(){
    int numberOfKnights=0;
    for(int i = 0; i < size; i++){
      for(int j = 0; j < size; j++){
        numberOfKnights = logic.hasKnight(i, j) ? numberOfKnights+1 : numberOfKnights;
      }
    }  
    assertEquals(1, numberOfKnights); 
  }

  @Test
  public void hasOnePawn(){
    int numberOfPawn=0;
    for(int i = 0; i < size; i++){
      for(int j = 0; j < size; j++){
        numberOfPawn = logic.hasPawn(i, j) ? numberOfPawn+1 : numberOfPawn;
      }
    }  
    assertEquals(1, numberOfPawn); 
  }

  @Test
  public void firstHitInCellOutOfGrid(){
    Pair<Integer, Integer> wrongMove = new Pair<Integer,Integer>(-1, -1);
    assertThrows(IndexOutOfBoundsException.class, () -> logic.hit(wrongMove.getX(), wrongMove.getY()));
  }

  @Test
  public void firstHitInCellInNotAllowedCell(){
    Pair<Integer, Integer> wrongMove = new Pair<Integer,Integer>(1, 1);
    boolean moveResult = logic.hit(wrongMove.getX(), wrongMove.getY());
    assertAll(
      () -> assertFalse(moveResult),
      () -> assertFalse(logic.hasKnight(wrongMove.getX(), wrongMove.getY())),
      () -> assertTrue(logic.hasKnight(knightPosition.getX(), knightPosition.getY()))
    );
  }

  @Test
  public void checkPositionInGrid(){
    GridProperties gridProperties = new GridPropertiesImpl(size);
    assertFalse(knightMoveChecker.checkIsOutsideGrid(1, 1));
  }

  @Test
  public void checkInvalidMoveKnight(){
    Pair<Integer, Integer> firstMove = new Pair<Integer,Integer>(1, 1);
    GridProperties gridProperties = new GridPropertiesImpl(size);
    KnightMoveChecker knightMoveChecker = new KnightMoveChecker(gridProperties);
    assertFalse(knightMoveChecker.checkKnightMove(knightPosition, firstMove));
  }

  @Test
  public void checkValidMoveKnight(){
    Pair<Integer, Integer> firstMove = new Pair<Integer,Integer>(2,1);
    KnightMoveChecker knightMoveChecker = new KnightMoveChecker(gridProperties);
    assertTrue(knightMoveChecker.checkKnightMove(knightPosition, firstMove));
  }

  @Test
  public void checkMoveKnight(){
    Pair<Integer, Integer> firstMove = new Pair<Integer,Integer>(1,2);
    assertTrue(knight.move(firstMove.getX(), firstMove.getY()));
  }

  @Test
  public void firstHitInAllowedCell(){
    Pair<Integer, Integer> firstMove = new Pair<Integer,Integer>(2, 1);
    boolean firstHitResult = logic.hit(firstMove.getX(), firstMove.getY());
    assertAll(
      () -> assertFalse(firstHitResult),
      () -> assertFalse(logic.hasKnight(knightPosition.getX(), knightPosition.getY())),
      () -> assertTrue(logic.hasPawn(pawnPosition.getX(), pawnPosition.getY())),
      () -> assertTrue(logic.hasKnight(firstMove.getX(), firstMove.getY()))
    );
  }

  @Test
  public void secondHitInAllowedCell(){
    Pair<Integer, Integer> firstMove = new Pair<Integer,Integer>(2, 1);
    Pair<Integer, Integer> secondMove = new Pair<Integer,Integer>(4, 2);

    logic.hit(firstMove.getX(), firstMove.getY());
    boolean secondHitResult = logic.hit(secondMove.getX(), secondMove.getY());

    assertAll(
      () -> assertFalse(secondHitResult),
      () -> assertFalse(logic.hasKnight(knightPosition.getX(), knightPosition.getY())),
      () -> assertFalse(logic.hasKnight(firstMove.getX(), firstMove.getY())),
      () -> assertTrue(logic.hasPawn(pawnPosition.getX(), pawnPosition.getY())),
      () -> assertTrue(logic.hasKnight(secondMove.getX(), secondMove.getY()))
    );
  }

  @Test
  public void thirdHitInAllowedCell(){
    Pair<Integer, Integer> firstMove = new Pair<Integer,Integer>(2, 1);
    Pair<Integer, Integer> secondMove = new Pair<Integer,Integer>(4, 2);
    Pair<Integer, Integer> thirdMove = new Pair<Integer,Integer>(2, 3);

    logic.hit(firstMove.getX(), firstMove.getY());
    logic.hit(secondMove.getX(), secondMove.getY());
    boolean thirdHitResult = logic.hit(thirdMove.getX(), thirdMove.getY());
    assertAll(
      () -> assertFalse(thirdHitResult),
      () -> assertFalse(logic.hasKnight(knightPosition.getX(), knightPosition.getY())),
      () -> assertFalse(logic.hasKnight(secondMove.getX(), secondMove.getY())),
      () -> assertTrue(logic.hasPawn(pawnPosition.getX(), pawnPosition.getY())),
      () -> assertTrue(logic.hasKnight(thirdMove.getX(), thirdMove.getY()))
    );
  }

  @Test
  public void hitThePawn(){
    Pair<Integer, Integer> firstMove = new Pair<Integer,Integer>(2, 1);
    Pair<Integer, Integer> secondMove = new Pair<Integer,Integer>(4, 2);
    Pair<Integer, Integer> thirdMove = new Pair<Integer,Integer>(2, 3);
    Pair<Integer, Integer> finalMove = new Pair<Integer,Integer>(pawnPosition.getX(), pawnPosition.getY());

    logic.hit(firstMove.getX(), firstMove.getY());
    logic.hit(secondMove.getX(), secondMove.getY());
    logic.hit(thirdMove.getX(), thirdMove.getY());
    boolean finalHitResult = logic.hit(finalMove.getX(), finalMove.getY());
    assertAll(
      () -> assertTrue(finalHitResult),
      () -> assertFalse(logic.hasKnight(thirdMove.getX(), thirdMove.getY())),
      () -> assertTrue(logic.hasPawn(pawnPosition.getX(), pawnPosition.getY())),
      () -> assertTrue(logic.hasKnight(finalMove.getX(), finalMove.getY()))
    );
  }


}
