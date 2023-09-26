
// --== CS400 File Header Information ==--
// Name: Hyeonmin Yang
// Email: <hyang486 @wisc.edu>
// Group and Team: <your group name: BJ,  Blue>
// Group TA: <Naman Gupta>>
// Lecturer: <CS400 Lecture002>
// Notes to Grader: <optional extra notes>

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class is Backend program for the ShortestAirport program
 * This class is interacting with DataWrangler and Algorithm Engineer's codes.
 * It will take all data of Airports and it's paths from DataWrengler code and
 * create graph with inserting airports and paths. it will return the shortest
 * path between
 * two or three airports and it will also return the distance betwen those
 * airports.
 * For FrontendDeveloper it will also retur the list of all the path and the
 * Airports in
 * the graph. And Also this will return the full name of the airports with using
 * Hashtable
 */
public class AirportPathBD implements AirportPathInterfaceBD {
    // make graph object for inserting node and get data
    public AirportPathGraphADTInterfaceBD<String, Double> graph;
    // make AiportDatabase object to read all the nodes and edges from dot file
    private AirportDatabaseBD airports;
    // make list of airports to store all the aiports(nodes)
    private List<AirportBD> airportsList;
    // make list of paths to store all the paths(edges)
    private List<PathBD> paths;
    // make hashtable to return full name of airports(value) with airport's
    // code(key)
    private HashMap<String, String> airportDecode;

    // constructor for this class
    public AirportPathBD() {
        // initialize graph with String type node and Double type cost
        graph = new AirportPathGraphADTBD<String, Double>();
        airports = new AirportDatabaseBD();
        // initialize with the list that has all airport
        airportsList = airports.getAirportList();
        // initialize with the list that has all paths
        paths = airports.getPathList();
        // intialize hashtable with String type key and value because
        // the aiport's code and full name of airport is the string type
        airportDecode = new HashMap<String, String>();
        // call loadGraph() method so graph is created with creating AirportsPath object
        loadGraph();
    }

    /**
     * First, with using dotReader() method we will read all airports and paths from
     * the dot file.
     * And with using insertEdge() and insertNode() with the list of all airports
     * and paths,
     * insert all the airports and connecting each airports with path data.
     * So, with this method we can create the graph
     */
    private void loadGraph() {
        // use dotReader() method to read all the nodes and edges from the dot file
        airports.dotReader();
        // read each airport object that store in the airportList
        for (AirportBD airport : airportsList) {
            // insert each airport into the graph
            insertNode(airport.getAirportCode());
            // insert each airport code as a key and airport's full name as a value
            // to hastable
            airportDecode.put(airport.getAirportCode(), airport.getAirportName());
        }
        // read each path object that store in the paths list
        for (PathBD path : paths) {
            // insert each path into the graph
            insertEdge(path.getStart(), path.getEnd(), (double) path.getDistance());
        }
    }

    /**
     * this method is working for inserting edges to the graph. In other words with
     * this
     * method the nodes in the graph will be connected with edge weight.
     * 
     * @param predecessor start airport(node) in the graph
     * @param successor   end airport(node) in th the graph
     * @param weight      distance bewteen two airport
     * @return true if path(start -weight-> end) is succesfully inserted, otherwise
     *         false.
     */
    @Override
    public boolean insertEdge(String predecessor, String successor, double weight) {
        // if the predecessor or end successor is null then throw exception
        if (predecessor == null || successor == null) {
            throw new NoSuchElementException("Start or end node is null");
        } else {
            // with using insertEdge method in the AE's code insert edge to graph
            graph.insertEdge(predecessor, successor, weight);
            // check if the edge is succesfully inserted
            if (graph.containsEdge(predecessor, successor)) {
                // if it is return true
                return true;
            } else {
                // if not return false
                return false;
            }
        }

    }

    /**
     * this method is working for inserting node to the graph. In other words, the
     * airports will be inserted into graph as a node
     * 
     * @param data the airport's name
     * @return true if the airport is succesfully inserted, otherwise false.
     */
    @Override
    public boolean insertNode(String data) {
        // if the node is null then throw the exception
        if (data == null) {
            throw new NoSuchElementException("Start or end node is null");
        } else {
            // with using insertNode method in the AE's code insert node to graph
            graph.insertNode(data);
            // check if the node is succesfully inserted
            if (graph.containsNode(data)) {
                // if it is return true
                return true;
            } else {
                // if not return false
                return false;
            }
        }
    }

