import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        Graph graph = new Graph();
        graph.weightedGraph("http://www.iie.ntnu.no/fag/_alg/Astjerne/opg/norden/noder.txt", "http://www.iie.ntnu.no/fag/_alg/Astjerne/opg/norden/kanter.txt",
                "http://www.iie.ntnu.no/fag/_alg/Astjerne/opg/norden/interessepkt.txt" );
        Node vernes = graph.getNodes().get(6198111);
        Node roros = graph.getNodes().get(1117256);
        Node karvag = graph.getNodes().get(6013683);
        Node gjemnes = graph.getNodes().get(6225195);
        Node trondheim = graph.getNodes().get(2399829);
        Node helsinki = graph.getNodes().get(1221382);
        long start = System.currentTimeMillis();
        ArrayList<Node> gasStations = graph.findClosest(vernes, 2);
        long slutt = System.currentTimeMillis();
        System.out.println("Tiden det tar å finne de 10 nermeste bensinstasjonene fra værnes er: " + (slutt - start) + "ms");
        start = System.currentTimeMillis();
        ArrayList<Node> chargers = graph.findClosest(roros, 4);
        slutt = System.currentTimeMillis();
        System.out.println("Tiden det tar å finne de 10 nermeste ladestasjonene fra Røros Hotell er: " + (slutt - start) + "ms");
        System.out.println("Dette er de 10 bensinstasjonene nærmest værnes");
        gasStations.forEach(x -> System.out.println(x.name + " " + x.latitudeDegrees+ "," + x.longitudeDegrees));
        System.out.println("Dette er de 10 ladestasjonene nærmest Røros Hotell");
        chargers.forEach(x -> System.out.println(x.name + " " + x.latitudeDegrees+ "," + x.longitudeDegrees));
        System.out.println();
        start = System.currentTimeMillis();
        graph.aStar(karvag, gjemnes, true);
        slutt = System.currentTimeMillis();
        System.out.println("Dette er triden A* bruker for å finne raskeste vei fra Kårvåg til Gjemnes " + (slutt - start) +
                "ms. Over ser du tiden ruten tar, samt veien gitt med en liste koordinater\n");
        start = System.currentTimeMillis();
        graph.dijkstra(karvag, gjemnes);
        slutt = System.currentTimeMillis();
        System.out.println("Dette er triden dijkstra bruker for å finne raskeste vei fra Kårvåg til Gjemnes " + (slutt - start) +
                "ms. Over ser du tiden ruten tar, samt veien gitt med en liste koordinater\n");
        start = System.currentTimeMillis();
        graph.aStar(trondheim, helsinki, true);
        slutt = System.currentTimeMillis();
        System.out.println("Dette er triden A* bruker for å finne raskeste vei fra Trondheim til Helsinki " + (slutt - start) +
                "ms. Over ser du tiden ruten tar, samt veien gitt med en liste koordinater\n");
        start = System.currentTimeMillis();
        graph.dijkstra(trondheim, helsinki);
        slutt = System.currentTimeMillis();
        System.out.println("Dette er triden dijkstra bruker for å finne raskeste vei fra Trondheim til Helsinki " + (slutt - start) +
                "ms. Over ser du tiden ruten tar, samt veien gitt med en liste koordinater");
    }
}
