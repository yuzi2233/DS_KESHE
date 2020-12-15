package Graph;
import com.company.Subway;

import java.util.LinkedList;
import java.util.Queue;

public class MatrixGraph extends Graph{
    int [][]matrix;
    public String[] vertex_infos;
    public int [][]test_matrix;
    MatrixGraph(){};
    public MatrixGraph(int vertex_num, String[] vertex_infos)
    {
        super(vertex_num);
        this.vertex_infos=vertex_infos;
        this.matrix = new int[this.vertex_num][];
        for(int i=0;i<this.vertex_num; i++){
        this.matrix[i] = new int [this.vertex_num];
        for(int j=0;j<this.vertex_num;j++){
            this.matrix[i][j] = 0;
        }
    }

    }
    @Override
    public void Visit(int vertex) {
         System.out.println(vertex+"was visisted");

    }

    @Override
    public Edge GetFirstEdge(int vertex) {
        Edge e=new Edge(vertex,-1,0);
        int[] vs = this.matrix[vertex];
        for(int i=0;i<this.vertex_num;i++){
            if(this.matrix[vertex][i]!=0){
                e.weight = this.matrix[vertex][i];
                e.to = i;
                break;
            }
        }
        return e;
    }

    @Override
    public boolean SetEdge(int from, int to, int weight) {
        if((from<this.vertex_num)&&(to<this.vertex_num)&&(from>=0)&&(to>=0)&&(from!=to)){
            this.matrix[from][to] = weight;
            this.IncEdge(from,to);
            return true;
        }
        return false;
    }

    @Override
    public boolean DelEdge(int from, int to) {
        if((from<this.vertex_num)&&(to<this.vertex_num)&&(from>=0)&&(to>=0)&&(from!=to)){
            if(this.matrix[from][to]!=0){
                this.matrix[from][to] = 0;
                this.DecEdge(from,to);
                return true;
            }
        }
        return false;
    }

    @Override
    public  boolean IsEdge(Edge edge) {
        if((edge.from<this.vertex_num)&&(edge.to<this.vertex_num)&&(edge.from>=0)&&(edge.to>=0)&&(edge.from!=edge.to)){
            if(this.matrix[edge.from][edge.to]!=0){
                return true;
            }
        }
        return false;
    }

