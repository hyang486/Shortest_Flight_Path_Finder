
// --== CS400 File Header Information ==--
// Name: Hyeonmin Yang
// Email: <hyang486 @wisc.edu>
// Group and Team: <your group name: BJ,  Blue>
// Group TA: <Naman Gupta>>
// Lecturer: <CS400 Lecture002>
// Notes to Grader: <optional extra notes>
import java.util.List;

/**
 * Interface of AirportDatabaseBD
 */
public interface AirportDatabaseInterfaceBD<AirportBD, PathBD> {
    public void dotReader();

    public List<AirportBD> getAirportList();

    public List<PathBD> getPathList();
}
