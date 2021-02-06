package Oblig6;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import javafx.scene.layout.Priority;
import static javax.swing.JOptionPane.showMessageDialog;
public class Graph {
    int N;
    int K;
    Node[] node;

    public int getN() {
        return N;
    }

    public int getK() {
        return K;
    }

    public Node[] getNode() {
        return node;
    }

    public void weightedGraph(String url) throws MalformedURLException {
        URL link = new URL(url);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(link.openStream()));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            node = new Node[N];
            for (int i = 0; i < N; i++) {
                node[i] = new Node();
            }
            K = Integer.parseInt(st.nextToken());
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                WeightedEdge e = new WeightedEdge(node[to], (WeightedEdge) node[from].edge, weight);
                node[from].edge = e;
                node[from].nodeNr = from;
            }
        } catch (FileNotFoundException fe) {
            System.out.println("Fil ikke funnet");
        } catch (IOException ex) {
            System.out.println("Fil ikke funnet");
        }
    }

    public void initPredecessor(Node start) {
        for (int i = N; i-- > 0;) {
            node[i].d = new Predecessor();
        }
        ((Predecessor) start.d).dist = 0;
    }

    public void shorten(Node n, WeightedEdge e, PriorityQueue kø) {
        Predecessor nd = n.d;
        Predecessor md = e.to.d;
        if (md.dist > nd.dist + e.weight) {
            md.dist = nd.dist + e.weight;
            md.pre = n;
            kø.remove(e.to);
            kø.add(e.to);
        }
    }

    public void dijkstra(Node start) {
        initPredecessor(start);
        Node[] pri = getNode();
        PriorityQueue kø = lag_priko(pri);
        for (int i = N; i > 1; i--) {
            Node n = (Node) kø.poll();
            for (WeightedEdge e = (WeightedEdge) n.edge; e != null; e = (WeightedEdge) e.next) {
                shorten(n, e, kø);
            }
        }
        result(start);
    }

    public Node getMin() {
        Node res = new Node();
        int tempDist = 1000000000;
        int resultIndex = 0;
        for (int i = 0; i < node.length; i++) {
            if (!node[i].known && (node[i].d).dist < tempDist) {
                tempDist = (node[i].d).dist;
                res = node[i];
                resultIndex = i;
            }
        }
        node[resultIndex].known = true;
        return res;
    }

    public void result(Node start) {
        System.out.println("SEARCH");
        System.out.println("Node " + " Predecessor " + " Distance");
        for (int i = 0; i < node.length; i++) {

            if (node[i] == start) {
                System.out.println(i + "     start        " + (node[i].d).dist); // Has no predecessor
            } else {
                if (((Predecessor) node[i].d).dist == 1000000000) {
                    System.out.println(i + "                  Not reached");
                } else {
                    System.out.println(i + "     " + (node[i].d).pre.nodeNr + "            " + (node[i].d).dist);
                }
            }

        }
    }


    public PriorityQueue lag_priko(Node t[]) {
        PriorityQueue prioritetskø = new PriorityQueue<>(N, new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return (node1.d.dist - node2.d.dist);
            }
        });
        prioritetskø.addAll(new ArrayList<Node>(Arrays.asList(t)));
        return prioritetskø;
    }

}
