package com.company;

import java.io.*;
import java.lang.System;
import java.util.*;
import java.util.regex.*;

public class Read_text {
    private ArrayList<String>[] Station_list; //站台信息
    private Set<String> Stations = new TreeSet<>();  //不重复的站台信息
    private LinkedHashMap<String, Integer>[] Curcuit_list;//站台和对应的历程信息
    private File file;
    public LinkedHashMap<String,int[]> special_station,special_station_g;//换乘站信息,对应表1，2
    private int Line_Num;//线路数量
    private int specila_num;
    private int station_num;
    public String[] only_specila;//储存全部的换成站
    public HashMap<Integer, Integer> ES,OS;
    public Read_text(String path,String path1,String path2) throws IOException {

        First_stap();
        Get_Lines_Num(Dofile(path));
        Get_Lines_Infortion(Dofile(path));
        Get_Specila_Num(Dofile(path1));
        special_station=  Get_special_s(Dofile(path1));
        station_num= Get_total_Num();
        special_station_g=Get_special_s(Dofile(path2));
        Get_station_num();
        only_specila=Return_only_spe(Dofile(path2));
    }

    private String[] Return_only_spe(BufferedReader bw) throws IOException {
        String []word=new String[specila_num];
        String line;
        int i=-1;
        while ((line = bw.readLine()) != null) {
            String pattern = "[\\u4e00-\\u9fa5]+";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(line);

            while (m.find() ) {
                if(i!=-1)
                    word[i]=m.group(0);
                }
            i++;
            }
        return  word;
    }

    public void First_stap()
    {
        ES=new HashMap<Integer, Integer>();
        ES.put(1,0);
        ES.put(2,1);
        ES.put(4,2);
        ES.put(5,3);
        ES.put(6,4);
        ES.put(7,5);
        ES.put(8,6);
        ES.put(9,7);
        ES.put(10,8);
        ES.put(13,9);
        ES.put(14,10);
        ES.put(15,11);
        ES.put(16,12);
        ES.put(17,13);
        ES.put(18,14);
        OS=new HashMap<Integer, Integer>();
        OS.put(0,1);
        OS.put(1,2);
        OS.put(2,4);
        OS.put(3,5);
        OS.put(4,6);
        OS.put(5,7);
        OS.put(6,8);
        OS.put(7,9);
        OS.put(8,10);
        OS.put(9,13);
        OS.put(10,14);
        OS.put(11,15);
        OS.put(12,16);
        OS.put(13,17);
        OS.put(14,18);
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
    public LinkedHashMap<String,int[]> Get_special_s(BufferedReader bw)throws IOException {
        LinkedHashMap<String,int[]> sta = new LinkedHashMap<String, int[]>(); //假设最大位100
        /*for (int i = 0; i < 100; i++) {
            special_station[i] = new LinkedHashMap<String, int[]>();
        }*/
        int []Catch=new int[6];
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
                Catch=new int[6];
                while( m2.find()) {
                    Catch[x]=Integer.parseInt(m2.group(0));
                    x++;
                }
            }
            if(i!=-1)
            sta.put(word,Catch);
            i++;
        }
        return sta;
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
    public int Return_s_num()
    {
        return this.specila_num;
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
        return num+1;
    }
}
