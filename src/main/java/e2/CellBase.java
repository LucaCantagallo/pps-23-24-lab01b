package e2;

public class CellBase implements Cell {

    private final String waitingForNumber="";
    private String value;
    private boolean isBomb;

    public CellBase(){
        this.value=this.waitingForNumber;
        this.isBomb=false;
    }

    @Override
    public String textValue() {
        return this.value;
    }

    @Override
    public boolean hasValue() {
        return this.value!=this.waitingForNumber;
    }

    @Override
    public void setValue(int value) {
        this.value = value+"";
    }

    @Override
    public boolean isBomb() {
        return this.isBomb;
    }

    @Override
    public void activeBomb() {
        this.isBomb=true;
    }

}
