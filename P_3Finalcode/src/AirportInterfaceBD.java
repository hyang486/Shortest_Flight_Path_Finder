// --== CS400 File Header Information ==--
// Name: Hyeonmin Yang
// Email: <hyang486 @wisc.edu>
// Group and Team: <your group name: BJ,  Blue>
// Group TA: <Naman Gupta>>
// Lecturer: <CS400 Lecture002>
// Notes to Grader: <optional extra notes>

/**
 * Interface of AirportBD
 */
public interface AirportInterfaceBD {
    // public Airport(String code, String name, float latitude, float longitude)
    public String getAirportCode();

    public String getAirportName();

    public float getLatitude();

    public float getLongitude();
}
