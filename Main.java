/**
 * Class: B.Sc. Cloud Computing
 * Instructor: Gary Cullen
 * Description: Algorithm assessment GUI main application class
 * Date: 01/03/2019
 * @author Dane Campbell [L00142041]
 * @version 1.0
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Main GUI class containing launch method */
public class Main extends Application {

    /** Application start method override -> create stage */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        root.getStylesheets().add(getClass().getResource("adsStyle.css").toExternalForm());
        primaryStage.setTitle("Algorithm Assessor");
        primaryStage.setScene(new Scene(root, 520, 460));
        primaryStage.show();
    }

    /** Application main method -> launch GUI */
    public static void main(String[] args) {
        launch(args);
    }
}
