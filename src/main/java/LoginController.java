import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.ResultSet;

public class LoginController {

    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Button loginButton, clearLogin;

    JdbcDao jdbcDao = new JdbcDao();
    FileHandling fileHandling = new FileHandling();
    AlertBoxes alert = new AlertBoxes();

    @FXML
    public void confirmLogin(ActionEvent event) throws Exception {

        // Retrieve user input from all fields
        boolean validLogin = fileHandling.validateLogin(username.getText(), password.getText());
        String errorInformation = "Invalid Login!";
        String confirmInformation = "Successful Login!";

        if (validLogin) {
            alert.confirmAlertBoxes(confirmInformation);
            launchStockPage(event);
        } else {
            alert.errorAlertBoxes(errorInformation);
        }
        clearLoginContent();

    //        ResultSet resultSet = jdbcDao.selectLoginCredentials(usernameInput, passwordInput);
//        if (resultSet.next()) {
//            availability.confirmAlertBoxes(confirmInformation);
//            launchStockPage(event);
//        } else {
//            availability.errorAlertBoxes(errorInformation);
//        }

}

        public void clearLoginContent() {
        // Clear all fields on the login page
        username.clear();
        password.clear();
        loginButton.setDisable(true);
    }

    @FXML
    public void disableLoginButton() {
        // Prevent users from logging in until both fields are filled in
        String usernameInput = username.getText();
        String passwordInput = password.getText();
        boolean isDisabled = usernameInput.isEmpty() || usernameInput.trim().isEmpty()
                || passwordInput.isEmpty() || passwordInput.trim().isEmpty();
        loginButton.setDisable(isDisabled);
    }


    // Creates the notice page
    public void launchStockPage(ActionEvent event) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("stock.fxml"));
        Scene noticeScene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Stock Availability");
        window.setScene(noticeScene);
        window.show();
    }
}
