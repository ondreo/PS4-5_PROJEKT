public class TreeVertex {
    private String name;
    //private int depthLevel;
    private TreeVertex leftChild;
    private TreeVertex rightChild;

    public TreeVertex(String name) {
        this.name = name;
        //this.depthLevel = 0;
        leftChild = rightChild = null;
    }

    /*public TreeVertex(TreeVertex x) {//konstruktor kopiujący
        this.name = x.name;
        //this.depthLevel = x.depthLevel;
        this.leftChild = x.leftChild;
        this.rightChild = x.rightChild;
    }*/
    public Boolean hasChildren() {
        if(leftChild != null || rightChild != null)
            return true;
        return false;
    }

    public String getName() {
        return name;
    }

    public Boolean hasLeftChild() {
        if(this.leftChild != null) return true;
        return false;
    }
    public TreeVertex getLeftChild() {
        return leftChild;
    }
    public void setLeftChild(TreeVertex leftChild) {
        this.leftChild = leftChild;
    }

    public Boolean hasRightChild() {
        if(this.rightChild != null) return true;
        return false;
    }
    public TreeVertex getRightChild() {
        return rightChild;
    }
    public void setRightChild(TreeVertex rightChild) {
        this.rightChild = rightChild;
    }
}
