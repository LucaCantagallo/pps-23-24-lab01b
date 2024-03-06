package e2;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

public class LogicsImpl implements Logics {

    private int gridSize;
    //private Random random;
    private Set<Pair<Integer, Integer>> mines;
    private Map<Pair<Integer, Integer>, String> cells;
    private String waitingForNumber;

    public LogicsImpl(int gridSize) {
        this.gridSize = gridSize;
        this.mines = new HashSet<>();
        this.cells = new HashMap<>();
        //this.random = new Random();
        this.waitingForNumber="";

        /*while(this.mines.size()<this.gridSize){
            this.mines.add(new Pair<>(random.nextInt(gridSize), random.nextInt(gridSize)));
        }*/
        this.mines.add(new Pair<>(0,0));
        this.mines.add(new Pair<>(3,5));
        this.mines.add(new Pair<>(6,4));
        this.mines.add(new Pair<>(2,6));
        this.mines.add(new Pair<>(0,4));
        this.mines.add(new Pair<>(1,5));
        this.mines.add(new Pair<>(4,3));

        for(int i = 0; i <this.gridSize; i++){
            for(int j = 0; j<this.gridSize;j++){
                this.cells.put(new Pair<Integer,Integer>(i, j), waitingForNumber);
            }
        }
    }

    @Override
    public int gridSize() {
        return this.gridSize;
    }

    @Override
    public boolean isBomb(Pair<Integer, Integer> cell){
        return mines.contains(cell);
    }

    @Override
    public boolean victory(){
        return cells.keySet().stream().filter(p -> !hasNumber(p)).count()==8;
    }

    @Override
    public int activeMines() {
        return this.mines.size();
    }

    private int countAdiacent(Pair<Integer, Integer> cell){
        return (int) this.setAdiacentMines(cell).stream().count();
    }

    private Set<Pair<Integer,Integer>> setAdiacentMines(Pair<Integer, Integer> cell){
        Set<Pair<Integer, Integer>> adiacents = this.mines;
        return adiacents.stream().filter(el -> el.getX() >= cell.getX()-1)
                                    .filter(el -> el.getY() >= cell.getY()-1)
                                    .filter(el -> el.getX() <= cell.getX()+1)
                                    .filter(el -> el.getY() <= cell.getY()+1)
                                    .collect(Collectors.toSet());

    }

    @Override
    public Set<Pair<Integer,Integer>> setAdiacentCells(Pair<Integer, Integer> cell){
        Set<Pair<Integer, Integer>> adiacents = this.cells.keySet();
        return adiacents.stream().filter(el -> el.getX() >= cell.getX()-1)
                                    .filter(el -> el.getY() >= cell.getY()-1)
                                    .filter(el -> el.getX() <= cell.getX()+1)
                                    .filter(el -> el.getY() <= cell.getY()+1)
                                    .collect(Collectors.toSet());

    }

    private String numberMinesAroundToString(int numberMinesAround){
        return numberMinesAround+"";
    }

    @Override
    public void hit(Pair<Integer, Integer> cell) {
        int numberMinesAround = countAdiacent(cell);
        this.cells.put(cell, this.numberMinesAroundToString(numberMinesAround));
    }

    @Override
    public String getNumber(Pair<Integer, Integer> cell) {
        return this.cells.get(cell);
    }

    @Override
    public boolean hasNumber(Pair<Integer, Integer> cell) {
        return this.cells.get(cell)!=this.waitingForNumber;
    }

}
