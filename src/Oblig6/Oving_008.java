package Oblig6;

import java.net.MalformedURLException;

public class Oving_008 {
    public static void main(String[] args) throws MalformedURLException {
        Graph graph = new Graph();
        Graph graph2 = new Graph();
        graph.weightedGraph("http://www.iie.ntnu.no/fag/_alg/v-graf/vg1");
        graph2.weightedGraph("http://www.iie.ntnu.no/fag/_alg/v-graf/vg2");
        System.out.println("vg1 med start i node 0");
        graph.dijkstra(graph.getNode()[0]);

    }
    
}
