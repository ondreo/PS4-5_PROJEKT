public class TreeVertex {
    private String name;
    private TreeVertex leftChild;
    private TreeVertex rightChild;
    public TreeVertex(String name) {
        this.name = name;
        //leftChild = rightChild = null;
    }

    public TreeVertex(TreeVertex x) {//konstruktor kopiujący
        this.name = x.name;
        this.leftChild = x.leftChild;
        this.rightChild = x.rightChild;
    }
    public Boolean hasChildren() {
        if(leftChild != null || rightChild != null)
            return true;
        return false;
    }

    public String getName() {
        return name;
    }
    public TreeVertex getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeVertex leftChild) {
        this.leftChild = leftChild;
    }

    public TreeVertex getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeVertex rightChild) {
        this.rightChild = rightChild;
    }
}
