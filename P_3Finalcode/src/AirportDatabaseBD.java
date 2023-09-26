// --== CS400 File Header Information ==--
// Name: Hyeonmin Yang
// Email: <hyang486 @wisc.edu>
// Group and Team: <your group name: BJ,  Blue>
// Group TA: <Naman Gupta>>
// Lecturer: <CS400 Lecture002>
// Notes to Grader: <optional extra notes>

import java.util.List;
import java.util.LinkedList;

/**
 * This is placeholder of AirportDatabase class made by BackendDeveloper
 */
public class AirportDatabaseBD implements AirportDatabaseInterfaceBD<AirportBD, PathBD> {

    @Override
    public void dotReader() {
    }

    // To make placeholder make a airport object and store it to list
    @Override
    public List getAirportList() {
        // TODO Auto-generated method stub
        List<AirportBD> airportsList = new LinkedList<>();

        AirportBD airport1 = new AirportBD("A", "AAA", 1, 1);
        airportsList.add(airport1);

        AirportBD airport2 = new AirportBD("B", "BBB", 2, 2);
        airportsList.add(airport2);

        AirportBD airport3 = new AirportBD("C", "CCC", 3, 3);
        airportsList.add(airport3);

        AirportBD airport4 = new AirportBD("D", "DDD", 4, 4);
        airportsList.add(airport4);

        AirportBD airport5 = new AirportBD("E", "EEE", 5, 5);
        airportsList.add(airport5);

        AirportBD airport6 = new AirportBD("F", "FFF", 6, 6);
        airportsList.add(airport6);

        return airportsList;
    }

    /**
     * To make placeholder make a Path type list and store all the path
     */
    @Override
    public List getPathList() {
        // TODO Auto-generated method stub
        List<PathBD> pathsList = new LinkedList<>();
        PathBD path1 = new PathBD("A", "C", 2);
        pathsList.add(path1);

        PathBD path2 = new PathBD("A", "B", 6);
        pathsList.add(path2);

        PathBD path3 = new PathBD("A", "D", 5);
        pathsList.add(path3);

        PathBD path4 = new PathBD("C", "F", 1);
        pathsList.add(path4);

        PathBD path5 = new PathBD("C", "B", 3);
        pathsList.add(path5);

        PathBD path6 = new PathBD("B", "C", 2);
        pathsList.add(path6);

        PathBD path7 = new PathBD("B", "E", 1);
        pathsList.add(path7);

        PathBD path8 = new PathBD("F", "A", 1);
        pathsList.add(path8);

        PathBD path9 = new PathBD("F", "D", 1);
        pathsList.add(path9);

        PathBD path10 = new PathBD("D", "E", 3);
        pathsList.add(path10);

        PathBD path11 = new PathBD("E", "A", 4);
        pathsList.add(path11);

        return pathsList;

    }

}
