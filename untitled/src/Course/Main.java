package Course;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {

            Pane root = new Pane();

            Button button = new Button( "Click me!");
            button.setStyle("-fx-background-image: url('GUI/picture/four.png')");

            root.getChildren().add(button);

            Scene scene = new Scene(root, 800, 400);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}