import java.util.Objects;

public class Node implements Comparable<Node> {

    Edge edge;
    double gScore = 0; // Distance from start node.
    double fScore = 0; // Distance from start node + estimated distance to target.
    double hScore = 0; // Estimated distance to target.
    int nodeNr;
    double latitudeDegrees;
    double longitudeDegrees;
    double latitude;
    double longitude;
    String name;
    int category;
    double cos_latitude;
    Predecessor d; // Nodedata


    public Node(int nodeNr, double latitude, double longitude, String name) {
        this.nodeNr = nodeNr;
        this.latitudeDegrees = latitude;
        this.longitudeDegrees = longitude;
        this.latitude = Math.toRadians(latitude);
        this.longitude = Math.toRadians(longitude);
        this.name = name.substring(1, name.length()-1);
        this.cos_latitude = Math.cos(Math.toRadians(latitude));
    }

    public Node(int nodeNr, double latitude, double longitude) {
        this.nodeNr = nodeNr;
        this.latitudeDegrees = latitude;
        this.longitudeDegrees = longitude;
        this.latitude = Math.toRadians(latitude);
        this.longitude = Math.toRadians(longitude);
        this.cos_latitude = Math.cos(Math.toRadians(latitude));
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public int compareTo(Node n) {
        return Double.compare(this.fScore, n.fScore);
    }
    
//    @Override
//    public int compareTo(Node n) {
//        return Double.compare(((Predecessor)d).dist, ((Predecessor)n.d).dist);
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return nodeNr == node.nodeNr;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeNr);
    }
}
