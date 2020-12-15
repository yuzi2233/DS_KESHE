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

public class MainPane extends Application{
    String path = "E:\\untitled3\\线路图1\\test.txt";
    String path1 = "E:\\untitled3\\线路图1\\test1.txt";
    String path2 = "E:\\untitled3\\线路图1\\test2.txt";
    String []LineName={"一号线","二号线","四号线","五号线","六号线","七号线","八号线","九号线","十号线","十三号线","十四号线","十五号线","八通线","昌平线"};
    Read_text rt;
    GridPane pane;
    Button []bt;
    ComboBox<String> line_name;
    ComboBox<String> cbo;
    ArrayList []lines;
    Stage primaryStage;
    public static void main(String[]args)  {
        Application.launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage=primaryStage;
        Label l1=new Label("路线：");
        Label l2=new Label("站：");
        rt= new Read_text(path, path1, path2);
        pane=new GridPane();
        pane.setPadding(new Insets(10,10,10,10));
        pane.setHgap(20);
        lines=rt.Return_Line_List();
        bt=new Button[2];
        bt[0]=new Button("确定");
        bt[0].setOnAction(new BuListen());
        bt[1]=new Button("退出");
        bt[1].setOnAction(new BuListen());
        line_name=new ComboBox<>();
        line_name.setEditable(false);
        cbo=new ComboBox<>();
        cbo.setEditable(false);
        //一号线的所有站
        String[] Stations=  Arrays.copyOf(rt.Return_Line_List()[0].toArray(), rt.Return_Line_List()[0].toArray().length, String[].class);
        ObservableList<String> item2= FXCollections.observableArrayList(Stations);
        cbo.setItems(item2);
        cbo.setValue("苹果园");
        //每一号线
        ObservableList<String> items= FXCollections.observableArrayList(LineName);
        line_name.getItems().addAll(items);
        line_name.setOnAction(e->setOthers(items.indexOf(line_name.getValue())));
        line_name.setValue("一号线");
        pane.add(l1,0,0);
        pane.add(line_name,1,0);
        pane.add(l2,0,1);
        pane.add(cbo,1,1);
        pane.add(bt[0],0,2);
        pane.add(bt[1],2,2);
        Scene scene=new Scene(pane,300,150);
        primaryStage.setTitle("请选择当前要查看的路线");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void setOthers(int indexOf) {
        System.out.println(indexOf);
        String[] Stations=  Arrays.copyOf(rt.Return_Line_List()[indexOf].toArray(), rt.Return_Line_List()[indexOf].toArray().length, String[].class);
        ObservableList<String> items= FXCollections.observableArrayList(Stations);
        cbo.setItems(items);
        cbo.setValue(Stations[0]);

    }
    private int Record(String station)
    {
        switch (station)
        {
            case "一号线":
                return 1;
            case "二号线":
                return 2;
            case "四号线":
                return 4;
            case "五号线":
                return 5;
            case "六号线":
                return 6;
            case "七号线":
                return 7;
            case "八号线":
                return 8;
            case "九号线":
                return 9;
            case "十号线":
                return 10;
            case "十三号线":
                return 13;
            case "十四号线":
                return 14;
            case "十五号线":
                return 15;
            case "八通线":
                return 16;
            case "昌平线":
                return 17;
        }
        return -1;
    }
    class BuListen implements EventHandler<ActionEvent>
    {
        Main ma;
        @Override
        public void handle(ActionEvent event) {
            Button a= (Button) event.getSource();
            if(a.getText().equals("确定"))
            {
                System.out.println(line_name.getValue()+cbo.getValue());
                try {
                    ma=new Main(cbo.getValue(),Record(line_name.getValue()));

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Flow_Pane gui1=new Flow_Pane(ma);
                try {
                    primaryStage.close();
                    gui1.start(new Stage());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
            if(a.getText().equals("退出"))
            {
                System.out.println("退出！");
                System.exit(0);
            }
        }
    }

}
