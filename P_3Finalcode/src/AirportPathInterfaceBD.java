
// --== CS400 File Header Information ==--
// Name: Hyeonmin Yang
// Email: <hyang486 @wisc.edu>
// Group and Team: <your group name: BJ,  Blue>
// Group TA: <Naman Gupta>>
// Lecturer: <CS400 Lecture002>
// Notes to Grader: <optional extra notes>
import java.util.List;

/**
 * this is interface of AirportPathInterfaceBD
 */
public interface AirportPathInterfaceBD {

    public boolean insertEdge(String predecessor, String successor, double weight);

    public boolean insertNode(String data);

    public List<String> getShortestPath(String start, String end);

    public double getShortestDistance(String start, String end);

    public List<String> getShortestTrioPath(String uno, String dos, String tres);

    public double getShortestTrioDistance(String uno, String dos, String tres);

    public List<AirportBD> getAirports();

    public List<PathBD> getPaths();

    // get airport code from frontend and find airport’s full name
    public String getFullAirportName(String airportCode);
}
