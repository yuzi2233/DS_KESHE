package GUI;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
public class Text_class {
    private Label[] label=new Label[10];
    Text_class(Picture pic)
    {
        Font fon=new Font("KaiTi",12);
        for(int i=0;i<8;i++)
        {
            label[i]=new Label("票价"+(int)(3+i)+"元");
            label[i].setTextFill(Color.WHITE);
            label[i].setFont(fon);
            if(i==7)
                label[i].setText("暂停开放");
        }
    }
    Label[] Return_Lavel()
    {
        return label;
    }
;}
