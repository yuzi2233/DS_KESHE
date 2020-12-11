package GUI;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import com.company.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
