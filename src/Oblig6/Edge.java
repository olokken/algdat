package Oblig6;

public class Edge {
    Edge next;
    Node to;
    
    public Edge(Node n, Edge nxt) {
        this.to = n;
        this.next = nxt;
    }
}
