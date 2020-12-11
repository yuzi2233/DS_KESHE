package com.company;
import java.io.*;
import java.lang.System;
import java.util.ArrayList;
import java.util.Scanner;
import Graph.*;
public class Main {
    String path = "E:\\untitled3\\线路图1\\test.txt";
    String path1 = "E:\\untitled3\\线路图1\\test1.txt";
    String path2 = "E:\\untitled3\\线路图1\\test2.txt";
    public Read_text rt;
    public Subway subway;
        public Main(String instation,int inline) throws IOException {
            ArrayList<String> a = new ArrayList<String>();
            rt= new Read_text(path, path1, path2);
            subway= new Subway(rt);
            int distance;
            int[][] test_matrix;
            String station=instation;
            String []transfer=new String[subway.text.only_specila.length];//这个数组用来记录到每个站的路线
            int []last_tranline=new int[subway.text.only_specila.length];//这个数组用来记录每一个站最后的路线  《边缘检测》
            int []last_tran_sta=new int[subway.text.only_specila.length];//每一个站如何到达
            for(int i=0;i<this.subway.text.only_specila.length;i++)
            {
                transfer[i]="";
            }
            int line=inline;
            int aa=subway.get_close(station,line); //获得距离最近的换乘站
            distance= subway.Get_weight(station,subway.text.only_specila[aa]);
            test_matrix = subway.graph.Dijkstra(aa,subway,line,transfer,last_tranline,last_tran_sta);
            for(int i=0;i<this.subway.text.only_specila.length;i++)
            {
                System.out.println(i);
                System.out.println(transfer[i]);
                System.out.println(last_tranline[i]);
            }
            subway.Price(station,aa,distance,transfer,last_tranline,last_tran_sta);
            System.out.println("-------------------------------------------------");
            for(Object key:subway.go_every_station.keySet())
            {
                System.out.println("Key: "+key+" Value: "+subway.go_every_station.get(key));
            }
    }
}
