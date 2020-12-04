package GUI;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import com.company.*;
import javafx.util.Duration;

import javax.swing.plaf.multi.MultiPanelUI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;
//应该是Grid没用限制大小把,或者变为HBOX和vbox的结合，不用自动调整大小
public class test_state extends Application {
    static int LINE_NUM = 15;
    static int screen_high=700;
    static int screen_weight=1200; //一共按8条来走，105的倍数
    Main ma;
//    public static void main(String[]args)  {
//        Application.launch(args);
//    }
    test_state(Main ma)
    {
        this.ma=ma;
    }
    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {
        //GridPane Mpain=new GridPane();
        Pane pane=new Pane();
        HBox Mpain=new HBox();
        VBox []vpane=new VBox[screen_weight/150];


        for(int i=0;i<8;i++)
        {
            vpane[i]=new VBox();
            vpane[i].setMaxSize(150,screen_high);
            vpane[i].setMinSize(150,screen_high);
        }
        Mpain.setMaxHeight(screen_high);
        Mpain.setMaxWidth(screen_high);

      //  Mpain.setGridLinesVisible(true);
        //ma=new Main("天通苑",5);
        ArrayList[] st;
        ArrayList[] price;
        ONE_LINES []pan=new ONE_LINES[LINE_NUM];
        price=ma.subway.ev_price;
        st=ma.subway.text.Return_Line_List();
        int []ave= space_assign(st);
        int cl=0;
        int rl=0;
        int sum=0;
        for (int i = 0; i < LINE_NUM-1; i++) {   //-1
            int evave=ave[cl]*st[i].size();
            sum=sum+evave;
            if (sum>screen_high-50)
                evave=(screen_high-sum)+evave;
             pan[i]=new ONE_LINES(evave,i,st[i].size(),st[i],price[i]); //生的面板成每一个

            //Mpain.add(pan[i].pane,cl,rl);
            vpane[cl].getChildren().add(pan[i].pane);
            if(sum>screen_high-50&&sum<screen_high+50)
            {
                Mpain.getChildren().add(vpane[cl]);
                sum=0;
                cl++;
                rl=0;
            }
            else
            {
                rl++;
            }
        }
        for(int i=0;i<ave.length;i++)
        {
            System.out.println(ave[i]);
        }
        //开始布局
        pane.getChildren().add(Mpain);
        //pane.getChildren().add(pan[2].pane);
        Line line=new Line(0,700,1200,700);
        line.setStroke(Color.RED);
        pane.getChildren().add(line);
        //Scene scence=new Scene(Mpain);
        Scene scence=new Scene(pane);
        primaryStage.setTitle("111");
        primaryStage.setWidth(screen_weight+111);
        primaryStage.setHeight(screen_high+100);
        primaryStage.setScene(scence);
        primaryStage.show();
    }
    int[] space_assign(ArrayList[] st)   //资源调度函数   //每一条线路和总的车数量
    {
        int sum=0;
        int gl=0;//代表第几列
        //int standard=num/6; //每一列最好的标准
        int[]percent=new int[8];
        //ArrayList<Integer> pro_list=new ArrayList<>(); //临时的
        for(int i=0;i<LINE_NUM;i++)
        {
            sum=sum+st[i].size();
            if(i==LINE_NUM-1)
            {
                percent[gl]=screen_high/sum;
                break;
            }
             if(sum>38)
            {
                percent[gl]=screen_high/sum;
                sum=0;
                gl++;
            }
        }
        return percent;
    }

}
