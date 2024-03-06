package e2;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
//import java.util.Random;

public class LogicsImpl implements Logics {

    private Grid grid;

    public LogicsImpl(int gridSize) {
        this.grid = new GridBase(gridSize);
        this.grid.cell(new Pair<>(0,0)).activeBomb();
        this.grid.cell(new Pair<>(0,0)).activeBomb();;
        this.grid.cell(new Pair<>(3,5)).activeBomb();;
        this.grid.cell(new Pair<>(6,4)).activeBomb();;
        this.grid.cell(new Pair<>(2,6)).activeBomb();;
        this.grid.cell(new Pair<>(0,4)).activeBomb();;
        this.grid.cell(new Pair<>(1,5)).activeBomb();;
        this.grid.cell(new Pair<>(4,3)).activeBomb();;
    }

    @Override
    public int gridSize() {
        return this.grid.size();
    }

    @Override
    public boolean isBomb(Pair<Integer, Integer> cell){
        return this.grid.cell(cell).isBomb();
    }

    @Override
    public boolean victory(){
        return this.grid.emptyCells().size()==1;
    }

    @Override
    public int activeMines() {
        return this.grid.mines().size();
    }

    @Override
    public void hit(Pair<Integer, Integer> cell) {
        this.grid.hit(cell);
    }

    @Override
    public String getNumber(Pair<Integer, Integer> cell) {
        return this.grid.cell(cell).textValue();
    }

    @Override
    public boolean hasNumber(Pair<Integer, Integer> cell) {
        return this.grid.cell(cell).hasValue();
    }

}
