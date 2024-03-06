package e2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class GridBase implements Grid{

    private int size;
    Map<Pair<Integer,Integer>,Cell> cells;

    public GridBase(int size, int numberOfBombs){
        this.size = size;
        this.cells = new HashMap<>();

        for(int i = 0; i <this.size; i++){
            for(int j = 0; j<this.size;j++){
                this.cells.put(new Pair<Integer,Integer>(i, j), new CellBase());
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void setMine(Pair<Integer, Integer> mine) {
        cells.get(mine).activeBomb();
    }

    @Override
    public Set<Pair<Integer, Integer>> mines() {
        return cells.entrySet().stream().filter(c -> cells.get(c).isBomb()).collect(Collectors.toSet());
    }

    @Override
    public int countAdiacent(Pair<Integer, Integer> cell) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'countAdiacent'");
    }

    @Override
    public Set<Pair<Integer, Integer>> adiacentMines(Pair<Integer, Integer> cell) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adiacentMines'");
    }

    @Override
    public void hit(Pair<Integer, Integer> cell) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hit'");
    }

}
