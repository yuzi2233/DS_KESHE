package GUI;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import com.company.*;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

public class Gui extends Application {
   Main ma;
   Pane pane3;//容器
   HBox hbox1;//翻页
   ComboBox<String> cbo,line_name;
   Button last,next;
   GridPane pane;//价目表
   GridPane pane1;//主界面
   Button bt1,bt2;
   int page_flag=0;
    Picture pic;
    Liness lines;
    HBox hbox2;
    String []LineName={"一号线","二号线","四号线","五号线","六号线","七号线","八号线","九号线","十号线","十三号线","十四号线","十五号线","八通线","昌平线"};
    Gui(Main ma)
    {
        this.ma=ma;
        pane3=new Pane();
        pane3.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        hbox1=new HBox();
        last=new Button("上一页");
        next=new Button("下一页");
        HBox.setMargin(last, new Insets(5,5,30,400));
        HBox.setMargin(next, new Insets(5,400,30,5));
        hbox1.getChildren().addAll(last,next);
        last.setPrefSize(80,50);
        next.setPrefSize(80,50);
        last.setOnAction(new ButtonListener());
        next.setOnAction(new ButtonListener());

    }
    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {
        System.out.println("站的数量"+ma.subway.text.Retrun_station_num());
        pic =new Picture();//创建图片数组
        Text_class textc=new Text_class(pic);
        pane =new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        //pane.setPadding(new Insets(10));
        for(int i=0;i<8;i++)
        {
            pane.add(pic.return_picture()[i],46+(i%4)*2,10+(i/4) );
            pane.add(textc.Return_Lavel()[i],47+(i%4)*2,10+(i/4));
        }
//        lines=new Liness(ma.subway,pic);
//        pane1=new GridPane();
//        for (int i = 0; i < 3; i++) {
//            RowConstraints column = new RowConstraints(222);
//            pane1.getRowConstraints().add(column);
//        }
//        pane1.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
//        pane1.add(pane,0,2);
//        int a = 0;
//        pane1.add(lines.pane[a], 0,0);
//        pane1.add(lines.pane[a +1], 0,1);
        //timer1(pane1,lines);
        //pane1.add(lines.pane,0,1);

        hbox1.setLayoutY(100);

        pane3.getChildren().add(hbox1);
        pane3.getChildren().add(Get_Main_pane());
        pane3.getChildren().add(Get_Choice_pane());
        Scene scence=new Scene(pane3);
        primaryStage.setTitle("111");
        primaryStage.setWidth(980);
        primaryStage.setHeight(800);
        primaryStage.setScene(scence);
        primaryStage.show();
        //primaryStage.setResizable(false);
    }
    public static void timer1(GridPane pane,Liness lines) {
        Timer timer = new Timer();
        /* void java.util.Timer.schedule(TimerTask task, long delay) */
        var ref = new Object() {
            int a = 0;
        };
        timer.schedule(new TimerTask() {
            public void run() {

                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished( event -> {
                    ref.a = ref.a +2;
                    pane.add(lines.pane[ref.a], 0,0);
                    if(ref.a!=14)
                            pane.add(lines.pane[ref.a +1], 0,1);
                    else {
                        pane.getChildren().remove(lines.pane[13]);
                        try {
                            sleep(1000); //暂停，每一秒输出一次
                        }catch (InterruptedException e) {
                            return;
                        }
                    }
                    if(ref.a ==14)
                    {pane.getChildren().removeAll(lines.pane);
                        ref.a =0;
                        pane.add(lines.pane[ref.a], 0,0);

                        pane.add(lines.pane[ref.a +1], 0,1);

                    }
                } );
                delay.play();
            }
        }, 2000,2000);// delay=2000毫秒 后执行该任务
    }
    GridPane Get_Main_pane()
    {
        lines=new Liness(ma.subway,pic);
        pane1=new GridPane();
        for (int i = 0; i < 3; i++) {
            RowConstraints column = new RowConstraints(222);
            pane1.getRowConstraints().add(column);
        }
        pane1.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        pane1.add(pane,0,2);
        int a = 0;
        pane1.add(lines.pane[a], 0,0);
        pane1.add(lines.pane[a +1], 0,1);
        pane1.setLayoutY(150);
        return  pane1;
    }
    HBox Get_Choice_pane()   //获得选项框
    {
        hbox2=new HBox();
        line_name=new ComboBox<>();
        line_name.setEditable(false);
        cbo=new ComboBox<>();
        cbo.setEditable(false);
        //一号线的所有站
        String[] Stations=  Arrays.copyOf(ma.rt.Return_Line_List()[0].toArray(), ma.rt.Return_Line_List()[0].toArray().length, String[].class);
        ObservableList<String> item2= FXCollections.observableArrayList(Stations);
        cbo.setItems(item2);
        cbo.setValue("苹果园");
        ObservableList<String> items= FXCollections.observableArrayList(LineName);
        line_name.getItems().addAll(items);
        line_name.setOnAction(e->setOthers(items.indexOf(line_name.getValue())));
        line_name.setValue("一号线");
        hbox2.getChildren().add(line_name);
        hbox2.getChildren().add(cbo);
       HBox.setMargin(line_name, new Insets(0,0,0,200));

        hbox2.setLayoutY(50);
        bt1=new Button("确定");
        bt1.setOnAction(new TBuListen());
        bt2=new Button("生成价目表");
        bt2.setOnAction(new TBuListen());
        hbox2.getChildren().add(bt1);
        hbox2.getChildren().add(bt2);
        HBox.setMargin(bt1, new Insets(0,0,0,200));
        HBox.setMargin(bt2, new Insets(0,0,0,50));
        return hbox2;
    }

