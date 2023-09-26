
public class Airport implements AirportInterface {
  // Stores airport data and each will represents a node

  private String airportCode;
  private String airportName;
  private float latitude;
  private float longitude;

  /**
   * AirportBD constructor
   * 
   * @param code      is the airportCode
   * @param name      is the airportName
   * @param latitude  is the latititude
   * @param longitude is the longitude
   */
  public Airport(String code, String name, float latitude, float longitude) {
    this.airportCode = code;
    this.airportName = name;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  /**
   * This is an accessor for airportCode
   * returns airportCode
   */
  public String getAirportCode() {
    return airportCode;
  }

  /**
   * This is an accessor for airportName
   * returns airportName
   */
  public String getAirportName() {
    return airportName;
  }

  /**
   * This is an accessor for latitude
   * returns latitude
   */
  public float getLatitude() {
    return latitude;
  }

  /**
   * This is an accessor for longitude
   * returns longitude
   */
  public float getLongitude() {
    return longitude;
  }

  // @Override
  // public String toString() {
  // return airportName+ "["+airportCode+"]("+latitude+", "+longitude+")";
  // }

}
