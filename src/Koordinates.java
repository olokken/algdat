import java.io.IOException;

public class Koordinates {
    public static void main(String[] args) throws IOException {
        Graph graph = new Graph();
        graph.weightedGraph("C:\\Dev\\algdat\\src\\noder.txt", "C:\\Dev\\algdat\\src\\kanter.txt",
                "C:\\Dev\\algdat\\src\\interessepkt.txt" );
        Node n1 = graph.getNodes().get(3563448);
        Node n2 = graph.getNodes().get(1398759);
        graph.dijkstra2(n1, n2);
    }
}
