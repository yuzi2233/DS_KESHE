package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
public abstract class Graph {
    public int vertex_num;
    public int edge_num;
    public boolean []visited;
    public int []indegree;
    public int []outdegree;

    public Graph() {

    }

    void IncEdge(int from, int to)
    {
        indegree[to]++;
        outdegree[from]++;
        edge_num++;
    }
    void DecEdge(int from, int to)
    {
        indegree[to]--;
        outdegree[from]--;
        edge_num--;
    }
    public Graph(int vertex_num) {
        this.vertex_num = vertex_num;
        this.edge_num = 0;
        this.indegree = new int[vertex_num];
        this.outdegree = new int[vertex_num];
        this.visited = new boolean[vertex_num];
        for (int i = 0; i < vertex_num; i++) {
            this.visited[i] = false;
            this.indegree[i] = 0;
            this.outdegree[i] = 0;
        }
    }
    public Graph(Graph g)
    {
        this.vertex_num = g.vertex_num;
        this.edge_num = g.edge_num;
        this.indegree = new int[g.vertex_num];
        this.outdegree = new int[g.vertex_num];
        this.visited = new boolean[g.vertex_num];
        for (int i = 0; i < g.vertex_num; i++) {
            this.visited[i] = g.visited[i];
            this.indegree[i] = g.indegree[i];
            this.outdegree[i] = g.outdegree[i];
        }
    }
    public int GetVerticesNum(){
        return this.vertex_num;
    }

    public int GetEdgeNum(){
        return this.edge_num;
    }

    public void ClearVisitState(){
        for(int i=0;i<this.vertex_num;i++){
            this.visited[i] = false;
        }
    }
    public void DeepFirstSearch(int vertex){
        if(vertex<this.vertex_num&&vertex>=0){
            this.Visit(vertex);
            this.visited[vertex] = true;
            for(Edge e = this.GetFirstEdge(vertex); this.IsEdge(e); e = this.GetNextEdge(e)){
                if(this.visited[e.to] == false){
                    this.DeepFirstSearch(e.to);

                }
            }
        }else{
            System.out.print("Vertex id");
        }
    }
    public  void BroadFirstSearch(int vertex){
        if(vertex<this.vertex_num&&vertex>=0){
            Queue<Integer> queue = new LinkedList();
             queue.add(vertex);
            while(!queue.isEmpty()){
                vertex = queue.remove();
                if(this.visited[vertex]==false){
                    this.Visit(vertex);
                    this.visited[vertex] = true;
                    for(Edge e = this.GetFirstEdge(vertex); this.IsEdge(e);e=this.GetNextEdge(e)){
                        if(this.visited[e.to] == false){
                            queue.add(e.to);
                        }
                    }
                }
            }
        }
    }
    public  void DoDeepFirstTraverse(){
        this.ClearVisitState();
        for(int i = 0; i<this.vertex_num;i++){
            if(this.visited[i]==false){
                this.DeepFirstSearch(i);
            }
        }
    }
     abstract void Visit(int vertex);
     abstract Edge GetFirstEdge(int vertex);
     abstract Edge GetNextEdge(Edge pre_edge);
     abstract boolean SetEdge(int from, int to, int weight);//weight==1
     abstract boolean DelEdge(int from, int to);
     abstract boolean IsEdge(Edge edge);


}
