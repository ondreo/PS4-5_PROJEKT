import java.util.LinkedList;
import java.util.PriorityQueue;

public class Graph {
    public Graph() {
        ;//
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

        cityA.addRoad(cityB,length);
        return cityB.addRoad(cityA,length);
    }
    public String removeRoad(String cityAName, String cityBName) {
        City cityA = Main.tree.findCity(cityAName);
        City cityB = Main.tree.findCity(cityBName);

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

        //int[] dist = new int[]

        PriorityQueue<City>pq = new PriorityQueue<>();//TODO: dodać compareTo oraz equals
        sourceCity.setDistFromSource(0);
        //sourceCity.setPredecessor(null);
        //sourceCity.setVisited(false);
        //TODO: ustawienie pozostałych wierzchołków na odpowiednie wartości i to możliwe, że wcześniej w kodzie o 2 linijki
        //podobnie z visited

        pq.add(sourceCity);

        while(pq.peek() != destCity) {
            City currCity = pq.poll();
            currCity.setVisited(true);
            LinkedList<Road> adjacentCities = currCity.getAdjacentCities();

            for(Road road : adjacentCities) {
                if(currCity.getDistFromSource() + road.getLength() < road.getDestCity().getDistFromSource()) {
                    road.getDestCity().setDistFromSource(currCity.getDistFromSource() + road.getLength());
                    //road.getDestCity().setPredecessor(currCity);

                    pq.remove(road.getDestCity());
                    pq.add(road.getDestCity());
                }
            }
            currCity.setAdjacentCities(adjacentCities);
        }
        return String.valueOf(destCity.getDistFromSource());

        //return out;//samą długość czy postać drogi też??
    }
}
