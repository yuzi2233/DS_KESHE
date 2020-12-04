package GUI;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Picture{
   private Image[] imb=new Image[10] ;
   private ImageView []imbv=new ImageView[10];
   private Image []StationImage=new Image[15];
   private Image[]Price=new Image[10];

    Picture()
  {

      for(int i=0;i<10;i++)
      {
          imbv[i]=new ImageView();
      }

      imb[0]=new Image("GUI/picture/blue1.png");
      imb[1]=new Image("GUI/picture/green.png");
      imb[2]=new Image("GUI/picture/green1.png");
      imb[3]=new Image("GUI/picture/orange1.png");
      imb[4]=new Image("GUI/picture/purpue.png");
      imb[5]=new Image("GUI/picture/purpue2.png");
      imb[6]=new Image("GUI/picture/red.png");
      imb[7]=new Image("GUI/picture/sky1.png");
      StationImage[0]=new Image("GUI/picture/1sta.png");
      StationImage[1]=new Image("GUI/picture/2sta.png");
      StationImage[2]=new Image("GUI/picture/4sta.png");
      StationImage[3]=new Image("GUI/picture/5sta.png");
      StationImage[4]=new Image("GUI/picture/6sta.png");
      StationImage[5]=new Image("GUI/picture/7sta.png");
      StationImage[6]=new Image("GUI/picture/8sta.png");
      StationImage[7]=new Image("GUI/picture/9sta.png");
      StationImage[8]=new Image("GUI/picture/10sta.png");
      StationImage[9]=new Image("GUI/picture/13sta.png");
      StationImage[10]=new Image("GUI/picture/14sta.png");
      StationImage[11]=new Image("GUI/picture/15sta.png");
      StationImage[12]=new Image("GUI/picture/batong.png");
      StationImage[13]=new Image("GUI/picture/changping.png");
      StationImage[14]=new Image("GUI/picture/fangshan.png");
      Price[0]=new Image("GUI/picture/three.png");
      Price[1]=new Image("GUI/picture/four.png");
      Price[2]=new Image("GUI/picture/five.png");
      Price[3]=new Image("GUI/picture/six.png");
      Price[4]=new Image("GUI/picture/seven.png");
      Price[5]=new Image("GUI/picture/eight.png");
      Price[6]=new Image("GUI/picture/red.png");
      Price[7]=new Image("GUI/picture/nine.png");


      imbv[0].setImage(Price[0]);
      imbv[1].setImage(Price[1]);
      imbv[2].setImage(Price[2]);
      imbv[3].setImage(Price[3]);
      imbv[4].setImage(Price[4]);
      imbv[5].setImage(Price[5]);
      imbv[6].setImage(Price[6]);
      imbv[7].setImage(Price[7]);

      for(int i=0;i<8;i++)
      {
          imbv[i].setPreserveRatio(true);
          imbv[i].setSmooth(true);
          imbv[i].setFitHeight(50);
          imbv[i].setFitWidth(50);
      }



  }
  public ImageView[] return_picture()
  {
      return imbv;
  }
  public Image[] Return_Image()
  {
      return imb;
  }
  public Image[] Return_sta(){return StationImage;}
  public Image[] Return_Price(){return Price;}
}
