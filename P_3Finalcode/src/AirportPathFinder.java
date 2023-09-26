
// --== CS400 Spring 2023 File Header Information ==--
// Name: Brian Han
// Email: shan289@wisc.edu
// Team: BJ
// TA: Naman Gupta
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AirportPathFinder extends Application implements AirportPathFinderInterface {
    private AirportPath airport;

    // AirportPathBD backend;
    public AirportPathFinder() throws FileNotFoundException {
        airport = new AirportPath();
    }

    private int score = 0;

    /**
     * This private method converts longitude to X coordinate using a function
     * the author generated using regraeesion
     * 
     * @param lon longitude
     * @return converted longitude
     */
    private static double convertLongitudeToX(double lon) {
        return -0.000646 * lon * lon - 12.858 * lon + 1697.9;

    }

    /**
     * This private method converts latitude to Y coordinate using a function
     * the author generated using regraeesion
     * 
     * @param lat latitude
     * @return converted latitude
     */
    private static double convertLatitudeToY(double lat) {
        return -0.892599 * lat * lat + 44.4225 * lat - 73.7799;
    }

    private StringBuilder inputField = new StringBuilder();
    private Label inputDisplay = new Label();

    private String confirm = "Selected: ";
    private String result = "Result: ";
    private String code1 = "";
    private String code2 = "";
    private String code3 = "";
    private double distance = 0;

    private Pane root = new Pane();
    private List<String> path = new ArrayList<>();
    private String pathString = "";

    /**
     * Strart method for executing program
     * 
     * @param stage Stage for the UI
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        // setting up title and calling drawMap()
        stage.setTitle("Airport Pathfinder");
        drawMap();
        // label for displaying selected airports
        Label confirmLabel = new Label(confirm);
        confirmLabel.setLayoutX(75);
        confirmLabel.setLayoutY(577);
        confirmLabel.setFont(new Font(15));
        root.getChildren().add(confirmLabel);
        confirmLabel.setId("confirmL");

        // retrieving list of airports and creating new buttons for each airport
        List<Airport> airportList = airport.getAirports();
        for (Airport aps : airportList) {
            Button newAP = new Button(aps.getAirportCode());
            newAP.setPrefWidth(50);
            newAP.setPrefHeight(15);
            // setting button (airport) location
            newAP.setLayoutX(convertLongitudeToX(aps.getLongitude()));
            newAP.setLayoutY(convertLatitudeToY(aps.getLatitude()));
            // new Id for each button
            newAP.setId(aps.getAirportCode());
            root.getChildren().add(newAP);
            newAP.setOnAction(event -> {
                try {
                    // this character is essential for try-catch condition
                    Character DONOTDELETE = confirm.charAt(10);
                    confirm += (" -> " + aps.getAirportCode());
                } catch (Exception e) {
                    confirm += aps.getAirportCode();
                }
                confirmLabel.setText(confirm);
                if (checkLength(confirm)) {
                    confirmLabel.setText("Limit is Three Airports. Reset and Try Again.");
                }
            });
        }
        // exit button setup
        Button exitB = new Button("Exit Program");
        exitB.setOnAction(event -> {
            Platform.exit();
        });
        exitB.setPrefHeight(30);
        exitB.setPrefWidth(100);
        exitB.setLayoutX(725);
        exitB.setLayoutY(577);
        exitB.setId("exitB");
        root.getChildren().add(exitB);

        // result label
        Label resultLabel = new Label(result);
        resultLabel.setLayoutX(75);
        resultLabel.setLayoutY(610);
        resultLabel.setFont(new Font(15));
        root.getChildren().add(resultLabel);
        resultLabel.setId("resultL");

        // reset button
        Button reset = new Button("Reset Selection");
        reset.setPrefWidth(110);
        reset.setPrefHeight(30);
        reset.setLayoutX(613);
        reset.setLayoutY(577);
        reset.setId("resetB");
        root.getChildren().add(reset);
        // when reset button is clicked
        reset.setOnAction(event -> {
            resetMap();
            confirmLabel.setText(confirm);
            // if (!checkLength(confirm)) {
            // }
            resultLabel.setText(result);
        });
        // print result
        Button result = new Button("Give Result");
        result.setPrefWidth(110);
        result.setPrefHeight(30);
        result.setLayoutX(500);
        result.setLayoutY(577);
        result.setId("resultB");

        root.getChildren().add(result);

        // 1. when the result button is clicked, check the number of airports in the
        // list and
        // limit the number
        // 2. Check the number of airports and if there's two, call createRoute()
        // if three, call createRouteThreeAirports()
        result.setOnAction(event -> {
            // 2.
            if (resultDecoder(confirm).size() == 2) {
                code1 = resultDecoder(confirm).get(0);
                code2 = resultDecoder(confirm).get(1);
                distance = airport.getShortestDistance(code1, code2);
                createRoute();
            } else if (resultDecoder(confirm).size() == 3) {
                code1 = resultDecoder(confirm).get(0);
                code2 = resultDecoder(confirm).get(1);
                code3 = resultDecoder(confirm).get(2);
                distance = airport.getShortestTrioDistance(code1, code2, code3);
                createRouteThreeAirports();

            }
            // 1.

            if (!pathString.equals("")) {
                resultLabel.setText("Result: " + pathString
                        + "\n Traveling " + distance + " Kilometers. ");
            } else {
                confirm = "Result: ";
            }
        });
        // create scene
        Scene scene = new Scene(root, 900, 700);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Loads airport data (get list of airports to display through backend)
     * and draws initial map setup
     */
    public void drawMap() {
        // label for title at top
        Label title = new Label("Airport Pathfinder");
        title.setFont(new Font(50));
        title.setLayoutX(78);
        title.setLayoutY(10);
        root.getChildren().add(title);
        // label for instruction at top
        Label instruction = new Label("Select Up to Three Airports That You're Planning to Visit");
        instruction.setFont(new Font(15));
        instruction.setLayoutX(469);
        instruction.setLayoutY(43);
        root.getChildren().add(instruction);

        // creating the image object (MUST BE ON TOP OF EVERY BUTTONS OR LABEL DECLARED)
        InputStream stream = null;
        try {
            stream = new FileInputStream("usa-map.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image(stream);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setX(75);
        imageView.setY(75);
        imageView.setFitWidth(750);
        imageView.setPreserveRatio(true);
        root.getChildren().add(imageView);
        // END of image object declaration

    }

    /**
     * This method creates a route that is for displaying on the screen
     * This method called when the user selects the button for selecting
     * two airports
     *
     */
    public void createRoute() {
        path = airport.getShortestPath(code1, code2);
        for (String s : path) {
            if (pathString.equals("")) {
                pathString += s;
            } else {
                pathString += " -> " + s;
            }
        }

    }

    /**
     * Using backend, it creates a route using three airports
     */
    public void createRouteThreeAirports() {
        path = airport.getShortestTrioPath(code1, code2, code3);

        for (String s : path) {
            if (pathString.equals("")) {
                pathString += s;
            } else {
                pathString += " -> " + s;
            }
        }
    }

    /**
     * This method turns result into Lists of Strings that doesn't contain the
     * arrow symbol
     * 
     * @param result the result is the user-selected airports
     * @return returns the list of string
     */
    private List<String> resultDecoder(String result) {
        result = result.substring(10);
        List<String> output = new ArrayList<>();
        // adding airport code to a list by ignoring "->"
        String[] parts = result.split(" -> ");
        for (String part : parts) {
            output.add(part);
        }
        return output;
    }

    /**
     * This method decode the result from resultDecoder(result) to String
     * 
     * @param ls ls is the list of string from resultDecoder(result)
     * @return String made from ls
     */
    private String decodeResultToString(List<String> ls) {
        String returnS = "";
        // traversing list and creating string
        for (String s : ls) {
            if (returnS.isEmpty()) {
                returnS += airport.getFullAirportName(s);
            } else {
                if (returnS.length() > 45) {
                    returnS += "\n -> " + airport.getFullAirportName(s);
                } else {
                    returnS += " -> " + airport.getFullAirportName(s);
                }
            }
        }
        return returnS;
    }

    /**
     * This method is used to check length of the resultDecoded(check)
     * 
     * @param check the result trying to check
     * @return boolean value
     */
    private boolean checkLength(String check) {
        if (resultDecoder(check).size() >= 4) {
            return true;
        }
        return false;

    }

    /**
     * Resets the map by changing the confirm string into original
     */
    public void resetMap() {
        confirm = "Selected: ";
        result = "Result: ";
        code1 = "";
        code2 = "";
        code3 = "";
        path = new ArrayList<>();
        pathString = "";

    }
    // UNUSED METHODS
    // public String getAirport() {
    // return null;
    //
    // }

    // /**
    // * Gets the result path list and display the path on map
    // * @param path
    // */
    // public void drawPath(List<String> path) {
    //
    // }

    public static void main(String[] args) {
        Application.launch();
    }

}
