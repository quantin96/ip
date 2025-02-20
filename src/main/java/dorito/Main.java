package dorito;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Dorito using FXML.
 */
public class Main extends Application {

    private Dorito dorito = new Dorito("./data/dorito.txt");

    public Main() throws IOException {}

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDorito(dorito);  // inject the Dorito instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

