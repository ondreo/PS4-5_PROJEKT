import java.util.LinkedList;

public class City {
    //tree:
    private String name;
    private City leftChild;
    private City rightChild;
    private int height;

    //graph:
    private LinkedList<Road> adjacentCities;

    //priorityQueue:
    private int distFromSource;
    //private City predecessor;
    private Boolean visited;


    public City(String name) {
        this.name = name;
        leftChild = rightChild = null;
        this.height = 1;
        adjacentCities = new LinkedList<>();
        //distFromSource = Integer.MAX_VALUE;//TODO: za każdym przejściem Dijkstry aktualizować
        //predecessor = null//TODO: za każdym przejściem Dijkstry aktualizować
        //visited = false//podobnie jak wyżej
    }

    /*public City(City x) {//konstruktor kopiujący
        this.name = x.name;
        //this.depthLevel = x.depthLevel;
        this.leftChild = x.leftChild;
        this.rightChild = x.rightChild;
    }*/

    public String addRoad(City destCity, int length) {
        if(!adjacentCities.contains(new Road(destCity, length))) {
            adjacentCities.add(new Road(destCity,length));
            return "TAK";
        }
        return "NIE";
    }

    public String removeRoad(City destCity) {
        /*for(Road road : adjacentCities) {
            if(road.getDestCity() == destCity) {
                adjacentCities.remove(road);
                return "TAK";
            }
        }*/
        if(adjacentCities.removeIf(road -> road.getDestCity() == destCity)) return "TAK";
        return "NIE";
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Boolean hasChildren() {
        if(leftChild != null || rightChild != null)
            return true;
        return false;
    }

    public Boolean hasLeftChild() {
        if(this.leftChild != null) return true;
        return false;
    }
    public City getLeftChild() {
        return leftChild;
    }
    public void setLeftChild(City leftChild) {
        this.leftChild = leftChild;
    }

    public Boolean hasRightChild() {
        if(this.rightChild != null) return true;
        return false;
    }
    public City getRightChild() {
        return rightChild;
    }
    public void setRightChild(City rightChild) {
        this.rightChild = rightChild;
    }

    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    public LinkedList<Road> getAdjacentCities() {
        return adjacentCities;
    }
    public void setAdjacentCities(LinkedList<Road> adjacentCities) {
        this.adjacentCities = adjacentCities;
    }

    public int getDistFromSource() {
        return distFromSource;
    }
    public void setDistFromSource(int distFromSource) {
        this.distFromSource = distFromSource;
    }

    /*public City getPredecessor() {
        return predecessor;
    }
    public void setPredecessor(City predecessor) {
        this.predecessor = predecessor;
    }*/

    public Boolean getVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        String out = "City{" +
                "name='" + name + '\''
                + ", leftChild=";
        if(leftChild!=null) out += leftChild.getName();
        else out += "null";
        out += ", rightChild=";
        if(rightChild!=null) out += rightChild.getName();
        else out += "null";
        out += ", height=" + height +
                '}';
        return out;
    }
}
