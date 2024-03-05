package e1.grid;

public class GridPropertiesImpl implements GridProperties{

    private int size;

    public GridPropertiesImpl(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return this.size;
    }

}
