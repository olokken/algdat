package Oblig6;

public class Node {
    Edge edge;
    int nodeNr;
    Object object;
    Predecessor d; // Nodedata
    boolean known;

    public void setKnown(boolean known) {
        this.known = known;
    }

    public Node() {
    }
}
