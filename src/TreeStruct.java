public class TreeStruct {
    private TreeVertex startVertex;
    private TreeVertex[] vertices;//a może lista??
    public TreeStruct() {
        startVertex = null;
        vertices = new TreeVertex[Main.MAX_N];
    }
    //public void add(TreeVertex x) {
    public void add(String name) throws NameException {
        TreeVertex currVertex = startVertex;
        while(currVertex != null) {
            /**
             * wstawiany wierzchołek jest mniejszy leksykograficznie od aktualnego
             */
            if(name.compareTo(currVertex.getName()) < 0) {
                currVertex = currVertex.getLeftChild();
            }
            else if(name.compareTo(currVertex.getName()) > 0) {
                currVertex = currVertex.getRightChild();
            }
            else {
                throw new NameException("Given city name already exists!");
            }
        }
        currVertex = new TreeVertex(name);//jak dodać nowy?
    }
    private Boolean isEmpty() {
        if(startVertex == null)
            return true;
        return false;
    }
}
