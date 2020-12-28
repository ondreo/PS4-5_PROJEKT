public class Road {
    private City destCity;
    private int length;

    public Road(City destCity, int length) {
        this.destCity = destCity;
        this.length = length;

    }

    public City getDestCity() {
        return destCity;
    }

    public int getLength() {
        return length;
    }
}