    @Override
    public Edge GetNextEdge(Edge pre_edge) {
        Edge e=new Edge(pre_edge.from,-1,0);
        int []vs = this.matrix[pre_edge.from];
        for(int i = pre_edge.to+1; i <this.vertex_num; i++){
            if(this.matrix[pre_edge.from][i]!=0){
                e.to = i;
                e.weight = this.matrix[pre_edge.from][i];
                break;
            }
        }
        return e;
    }
    public void topSortByQueue(){
        this.ClearVisitState(); //清除各顶点的访问标志
        //为避免破坏原图，复制原图的入度数组
        int[] indegree =new int[this.vertex_num];
        for(int i = 0; i < this.vertex_num; i++){
            indegree[i] = this.indegree[i];  }
        Queue <Integer>queue=new LinkedList<>();
        for( int i = 0; i < this.vertex_num; i++ ){
            if(indegree[i] == 0){ queue.add(i);  } //图中入度为0的顶点入队
        }
        while(!queue.isEmpty()){
            int v = queue.remove();
            Visit(v);
            this.visited[v] = true;
            for(Edge e = this.GetFirstEdge(v); this.IsEdge(e); e= this.GetNextEdge(e)){
                indegree[e.to]--;
                //入度为0的顶点入队
                if(indegree[e.to]== 0){ queue.add(e.to);}
            }
        }
        for(int i= 0; i < this.vertex_num; i++ ){
            if(this.visited[i]==false){
                System.out.println("图有环！");
            }
            }
        }
    public int[][] Dijkstra(int s, Subway su,int line,String []transfer,int []last_tranline,int []last_tran_sta)
    {

        test_matrix = new int[this.vertex_num][];
        for(int i=0;i<this.vertex_num; i++){
            test_matrix[i] = new int [this.vertex_num];
            for(int j=0;j<this.vertex_num;j++){
                if(this.matrix[i][j]==0&&i!=j)
                {
                    test_matrix[i][j]=9999999;

                }
                else
                {
                    test_matrix[i][j]=this.matrix[i][j];
                }
            }

            }
        String[] path = new String[matrix.length];
        for (int i = 0; i < matrix.length; i++) {

            path[i] = new String(s + "->"+ i);
        }
            for(int i = 0; i<this.vertex_num; i++)
        {
            for(int j = 0; j<this.vertex_num; j++)
            {
                if(i!=j&&test_matrix[i][j]==0)
                {
                    test_matrix[i][j]=999999999;

                }
                this.visited[i]=false;
            }
        }
        int index=0;
        //dist[s].m_length=0;
        this.visited[s]=true;
        for(int i=0;i<this.vertex_num;i++)
        {
            int min=999999999;
            for(int j=0;j<this.vertex_num;j++)
            {
                if(!this.visited[j] &&test_matrix[s][j]!=0&&test_matrix[s][j]<min)
                {
                    min= test_matrix[s][j];
                    index=j;
                }
            }
            this.visited[index]=true;
            for(int j=0;j<this.vertex_num;j++)
            {
                if(!this.visited[j] &&test_matrix[s][index]+test_matrix[index][j]<test_matrix[s][j])
                {
                    test_matrix[s][j]=test_matrix[s][index]+test_matrix[index][j];
                    path[j] = path[index] + "->" + j;
                }
            }
        }

        for(int i=0;i<this.vertex_num;i++)
        {
            System.out.println(test_matrix[s][i]);
            System.out.println(s+ "到" + i + "的最短路径为：" + path[i]);
            String[] sourceStrArray = path[i].split("->");
            int []pass=new int[sourceStrArray.length];
            for(int j=0;j< sourceStrArray.length;j++)
            {
                pass[j]=Integer.parseInt(sourceStrArray[j]);
            }
            if(pass[0]!=pass[1])
            {
                int a[] = su.Find_Station_Line(su.text.only_specila[pass[0]]);
                int b[] = su.Find_Station_Line(su.text.only_specila[pass[1]]);
                int Toghter1_line = su.R_LINE(a, b);
                if(Toghter1_line!=su.text.ES.get(line))
                {
                    transfer[i]="在" + su.text.only_specila[pass[0]] + "换乘"+ su.text.OS.get(Toghter1_line)+" ";
                }
            }
            for(int j=0;j< sourceStrArray.length;j++)
            {
                if(sourceStrArray.length>2) {
                    if (j != 0 && j != sourceStrArray.length - 1) {
                        int a[] = su.Find_Station_Line(su.text.only_specila[pass[j]]);
                        int b[] = su.Find_Station_Line(su.text.only_specila[pass[j - 1]]);
                        int Toghter1_line = su.R_LINE(a, b);
                        int c[] = su.Find_Station_Line(su.text.only_specila[pass[j + 1]]);
                        int Toghter2_line = su.R_LINE(a, c);
                        if (Toghter1_line != Toghter2_line) {
                            transfer[i] = transfer[i] + "在" + su.text.only_specila[pass[j]] + "换乘" + su.text.OS.get(Toghter2_line) + "号线 ";
                        }
                    }
                }
            }
            if(transfer[i].equals(""))
            {
                transfer[i]="无需换乘";
            }
            int a[] = su.Find_Station_Line(su.text.only_specila[pass[pass.length-1]]);
            int b[] = su.Find_Station_Line(su.text.only_specila[pass[pass.length-2]]);
            int Toghter1_line = su.R_LINE(a, b);
            last_tranline[i]=su.text.OS.get(Toghter1_line);
            last_tran_sta[i]=pass[pass.length-2];
        }
        return test_matrix;
    }

    }
