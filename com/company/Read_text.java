package com.company;

import java.io.*;
import java.lang.System;
import java.util.*;
import java.util.regex.*;

class Read_text {
    private ArrayList<String>[] Station_list; //站台信息
    private Set<String> Stations = new TreeSet<>();
    private LinkedHashMap<String, Integer>[] Curcuit_list;//站台和对应的历程信息
    private File file;
    private LinkedHashMap<String,int[]> special_station;//换乘站信息
    private int Line_Num;//线路数量
    private int specila_num;
    private int station_num;
    private HashMap<String, Integer> ES;
    public Read_text(String path,String path1) throws IOException {

        First_stap();
        Get_Lines_Num(Dofile(path));
        Get_Lines_Infortion(Dofile(path));
        Get_Specila_Num(Dofile(path1));
        Get_special_s(Dofile(path1));
        station_num= Get_total_Num();
        Get_station_num();

    }
    public void First_stap()
    {
        ES=new HashMap<String, Integer>();
        ES.put("1号线",0);
        ES.put("2号线",1);
        ES.put("4号线",2);
        ES.put("5号线",3);
        ES.put("6号线",4);
        ES.put("7号线",5);
        ES.put("8号线",6);
        ES.put("9号线",7);
        ES.put("10号线",8);
        ES.put("13号线",9);
        ES.put("14号线东",10);
        ES.put("15号线",11);
        ES.put("16号线",12);
        ES.put("17号线",13);
        ES.put("18号线",14);
    }
    public void Get_station_num()
    {
        Stations = new TreeSet<>();

        for(int i=0;i<Line_Num;i++)
        {
            for(String ss:Station_list[i])
            {
                Stations.add(ss);

            }
        }
    }
    public BufferedReader Dofile( String path) throws IOException {
        file = new File(path);
        InputStreamReader read = new InputStreamReader(
                new FileInputStream(file), "gbk");
        BufferedReader  bw = new BufferedReader(read);
        return bw;
    }
    public void Get_Lines_Num(BufferedReader bw) throws IOException {
        String line=null;
        line=bw.readLine();
        String pattern = "(\\d+)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);
        //Line_Num =  Integer.parseInt(m.group(0));
        while (m.find()) {
            //System.out.println(m.group(0));
            Line_Num =  Integer.parseInt(m.group(0));
            System.out.println(Line_Num);

        }
    }
    public Set Return_Stations()
    {
        return Stations;
    }
    public void Get_Specila_Num(BufferedReader bw) throws IOException {
        String line=null;
        line=bw.readLine();
        String pattern = "(\\d+)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);
        //Line_Num =  Integer.parseInt(m.group(0));
        while (m.find()) {
            //System.out.println(m.group(0));
            specila_num =  Integer.parseInt(m.group(0));
            System.out.println(specila_num);
        }
    }
    public int Retrun_station_num()
    {
        return station_num;
    }
    public void Get_Lines_Infortion(BufferedReader bw) throws IOException {
        Station_list= new ArrayList[Line_Num];
        Curcuit_list = new LinkedHashMap[Line_Num];
        for(int ii=0;ii<Line_Num;ii++)
        {
            Station_list[ii]=new ArrayList<String>();
        }
        for(int ii=0;ii<Line_Num;ii++)
        {
            Curcuit_list[ii]=new LinkedHashMap<String, Integer>();
        }
        String line;
        int i=-1;
        while ((line = bw.readLine()) != null) {
            //String pattern = "(?<=\\()[\\u4e00-\\u9fa5]+(?=\\))";
            String pattern = "(?<=\\()(\\S)+(?=\\))";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(line);
            while (m.find()) {
                String pattern1 = "[\\u4e00-\\u9fa5]+";
                Pattern r1 = Pattern.compile(pattern1);
                Matcher m1 = r1.matcher(m.group(0));
                String pattern2 = "(\\d)+";
                Pattern r2 = Pattern.compile(pattern2);
                Matcher m2 = r2.matcher(m.group(0));
                while (m1.find()&&m2.find()){
                    Station_list[i].add(m1.group(0));
                    Curcuit_list[i].put(m1.group(0),Integer.parseInt(m2.group(0)));
                }
            }
            i++;
        }
        /*for(String n:Station_list[1])
        {
                System.out.println(n);
        } *///测试函数
        /*for (Map.Entry<String, Integer> aaa : Ages[0].entrySet()) {
            System.out.println(aaa);
        }*/ //测试函数
    }
    public void Get_special_s(BufferedReader bw)throws IOException {
        special_station = new LinkedHashMap<String, int[]>(); //假设最大位100
        /*for (int i = 0; i < 100; i++) {
            special_station[i] = new LinkedHashMap<String, int[]>();
        }*/
        int []Catch=new int[5];
        String line;
        String word = null;
        int i = -1;
        while ((line = bw.readLine()) != null) {
            String pattern = "[\\u4e00-\\u9fa5]+";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(line);
            String pattern2 = "[0-9]+";
            Pattern r2 = Pattern.compile(pattern2);
            Matcher m2 = r2.matcher(line);
            while (m.find() ) {
                word=m.group(0);
                int x=0;
                Catch=new int[5];
                while( m2.find()) {
                    Catch[x]=Integer.parseInt(m2.group(0));
                    x++;
                }
            }
            if(i!=-1)
            special_station.put(word,Catch);
            i++;
        }
    }
    public int Return_Line_Num()
    {
        return this.Line_Num;
    }
    public ArrayList[] Return_Line_List()
    {
        return this.Station_list;
    }
    public LinkedHashMap[] Return_Distance()
    {
        return this.Curcuit_list;
    }
    public LinkedHashMap<String, int[]> Return_special_line()
    {
        return this.special_station;
    }
    public void Add_Specila_Station(String station,int a[])
    {
        this.special_station.put(station,a);
        this.specila_num++;
    }
    public void Add_Station(String station,int distance) //未实现
    {
        Station_list[equal_station(station)].add(station);

    }
    public int equal_station(String station)  //获得对应的线路
    {
        return ES.get(station);
    }
    public int Get_total_Num()
    {
        int num=0;
        for(int i=0;i<Line_Num;i++)
        {
            num=num+Station_list[i].size();
        }
        /*for(int[]a:special_station.values()) {
            for(int i=0;i<a.length;i++)
                if(a[i]!=0)
                 System.out.println(a[i]);
        }*/ //test
        for(int[]a:special_station.values()) {
            for(int i=1;i<a.length;i++)
                if(a[i]!=0)
                    num--;

        }
        return num;
    }
}
