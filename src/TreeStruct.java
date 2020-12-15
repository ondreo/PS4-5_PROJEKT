import java.util.NoSuchElementException;

public class TreeStruct {
    private TreeVertex startVertex;
    //private TreeVertex[] vertices;//a może lista??
    public TreeStruct() {
        startVertex = null;
        //vertices = new TreeVertex[Main.MAX_N+1];
    }

    public int findCity(String name) {
        int currVertex = 0;
        while(vertices[currVertex] != null) {
            if(name.compareTo(vertices[currVertex].getName()) < 0) {
                currVertex *= 2;
            }
            else if(name.compareTo(vertices[currVertex].getName()) > 0) {
                currVertex = currVertex * 2 + 1;
            }
            else {
                return currVertex;
            }
        }
        throw new NoSuchElementException("There is no city with that name.");//Check it's name again.
    }
    //public void add(TreeVertex x) {
    public void add(String name) throws NameException {
        int currVertex = 0;
        while(vertices[currVertex] != null) {
            /**
             * wstawiany wierzchołek jest mniejszy leksykograficznie od aktualnego
             */
            if(name.compareTo(vertices[currVertex].getName()) < 0) {
                currVertex *= 2;
            }
            else if(name.compareTo(vertices[currVertex].getName()) > 0) {
                currVertex = currVertex * 2 + 1;
            }
            else {
                throw new NameException("Given city name already exists!");
            }
        }
        vertices[currVertex] = new TreeVertex(name);//jak dodać nowy?
    }
    public void remove(String name) {
        int currVertex;
        try {
            currVertex = this.findCity(name);
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException(e.getMessage());
        }
        //sprawdzić obsługę przez wyjątek
        if(vertices[currVertex*2] == null && vertices[currVertex*2+1] == null) {
            vertices[currVertex] = null;
        }
        else if(vertices[currVertex*2] == null) {
            vertices
        }
    }


    private Boolean isEmpty() {
        if(vertices[0] == null)
            return true;
        return false;
    }
}
