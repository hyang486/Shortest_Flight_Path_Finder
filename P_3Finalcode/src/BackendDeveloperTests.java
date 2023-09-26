
// --== CS400 File Header Information ==--
// Name: Hyeonmin Yang
// Email: <hyang486 @wisc.edu>
// Group and Team: <your group name: BJ, Blue>
// Group TA: <Naman Gupta>>
// Lecturer: <CS400 Lecture002>
// Notes to Grader: <optional extra notes>

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.accessibility.AccessibleAction;

// import statement for testing javaFX
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import edu.wisc.cs.cs400.JavaFXTester;

/**
 * This class is working for testing the method's function is working right
 * and return value in the AirportPath class. Basically, Backend Devleoper's
 * method should bring Data from the DataWrangler and make a graph with those
 * data. With using Junit this methods will be tested with comparing
 * return value with expected value with assertEquals.
 * Also This test will test Exception is thorwn in the specific case or not.
 * So, starting with testing inserting data and creating graph
 * we will test methods that frontEnd need from us. This graph has this form
 *
 * Nodes
 * A, B, C, D, E, F
 * Edges : start -> end (cost)
 * A -> C (2)
 * A -> B (6)
 * A -> D (5)
 * C -> F (1)
 * C -> B (3)
 * B -> C (2)
 * B -> E (1)
 * F -> A (1)
 * F -> D (1)
 * D -> E (3)
 * E -> A (4)
 */

public class BackendDeveloperTests extends JavaFXTester {

    public BackendDeveloperTests() {
        super(AirportPathFinder.class);
    }

/**
* testLoadAndInserting is testing if it working well with inserting nodes
* and edges that are from the Data Wrangler. It will take a two list
* (1. list of all airports, 2. list of all the path) After then with
* containsNode and containsEdge method we will check if each nodes and
* edges is in the graph or not
*/
@Test
public void testLoadAndInserting() {
// make AirportPathBD object because inserting functino is in this
constructor
// without calling insert methods, it automatically insert nodes and edges
AirportPathBD test1 = new AirportPathBD();
// create list object that has AirportsBD type elements
List<AirportBD> airportsList = new LinkedList<>();
// create list object that has PathBD type elements
List<PathBD> pathList = new LinkedList<>();
// assign airportsList with the list of all airports.
airportsList = test1.getAirports();
// assign pathList with the list of all paths.
pathList = test1.getPaths();

// with for loop check if each node(airport) is stored in the graph
succesfully
// or not
for (AirportBD airport : airportsList) {
assertEquals(true, test1.graph.containsNode(airport.getAirportCode()));
}
// with for loop check if each path is stored in the graph succesfully
// or not
for (PathBD paths : pathList) {
assertEquals(true, test1.graph.containsEdge(paths.getStart(),
paths.getEnd()));
}
}

