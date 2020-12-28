import java.util.ArrayList;
import java.util.Stack;

public class BstTree {//class sieć, net,??
    private TreeVertex startVertex;

    public BstTree() {
        startVertex = null;
    }

    public String add(String name) {
        Stack<TreeVertex>stack = new Stack<>();

        TreeVertex currVertex = this.startVertex;
        if(this.startVertex == null) {
            startVertex = new TreeVertex(name);
            return "TAK";
        }
        while(currVertex != null) {
            stack.add(currVertex);
            /**
             * wstawiany wierzchołek jest mniejszy leksykograficznie od aktualnego
             */
            if(name.compareTo(currVertex.getName()) < 0) {
                if(currVertex.getLeftChild() == null) {
                    currVertex.setLeftChild(new TreeVertex(name));
                    balanceTheTree(currVertex.getLeftChild(), stack);
                    return "TAK";
                }
                else {
                    currVertex = currVertex.getLeftChild();
                }
            }
            else if(name.compareTo(currVertex.getName()) > 0) {
                if(currVertex.getRightChild() == null) {
                    currVertex.setRightChild(new TreeVertex(name));
                    balanceTheTree(currVertex.getRightChild(), stack);
                    return "TAK";
                }
                else {
                    currVertex = currVertex.getRightChild();
                }
            }
            else {
                return "NIE";//stos automatycznie się opróżni
            }
        }
        return "";//nie będie nigdy takiej sytuacji, ale kompilator wymaga zwrócenia czegokolwiek
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
        return null;
    }

    public String remove(String name) {//TODO: dodać elementy AVL
        Stack<TreeVertex>stack = new Stack<>();

        TreeVertex parent = this.startVertex;
        TreeVertex currVertex = this.startVertex;
        String whichChild = "";

        while(currVertex != null) {
            /**
             * jeśli wierzchołek do usunięcia jest mniejszy leksykograficznie
             * od aktualnego, to przejdź do jego lewego poddrzewa
             */
            if (name.compareTo(currVertex.getName()) < 0) {
                stack.add(currVertex);
                parent = currVertex;
                whichChild = "left";
                currVertex = currVertex.getLeftChild();
            }
            else if (name.compareTo(currVertex.getName()) > 0) {
                stack.add(currVertex);
                parent = currVertex;
                whichChild = "right";
                currVertex = currVertex.getRightChild();
            }
            else {//znaleziony wierzcholek do usunięcia
                break;
            }
        }
        if(currVertex == null) {
            return "NIE";
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
            stack.add(successor);
        }
        else if(currVertex.hasLeftChild()) {
            if(whichChild.equals("left")) parent.setLeftChild(currVertex.getLeftChild());
            else if(whichChild.equals("right")) parent.setRightChild(currVertex.getLeftChild());
            else this.startVertex = currVertex.getLeftChild();
            stack.add(currVertex.getLeftChild());
        }
        else if(currVertex.hasRightChild()) {
            if(whichChild.equals("left")) parent.setLeftChild(currVertex.getRightChild());
            else if(whichChild.equals("right")) parent.setRightChild(currVertex.getRightChild());
            else this.startVertex = currVertex.getRightChild();
            stack.add(currVertex.getRightChild());
        }
        //balanceTheTree();
        return "TAK";
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

    private TreeVertex leftRotation(TreeVertex x) {
        TreeVertex y = x.getRightChild();
        TreeVertex T2 = y.getLeftChild();

        y.setLeftChild(x);
        x.setRightChild(T2);

        x.setHeight(Math.max(getVertexHeight(x.getLeftChild()),getVertexHeight(x.getRightChild()))+1);
        y.setHeight(Math.max(getVertexHeight(y.getLeftChild()),getVertexHeight(y.getRightChild()))+1);
        if(getVertexHeight(y) == 8) {
            System.out.println("wtf");
        }

        return y;
    }

    private TreeVertex rightRotation(TreeVertex x) {
        TreeVertex y = x.getLeftChild();
        TreeVertex T2 = y.getRightChild();

        y.setRightChild(x);
        x.setLeftChild(T2);

        x.setHeight(Math.max(getVertexHeight(x.getLeftChild()),getVertexHeight(x.getRightChild()))+1);
        y.setHeight(Math.max(getVertexHeight(y.getLeftChild()),getVertexHeight(y.getRightChild()))+1);

        return y;
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
            //out += currVertex.getName() + " ";
            out += currVertex.toString()+ "\n";
            if(currVertex.hasLeftChild()) q.add(currVertex.getLeftChild());
            if(currVertex.hasRightChild()) q.add(currVertex.getRightChild());
        }
        return out;
    }

    private void balanceTheTree(TreeVertex currVertex ,Stack<TreeVertex>stack) {
        TreeVertex x;
        while(!stack.empty()) {
            x = stack.peek();
            stack.pop();
            x.setHeight(Math.max(getVertexHeight(x.getLeftChild()),getVertexHeight(x.getRightChild()))+1);

            int balance = getVertexHeight(x.getLeftChild())-getVertexHeight(x.getRightChild());
            if (balance > 1) {
                //System.out.println("currVertex = " + currVertex.getName() + ",   x.getLeftChild() = " + x.getLeftChild().getName());
                if (currVertex.getName().compareTo(x.getLeftChild().getName()) < 0) {//LL case
                    if (stack.empty()) this.startVertex = rightRotation(x);
                    else {
                        TreeVertex ancestor = stack.peek();
                        //stack.pop();
                        if(ancestor.getLeftChild() == x) ancestor.setLeftChild(rightRotation(x));
                        if(ancestor.getRightChild() == x) ancestor.setRightChild(rightRotation(x));
                        //stack.add(ancestor);
                    }
                }
                else {//LR case
                    x.setLeftChild(leftRotation(x.getLeftChild()));
                    if (stack.empty()) this.startVertex = rightRotation(x);
                    else {
                        TreeVertex ancestor = stack.peek();
                        //stack.pop();
                        if(ancestor.getLeftChild() == x) ancestor.setLeftChild(rightRotation(x));
                        if(ancestor.getRightChild() == x) ancestor.setRightChild(rightRotation(x));
                        //stack.add(ancestor);
                    }
                }
            }
            else if (balance < -1) {
                if (currVertex.getName().compareTo(x.getRightChild().getName()) > 0) {//RR case
                    if (stack.empty()) this.startVertex = leftRotation(x);
                    else {
                        TreeVertex ancestor = stack.peek();
                        if(ancestor.getLeftChild() == x) ancestor.setLeftChild(leftRotation(x));
                        if(ancestor.getRightChild() == x) ancestor.setRightChild(leftRotation(x));
                    }
                }
                else {//RL case
                    x.setRightChild(rightRotation(x.getRightChild()));
                    if (stack.empty()) this.startVertex = leftRotation(x);
                    else {
                        TreeVertex ancestor = stack.peek();
                        if(ancestor.getLeftChild() == x) ancestor.setLeftChild(leftRotation(x));
                        if(ancestor.getRightChild() == x) ancestor.setRightChild(leftRotation(x));
                    }
                }
            }
        }
    }

    private int getVertexHeight(TreeVertex v) {
        return v!=null ? v.getHeight() : 0;
    }
}
