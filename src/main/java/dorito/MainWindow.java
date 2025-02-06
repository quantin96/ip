package dorito;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Dorito dorito;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image doritoImage = new Image(this.getClass().getResourceAsStream("/images/DaDorito.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Dorito instance */
    public void setDorito(Dorito d) {
        dorito = d;

        dialogContainer.getChildren().addAll(DialogBox.getDoritoDialog(d.start(), doritoImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Dorito's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = dorito.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDoritoDialog(response, doritoImage)
        );
        if (response.equals("\nBye. Remember to stay hydrated!  >.<")) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
        }
        userInput.clear();
    }
}

