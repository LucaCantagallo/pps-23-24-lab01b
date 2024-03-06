package e2;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class LogicTest {

    int size;
    int mines;
    Logics logic;



    @Test
    public void test() {
        assert(true);
    }

    @BeforeEach
    public void init(){
        size = 7;
        mines = size;
        logic = new LogicsImpl(size);
    }

    @Test
    public void getGridSize(){
        assertEquals(size, logic.gridSize());
    }

    @Test
    public void getNumberOfMines(){
        assertEquals(size, logic.gridSize());
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
    
}
