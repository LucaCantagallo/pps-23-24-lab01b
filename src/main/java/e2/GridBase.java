package e2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GridBase implements Grid{

    private int size;
    Map<Pair<Integer,Integer>,Cell> cells;

    public GridBase(int size){
        this.size = size;
        this.cells = new HashMap<>();

        for(int i = 0; i <this.size; i++){
            for(int j = 0; j<this.size; j++){
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
        return cells.keySet().stream().filter(c -> cells.get(c).isBomb()).collect(Collectors.toSet());
    }

    @Override
    public Set<Pair<Integer, Integer>> emptyCells() {
        return cells.keySet().stream().filter(c -> !cells.get(c).hasValue())
                                        .filter(c -> !cells.get(c).isBomb())
                                        .collect(Collectors.toSet());
    }

    private Set<Pair<Integer, Integer>> adiacentStrategy(Pair<Integer, Integer> cell, Set<Pair<Integer,Integer>> cells) {
        Set<Pair<Integer, Integer>> adiacents = cells;
        return adiacents.stream().filter(el -> el.getX() >= cell.getX()-1)
                                    .filter(el -> el.getY() >= cell.getY()-1)
                                    .filter(el -> el.getX() <= cell.getX()+1)
                                    .filter(el -> el.getY() <= cell.getY()+1)
                                    .collect(Collectors.toSet());
    }

    
    @Override
    public int countAdiacent(Pair<Integer, Integer> cell) {
        return (int) this.adiacentStrategy(cell, mines()).stream().count();
    }

    @Override
    public void hit(Pair<Integer, Integer> cell) {
        int numberMinesAround = countAdiacent(cell);
        this.cells.get(cell).setValue(numberMinesAround);
        if(numberMinesAround==0){
            this.zeroMines(cell);
        }

    }

    private void zeroMines(Pair<Integer, Integer> cell){
        for (Pair<Integer,Integer> c : this.adiacentStrategy(cell, this.emptyCells())) {
            this.hit(c);
        };
    }

    @Override
    public Cell cell(Pair<Integer, Integer> cell) {
        return this.cells.get(cell);
    }

}
