
import java.util.List;

public interface AirportDatabaseInterface {
  private void dotReader() {
  } // reads all nodes, stores it

  public List<Airport> getAirportList();

  public List<Path> getPathList();
}
