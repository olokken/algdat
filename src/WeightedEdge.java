
public class WeightedEdge extends Edge {
    int time; // hundredths of a second
    int length; // Meters
    int speedlimit; // km/t
    
    public WeightedEdge(Node n, WeightedEdge next, int time, int length, int speedlimit) {
        super(n, next);
        this.time = time;
        this.length = length;
        this.speedlimit = speedlimit;
    }
}
