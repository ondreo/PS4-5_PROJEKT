import java.util.ArrayList;
import java.util.Stack;

public class AvlTree {
    private City startCity;
    private int n;

    public AvlTree() {
        startCity = null;
        n = 0;
    }

    public String addVertex(String name) {
        Stack<City>stack = new Stack<>();

        City currCity = this.startCity;
        if(this.startCity == null) {
            startCity = new City(name);
            ++n;
            return "TAK";
        }
        while(currCity != null) {
            stack.add(currCity);
            /**
             * wstawiany wierzchołek jest mniejszy leksykograficznie od aktualnego
             */
            if(name.compareTo(currCity.getName()) < 0) {
                if(currCity.getLeftChild() == null) {
                    currCity.setLeftChild(new City(name));
                    balanceTheTree(currCity.getLeftChild(), stack);
                    ++n;
                    return "TAK";
                }
                else {
                    currCity = currCity.getLeftChild();
                }
            }
            else if(name.compareTo(currCity.getName()) > 0) {
                if(currCity.getRightChild() == null) {
                    currCity.setRightChild(new City(name));
                    balanceTheTree(currCity.getRightChild(), stack);
                    ++n;
                    return "TAK";
                }
                else {
                    currCity = currCity.getRightChild();
                }
            }
            else {
                return "NIE";//stos automatycznie się opróżni
            }
        }
        return "";//nie będie nigdy takiej sytuacji, ale kompilator wymaga zwrócenia czegokolwiek
    }

    public City findCity(String name) {
        City currCity = this.startCity;
        while(currCity != null) {
            if(name.compareTo(currCity.getName()) < 0) {
                currCity = currCity.getLeftChild();
            }
            else if(name.compareTo(currCity.getName()) > 0) {
                currCity = currCity.getRightChild();
            }
            else {
                return currCity;
            }
        }
        return null;
    }

    public String removeVertex(String name,String predecessorName) {
        Stack<City>stack = new Stack<>();

        City parent = this.startCity;
        City currCity = this.startCity;
        String whichChild = "";

        while(currCity != null) {
            /**
             * jeśli wierzchołek do usunięcia jest mniejszy leksykograficznie
             * od aktualnego, to przejdź do jego lewego poddrzewa
             */
            if (name.compareTo(currCity.getName()) < 0) {
                stack.add(currCity);
                parent = currCity;
                whichChild = "left";
                currCity = currCity.getLeftChild();
            }
            else if (name.compareTo(currCity.getName()) > 0) {
                stack.add(currCity);
                parent = currCity;
                whichChild = "right";
                currCity = currCity.getRightChild();
            }
            else {//znaleziony wierzcholek do usunięcia
                break;
            }
        }
        if(currCity == null) {
            return "NIE";
        }
        //System.out.println("currCity.getName() = " + currCity.getName());
        if(!currCity.hasChildren()) {
            if(whichChild == "left") parent.setLeftChild(null);
            else if(whichChild == "right") parent.setRightChild(null);
            else this.startCity = null;

            currCity = parent;//na potrzeby metody balanceTheTree
        }
        else if(currCity.hasLeftChild() && currCity.hasRightChild()){
            City successor = findSuccessor(currCity);
            String tmp = successor.getName();
            if(currCity.getName().equals("f")) {
                //System.out.println("currCity.getName() = " + currCity.getName() + ", successor.getName() = " +successor.getName());
            }//TODO: to be deleted
            this.removeVertex(successor.getName(),currCity.getName());
            currCity.setName(tmp);

            stack.add(currCity);
        }
        else if(currCity.hasLeftChild()) {
            //stack.add(currCity);
            //v1:
            currCity.setName(currCity.getLeftChild().getName());
            currCity.setRightChild(currCity.getLeftChild().getRightChild());
            currCity.setLeftChild(currCity.getLeftChild().getLeftChild());

            /*
            //v2:
            if(whichChild.equals("left")) parent.setLeftChild(currCity.getLeftChild());
            else if(whichChild.equals("right")) parent.setRightChild(currCity.getLeftChild());
            else this.startCity = currCity.getLeftChild();*/

            stack.add(currCity);
        }
        else if(currCity.hasRightChild()) {
            //stack.add(currCity);
            //v1:
            currCity.setName(currCity.getRightChild().getName());
            currCity.setLeftChild(currCity.getRightChild().getLeftChild());
            currCity.setRightChild(currCity.getRightChild().getRightChild());

            /*
            //v2
            if(whichChild.equals("left")) parent.setLeftChild(currCity.getRightChild());
            else if(whichChild.equals("right")) parent.setRightChild(currCity.getRightChild());
            else this.startCity = currCity.getRightChild();*/

            stack.add(currCity);
        }

        //raczej currCity jest dobrze, ale sprawdzić jeśli będzie błąd
        //System.out.println("currCity = " + currCity);
        if(!currCity.getName().equals(predecessorName)) balanceTheTree(currCity,stack);//czy na pewno???????????????????????????????
        //else System.out.println("currCity = " + currCity);

        --n;
        return "TAK";
    }


