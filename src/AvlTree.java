import java.util.ArrayList;
import java.util.Stack;

public class AvlTree {//class sieć, net,??
    private City startCity;

    public AvlTree() {
        startCity = null;
    }

    public String addVertex(String name) {
        Stack<City>stack = new Stack<>();

        City currCity = this.startCity;
        if(this.startCity == null) {
            startCity = new City(name);
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

    public String removeVertex(String name) {
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
        if(!currCity.hasChildren()) {
            if(whichChild == "left") parent.setLeftChild(null);
            else if(whichChild == "right") parent.setRightChild(null);
            else this.startCity = null;

            currCity = parent;//na potrzeby metody balanceTheTree
        }
        else if(currCity.hasLeftChild() && currCity.hasRightChild()){
            City successor = findSuccessor(currCity);
            currCity.setName(successor.getName());
            this.removeVertex(successor.getName());

            stack.add(currCity);
        }
        else if(currCity.hasLeftChild()) {
            //stack.add(currCity);
            //v1:
            currCity.setName(currCity.getLeftChild().getName());
            currCity.setLeftChild(currCity.getLeftChild().getLeftChild());
            currCity.setLeftChild(currCity.getLeftChild().getRightChild());

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
            currCity.setLeftChild(currCity.getRightChild().getRightChild());

            /*
            //v2
            if(whichChild.equals("left")) parent.setLeftChild(currCity.getRightChild());
            else if(whichChild.equals("right")) parent.setRightChild(currCity.getRightChild());
            else this.startCity = currCity.getRightChild();*/

            stack.add(currCity);
        }


        //raczej currCity jest dobrze, ale sprawdzić jeśli będzie błąd
        balanceTheTree(currCity,stack);



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
        City y = x.getRightChild();
        City T2 = y.getLeftChild();

        y.setLeftChild(x);
        x.setRightChild(T2);

        x.setHeight(Math.max(getVertexHeight(x.getLeftChild()),getVertexHeight(x.getRightChild()))+1);
        y.setHeight(Math.max(getVertexHeight(y.getLeftChild()),getVertexHeight(y.getRightChild()))+1);
        if(getVertexHeight(y) == 8) {
            System.out.println("wtf");
        }

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

    @Override
    public String toString() {
        ArrayList<City>q = new ArrayList<>();
        String out = "";
        City currCity = this.startCity;
        q.add(currCity);
        while(!q.isEmpty()) {
            currCity = q.remove(0);
            //System.out.println(currCity.getName());
            //out += currCity.getName() + " ";
            out += currCity.toString()+ "\n";
            if(currCity.hasLeftChild()) q.add(currCity.getLeftChild());
            if(currCity.hasRightChild()) q.add(currCity.getRightChild());
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
            if (balance > 1) {
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
