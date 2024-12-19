package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Loads the main layout from the "mainView.fxml" file and setting up the main window
 *
 * @author Grzegorz Franciszek Frankowicz
 * @version 1.0
 */
public class VIAPets extends Application {

    /**
     * Loads the layout from mainView.fxml, handles the window
     * @param primaryStage takes the Stage object
     * */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainView.fxml"));
        Parent root = loader.load();

        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("VIA Pets");
        primaryStage.show();
    }
}
