package Graph;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MatrixGraph extends Graph{
    int [][]matrix;
     String[] vertex_infos;
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

    }
