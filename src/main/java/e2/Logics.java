package e2;

import java.util.Set;

public interface Logics {

    int gridSize();
    int activeMines();
    void hit(Pair<Integer, Integer> cell);
    Set<Pair<Integer,Integer>> setAdiacentCells(Pair<Integer, Integer> cell);
    boolean isBomb(Pair<Integer, Integer> cell);
    boolean victory();
    String getNumber(Pair<Integer, Integer> cell);
    boolean hasNumber(Pair<Integer, Integer> cell);
    
}
