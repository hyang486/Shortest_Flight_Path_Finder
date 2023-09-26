
// --== CS400 File Header Information ==--
// Name: Hyeonmin Yang
// Email: <hyang486 @wisc.edu>
// Group and Team: <your group name: BJ,  Blue>
// Group TA: <Naman Gupta>>
// Lecturer: <CS400 Lecture002>
// Notes to Grader: <optional extra notes>

/**
 * this is palceholde of Airports Class of DW
 */
public class AirportBD implements AirportInterfaceBD {

    public String code;
    public String name;
    public float latitude;
    public float longitude;

    public AirportBD(String code, String name, float latitude, float logitude) {
        this.code = code;
        this.name = name;
        this.latitude = latitude;
        this.longitude = logitude;
    }

    public String getAirportCode() {
        return this.code;
    }

    public String getAirportName() {
        return this.name;
    }

    public float getLatitude() {
        return this.latitude;
    }

    public float getLongitude() {
        return this.longitude;
    }

    @Override
    public String toString() {
        return this.name + "[" + this.code + "](" + this.latitude + ", " + this.longitude + ")";
    }
}
