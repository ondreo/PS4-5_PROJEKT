public class TreeVertex {
    private String name;
    private TreeVertex leftChild;
    private TreeVertex rightChild;
    private int height;

    public TreeVertex(String name) {
        this.name = name;
        leftChild = rightChild = null;
        this.height = 1;
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
    public void setName(String name) {
        this.name = name;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        String out = "TreeVertex{" +
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
