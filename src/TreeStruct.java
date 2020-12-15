
import com.sun.source.tree.Tree;

import java.util.NoSuchElementException;

public class TreeStruct {
    private TreeVertex startVertex;
    //private TreeVertex[] vertices;//a może lista??
    public TreeStruct() {
        startVertex = null;
        //vertices = new TreeVertex[Main.MAX_N+1];
    }

    //public void add(TreeVertex x) {
    public void add(String name) throws NameException {
        TreeVertex currVertex = this.startVertex;
        while(currVertex!= null) {
            /**
             * wstawiany wierzchołek jest mniejszy leksykograficznie od aktualnego
             */
            if(name.compareTo(currVertex.getName()) < 0) {
                if(currVertex.getLeftChild() == null) {
                    currVertex.setLeftChild(new TreeVertex(name));
                }
                else currVertex = currVertex.getLeftChild();
            }
            else if(name.compareTo(currVertex.getName()) > 0) {
                if(currVertex.getRightChild() == null) {
                    currVertex.setRightChild(new TreeVertex(name));
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
        TreeVertex currVertex = this.startVertex;

        //drugi przypadek w while'u to jeśli startowy wierzchołek jest tym właściwym:
        while(currVertex != null) {
            TreeVertex child = null;
            /**
             * jeśli wierzchołek do usunięcia jest mniejszy leksykograficznie
             * od aktualnego, to przejdź do jego lewego poddrzewa
             */
            if(name.compareTo(currVertex.getName()) < 0) {
                child = currVertex.getLeftChild();
            }
            else if(name.compareTo(currVertex.getName()) > 0) {
                child = currVertex.getRightChild();
            }
            else {//tutaj
                //TODO:
                //znajdzNastepnika i wstaw go gdzie trzeba
                TreeVertex tmp = findSuccessor(currVertex);
            }
            if(child != null && name.compareTo(child.getName()) == 0) {
                if(!child.hasChildren())
                    child = null;
                else if(child.getLeftChild() != null && child.getRightChild() != null) {
                    //TODO:
                    //znajdzNastepnika i wstaw go gdzie trzeba
                    //ale z uwzględnieniem nowego ojca (currVertex)
                }
                else if(child.getLeftChild() != null) {
                    currVertex.setLeftChild(child.getLeftChild());
                    child = null;
                }
                else if(child.getRightChild() != null) {
                    currVertex.setRightChild(child.getRightChild());
                    child = null;
                }
            }
            return;
        }
        throw new NoSuchElementException("There is no city with given name.");
    }


    private TreeVertex findSuccessor(TreeVertex currVertex) {
        //currVertex = currVertex.getRightChild();
    }


    private Boolean isEmpty() {//czy przy usuwaniu i szukaniu nie będzie to potrzebne??
        if(this.startVertex == null)
            return true;
        return false;
    }
}