    private City findSuccessor(City currCity) {
        currCity = currCity.getRightChild();
        while(currCity.hasLeftChild()) {
            currCity = currCity.getLeftChild();
        }
        return currCity;
    }

    //static int counter;
    public int countCitiesByPrefix(String prefix, City currCity) {
        int counter = 0;
        if(currCity == null) {
            return 0;
        }


        if(isItAPrefix(prefix, currCity.getName()) <= 0) {
            counter += countCitiesByPrefix(prefix, currCity.getLeftChild());
            if(counter > 100)
                return counter;
        }
//        if(prefix.compareTo(currCity.getName()) >= 0) {
        if(isItAPrefix(prefix, currCity.getName()) >= 0) {
            counter += countCitiesByPrefix(prefix, currCity.getRightChild());
            if(counter > 100)
                return counter;
        }
        if(isItAPrefix(prefix, currCity.getName()) == 0) {
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

    private City leftRotation(City x) {
        //System.out.println("x = " + x);
        City y = x.getRightChild();
        City T2 = y.getLeftChild();

        y.setLeftChild(x);
        x.setRightChild(T2);

        x.setHeight(Math.max(getVertexHeight(x.getLeftChild()),getVertexHeight(x.getRightChild()))+1);
        y.setHeight(Math.max(getVertexHeight(y.getLeftChild()),getVertexHeight(y.getRightChild()))+1);

        return y;
    }

    private City rightRotation(City x) {
        City y = x.getLeftChild();
        City T2 = y.getRightChild();

        y.setRightChild(x);
        x.setLeftChild(T2);

        x.setHeight(Math.max(getVertexHeight(x.getLeftChild()),getVertexHeight(x.getRightChild()))+1);
        y.setHeight(Math.max(getVertexHeight(y.getLeftChild()),getVertexHeight(y.getRightChild()))+1);

        return y;
    }

    public City getStartVertex() {
        return startCity;
    }

    public int getN() {
        return n;
    }
    public void setN(int n) {
        this.n = n;
    }

    @Override
    public String toString() {//TODO: rzeczywisty wygląd drzewa (spacje/tabulacje według wysokości)
        if(this.startCity == null) return "Drzewo nie zawiera żadnych wierzchołków.";
        ArrayList<City>q = new ArrayList<>();
        String out = "";

        int maxDepth = 0;
        this.startCity.setDepth(1);
        int[] howManyWrittenInGivenDepth;

        City currCity = this.startCity;
        q.add(currCity);
        while(!q.isEmpty()) {
            currCity = q.remove(0);
            maxDepth = Math.max(maxDepth,currCity.getDepth());
            if(currCity.hasLeftChild()) {
                q.add(currCity.getLeftChild());
                currCity.getLeftChild().setDepth(currCity.getDepth()+1);
            }
            if(currCity.hasRightChild()) {
                q.add(currCity.getRightChild());
                currCity.getRightChild().setDepth(currCity.getDepth()+1);
            }
        }

        howManyWrittenInGivenDepth = new int[maxDepth+2];
        for(int i=1;i<=maxDepth+1;++i) howManyWrittenInGivenDepth[i] = 0;

        if(maxDepth <= 4) {//TODO:dobrze dobrać rozmiar
            currCity = this.startCity;
            q.add(currCity);
            while(!q.isEmpty()) {
                currCity = q.remove(0);
                //System.out.println("currCity.getDepth() = " + currCity.getDepth() + ", maxDepth = " + maxDepth);
                if(currCity.getDepth() == 4) ;//nic nie rób
                else if(currCity.getDepth() == 3) for(int i=1;i<=4;++i) out += '\t';
                else if(currCity.getDepth() == 2) for(int i=1;i<=8;++i) out += '\t';
                else if(currCity.getDepth() == 1) for(int i=1;i<=16;++i) out += '\t';
                out += currCity.getName();
                if(currCity.getName().length() < 16) out += '\t';
                if(currCity.getName().length() < 12) out += '\t';
                if(currCity.getName().length() < 8) out += '\t';
                if(currCity.getName().length() < 4) out += '\t';
                if(++howManyWrittenInGivenDepth[currCity.getDepth()] == currCity.getDepth()) out +='\n';

                if(!currCity.getName().equals("null")) {
                    if(currCity.hasLeftChild()) q.add(currCity.getLeftChild());
                    else q.add(new City("null",currCity.getDepth()+1));
                    if(currCity.hasRightChild()) q.add(currCity.getRightChild());
                    else q.add(new City("null",currCity.getDepth()+1));
                }
            }
        }
        else {
            currCity = this.startCity;
            q.add(currCity);
            while(!q.isEmpty()) {
                currCity = q.remove(0);
                //System.out.println(currCity.getName());
                //out += currCity.getName() + " ";
                out += currCity.toString()+ "\n";
                if(currCity.hasLeftChild()) q.add(currCity.getLeftChild());
                if(currCity.hasRightChild()) q.add(currCity.getRightChild());
            }
        }
        return out;
    }

    private void balanceTheTree(City currCity, Stack<City>stack) {
        City x;
        while(!stack.empty()) {
            x = stack.peek();
            stack.pop();
            x.setHeight(Math.max(getVertexHeight(x.getLeftChild()),getVertexHeight(x.getRightChild()))+1);

            int balance = getVertexHeight(x.getLeftChild())-getVertexHeight(x.getRightChild());
            if (balance > 1) {//dać inne warunki wewnętrzne!!! zależne od balansu dzieci, a nie od nazwy wierzchołka
                //System.out.println("currCity = " + currCity.getName() + ",   x.getLeftChild() = " + x.getLeftChild().getName());
                if (currCity.getName().compareTo(x.getLeftChild().getName()) < 0) {//LL case
                    if (stack.empty()) this.startCity = rightRotation(x);
                    else {
                        City ancestor = stack.peek();
                        //stack.pop();
                        if(ancestor.getLeftChild() == x) ancestor.setLeftChild(rightRotation(x));
                        if(ancestor.getRightChild() == x) ancestor.setRightChild(rightRotation(x));
                        //stack.add(ancestor);
                    }
                }
                else {//LR case
                    x.setLeftChild(leftRotation(x.getLeftChild()));
                    if (stack.empty()) this.startCity = rightRotation(x);
                    else {
                        City ancestor = stack.peek();
                        //stack.pop();
                        if(ancestor.getLeftChild() == x) ancestor.setLeftChild(rightRotation(x));
                        if(ancestor.getRightChild() == x) ancestor.setRightChild(rightRotation(x));
                        //stack.add(ancestor);
                    }
                }
            }
            else if (balance < -1) {
                if (currCity.getName().compareTo(x.getRightChild().getName()) > 0) {//RR case
                    if (stack.empty()) this.startCity = leftRotation(x);
                    else {
                        City ancestor = stack.peek();
                        if(ancestor.getLeftChild() == x) ancestor.setLeftChild(leftRotation(x));
                        if(ancestor.getRightChild() == x) ancestor.setRightChild(leftRotation(x));
                    }
                }
                else {//RL case
                    x.setRightChild(rightRotation(x.getRightChild()));
                    if (stack.empty()) this.startCity = leftRotation(x);
                    else {
                        City ancestor = stack.peek();
                        if(ancestor.getLeftChild() == x) ancestor.setLeftChild(leftRotation(x));
                        if(ancestor.getRightChild() == x) ancestor.setRightChild(leftRotation(x));
                    }
                }
            }
        }
    }

    private int getVertexHeight(City v) {
        return v!=null ? v.getHeight() : 0;
    }
}
