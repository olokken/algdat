
class Predecessor {
    double dist;
    Node pre;
    static int infinite = 1000000000;
    
    public double findDist() {
        return dist;
    }
    
    public Node findPredecessor() {
        return pre;
    }
    
    public Predecessor() {
        dist = infinite;
    }
    
}
