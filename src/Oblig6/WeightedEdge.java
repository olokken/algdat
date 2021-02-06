package Oblig6;

public class WeightedEdge extends Edge {
    int weight;
    
    public WeightedEdge(Node n, WeightedEdge next, int weight) {
        super(n, next);
        this.weight = weight;
    }
}
