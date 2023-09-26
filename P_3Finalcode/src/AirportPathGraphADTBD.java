
// --== CS400 File Header Information ==--
// Name: Hyeonmin Yang
// Email: <hyang486 @wisc.edu>
// Group and Team: <your group name: BJ,  Blue>
// Group TA: <Naman Gupta>>
// Lecturer: <CS400 Lecture002>
// Notes to Grader: <optional extra notes>
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/**
 * this is placeholder for AirportPathGraphADT
 */
public class AirportPathGraphADTBD<NodeType, EdgeType extends Number>
        implements AirportPathGraphADTInterfaceBD<NodeType, EdgeType> {

    // Each node contains unique data along with two lists of directed edges
    protected class Node {
        public NodeType data;
        public List<Edge> edgesLeaving = new LinkedList<>();
        public List<Edge> edgesEntering = new LinkedList<>();

        public Node(NodeType data) {
            this.data = data;
        }
    }

    // Nodes can be retrieved from this hashtable by their unique data
    protected Hashtable<NodeType, Node> nodes = new Hashtable();

    // Each edge contains data/weight, and two nodes that it connects
    protected class Edge {
        public EdgeType data; // the weight or cost of this edge
        public Node predecessor;
        public Node successor;

        public Edge(EdgeType data, Node pred, Node succ) {
            this.data = data;
            this.predecessor = pred;
            this.successor = succ;
        }
    }

    protected int edgeCount = 0;
    // Edges can be retrieved through the edge lists in either connected node

    /**
     * Insert a new node into the graph.
     * 
     * @param data is the data item stored in the new node
     * @return true if the data is unique and can be inserted into a new node,
     *         or false if this data is already in the graph
     * @throws NullPointerException if data is null
     */
    @Override
    public boolean insertNode(NodeType data) {
        if (data == null) {
            throw new NullPointerException("Cannot insert null data into graph");
        }
        if (nodes.containsKey(data)) {
            return false;
        }
        nodes.put((NodeType) data, new Node((NodeType) data));
        return true;
    }

    /**
     * Remove a node from the graph.
     * And also remove all edges adjacent to that node.
     * 
     * @param data is the data item stored in the node to be removed
     * @return true if a vertex with data is found and removed, or
     *         false if that data value is not found in the graph
     * @throws NullPointerException if data is null
     */
    @Override
    public boolean removeNode(NodeType data) {
        // remove this node from nodes collection
        if (!nodes.containsKey(data))
            return false; // throws NPE when data==null
        Node oldNode = nodes.remove(data);
        // remove all edges entering neighboring nodes from this one
        for (Edge edge : oldNode.edgesLeaving)
            edge.successor.edgesEntering.remove(edge);
        // remove all edges leaving neighboring nodes toward this one
        for (Edge edge : oldNode.edgesEntering)
            edge.predecessor.edgesLeaving.remove(edge);
        return true;
    }

    /**
     * Check whether the graph contains a node with the provided data.
     * 
     * @param data the node contents to check for
     * @return true if data item is stored in a node within the graph, or
     *         false otherwise
     */
    @Override
    public boolean containsNode(NodeType data) {
        return nodes.containsKey(data);
    }

    /**
     * Return the number of nodes in the graph
     * 
     * @return the number of nodes in the graph
     */
    @Override
    public int getNodeCount() {
        return nodes.size();
    }

    /**
     * Insert a new directed edge with positive edges weight into the graph.
     * Or if an edge between pred and succ already exists, update the data
     * stored in hat edge to be weight.
     * 
     * @param pred   is the data item contained in the new edge's predecesor node
     * @param succ   is the data item contained in the new edge's successor node
     * @param weight is the non-negative data item stored in the new edge
     * @return true if the edge could be inserted or updated, or
     *         false if the pred or succ data are not found in any graph nodes
     */
    @Override
    public boolean insertEdge(NodeType pred, NodeType succ, EdgeType weight) {
        // find nodes associated with node data, and return false when not found
        Node predNode = nodes.get(pred);
        Node succNode = nodes.get(succ);
        if (predNode == null || succNode == null)
            return false;
        try {
            // when an edge alread exists within the graph, update its weight
            Edge existingEdge = getEdgeHelper(pred, succ);
            existingEdge.data = weight;
        } catch (NoSuchElementException e) {
            // otherwise create a new edges
            Edge newEdge = new Edge(weight, predNode, succNode);
            this.edgeCount++;
            // and insert it into each of its adjacent nodes' respective lists
            predNode.edgesLeaving.add(newEdge);
            succNode.edgesEntering.add(newEdge);
        }
        return true;
    }

    /**
     * Remove an edge from the graph.
     * 
     * @param pred the data item contained in the source node for the edge
     * @param succ the data item contained in the target node for the edge
     * @return true if the edge could be removed, or
     *         false if such an edge is not found in the graph
     */
    @Override
    public boolean removeEdge(NodeType pred, NodeType succ) {
        try {
            // when an edge exists
            Edge oldEdge = getEdgeHelper(pred, succ);
            // remove it from the edge lists of each adjacent node
            oldEdge.predecessor.edgesLeaving.remove(oldEdge);
            oldEdge.successor.edgesEntering.remove(oldEdge);
            // and decrement the edge count before removing
            this.edgeCount--;
            return true;
        } catch (NoSuchElementException e) {
            // when no such edge exists, return false instead
            return false;
        }
    }

    /**
     * Check if edge is in the graph.
     * 
     * @param pred the data item contained in the source node for the edge
     * @param succ the data item contained in the target node for the edge
     * @return true if the edge is found in the graph, or false other
     */
    @Override
    public boolean containsEdge(NodeType pred, NodeType succ) {
        try {
            getEdgeHelper(pred, succ);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Return the data associated with a specific edge.
     * 
     * @param pred the data item contained in the source node for the edge
     * @param succ the data item contained in the target node for the edge
     * @return the non-negative data from the edge between those nodes
     * @throws NoSuchElementException if either node or the edge between them
     *                                are not found within this graph
     */
    @Override
    public EdgeType getEdge(NodeType pred, NodeType succ) {
        return getEdgeHelper(pred, succ).data;
    }

    protected Edge getEdgeHelper(NodeType pred, NodeType succ) {
        Node predNode = nodes.get(pred);
        // search for edge through the predecessor's list of leaving edges
        for (Edge edge : predNode.edgesLeaving)
            // compare succ to the data in each leaving edge's successor
            if (edge.successor.data.equals(succ))
                return edge;
        // when no such edge can be found, throw NSE
        throw new NoSuchElementException("No edge from " + pred.toString() + " to " +
                succ.toString());
    }

    /**
     * Return the number of edges in the graph.
     * 
     * @return the number of edges in the graph
     */
    @Override
    public int getEdgeCount() {
        return this.edgeCount;
    }

    /**
     * While searching for the shortest path between two nodes, a SearchNode
     * contains data about one specific path between the start node and another
     * node in the graph. The final node in this path is stored in it's node
     * field. The total cost of this path is stored in its cost field. And the
     * predecessor SearchNode within this path is referened by the predecessor
     * field (this field is null within the SearchNode containing the starting
     * node in it's node field).
     *
     * SearchNodes are Comparable and are sorted by cost so that the lowest cost
     * SearchNode has the highest priority within a java.util.PriorityQueue.
     */
    protected class SearchNode implements Comparable<SearchNode> {
        public Node node;
        public double cost;
        public SearchNode predecessor;

        public SearchNode(Node node, double cost, SearchNode predecessor) {
            this.node = node;
            this.cost = cost;
            this.predecessor = predecessor;
        }

        public int compareTo(SearchNode other) {
            if (cost > other.cost)
                return +1;
            if (cost < other.cost)
                return -1;
            return 0;
        }
    }

    /**
     * This helper method creates a network of SearchNodes while computing the
     * shortest path between the provided start and end locations. The
     * SearchNode that is returned by this method is represents the end of the
     * shortest path that is found: it's cost is the cost of that shortest path,
     * and the nodes linked together through predecessor references represent
     * all of the nodes along that shortest path (ordered from end to start).
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return SearchNode for the final end node within the shortest path
     * @throws NoSuchElementException when no path from start to end is found
     *                                or when either start or end data do not
     *                                correspond to a graph node
     */
    protected SearchNode computeShortestPath(NodeType start, NodeType end) {
        // TODO: implement in step 6
        // if the start node or end node is not in the graph then throw the exception
        if (!containsNode(start) || !containsNode(end)) {
            throw new NoSuchElementException("Start or end node are not in the graph");
        }

        // if the start and end node is same node then return start node
        else if (start.equals(end)) {
            return new SearchNode(new Node(start), 0, null);
        } else {
            // create a priority queue to store the nodes
            PriorityQueue<SearchNode> priorityQueue = new PriorityQueue<SearchNode>();
            // add starting node to PQ
            priorityQueue.add(new SearchNode(nodes.get(start), 0, null));
            // create a hashtable to store the visited nodes
            Hashtable<Node, Node> visitedNodes = new Hashtable<>();
            // create SearchNode to store the shorted Path
            SearchNode shortestPath = null;
            SearchNode returnPath = null;

            // run the loop until the PQ is empty
            while (!priorityQueue.isEmpty()) {
                // assign path of highest priority in the priority queue
                shortestPath = priorityQueue.poll();
                // make a Node variable and assign to the last node in the path
                Node finalNode = shortestPath.node;
                List<Edge> edgesOfFinalNode = finalNode.edgesLeaving;

                // if the last node in the path is equal with end(parameter) then return the
                // path
                if (shortestPath.node.data.equals(end)) {
                    return shortestPath;
                }

                // if the final node has no leaving edge change it to next priority node
                // Because if that node has lowest cost, this will make infinite loop
                if (edgesOfFinalNode.isEmpty()) {
                    resolveRevisitNode(priorityQueue, shortestPath, finalNode, visitedNodes);
                }

                // store the node to visitedNodes hashtable to check if it is visited
                visitedNodes.put(finalNode, finalNode);

                // store the all node that is sucessor of the leaving edge to priority queue
                // if the weight is negative value then throw illegal argument exception.
                for (int i = 0; i < edgesOfFinalNode.size(); i++) {
                    double weight = edgesOfFinalNode.get(i).data.doubleValue();
                    if (weight < 0.0) {
                        throw new IllegalArgumentException("there is negative cost");
                    } else {
                        priorityQueue.add(new SearchNode(edgesOfFinalNode.get(i).successor,
                                shortestPath.cost + weight, shortestPath));
                    }
                }

            }
            // if all code is complete but return statement is not working then it means
            // there are no path in the graph. So, throw the NoSuchElementException.
            throw new NoSuchElementException("We don't have path in our graph");

        }

    }

    /**
     * This is helper method for the computeShortestPath method. if the node is
     * already stored to visitedNodes hashtable and if there are no leaving edge and
     * if that node has lowedst cost, the program will make infinte loop, so with
     * this helper method, we need to change final node to second priority node.
     * 
     * @param priorityQueue priority queue that store the node of leaving edge from
     *                      final node
     * @param shortestPath  the object that contain the shortest path so far
     * @param finalNode     the last node that is store in the shortestPath
     * @param visitedNodes  the hashtable that contain the node that program visited
     */
    private void resolveRevisitNode(PriorityQueue<SearchNode> priorityQueue, SearchNode shortestPath, Node finalNode,
            Hashtable visitedNodes) {

        // run the while loop until the final node is contains in the visitedNodes
        if (visitedNodes.containsKey(finalNode)) {
            // if the priority queue is not empty,
            // then store the shortest path to next priority node
            if (priorityQueue.isEmpty()) {
                // if the priority Queue is empty,
                // then it means there are no way to find the shortest path
                throw new NoSuchElementException("We don't have path in our graph");
            } else {
                // if there are node in the priority queue update shortestPath with the another
                // priority Queue.
                // Because for avoid infinite loop(becasue of the node that has no leaving edge
                // but has lowest cost), we need to change the path to next priority
                shortestPath = priorityQueue.poll();

                // change the final node to last node of updated shortestPath
                finalNode = shortestPath.node;

            }
        }

    }

    /**
     * Returns the list of data values from nodes along the shortest path
     * from the node with the provided start value through the node with the
     * provided end value. This list of data values starts with the start
     * value, ends with the end value, and contains intermediary values in the
     * order they are encountered while traversing this shorteset path. This
     * method uses Dijkstra's shortest path algorithm to find this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return list of data item from node along this shortest path
     */

    public List<NodeType> shortestPathData(NodeType start, NodeType end) {
        // make SearchNode to get the data.
        SearchNode copyShortestPath = computeShortestPath(start, end);
        // create LinkedList to store the data from the SearchNode
        List<NodeType> Pathdata = new LinkedList<>();
        // run the loop until the copyShortestPath is null
        while (copyShortestPath != null) {
            // add the last node of the shortest Path to PathData
            Pathdata.add(0, copyShortestPath.node.data);
            // and change the final node to original one's predecessor
            copyShortestPath = copyShortestPath.predecessor;
        }
        return Pathdata;
    }

    /**
     * Returns the cost of the path (sum over edge weights) of the shortest
     * path freom the node containing the start data to the node containing the
     * end data. This method uses Dijkstra's shortest path algorithm to find
     * this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return the cost of the shortest path between these nodes
     */
    public double shortestPathCost(NodeType start, NodeType end) {
        // because the cost contains the sum of each edge's weight(shortest path)
        // return computShortestPath.cost
        return computeShortestPath(start, end).cost;
    }

    @Override
    public List shortestTrioPathData(NodeType airport1, NodeType airport2, NodeType airport3) {
        // TODO Auto-generated method stub
        List<NodeType> returnTrioPath1 = shortestPathData(airport1, airport2);
        List<NodeType> returnTrioPath2 = shortestPathData(airport2, airport3);

        returnTrioPath1.addAll(returnTrioPath2);

        return returnTrioPath1;
    }

    @Override
    public double shortestTrioPathCost(NodeType airport1, NodeType airport2, NodeType airport3) {
        // TODO Auto-generated method stub

        return shortestPathCost(airport1, airport2) + shortestPathCost(airport2, airport3);
    }

}
