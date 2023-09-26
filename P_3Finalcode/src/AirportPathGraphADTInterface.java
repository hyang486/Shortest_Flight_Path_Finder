
// --== CS400 File Header Information ==--
// Name: Hyeonmin Yang
// Email: <hyang486 @wisc.edu>
// Group and Team: <your group name: BJ,  Blue>
// Group TA: <Naman Gupta>>
// Lecturer: <CS400 Lecture002>
// Notes to Grader: <optional extra notes>
import java.util.List;

/**
 * this is interface of AirportPathGraphADTBD
 */
public interface AirportPathGraphADTInterface<NodeType, EdgeType extends Number>
        extends GraphADT<NodeType, EdgeType> {

    public List<NodeType> shortestTrioPathData(NodeType airport1, NodeType airport2, NodeType airport3);

    public double shortestTrioPathCost(NodeType airport1, NodeType airport2, NodeType airport3);

}
