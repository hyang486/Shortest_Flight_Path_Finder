// --== CS400 File Header Information ==--
// Name: Hyeonmin Yang
// Email: <hyang486 @wisc.edu>
// Group and Team: <your group name: BJ,  Blue>
// Group TA: <Naman Gupta>>
// Lecturer: <CS400 Lecture002>
// Notes to Grader: <optional extra notes>

/**
 * this is the placeholde of PathBD class
 */
public class PathBD implements PathInterfaceBD {
    public String start;
    public String end;
    public int distance;

    public PathBD(String start, String end, int distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }

    public int getDistance() {
        return this.distance;
    }
}
