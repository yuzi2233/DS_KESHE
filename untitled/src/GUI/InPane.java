package GUI;
import com.company.Read_text;
import com.company.Subway;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.ArrayList;

public class InPane {
//    GridPane Mpane;
    HBox  Mpane;

    GridPane pane1,pane2; //pane1为站,pane2为价格
    VBox pa1,pa2;
    int weigth=150;
    int L_weight=100;
    int p_weight=50;
    int adjust=0;
    Subway su=new Subway();
    InPane(int length,int ave, int line , int i,ArrayList stations,ArrayList price)
    {
        Mpane=new HBox();

        Mpane.setMaxWidth(weigth);

        pa1=creat_st(ave,i,stations,adjust,line);
        pa2=creat_pr(ave,i,price,adjust,line);
        Mpane.getChildren().add(pa1);
        Mpane.getChildren().add(pa2);
    }
    VBox creat_st(int ave,int i,ArrayList stations,int adjust,int line)
    {
        Label[] labels=new Label[i];
        VBox pane=new VBox();
        pane.setSpacing(0);
        pane.setMaxWidth(L_weight);
        int j=0;
        for(Object a:stations) {
            int ave1=0;
            String ss=(String) a;
            labels[j]=new Label(ss);
            labels[j].setMinWidth(L_weight);
            if((i-j)<adjust)
                ave1=ave+1;
            else
                ave1=ave;
            labels[j].setMinHeight(ave);
            labels[j].setMaxHeight(ave);
            int font_size=11;
            if(ave>50)
                font_size=40;
            labels[j].setFont(new Font("KaiTi", font_size));
            pane.getChildren().addAll(labels[j]);

            j++;
        }
        return pane;

    }
    VBox creat_pr(int ave,int i,ArrayList price,int adjust,int line)
    {
        Picture pic=new Picture();
        //GridPane pane=new GridPane();
        VBox pane=new VBox();
        pane.setSpacing(0);
        pane.setMaxWidth(p_weight);

        int j=0;
        for(Object a:price) {



            int  ss=(int) a;

            Image p=Retrun_Image(ss,pic);
            ImageView pv=new ImageView(p);
            pv.setFitHeight(ave);
            pv.setFitWidth(p_weight);
            //pane.add(pv,j,0);
            pane.getChildren().add(pv);

            j++;
        }
        return pane;
    }

    Image Retrun_Image(int price,Picture pic)
    {
        switch (price){
            case 3:
                return pic.Return_Image()[0];

            case 4:return pic.Return_Image()[1];

            case 5:return pic.Return_Image()[2];

            case 6:return pic.Return_Image()[3];

            case 7:return pic.Return_Image()[4];

            case 8:return pic.Return_Image()[5];

            case 9:return pic.Return_Image()[6];



        }
        return  pic.Return_Image()[7];
    }
    int Adjust(int ave,int length,int i) //调整函数
    {
        i=i+1;
        System.out.println("需要减去的"+(length-(ave*(i+2)))+",ave="+ave+",length="+length+",i="+(i+2));
        return length-(ave*(i+2));
    }
}