    private void setOthers(int indexOf) {
        System.out.println(indexOf);
        String[] Stations=  Arrays.copyOf(ma.rt.Return_Line_List()[indexOf].toArray(), ma.rt.Return_Line_List()[indexOf].toArray().length, String[].class);
        ObservableList<String> items= FXCollections.observableArrayList(Stations);
        cbo.setItems(items);
        cbo.setValue(Stations[0]);
    }

    class ButtonListener implements EventHandler<ActionEvent>
    {
        Main ma;
        @Override
        public void handle(ActionEvent event) {
            Button flag_a= (Button) event.getSource();
            if(flag_a.getText().equals("上一页"))
            {
                if(page_flag>=2) {
                    pane1.getChildren().removeAll(lines.pane);
                    page_flag = page_flag - 2;
                    pane1.add(lines.pane[page_flag], 0, 0);

                    pane1.add(lines.pane[page_flag + 1], 0, 1);
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.titleProperty().set("信息");
                    alert.headerTextProperty().set("已经是第一页了！");
                    alert.showAndWait();
                }
            }
            if(flag_a.getText().equals("下一页"))
            {
                if(page_flag<12) {
                    pane1.getChildren().removeAll(lines.pane);
                    page_flag = page_flag + 2;
                    pane1.add(lines.pane[page_flag], 0, 0);
                    pane1.add(lines.pane[page_flag + 1], 0, 1);
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.titleProperty().set("信息");
                    alert.headerTextProperty().set("已经是最后一页了！");
                    alert.showAndWait();
                }
            }
        }
    }
    class TBuListen implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event) {
            Button a= (Button) event.getSource();
            if(a.getText().equals("确定"))
            {
                //System.out.println(line_name.getValue()+cbo.getValue());
                try {
                    ma=new Main( cbo.getValue(),Record(line_name.getValue()));

                } catch (IOException e) {
                    e.printStackTrace();
                }
                pane3.getChildren().removeAll(pane1);
                pane3.getChildren().add(Get_Main_pane());




            }
            if(a.getText().equals("生成价目表"))
            {
                Main mm;
                try {
                    mm=new Main( cbo.getValue(),Record(line_name.getValue()));
                    test_state price_pane=new test_state(mm);
                    price_pane.start(new Stage());
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
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

}
