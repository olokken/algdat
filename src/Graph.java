import java.io.*;
import java.net.URL;
import java.util.*;

import static javax.swing.JOptionPane.showMessageDialog;


public class Graph {

    List<Node> nodes = new ArrayList<>();
    int N;

    public List<Node> getNodes() {
        return nodes;
    }


    public void readNodes(String url) throws IOException {
        //URL link = new URL(url);
        BufferedReader br = new BufferedReader(new FileReader(url));
        String line;
        StringTokenizer st = null;
        br.readLine();
        while ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);
            int nr = Integer.parseInt(st.nextToken());
            double lat = Double.parseDouble(st.nextToken());
            double lon = Double.parseDouble(st.nextToken());
            nodes.add(new Node(nr, lat, lon));
        }
        N = nodes.size();
    }

    public void readInfo(String url) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(url));
        String line;
        StringTokenizer st = null;
        br.readLine();
        while ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);
            int nr = Integer.parseInt(st.nextToken());
            int category = Integer.parseInt(st.nextToken());
            String name = "";
            while (st.hasMoreTokens()) {
                name += " " + st.nextToken();
            }
            Node node = nodes.get(nr);
            node.setName(name);
            node.setCategory(category);
        }
    }

    public void weightedGraph(String urlNodes, String urlEdges, String urlInfo) throws IOException {
        readNodes(urlNodes);
        readInfo(urlInfo);
        BufferedReader br = new BufferedReader(new FileReader(urlEdges));
        StringTokenizer st;
        String line;
        br.readLine();
        while ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            WeightedEdge e = new WeightedEdge(nodes.get(to), (WeightedEdge) nodes.get(from).edge, time, length, speed);
            nodes.get(from).edge = e;
        }
    }

    public double haversine(Node n1, Node n2) {
        double R = 6372.8;
        double dLat = n2.latitude - n1.latitude;
        double dLon = n2.longitude - n1.longitude;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2) * Math.sin(dLon / 2) * n1.cos_latitude * n2.cos_latitude;
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }

    public double timeEstimate(Node n1, Node n2) {
        double distance = haversine(n1, n2);
        return (distance / 110) * 60 * 60 * 100; // In secends, 110 km/h speed limit
    }

    public ArrayList<Node> findClosest(Node start,  int category) {
        ArrayList<Node> closest = new ArrayList<>();
        initPredecessor(start);
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            if(n.category == category || n.category == 6) {
                if(!closest.contains(n)) {
                    closest.add(n);
                }
            }
            if (closest.size() == 10) {
                break;
            }
            for (WeightedEdge e = (WeightedEdge) n.edge; e != null; e = (WeightedEdge) e.next) {
                Predecessor nd = (Predecessor) n.d;
                Predecessor md = (Predecessor) e.to.d;
                if (md.dist > nd.dist + e.time) {
                    queue.remove(md.pre);
                    md.dist = nd.dist + e.time;
                    md.pre = n;
                    queue.add(e.to);
                }
            }
        }
        return closest;
    }

    public void aStar(Node start, Node target, boolean aStar) {
        initPredecessor(start);
        PriorityQueue<Node> queue = new PriorityQueue<>();
        Map<Integer, Node> checked = new HashMap<>();
        start.hScore = timeEstimate(start, target);
        start.fScore = start.hScore;
        queue.add(start);
        int numberChecked = 0;

        while (!queue.isEmpty()) {
            Node n = queue.poll();
            checked.put(n.nodeNr, n);
            numberChecked++;

            if (n.nodeNr == target.nodeNr) {
                break;
            }

            for (WeightedEdge e = (WeightedEdge) n.edge; e != null; e = (WeightedEdge) e.next) {
                Node child = e.to;
                Predecessor md = (Predecessor) child.d;
                double tempGScore = n.gScore + e.time;
                double heuristic;
                double tempFScore;

                if (aStar) {
                    heuristic = timeEstimate(child, target);
                    tempFScore = tempGScore + heuristic;
                } else {
                    tempFScore = tempGScore;
                }

                if (child == checked.get(e.to.nodeNr) && tempFScore >= child.fScore) {
                    continue;
                }

                if (!queue.contains(child) || tempFScore < child.fScore) {
                    md.pre = n;
                    child.gScore = tempGScore;
                    child.fScore = tempFScore;
                    if (queue.contains(child)) {
                        queue.remove(child);
                    }
                    queue.add(child);
                }
            }
        }
        result(start, target);
        System.out.println("Number checked: " + numberChecked);
    }

    public void result(Node start, Node end) {
        int time = 0;
        Node n = end;
        while (n != start) {
            time += ((WeightedEdge) ((n.d).pre.edge)).time;
            System.out.println(n.latitudeDegrees + "," + n.longitudeDegrees);
            n = n.d.pre;
        }
        int totalSecs = time/100;
        int seconds = totalSecs%60;
        int totalMinutes = totalSecs / 60;
        int minutes = totalMinutes % 60;
        int hours = totalMinutes / 60;


        System.out.println(start.latitudeDegrees + "," + start.longitudeDegrees);
        System.out.println("Tiden denne ruten tar er : ");
        System.out.println(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }

    public void initPredecessor(Node start) {
        for (int i = nodes.size(); i-- > 0;) {
            nodes.get(i).d = new Predecessor();
        }
        ((Predecessor) start.d).dist = 0;
    }


    public void dijkstra(Node start, Node end) {
        initPredecessor(start);
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(start);
        int numberChecked = 0;

        while (!queue.isEmpty()) {
            Node n = queue.poll();
            numberChecked++;
            if (n.nodeNr == end.nodeNr) {
                break;
            }
            for (WeightedEdge e = (WeightedEdge) n.edge; e != null; e = (WeightedEdge) e.next) {
                Predecessor nd = (Predecessor) n.d;
                Predecessor md = (Predecessor) e.to.d;
                if (md.dist > nd.dist + e.time) {
                    queue.remove(md.pre);
                    md.dist = nd.dist + e.time;
                    md.pre = n;
                    queue.add(e.to);
                }
            }
        }
        result(start, end);
        System.out.println("Number checked: " + numberChecked);
    }

    public void dijkstra2(Node start, Node end) {
        int count = 0;
        initPredecessor(start); //initialize based on our source node
        PriorityQueue<Node> pq = create_pq(start);
        System.out.println("PRIORITY QUEUE CREATED");
        Node n = null;
        while (n != end){
            count++;
            n = pq.poll();
            for (WeightedEdge w = (WeightedEdge) n.edge; w != null; w = (WeightedEdge) w.next) { //as long as there is an edge
                shorten(n, w, pq); //shorten the queue
            }
        }
        System.out.println("DIJKSTRA FINISHED\nNODES VISITED: " + count);
        result(start,end);
    }

    public PriorityQueue create_pq(Node source){
        PriorityQueue priorityQueue = new PriorityQueue(N);
        //priorityQueue.addAll(new ArrayList(Arrays.asList(pri))); //we add all of the conctents of our list to queue
        priorityQueue.add(source);
        return priorityQueue;
    }


    public void shorten(Node n, WeightedEdge w, PriorityQueue pq) {
        Predecessor nd = n.d;
        Predecessor md = w.to.d;
        //if the distance so far and the edge is shorter than the estimated distance
        if (md.dist > nd.dist + w.time) {
            md.dist = nd.dist + w.time;
            md.pre = n;
            pq.remove(w.to); //updates priority queue
            pq.add(w.to);
        }
    }

}
