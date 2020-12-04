package GUI;

import com.company.Read_text;
import com.company.Subway;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


//这个类用于生成图上的标签
public class Liness {
    static int LINE_NUM = 15;
    Label[] label = new Label[LINE_NUM];
    Button []bu=new Button[50];
    HBox[] pane = new HBox[LINE_NUM];
    Picture pic;
    Subway sub;
    Liness(Subway su, Picture pic) {
        this.sub=su;
        int flag=0;
        this.pic=pic;
        Font fon = new Font("KaiTi", 40);
        label[0] = new Label("一号线");
        label[1] = new Label("二号线");
        label[2] = new Label("四号线");
        label[3] = new Label("五号线");
        label[4] = new Label("六号线");
        label[5] = new Label("七号线");
        label[6] = new Label("八号线");
        label[7]=new Label("九号线");
        label[8]=new Label("十号线");
        label[9]=new Label("十三号线");
        label[10]=new Label("十四号线");
        label[11]=new Label("十五号线");
        label[12]=new Label("八通线");
        label[13]=new Label("昌平线");
        label[14]=new Label("房山线");
        for (int i = 0; i < 15; i++) {
            if(i!=9){
            label[i].setFont(fon);
            label[i].setTextFill(Color.WHITE);}
            else
            {
                label[i].setFont(new Font("KaiTi", 30));
                label[i].setTextFill(Color.WHITE);
            }

        }
        Insets in = new Insets(0, 0, 0, 30); //设置边距
        int flag1=0;//用在price[]中
        for (int i = 0; i < LINE_NUM; i++) {
            int price[]=sub.price;
            pane[i]=new HBox();
            pane[i].getChildren().add(label[i]);
            VBox col_pane=new VBox();//右边的面板
            pane[i].setPadding(in);
            pane[i].setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            int row_num=(sub.text.Return_Line_List()[i].size()/7)+1;
            HBox []InPane=new HBox[row_num]; //每一个面板分配多少行
            for(int n=0;n<row_num;n++)
            {
                InPane[n]=new HBox();
            }
            //InPane.setPadding(new Insets(10,10,10,10));
            int m=0;
            int n=0;
            Font font=Get_Font(row_num);//获得对应的字体
            for(Object a:sub.text.Return_Line_List()[i])
            {

                String aa=(String)a;
                Image p=Retrun_Image(price[flag1]);
                BackgroundImage backgroundImage = new BackgroundImage( p, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                bu[m]=new Button(aa);
                bu[m].setPrefSize(110,50);
                //bu[m].setMaxSize(80,50);
                bu[m].setText((String)a);
                bu[m].setFont(font);
                bu[m].setTextFill(Color.WHITE);
                bu[m].setBackground(background);
                InPane[n].getChildren().add(bu[m]);
                bu[m].setOnAction(new StationButtonLinstener());
                m++;
                if(m%7==0)
                {
                    n++;
                }
                flag1++;
            }
            col_pane.getChildren().addAll(InPane);
            pane[i].getChildren().add(col_pane);
        }

    }

    private Font Get_Font(int size) {
        Font fon1=new Font("KaiTi", 15);
        Font fon0=new Font("KaiTi", 11);
        Font fon2=new Font("KaiTi", 13);
        if(size<=3)
            return  fon1;
        if(size == 4)
            return fon2;
        return fon0;
    }

    Image Retrun_Image(int price)
    {
        switch (price){
            case 3:
                return pic.Return_Price()[0];

            case 4:return pic.Return_Price()[1];

            case 5:return pic.Return_Price()[2];

            case 6:return pic.Return_Price()[3];

            case 7:return pic.Return_Price()[4];

            case 8:return pic.Return_Price()[5];

            case 9:return pic.Return_Price()[6];

            case 10:return pic.Return_Price()[7];

        }
        return pic.Return_Price()[7];
    }

    private class StationButtonLinstener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            Button a= (Button) event.getSource();
            Metro_Interchange_Station mis=new Metro_Interchange_Station(sub.go_every_station.get(a.getText()));
            try {
                mis.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}