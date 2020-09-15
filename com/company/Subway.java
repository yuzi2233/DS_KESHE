package com.company;

import Graph.MatrixGraph;

import java.util.Arrays;

public class Subway {
    MatrixGraph graph; //数据结构
    Read_text text;// 数据存储
    String[] Stations; //站的信息
    public Subway(){}
    public Subway(Read_text text)
    {
        this.text=text;
        Stations=  Arrays.copyOf(text.Return_Stations().toArray(), text.Return_Stations().toArray().length, String[].class); //将Object对象转化为String
        graph=new MatrixGraph(text.Get_total_Num(),Stations);
    }
}
