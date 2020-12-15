package GUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
public class Metro_Interchange_Station extends Application{
    Label la;
    BorderPane pa;
    Metro_Interchange_Station(String st)
    {
        la=new Label(st);
        pa=new BorderPane();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        pa.setCenter(la);
        Scene scene=new Scene(pa,600,50);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
