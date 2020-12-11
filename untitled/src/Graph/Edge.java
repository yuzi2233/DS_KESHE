package Graph;

public class Edge {
    public int from;
    public int to;
    public int weight;
    public  Edge(){
        from=-1;
        to=-1;
        weight=0;
    };
    public  Edge(int f,int t,int w)
    {
        this.from=f;
        to=t;
        weight=w;
    }
}
