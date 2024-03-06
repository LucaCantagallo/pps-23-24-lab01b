package e2;

import java.util.Set;

public interface Grid {
    int size();
    void setMine(Pair<Integer, Integer> mine);
    Set<Pair<Integer, Integer>> mines();
    Set<Pair<Integer,Integer>> emptyCells();
    int countAdiacent(Pair<Integer,Integer> cell);
    Set<Pair<Integer,Integer>> adiacentMines(Pair<Integer, Integer> cell);
    void hit(Pair<Integer, Integer> cell);
    Cell cell(Pair<Integer, Integer> cell);

}