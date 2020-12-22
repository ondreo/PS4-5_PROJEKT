import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BstTree {
    private TreeVertex startVertex;
    public BstTree() {
        startVertex = null;
        //vertices = new TreeVertex[Main.MAX_N+1];
    }

    //public void add(TreeVertex x) {
    public void add(String name) throws NameException {
        TreeVertex currVertex = this.startVertex;
        if(this.startVertex == null) {
            startVertex = new TreeVertex(name);
            return;
        }
        while(currVertex != null) {
            /**
             * wstawiany wierzchołek jest mniejszy leksykograficznie od aktualnego
             */
            if(name.compareTo(currVertex.getName()) < 0) {
                if(currVertex.getLeftChild() == null) {
                    currVertex.setLeftChild(new TreeVertex(name));
                    return;
                }
                else currVertex = currVertex.getLeftChild();
            }
            else if(name.compareTo(currVertex.getName()) > 0) {
                if(currVertex.getRightChild() == null) {
                    currVertex.setRightChild(new TreeVertex(name));
                    return;
                }
                else currVertex = currVertex.getRightChild();
            }
            else {
                throw new NameException("Given city name already exists!");
            }
        }
    }

    public TreeVertex findCity(String name) {
        TreeVertex currVertex = this.startVertex;
        while(currVertex != null) {
            if(name.compareTo(currVertex.getName()) < 0) {
                currVertex = currVertex.getLeftChild();
            }
            else if(name.compareTo(currVertex.getName()) > 0) {
                currVertex = currVertex.getRightChild();
            }
            else {
                return currVertex;
            }
        }
        throw new NoSuchElementException("There is no city with given name.");//Check it's name again.
    }
    public void remove(String name) {
        TreeVertex parent = this.startVertex;
        TreeVertex currVertex = this.startVertex;
        String whichChild = "";

        while(currVertex != null) {
            /**
             * jeśli wierzchołek do usunięcia jest mniejszy leksykograficznie
             * od aktualnego, to przejdź do jego lewego poddrzewa
             */
            if (name.compareTo(currVertex.getName()) < 0) {
                parent = currVertex;
                whichChild = "left";
                currVertex = currVertex.getLeftChild();
            }
            else if (name.compareTo(currVertex.getName()) > 0) {
                parent = currVertex;
                whichChild = "right";
                currVertex = currVertex.getRightChild();
            }
            else {//znaleziony wierzcholek do usunięcia
                break;
            }
        }
        if(currVertex == null) {
            throw new NoSuchElementException("There is no city with given name.");
        }
        if(!currVertex.hasChildren()) {
            if(whichChild == "left") parent.setLeftChild(null);
            else if(whichChild == "right") parent.setRightChild(null);
            else this.startVertex = null;
        }
        else if(currVertex.hasLeftChild() && currVertex.hasRightChild()){
            TreeVertex successor = findSuccessor(currVertex, parent);
            successor.setLeftChild(currVertex.getLeftChild());
            successor.setRightChild(currVertex.getRightChild());
            if(whichChild.equals("left")) parent.setLeftChild(successor);
            else if(whichChild.equals("right")) parent.setRightChild(successor);
            else this.startVertex = successor;
        }
        else if(currVertex.hasLeftChild()) {
            if(whichChild.equals("left")) parent.setLeftChild(currVertex.getLeftChild());
            else if(whichChild.equals("right")) parent.setRightChild(currVertex.getLeftChild());
            else this.startVertex = currVertex.getLeftChild();
        }
        else if(currVertex.hasRightChild()) {
            if(whichChild.equals("left")) parent.setLeftChild(currVertex.getRightChild());
            else if(whichChild.equals("right")) parent.setRightChild(currVertex.getRightChild());
            else this.startVertex = currVertex.getRightChild();
        }
    }


    private TreeVertex findSuccessor(TreeVertex currVertex, TreeVertex parent) {
        parent = currVertex;
        currVertex = currVertex.getRightChild();
        String whichChild = "right";
        while(currVertex.hasLeftChild()) {
            parent = currVertex;
            currVertex = currVertex.getLeftChild();
            whichChild = "left";
        }
        if(currVertex.hasRightChild()) {//można ifa i elsa uprościć do tego pierwszego
            if(whichChild.equals("left")) parent.setLeftChild(currVertex.getRightChild());
            else if(whichChild.equals("right")) parent.setRightChild(currVertex.getRightChild());
        }
        else {
            if(whichChild.equals("left")) parent.setLeftChild(null);
            else if(whichChild.equals("right")) parent.setRightChild(null);
        }
        return currVertex;
    }

    //static int counter;
    public int countCitiesByPrefix(String prefix, TreeVertex currVertex) {
        int counter = 0;
        if(currVertex == null) {
            return 0;
        }


        if(isItAPrefix(prefix,currVertex.getName()) <= 0) {
            counter += countCitiesByPrefix(prefix, currVertex.getLeftChild());
            if(counter > 100)
                return counter;
        }
//        if(prefix.compareTo(currVertex.getName()) >= 0) {
        if(isItAPrefix(prefix,currVertex.getName()) >= 0) {
            counter += countCitiesByPrefix(prefix,currVertex.getRightChild());
            if(counter > 100)
                return counter;
        }
        if(isItAPrefix(prefix,currVertex.getName()) == 0) {
            return counter + 1;
        }

        return counter;
    }

    private int isItAPrefix(String prefix,String name) {
        if(prefix.length() <= name.length()) {
            //return prefix.compareTo(name.substring(0, prefix.length()));
            int compare = prefix.compareTo(name.substring(0, prefix.length()));
            if(compare < 0) return -1;
            if(compare == 0) return 0;
            if(compare > 0) return 1;
        }
        else {
            int compare = prefix.substring(0,name.length()).compareTo(name);
            if(compare < 0) return -1;
            //if(compare == 0) return 0;
            if(compare >= 0) return 1;
        }
        //else return -10_000;


        return -3;
    }

    //TODO:
    //change to AVL (RR,LL,RL,LR)
    private void RR() {
        ;//
    }


    public TreeVertex getStartVertex() {
        return startVertex;
    }

    @Override
    public String toString() {
        ArrayList<TreeVertex>q = new ArrayList<>();
        String out = "";
        TreeVertex currVertex = this.startVertex;
        q.add(currVertex);
        while(!q.isEmpty()) {
            currVertex = q.remove(0);
            //System.out.println(currVertex.getName());
            out += currVertex.getName() + " ";
            if(currVertex.hasLeftChild()) q.add(currVertex.getLeftChild());
            if(currVertex.hasRightChild()) q.add(currVertex.getRightChild());
        }
        return out;
    }
}