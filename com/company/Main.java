package com.company;
import java.io.*;
import java.lang.System;
import java.util.ArrayList;

import Graph.*;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        ArrayList<String> a=new ArrayList<String>();
        String path="E:\\untitled3\\线路图1\\test.txt";
        String path1="E:\\untitled3\\线路图1\\test1.txt";
        Read_text rt=new Read_text(path,path1);
        Subway subway=new Subway(rt);

    }

}
