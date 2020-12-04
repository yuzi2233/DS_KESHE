package com.company;

import Graph.MatrixGraph;

import java.util.*;

public class Subway {
    MatrixGraph graph; //数据结构
    public Read_text text;// 数据存储
    String[] Stations; //站的信息
    public int[] price;
    public ArrayList<Integer>[] ev_price;
    public HashMap<String,String> go_every_station;
    public Subway(){}
    public Subway(Read_text text)
    {
        this.text=text;
        //Stations=  Arrays.copyOf(text.Return_Stations().toArray(), text.Return_Stations().toArray().length, String[].class); //将Object对象转化为String
        graph=new MatrixGraph(text.Return_s_num(),text.special_station_g.keySet().toArray(new String[0]));

        Set_Graph();
    }

    public int get_close(String station,int a) {  //获取输入站最近的换乘站   a为哪条线
        for(int i=0;i<this.graph.vertex_num;i++)
        {
            if(station.equals(text.only_specila[i]))
            {
                return i;
            }
        }
        ArrayList[] Line_station=text.Return_Line_List();
        String[] Line_station_array;
        int size= Line_station[text.ES.get(a)].size();
       Line_station_array= ((String[])Line_station[text.ES.get(a)].toArray(new String[size]));
       int i=0;
       for( i=0;i<Line_station_array.length;i++)
       {
           if(station.equals(Line_station_array[i]))
               break;
       }
       int b=i;
       int c=i;
        while(true)
        {
            if((b+1)<Line_station_array.length)
            {
                if(Is_special(b+1,Line_station_array)!=-1)
                {
                    return Is_special(b+1,Line_station_array);
                }
            }
            if((c-1)>=0)
            {
                if(Is_special(c-1,Line_station_array)!=-1)
                {
                    return Is_special(c-1,Line_station_array);
                }
            }
            b++;
            c--;
        }
    }
    public int Is_special(int i,String[] st)
    {
        for(int a=0;a<this.graph.vertex_num;a++)
        {
            if(st[i].equals(text.only_specila[a]))
            {
                return a;
            }
        }
        return -1;
    }


