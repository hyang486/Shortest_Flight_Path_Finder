
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AirportDatabase implements AirportDatabaseInterface {

  private List<Airport> airportList = new ArrayList<Airport>();
  private List<Path> pathList = new ArrayList<Path>();

  /*
   * This method will read the formatted dot and parse it for nodes and edges as
   * well as
   * their attributes
   * 
   * @throws FileNotFoundException if the file doesn't exist.
   */
  public void dotReader() throws FileNotFoundException {
    File data = new File("data.dot");
    Scanner scnr = null;
    try {
      scnr = new Scanner(data);
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("Data not found");
    }

    scnr.hasNext();
    while (scnr.hasNext()) {
      // This removes the semicolon that represents endline on dot files
      String newVal = scnr.nextLine().replaceAll(";", "");
      // If this is the case then a node is being declared
      if (newVal.contains("node")) {

        // This will split the attribute from the node, and the max of 3 will stop
        // formatting issues
        String[] splitVal = newVal.replaceAll("\\[", "").replaceAll("\\]", "").split(" ", 3);
        String initials = splitVal[1];
        String[] attributes = splitVal[2].split(",");
        // System.out.println(attributes[0]);

        // After this it's just prepping the values so that that we can initialize them
        String name = null;
        Float lat = 0.0f;
        Float longi = 0.0f;

        // This will check all the attributes for if they match our attributes
        for (int i = 0; i < attributes.length; i++) {
          String[] attributeVal = attributes[i].split("=");

          if (attributeVal[0].replaceAll(" ", "").equals("name")) {
            // System.out.println(attributeVal[1]);
            name = attributeVal[1].replaceAll("\"", "");
          } else if (attributeVal[0].replaceAll(" ", "").equals("lat")) {
            // System.out.println(attributeVal[1]);
            lat = Float.parseFloat(attributeVal[1]);
          } else if (attributeVal[0].replaceAll(" ", "").equals("long")) {
            longi = Float.parseFloat(attributeVal[1]);
          }
        }
        // Finally we create our airport using the working values and add it to the list
        airportList.add(new Airport(initials, name, lat, longi));

        // String nodeName =

      }
      // In this case it means there's a path instead
      else if (newVal.contains("->")) {
        // By splitting using the -> we can find the start and end points
        String[] splitVal = newVal.split("->");
        String loc1 = splitVal[0].replaceAll(" ", "");
        // System.out.println(splitVal[1]);
        String loc2 = splitVal[1].split("\\[")[0].replaceAll(" ", "");
        // System.out.println(splitVal[3]);

        // This will get the weight value by removing the brackets and splitting by the
        // equal sign
        String weight = splitVal[1].split("\\[")[1].split("=")[1].replaceAll("\\]", "").replaceAll(" ", "");
        int dist = Integer.parseInt(weight.replaceAll(" ", ""));
        // Finally we create the path and add it to the list to be accessed later
        pathList.add(new Path(loc1, loc2, dist));
      }
    }
  }

  /**
   * This method just reads the file and creates the lists using dotReader
   * 
   * @throws FileNotFoundException if the file doesnt exist to be loaded.
   */
  public AirportDatabase() throws FileNotFoundException {
    dotReader();
  }

  /**
   * This file will simply return the constructed airport list
   * return airportList
   */
  @Override
  public List<Airport> getAirportList() {
    return airportList;
  }

  /**
   * This will return the constructed path list
   * return pathList
   */
  @Override
  public List<Path> getPathList() {
    return pathList;
  }

}
