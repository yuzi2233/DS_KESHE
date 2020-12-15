package GUI;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
public class rectangle_pane_every_Line {
    //GridPane pane;
    VBox pane;
    int weight=151; //每一块的宽度一样
    float ave; //每块长度
    Image imb;
    rectangle_pane_every_Line(int length, int line,int i, ArrayList stations,ArrayList price) //length为分配到的面板长度，line 为路线,i为站的数量，station 为哪些站
    {
        //采用VBOX
        ave=AVE(length,i);
       // System.out.println("ave"+ave+"   length "+i+"leng"+length+  "stations"+stations.size());
        pane=new VBox();
        //imb=new Image("GUI/picture/1sta.png");
        Picture pic=new Picture();
        imb=pic.Return_sta()[line];
        pane.setSpacing(0);
        ImageView iv1=new ImageView(imb);
        iv1.setFitHeight(3*ave);
        iv1.setFitWidth(weight);
        pane.getChildren().add(iv1);
        if(line ==5)
            ave=ave+1;
        if(line==13)
            ave=ave-1;
        rectangle_pane_every rectangle_pane_every = new rectangle_pane_every(length,(int)ave, line, i,stations, price);
        pane.getChildren().add(rectangle_pane_every.Mpane);   ///////**************需要修改！
    }
    float AVE(int length,int i)
    {
        return (int) ((float)length/(i+3.00));
    }

}