     int Get_weight(String s1,String s2) {
        if(s1.equals(s2))
        {
            return 0;
        }
        int a[]=Find_Station_Line(s1);
        int b[]=Find_Station_Line(s2) ;
        int distance=0;
        int min;
        boolean flag=false;
        LinkedHashMap line= text.Return_Distance()[R_LINE(a,b)];
       // if(R_LINE(a,b)!=1&&R_LINE(a,b)!=8){
        for(Iterator it= line.entrySet().iterator();it.hasNext();){
            Map.Entry entry = (Map.Entry) it.next();
            if(entry.getKey().equals(s1)){
                flag=true;
            }
            if(entry.getKey().equals(s2))
            {
                if(distance==0)
                    continue;
                if(R_LINE(a,b)!=1&&R_LINE(a,b)!=8){
                return  distance;}
                else
                    if(R_LINE(a,b)==1)
                    {
                        if (26400-distance<distance)
                            return 26400-distance;
                        else
                            return distance;
                    }
                if(R_LINE(a,b)==8)
                {
                    if (57100-distance<distance)
                        return 57100-distance;
                    else
                        return distance;
                }
            }
            if(flag)
            {
                distance=distance+(int)entry.getValue();
            }
        }
        if(flag==true)
        {
            return Get_weight(s2,s1);
        }
        return 0;
    }
    public int R_LINE(int[] a, int[] b)
    {
        if(a.length!=1&&b.length!=1)
        {
        for(int i=0;i<a.length;i++)
        {
            for(int j=0;j<b.length;j++)
            {
                if(a[i]==b[j]&&a[i]!=0)
                    return text.ES.get(a[i]);
            }
        }
        }
        else if(a.length==1)
        {
            return a[0];
        }
        else if(b.length==1)
        {
            return b[0];
        }
        return -1;
    }
    private void Creat_Graph() {

    }
    private void TEST() {
        while (true)
        {
            System.out.println("请输入");
            Scanner input=new Scanner(System.in);
            int a[]=Find_Station_Line(input.nextLine());
            if(a!=null)
            for(int i=0;i<a.length;i++)
            {
                if(a[i]==0)
                    break;
                System.out.println(a[i]);
            }
        }
    }
    public void Set_Graph()
    {
        int i=0;
        for(int x[]:text.special_station_g.values())
        {
            int j=0;
            while(x[j]!=0) {
                    int n = Get_weight(text.only_specila[i], text.only_specila[x[j] - 1]);
                    graph.SetEdge(i, x[j]-1, n);  //需要改
                j++;
                }
            i++;
        }
    }
    public int[] Find_Station_Line(String station)  //返回值+1
    {
        String []stations=text.special_station.keySet().toArray(new String[0]);
        for(String a:stations)
        {
            if(station.equals(a))
            {
                return text.special_station.get(station);
            }
        }
        for(int i=0;i<text.Return_Line_Num();i++)
        {
            for(Object a:text.Return_Distance()[i].keySet())
            {
                if(station.equals(a))
                {
                    int num[]=new int[1];
                    num[0]=i;
                    return num;
                }
            }
        }
        return null;
    }
    public String Return_During(int x,int y)
    {
        String string ="";
        ArrayList lines_station;
        int a[]=Find_Station_Line(this.text.only_specila[x]);
        int b[]=Find_Station_Line(this.text.only_specila[y]) ;
        int Toghter_line=R_LINE(a,b);
        if(Toghter_line==-1)
            return string;
        lines_station=this.text.Return_Line_List()[Toghter_line];
        for(int i=0;i<lines_station.size();i++)
        {
            if(((String)lines_station.get(i)).equals(this.text.only_specila[x])||lines_station.get(i).equals(this.text.only_specila[y]))
            {
                int flag = i + 1;
                while(true) {

                    string = string + "->" + (String) lines_station.get(flag);
                    flag++;
                    if(((String)lines_station.get(flag)).equals(this.text.only_specila[x])||lines_station.get(flag).equals(this.text.only_specila[y]))
                    {
                        break;
                    }
                }
                break;
            }

        }

        return string;
    }
    void Price(String station,int s,int distance,String []transfer,int []last_tranline,int []last_tran_sta)
    {
        price=new int[500];
        go_every_station=new HashMap<>();
        ArrayList stations;
        int flag=0;
        ev_price=new ArrayList[this.text.Return_Line_Num()]; //储存每路线的价钱
        for(int i=0;i<this.text.Return_Line_Num();i++)
        {
            ev_price[i]=new ArrayList<>();
            for(Object a:this.text.Return_Line_List()[i])
            {
                //int dis2=this.Get_weight((String)a,station);
                int dis_o=this.get_close((String)a,text.OS.get(i)); //到达点最近的换成站
                int dis= this.Get_weight((String)a,this.text.only_specila[dis_o]); //到达点换成站-到达点

                int num=Return_Price(this.graph.test_matrix[s][dis_o]+distance+dis);
                //System.out.println("到"+(String)a+"的价格是"+ num);
                price[flag]=num;
                flag++;
                ev_price[i].add(num);
                if(Wether_sp((String)a))
                {
                    go_every_station.put((String)a,transfer[dis_o]);
                }
                else
                {
                    if(last_tranline[dis_o]!=this.text.OS.get(i))
                    {
                        String tem="";
                        tem=transfer[dis_o];
                        if(transfer[dis_o].equals("无需换乘"))
                            tem="";

                        String ss=tem+" 在"+this.text.only_specila[last_tran_sta[dis_o]]+"换乘"+this.text.OS.get(i)+"号线";
                        go_every_station.put((String)a,ss);
                    }
                    else
                    {
                        String ss=transfer[dis_o];
                        go_every_station.put((String)a,ss);
                    }
                }

            }

        }

    }
    boolean Wether_sp(String s)
    {
        for(int i=0;i<this.text.only_specila.length;i++)
        {
            if(s.equals(this.text.only_specila[i]))
            {
                return true;
            }
        }
        return false;
    }
    int Return_Price(int x) {
        if (x < 6000) {
            return 3;
        }
        if (x > 6000 && x < 12000) {
            return 4;
        }
        if (x > 12000 && x < 22000) {
            return 5;
        }
        if (x > 22000 && x < 32000) {
            return 6;
        }
        if (x > 32000)
        {
            return (int) Math.ceil((6+Math.ceil((float)x-32000)/20000));
        }
        return -1;
    }
}
