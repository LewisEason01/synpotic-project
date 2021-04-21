import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    // Creates the login page
    public void start(Stage stage) throws Exception {
        new FileHandling().createLoginFile();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.setMaximized(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
