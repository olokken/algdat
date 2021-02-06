package Oblig6;

class Predecessor {
    int dist;
    Node pre;
    static int infinite = 1000000000;
    
    public int findDist() {
        return dist;
    }
    
    public Node findPredecessor() {
        return pre;
    }
    
    public Predecessor() {
        dist = infinite;
    }
    
}
