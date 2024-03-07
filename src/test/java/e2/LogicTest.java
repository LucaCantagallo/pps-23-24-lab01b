package e2;

import org.junit.jupiter.api.*;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


public class LogicTest {

    int size;
    int mines;
    Logics logic;
    Grid grid;

    @Test
    public void test() {
        assert(true);
    }

    @BeforeEach
    public void init(){
        size = 7;
        mines = size;
        logic = new LogicsImpl(size);
        grid = new GridBase(size);
    }

    @Test
    public void getGridSize(){
        assertEquals(size, logic.gridSize());
    }

    @Test
    public void activateNewBomb(){
        Pair<Integer, Integer> bomb = new Pair<Integer,Integer>(0, 0);
        grid.setMine(bomb);
        assertTrue(grid.mines().contains(bomb));
    }

    @Test
    public void activeBombInLogicImpl(){
        Pair<Integer, Integer> bomb = new Pair<Integer,Integer>(0, 0);
        assertTrue(logic.grid().mines().contains(bomb));
    }

    @Test
    public void getNumberOfMines(){
        assertEquals(size, logic.grid().size());
    }

    @Test
    public void getACellWithMine(){
        Pair<Integer, Integer> bomb = new Pair<Integer,Integer>(0, 0);
        assertTrue(logic.isBomb(bomb));
    }

    @Test
    public void getACellWithoutMine(){
        Pair<Integer, Integer> notbomb = new Pair<Integer,Integer>(0, 1);
        assertFalse(logic.isBomb(notbomb));
    }

    @Test
    public void numberAdiacentMinesNotInitialized(){
        Pair<Integer, Integer> one = new Pair<Integer,Integer>(0, 1);
        assertEquals("", logic.getNumber(one));
    }

    @Test
    public void numberAdiacentMines(){
        Pair<Integer, Integer> one = new Pair<Integer,Integer>(0, 1);
        logic.hit(one);
        assertEquals("1", logic.getNumber(one));
    }

    @Test
    public void victory(){
        List<Pair<Integer,Integer>> moves = new ArrayList<>();
        moves.add(new Pair<Integer,Integer>(0, 6));
        moves.add(new Pair<Integer,Integer>(6, 6));
        moves.add(new Pair<Integer,Integer>(6, 0));
        moves.add(new Pair<Integer,Integer>(0, 5));
        moves.add(new Pair<Integer,Integer>(1, 6));
        moves.add(new Pair<Integer,Integer>(2, 5));
        moves.add(new Pair<Integer,Integer>(3, 6));
        moves.add(new Pair<Integer,Integer>(4, 4));
        for(Pair<Integer,Integer> p: moves){
            logic.hit(p);
        }
        assertTrue(logic.victory());
    }

    @Test
    public void losing(){
        List<Pair<Integer,Integer>> moves = new ArrayList<>();
        moves.add(new Pair<Integer,Integer>(1, 6));
        moves.add(new Pair<Integer,Integer>(6, 6));
        moves.add(new Pair<Integer,Integer>(5, 0));
        moves.add(new Pair<Integer,Integer>(0, 5));
        moves.add(new Pair<Integer,Integer>(1, 6));
        moves.add(new Pair<Integer,Integer>(5, 5));
        moves.add(new Pair<Integer,Integer>(3, 6));
        moves.add(new Pair<Integer,Integer>(4, 4));
        for(Pair<Integer,Integer> p: moves){
            logic.hit(p);
        }
        assertFalse(logic.victory());
    }
    
}
