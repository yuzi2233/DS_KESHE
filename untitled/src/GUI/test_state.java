package GUI;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.company.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
//应该是Grid没用限制大小把,或者变为HBOX和vbox的结合，不用自动调整大小
public class test_state extends Application {
    static int LINE_NUM = 15;
    static int screen_high=700;
    static int screen_weight=1200; //一共按8条来走，105的倍数
    Main ma;
    MenuBar menuBar;
//    public static void main(String[]args)  {
//        Application.launch(args);
//    }
    test_state(Main ma)
    {
        this.ma=ma;
        menuBar=new MenuBar();
    }
    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {
        //GridPane Mpain=new GridPane();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        Menu fileMenu = new Menu("工具");
        MenuItem newMenuItem = new MenuItem("保存该站图片");

        fileMenu.getItems().add(newMenuItem);
        menuBar.getMenus().add(fileMenu);
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
        Mpain.setLayoutY(28);
        newMenuItem.setOnAction(new MenuListener(Mpain.getLayoutX(),Mpain.getLayoutY()));
        pane.getChildren().addAll(Mpain,menuBar);
        Scene scence=new Scene(pane);
        primaryStage.setResizable(false);
        primaryStage.setTitle("111");
        primaryStage.setWidth(screen_weight-135);
        primaryStage.setHeight(screen_high+63);
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

    private class MenuListener implements EventHandler<ActionEvent> {
        double a,b;
        MenuListener(double a,double b)
        {
            this.a=a;
            this.b=b;
        }
        @Override
        public void handle(ActionEvent event) {
            ImageView iv;
            iv = new ImageView();
            iv.setFitHeight(400);
            iv.setPreserveRatio(true);
            Robot robot = null;
            try {
                robot = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }
            Rectangle re = new Rectangle(250,80,(int)1050,(int)700);
            System.out.println(a+' ');
            System.out.println(b);
            BufferedImage screenCapture = robot.createScreenCapture(re);
            //截图图片背景透明处理
            //BufferedImage bufferedImage = Picture4.transferAlpha(screenCapture);
            //不进行背景透明处理
            BufferedImage bufferedImage = screenCapture;
            //转换图片格式展示在主舞台的场景中
            WritableImage writableImage = SwingFXUtils.toFXImage(bufferedImage, null);
            iv.setImage(writableImage);

            //将截图内容，放入系统剪切板
            Clipboard cb = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putImage(writableImage);
            cb.setContent(content);

            //将截取图片放入到系统固定位置
            try {
                ImageIO.write(bufferedImage,"png",new File("./capter.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
