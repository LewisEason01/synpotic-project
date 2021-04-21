import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertBoxes {

    public void confirmAlertBoxes(String alertInformation) {
        // Creates an information alert box
        Alert confirmAlert = new Alert(Alert.AlertType.INFORMATION, alertInformation);
        confirmAlert.showAndWait();
    }

    public void errorAlertBoxes(String alertInformation) {
        // Creates an error alert box
        Alert errorAlert = new Alert(Alert.AlertType.ERROR, alertInformation);
        errorAlert.showAndWait();
    }

    public boolean confirmInputsAlertBoxes(String alertInformation) {
        // Creates a confirmation alert box
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, alertInformation);
        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else
            confirmation.close();
        return false;
    }
}
