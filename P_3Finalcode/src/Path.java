
public class Path implements PathInterface {

  private String loc1;
  private String loc2;
  private int dist;

  /**
   * Constructor will initialize the three necessary values of a path
   * 
   * @param loc1 is the start
   * @param loc2 is the end
   * @param dist is the distance in km
   */
  public Path(String loc1, String loc2, int dist) {
    this.loc1 = loc1;
    this.loc2 = loc2;
    this.dist = dist;
  }

  /**
   * This is an accessor for loc1
   * return loc1
   */
  @Override
  public String getStart() {
    // TODO Auto-generated method stub
    return loc1;
  }

  /**
   * 
   * This is an accessor for loc2
   * return loc2
   */
  @Override
  public String getEnd() {
    // TODO Auto-generated method stub
    return loc2;
  }

  /**
   * This is an accessor for distance
   * return dist
   */
  @Override
  public int getDistance() {
    // TODO Auto-generated method stub
    return dist;
  }

  // @Override
  // public String toString() {
  // return loc1 + " goes to "+loc2+" with a distance of "+dist;
  // }

}