    /**
     * testShortestPathTwoNodes test with the graph that created with node:airport
     * edge: path can return the shortest path between two airports or not.
     * And also check if it's distance is shortest distance or not
     * So, with this test we can test if getShortestPath() and getShortestDistance
     * working successfully or not.
     */
    @Test
    public void testShortestPathTwoNodes() {
        // create new AiportPathBD object for testing
        AirportPathBD test2 = new AirportPathBD();

        // because the return object of shortestPath is List<String> type
        // create new List<String> object
        List<String> shortestPathtest = new LinkedList<>();
        // assign shortestPathtest with the getShortestPath's return type with start
        and
        // end node
        shortestPathtest = test2.getShortestPath("E", "F");

        // check if the method return it's shorteestPath succesfully or not
        assertEquals("[[E, A, C, F]]", shortestPathtest.toString());
        // Also check if the getShortestDistance method return shortest distance
        // between two airports succesfully or not
        assertEquals(7.0, test2.getShortestDistance("E", "F"));

        // test the case when the start and end node is same.
        List<String> shortestPathOneNode = new LinkedList<>();
        // set getShortestPath with same start and end node
        shortestPathOneNode = test2.getShortestPath("E", "E");
        // Because there are no node to find shortest path it should return same node
        // with start or end
        assertEquals("[[E]]", shortestPathOneNode.toString());
        // and the distance should be 0
        assertEquals(0.0, test2.getShortestDistance("E", "E"));

    }

/**
* With testShortestPathThreeNodes Test If the getShortestTrioPath and
* getShortestTrioDistance bring right value succesfully, Basically it will
* successfully return the combined versioon of shortestPath from start to
* middle and middle to end.
* So, this shortestTrioPath is placeholder method, so it will print
* middle node two time and if the start, middle and end node is same
* it will print two same node.
*/
@Test
public void testShortestPathThreeNodes() {
// create new AiportPathBD object for testing
AirportPathBD test3 = new AirportPathBD();

// because the return object of getShortestTrioPath is List<String> type
// create new List<String> object
List<String> shortestTrioPathtest = new LinkedList<>();
// Assign shortestTrioPathTest to return value of getShortestTrioPath with
three
// airports
shortestTrioPathtest = test3.getShortestTrioPath("E", "C", "D");

// check if the method return three airports's shorteestPath succesfully or
not
// Because shortestTrioPath is placeholder, it will print middle node twice
time
assertEquals("[[E, A, C, C, F, D]]", shortestTrioPathtest.toString());
// Also check if the getShortestTrioDistance method return shortest distance
// between
// three airports succesfully or not
assertEquals(8.0, test3.getShortestTrioDistance("E", "C", "D"));

// test the case when the start, middle and end node is same.
List<String> shortestPathOneNode = new LinkedList<>();
// set getShortestPath with same start, middle and end node
shortestPathOneNode = test3.getShortestTrioPath("E", "E", "E");
// Because there are no node to find shortest path it should return same node
// with start, middle and end node
// However, getShortestTrioDistance is placeholder method, it will print two
// same node
assertEquals("[[E, E]]", shortestPathOneNode.toString());
// and the distance should be 0
assertEquals(0.0, test3.getShortestTrioDistance("E", "E", "E"));
}

    /**
     * testGetFullName is testing if teh getFullAirportName return rigth fullname
     * of
     * airports
     * with airport's code. The inserting key and value method is called in
     * constructor of
     * AirportPathPD class, So with creating object it will automatically
     * inserted.
     * This test
     * will test all the airports nodes in the graph manually.
     */
    @Test
    public void testGetFullName() {
        // create new AiportPathBD object for testing
        AirportPathBD test4 = new AirportPathBD();
        // with creating constructor pairs of airport code and airport's full name is
        // inserted as key and value to the hashTable.

        // airport code : key , full name : value
        // airport code : A , full name : AAA
        String airportA = "AAA";
        assertEquals(airportA, test4.getFullAirportName("A"));

        // airport code : B , full name : BBB
        String airportB = "BBB";
        assertEquals(airportB, test4.getFullAirportName("B"));

        // airport code : C , full name : CCC
        String airportC = "CCC";
        assertEquals(airportC, test4.getFullAirportName("C"));

        // airport code : D , full name : DDD
        String airportD = "DDD";
        assertEquals(airportD, test4.getFullAirportName("D"));

        // airport code : E , full name : EEE
        String airportE = "EEE";
        assertEquals(airportE, test4.getFullAirportName("E"));

        // airport code : F , full name : FFF
        String airportF = "FFF";
        assertEquals(airportF, test4.getFullAirportName("F"));

    }

/**
* testReturnAirportsAndPathList will test getAirports() is containing all
* airport nodes.
* And getPaths() have all path in the graph. Also, the each node of
getAirports
* should have contain all the data of Airport, so with toString()
* method we will test all the data of nodes
* the data shoule be Airport's fullname[airport code](latitude, altitude)
*/
@Test
public void testReturnAirportsAndPathLists() {
// create new AiportPathBD object for testing
AirportPathBD test5 = new AirportPathBD();

// make String Type List to contain all the node's data
List<String> airportsList = new LinkedList<>();
// make PathBD type List to test the size of the getPAth() method.
List<PathBD> pathsList = new LinkedList<>();

// with for loop add all the node's data to the airportsList
for (int i = 0; i < test5.getAirports().size(); i++) {
airportsList.add(i, test5.getAirports().get(i).toString());
}

// compare all the data that should be stored with getAirport() method. for
// testing use toStirng method
assertEquals(
"[AAA[A](1.0, 1.0), BBB[B](2.0, 2.0), CCC[C](3.0, 3.0), DDD[D](4.0, 4.0),
EEE[E](5.0, 5.0), FFF[F](6.0, 6.0)]",
test5.getAirports().toString());

// assign oathList to return object of getPath() method.
pathsList = test5.getPaths();
// the graph shoule have 11 paths
int numberOfAddedPath = 11;
// test pathList have 11 path or not
assertEquals(numberOfAddedPath, pathsList.size());

}