    /**
     * getShortestPath method is working for finding and return the shortest path
     * between start airport and end airport(two airports) this method is using
     * shrtestPathData() method in the AE's code.
     * 
     * @param start starting airport of the shortest path
     * @param end   ending airport of the shortest path
     * @return List<String> object that contains data of the shortestPath between
     *         two airports
     */
    @Override
    public List<String> getShortestPath(String start, String end) {
        // create List object that has String type element for store the data
        // of the shortest path between start and end
        List<String> shortestPath = new LinkedList<>();
        // First check if the start and end airport node is in the graph or not
        if (graph.containsNode(start) && graph.containsNode(end)) {
            // if it is, add the data of the shortest path between start and end
            // airports to List
            shortestPath.add(graph.shortestPathData(start, end).toString());
            return shortestPath;
        } else {
            // if the start or end node is not in graph throw the NoSuchElementException
            throw new NoSuchElementException("We don't have airport information between two airports");

        }

    }

    /**
     * getShortestDistance method is working for getting the shortest distance
     * between two airport this method is using shortestPathcost() method in AE's
     * code.
     * This methods also check if the start or end node is existing in the graph and
     * then return the distance of shortest path between two airports
     * 
     * @param start starting airport of the shortest path
     * @param end   ending airport of the shortest path
     * @return the distance of the shortest path between two airports
     */
    @Override
    public double getShortestDistance(String start, String end) {
        // First check if the start and end airport node is in the graph or not
        if (graph.containsNode(start) && graph.containsNode(end)) {
            // if it is, return the distance of the shortest path between start and end
            return graph.shortestPathCost(start, end);
        } else {
            // if not throw NoSuchElementException with error message
            throw new NoSuchElementException("We don't have airport information between two airports");

        }
    }

    /**
     * getShortestTrioPath method is working for finding and return the shortest
     * path
     * between start airport and end airport. However, technically it will return
     * the shorteat path from the uno and tres airport with visiting dos airport
     * So, it will return the shortestPath of three airports
     * 
     * @param uno  starting airport of the shortest path
     * @param dos  the middle position airport betwen uno and thres
     * @param tres ending airport of the shortest path
     * @return List<String> object that contains data of the shortestPath between
     *         three airports
     */
    @Override
    public List<String> getShortestTrioPath(String uno, String dos, String tres) {
        // create List object that has String type element for store the data
        // of the shortest path between start and end
        List<String> shortestTrioPath = new LinkedList<>();
        // First check if the start, middel and end airport node is in the graph or not
        if (graph.containsNode(uno) && graph.containsNode(dos) && graph.containsNode(tres)) {
            // if it is, add the data of the shortest path between start and end with
            // visiting middle airports to List
            shortestTrioPath.add(graph.shortestTrioPathData(uno, dos, tres).toString());
            return shortestTrioPath;
        } else {
            // if not throw NoSuchElementExcepton
            throw new NoSuchElementException("We don't have airport information between three airports");
        }
    }

    /**
     * getShortestTrioPath method is working for finding and return the shortest
     * path's cost between start airport and end airport throught middle airport.
     * So, it will return the shortest distance between three airports
     * 
     * @param uno  starting airport of the shortest path
     * @param dos  the middle position airport betwen uno and thres
     * @param tres ending airport of the shortest path
     * @return shortest distance between three airports
     */
    @Override
    public double getShortestTrioDistance(String uno, String dos, String tres) {
        // First check if the start, middel and end airport node is in the graph or not
        if (graph.containsNode(uno) && graph.containsNode(dos) && graph.containsNode(tres)) {
            // if it is, return the shorest distance between three airports
            return graph.shortestTrioPathCost(uno, dos, tres);
        } else {
            // if not throw NoSuchElementException
            throw new NoSuchElementException("We don't have airport information between three airports");
        }
    }

    /**
     * This method will return the list object that has Aiport Type elements. With
     * this method we can get all the airport object that is in the graph
     * Technically, we can get all the nodes in the graph with list
     * 
     * @return List that has Airport type elements (field of this class)
     */
    @Override
    public List<AirportBD> getAirports() {
        // if the node list of the graph is null then throw NoSuchElementExceptoin
        if (this.airportsList == null) {
            throw new NoSuchElementException("Loading Airports List has problem");
        } else {
            // if not then return the list
            return this.airportsList;
        }
    }

    /**
     * This method will return the list object that has Path Type elements. With
     * this method we can get all the Path object that is in the graph
     * Technically, we can get all the edges in the graph with list
     * 
     * @return List that has Path type elements (field of this class)
     */
    @Override
    public List<PathBD> getPaths() {
        // if the path list of the graph is null then throw NoSuchElementExceptoin
        if (this.paths == null) {
            throw new NoSuchElementException("Loading Path List has problem");
        } else {
            // if not then return the list
            return this.paths;
        }
    }

    /**
     * With this method we can get the full name of airport with airport code
     * This method use the hashtable that key is airport code and the value is
     * airport's full name
     */
    @Override
    public String getFullAirportName(String airportCode) {
        // check if the airports code is in the hastable
        if (airportDecode.containsKey(airportCode)) {
            // if so return the value of that key(airport code)
            return airportDecode.get(airportCode);
        } else {
            // if not throw NoSuchElementException
            throw new NoSuchElementException("We don't have your airportCode in our data");
        }
    }

}
