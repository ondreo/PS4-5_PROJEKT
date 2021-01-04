import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

public class Graph {
    public Graph() {
        //TODO: sprawdzić jaka to klasa bez zmiennych
    }

    /*public String addVertex(String name) {
        return Main.tree.addVertex(name);
    }
    public String removeVertex(String name) {
        return Main.tree.removeVertex(name);
    }*/
    //^????^

    /**
     *
     * @param cityAName - name of a city A
     * @param cityBName - name of a city B
     * @param length - length of the road expressed in kilometers
     * //@return
     */
    public String addRoad(String cityAName, String cityBName, int length) {//długość w kilometrach
        City cityA = Main.tree.findCity(cityAName);
        City cityB = Main.tree.findCity(cityBName);

        String out = "";
        if(cityA == null) out = "Miasto A nie istnieje";
        if(cityB == null) {
            if(!out.equals("")) out += '\n';
            out += "Miasto B nie istnieje";
        }
        if(!out.equals("")) return out;

        cityA.addRoad(cityB,length);
        return cityB.addRoad(cityA,length);
    }
    public String removeRoad(String cityAName, String cityBName) {
        City cityA = Main.tree.findCity(cityAName);
        City cityB = Main.tree.findCity(cityBName);

        String out = "";
        if(cityA == null) out = "Miasto A nie istnieje";
        if(cityB == null) {
            if(!out.equals("")) out += '\n';
            out += "Miasto B nie istnieje";
        }
        if(!out.equals("")) return out;

        cityA.removeRoad(cityB);
        return cityB.removeRoad(cityA);
    }

    public String findShortestPathBetweenTwoCities(String cityAName, String cityBName) {
        String out = "";
        City sourceCity = Main.tree.findCity(cityBName);
        City destCity = Main.tree.findCity(cityAName);

        if(destCity == null) out = "Miasto A nie istnieje";
        if(sourceCity == null) {
            if(!out.equals("")) out += '\n';
            out += "Miasto B nie istnieje";
        }
        if(!out.equals("")) return out;

        dijkstra(sourceCity);
        if(destCity.getDistFromSource() == Integer.MAX_VALUE)
            return "Nie istnieje ścieżka pomiędzy podanymi miastami!";
        return "Długość najkrótszej ścieżki pomiędzy miastami = " + destCity.getDistFromSource();

        //return out;//samą długość czy postać drogi też??
    }

    public String whatIfRoadAdded(String cityAName, String cityBName, String cityCName, int length) {
        String out = "";
        City cityA = Main.tree.findCity(cityAName);
        City cityB = Main.tree.findCity(cityBName);
        City cityC = Main.tree.findCity(cityCName);

        if(cityA == null) out = "Miasto A nie istnieje";
        if(cityB == null) {
            if(!out.equals("")) out += '\n';
            out += "Miasto B nie istnieje";
        }
        if(cityC == null) {
            if(!out.equals("")) out += '\n';
            out += "Miasto C nie istnieje";
        }
        if(!out.equals("")) return out;


        initializePrevDistances(cityA);
        dijkstra(cityA);
        copyDistances();
        cityB.addRoad(cityC,length);
        cityC.addRoad(cityB,length);
        dijkstra(cityA);
        cityB.removeRoad(cityC);
        cityC.removeRoad(cityB);
        return String.valueOf(compareDistances());
    }

    private void dijkstra(City sourceCity) {
        PriorityQueue<City>pq = new PriorityQueue<>();
        initializeDijkstraParams(sourceCity);
        int visCount = 0;
        pq.add(sourceCity);

        while(visCount < Main.tree.getN() && !pq.isEmpty()) {
            City currCity = pq.poll();
            currCity.setVisited(true);
            ++visCount;
            LinkedList<Road> adjacentCities = currCity.getAdjacentCities();

            for(Road road : adjacentCities) {
                if(currCity.getDistFromSource() + road.getLength() < road.getDestCity().getDistFromSource()) {
                    road.getDestCity().setDistFromSource(currCity.getDistFromSource() + road.getLength());
                    road.getDestCity().setPredecessor(currCity);

                    pq.remove(road.getDestCity());//aktualizujemy odległości na kolejce
                    pq.add(road.getDestCity());
                }
            }
            currCity.setAdjacentCities(adjacentCities);
        }
    }

    private void initializeDijkstraParams(City sourceCity) {
        City x = Main.tree.getStartVertex();
        Stack<City> stack = new Stack<>();
        stack.add(x);
        while(!stack.isEmpty()) {
            x = stack.peek();
            stack.pop();
            if(x == sourceCity) x.setDistFromSource(0);
            else x.setDistFromSource(Integer.MAX_VALUE);
            x.setPredecessor(null);
            x.setVisited(false);
            if(x.hasLeftChild()) stack.add(x.getLeftChild());
            if(x.hasRightChild()) stack.add(x.getRightChild());
        }
    }

    private void initializePrevDistances(City sourceCity) {
        City x = Main.tree.getStartVertex();
        Stack<City> stack = new Stack<>();
        stack.add(x);
        while(!stack.isEmpty()) {
            x = stack.peek();
            stack.pop();
            if(x == sourceCity) x.setPrevDistFromSource(0);
            else x.setPrevDistFromSource(Integer.MAX_VALUE);

            if(x.hasLeftChild()) stack.add(x.getLeftChild());
            if(x.hasRightChild()) stack.add(x.getRightChild());
        }
    }

    private void copyDistances() {
        City x = Main.tree.getStartVertex();
        Stack<City> stack = new Stack<>();
        stack.add(x);
        while(!stack.isEmpty()) {
            x = stack.peek();
            stack.pop();
            x.setPrevDistFromSource(x.getDistFromSource());

            if(x.hasLeftChild()) stack.add(x.getLeftChild());
            if(x.hasRightChild()) stack.add(x.getRightChild());
        }
    }

    private int compareDistances() {
        int sum = 0;

        City x = Main.tree.getStartVertex();
        Stack<City> stack = new Stack<>();
        stack.add(x);
        while(!stack.isEmpty()) {
            x = stack.peek();
            stack.pop();
            if(x.getDistFromSource() < x.getPrevDistFromSource()) ++sum;

            if(x.hasLeftChild()) stack.add(x.getLeftChild());
            if(x.hasRightChild()) stack.add(x.getRightChild());
        }
        return sum;
    }
}