/**
* testExceptionOccure is testing if the program occure the exception
* The NosuchElement should be occure when the nodes or successor
* or predecessor of the edge are null(before being inserted into graph)
* or not in the graph (after being inserted into graph)
*/
@Test
public void testExceptionOccure() {
// create new AiportPathBD object for testing
AirportPathBD test6 = new AirportPathBD();

// check when user try to insert null node
assertThrows(NoSuchElementException.class, () -> {
test6.insertNode(null);
});

// check when user try to insert null edge
assertThrows(NoSuchElementException.class, () -> {
test6.insertEdge(null, null, 1.0);
});

// check when user want to see shortestPath between two nodes but those nodes
// are not in the graph
assertThrows(NoSuchElementException.class, () -> {
test6.getShortestPath("P", "M");
test6.getShortestDistance("P", "M");
});

// check when user want to see shortestPath's cost between three nodes but
those
// nodes are not in the graph
assertThrows(NoSuchElementException.class, () -> {
test6.getShortestTrioPath("P", "M", "Z");
test6.getShortestTrioDistance("P", "M", "Z");
});

// check when FD try to find fullname of Airport with code but that code is
not
// in the
// HashTable
assertThrows(NoSuchElementException.class, () -> {
test6.getFullAirportName("P");
});

}

    /**
     * this test is for testing FrontendDeveloper's work. For this test
     * We will test if the buttons that should be exist is existing or not
     * And Also with using reset button check if the reset button can remove
     * all the button data that are pressed
     */
    @Test
    public void FrontendDeveloperTests1() {
        Button resetButton = lookup("#resetB").query();
        Label confirmL = lookup("#confirmL").query();

        // Test Den Button is exist with selected message
        Button DEN = lookup("#DEN").query();
        interact(() -> DEN.fireEvent(new ActionEvent()));
        // with the messaage we can know we have DEN button and it works
        assertEquals("Selected: DEN", confirmL.getText());
        // with this code we can also reset clicked history and check reset button is
        // working or not
        interact(() -> resetButton.fireEvent(new ActionEvent()));

        // test SLC button is exist with selected message
        Button SLC = lookup("#SLC").query();
        interact(() -> SLC.fireEvent(new ActionEvent()));
        // with the messaage we can know we have SLC button and it works
        assertEquals("Selected: SLC", confirmL.getText());
        // with this code we can also reset clicked history and check reset button is
        // working or not
        interact(() -> resetButton.fireEvent(new ActionEvent()));

        // test PHX button is exist with selected message
        Button SFO = lookup("#SFO").query();
        interact(() -> SFO.fireEvent(new ActionEvent()));
        // with the messaage we can know we have SFO button and it works
        assertEquals("Selected: SFO", confirmL.getText());
        // with this code we can also reset clicked history and check reset button is
        // working or not
        interact(() -> resetButton.fireEvent(new ActionEvent()));

        // test DFW button is exist with selected message
        Button DFW = lookup("#DFW").query();
        interact(() -> DFW.fireEvent(new ActionEvent()));
        // with the messaage we can know we have DFW button and it works
        assertEquals("Selected: DFW", confirmL.getText());
        // with this code we can also reset clicked history and check reset button is
        // working or not
        interact(() -> resetButton.fireEvent(new ActionEvent()));

        // test IAH button is exist with selected message
        Button IAH = lookup("#IAH").query();
        interact(() -> IAH.fireEvent(new ActionEvent()));
        // with the messaage we can know we have IAH button and it works
        assertEquals("Selected: IAH", confirmL.getText());
        // with this code we can also reset clicked history and check reset button is
        // working or not
        interact(() -> resetButton.fireEvent(new ActionEvent()));

        // test MCO button is exist with selected message
        Button MCO = lookup("#MCO").query();
        interact(() -> MCO.fireEvent(new ActionEvent()));
        // with the messaage we can know we have MCO button and it works
        assertEquals("Selected: MCO", confirmL.getText());
        // with this code we can also reset clicked history and check reset button is
        // working or not
        interact(() -> resetButton.fireEvent(new ActionEvent()));

        // test ORD button is exist with selected message
        Button ORD = lookup("#ORD").query();
        interact(() -> ORD.fireEvent(new ActionEvent()));
        // with the messaage we can know we have ORD button and it works
        assertEquals("Selected: ORD", confirmL.getText());
        // with this code we can also reset clicked history and check reset button is
        // working or not
        interact(() -> resetButton.fireEvent(new ActionEvent()));

        // test DTW button is exist with selected message
        Button DTW = lookup("#DTW").query();
        interact(() -> DTW.fireEvent(new ActionEvent()));
        // with the messaage we can know we have ORD button and it works
        assertEquals("Selected: DTW", confirmL.getText());
        // with this code we can also reset clicked history and check reset button is
        // working or not
        interact(() -> resetButton.fireEvent(new ActionEvent()));

        // test IAD button is exist with selected message
        Button IAD = lookup("#IAD").query();
        interact(() -> IAD.fireEvent(new ActionEvent()));
        // with the messaage we can know we have IAD button and it works
        assertEquals("Selected: IAD", confirmL.getText());
        // with this code we can also reset clicked history and check reset button is
        // working or not
        interact(() -> resetButton.fireEvent(new ActionEvent()));

        // test JFK button is exist with selected message
        Button JFK = lookup("#JFK").query();
        interact(() -> JFK.fireEvent(new ActionEvent()));
        // with the messaage we can know we have IAD button and it works
        assertEquals("Selected: JFK", confirmL.getText());
        // with this code we can also reset clicked history and check reset button is
        // working or not
        interact(() -> resetButton.fireEvent(new ActionEvent()));

    }

    /**
     * This is the second test for FrontendDevleoper, this will test the case when
     * user click more then Three button for the airport Path. If the user
     * clicked more then 3 button it will show message to reset
     */
    @Test
    public void FrontendDeveloperTests2() {
        // Create button for clicking more than three tines
        // these are button for will be clicked (three times)
        Button buttonSLC = lookup("#SLC").query();
        Button buttonMCO = lookup("#MCO").query();
        Button buttonJFK = lookup("#JFK").query();
        Button buttonIAD = lookup("#IAD").query();
        // this the messae that after 4button is clicked (not a result)
        Label confirmL = lookup("#confirmL").query();
        // with interact() and lamda expression, make three time
        // click with DTW button.
        interact(() -> buttonSLC.fireEvent(new ActionEvent()));
        interact(() -> buttonMCO.fireEvent(new ActionEvent()));
        interact(() -> buttonJFK.fireEvent(new ActionEvent()));
        interact(() -> buttonIAD.fireEvent(new ActionEvent()));
        // compare the result
        assertEquals("Limit is Three Airports. Reset and Try Again.",
                confirmL.getText());
    }

    /**
     * This is integration test,
     * Check if this program return right value when the user clicked two airport
     * button
     * to see thier shortest path and distacne
     */
    @Test
    public void IntegrationTests1() {
        Button buttonSLC = lookup("#SLC").query();
        Button buttonMCO = lookup("#MCO").query();
        Label confirmL = lookup("#confirmL").query();
        Button resultB = lookup("#resultB").query();
        Label resultL = lookup("#resultL").query();
        // click SLC and MCO button for the test
        interact(() -> buttonSLC.fireEvent(new ActionEvent()));
        interact(() -> buttonMCO.fireEvent(new ActionEvent()));

        // click result button to see the result
        interact(() -> resultB.fireEvent(new ActionEvent()));
        assertEquals("Selected: SLC -> MCO", confirmL.getText());
        assertEquals("Result: [SLC, DFW, MCO]\n" + " Traveling 3176.0 Kilometers. ",
                resultL.getText());
    }

/**
* This is integration test,
* Check if this program return right value when the user clicked three
airport
* button
* to see thier shortest path and distacne
*/
@Test
public void IntegrationTests2() {
// button for SFO airports
Button buttonSFO = lookup("#SFO").query();
// button for DEN airports
Button buttonDEN = lookup("#DEN").query();
// button for IAH airports
Button buttonIAH = lookup("#IAH").query();
// Label that after click three buttons
Label confirmL = lookup("#confirmL").query();
// button for get result
Button resultB = lookup("#resultB").query();

Label resultL = lookup("#resultL").query();
// with interacte method make robot to click button
interact(() -> buttonSFO.fireEvent(new ActionEvent()));
interact(() -> buttonDEN.fireEvent(new ActionEvent()));
interact(() -> buttonIAH.fireEvent(new ActionEvent()));

// click result button to see the result
interact(() -> resultB.fireEvent(new ActionEvent()));
// compare the output
assertEquals("Selected: SFO -> DEN -> IAH", confirmL.getText());
assertEquals("Result: [SFO, SLC, DEN, DFW, IAH]\n" + " Traveling 2986.0
Kilometers. ",
resultL.getText());

}

/**
* This is integration test3,
* This test is for testing if click reset button between click three button
and
* click
* 3button agian wil show rigth information or not
*/
@Test
public void IntegrationTests3() {
// button for SFO airports
Button buttonSFO = lookup("#SFO").query();
// button for DEN airports
Button buttonDEN = lookup("#DEN").query();
// button for IAH airports
Button buttonIAH = lookup("#IAH").query();
// button for reset
Button buttonReset = lookup("#resetB").query();
// button for after all process is finished
Label confirmL = lookup("#confirmL").query();
// button for see the result
Button resultB = lookup("#resultB").query();
// Label for see the result
Label resultL = lookup("#resultL").query();
// first click SFO and DEN Airport button
interact(() -> buttonSFO.fireEvent(new ActionEvent()));
interact(() -> buttonDEN.fireEvent(new ActionEvent()));

// then click reset button for reset every history
interact(() -> buttonReset.fireEvent(new ActionEvent()));

// then click again SFO, DEN,IAH button to see the information
interact(() -> buttonSFO.fireEvent(new ActionEvent()));
interact(() -> buttonDEN.fireEvent(new ActionEvent()));
interact(() -> buttonIAH.fireEvent(new ActionEvent()));

// click result button to see the result
interact(() -> resultB.fireEvent(new ActionEvent()));
assertEquals("Selected: SFO -> DEN -> IAH", confirmL.getText());
assertEquals("Result: [SFO, SLC, DEN, DFW, IAH]\n" + " Traveling 2986.0
Kilometers. ",
resultL.getText());

}

/**
* This is integration test,
* Check if this program return right value when the user clicked three
airport
* button but start and end node is same. it should show error message with
* 0.0 distance.
*/
@Test
public void IntegrationTests4() {
// button for SFO airports
Button buttonSFO = lookup("#SFO").query();
// button for DEN airports
Button buttonDEN = lookup("#DEN").query();
// button for SFO airports for make start and end is same
Button buttonSFO2 = lookup("#SFO").query();
// Label that after click three buttons
Label confirmL = lookup("#confirmL").query();
// button for get result
Button resultB = lookup("#resultB").query();

Label resultL = lookup("#resultL").query();
// with interacte method make robot to click button
interact(() -> buttonSFO.fireEvent(new ActionEvent()));
interact(() -> buttonDEN.fireEvent(new ActionEvent()));
// click SFO button one more time.
interact(() -> buttonSFO2.fireEvent(new ActionEvent()));

// click result button to see the result
interact(() -> resultB.fireEvent(new ActionEvent()));
// compare the output
assertEquals("Selected: SFO -> DEN -> SFO", confirmL.getText());
// it should show error message with 0.0 distance.
assertEquals(
"Result: Error! : Start and end airport is same! " + "Please click reset
button\n"
+ " Traveling 0.0 Kilometers. ",
resultL.getText());

}

/**
* This is integration test,
* Check if this program return right value when the user clicked two airport
* button but start and end node is same. it should show error message with
* 0.0 distance.
*/
@Test
public void IntegrationTests5() {
// button for SFO airports
Button buttonSFO = lookup("#SFO").query();
// button for SFO airports for make start and end is same
Button buttonSFO2 = lookup("#SFO").query();
// Label that after click two buttons
Label confirmL = lookup("#confirmL").query();
// button for get result
Button resultB = lookup("#resultB").query();

Label resultL = lookup("#resultL").query();
// with interacte method make robot to click button
interact(() -> buttonSFO.fireEvent(new ActionEvent()));
// click SFO button one more time.
interact(() -> buttonSFO2.fireEvent(new ActionEvent()));

// click result button to see the result
interact(() -> resultB.fireEvent(new ActionEvent()));
// compare the output
assertEquals("Selected: SFO -> SFO", confirmL.getText());
// it should show error message with 0.0 distance.
assertEquals(
"Result: Error! : Start and end airport is same! " + "Please click reset
button\n"
+ " Traveling 0.0 Kilometers. ",
resultL.getText());

}

}
