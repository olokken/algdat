package Oblig5;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.LinkedList;
class Graf {
    private int V;
    private LinkedList<Integer> adj[];
    private int antallSterktSammenhengende;

    public Graf(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    void leggTilKant(int v, int k) {
        adj[v].add(k);
    }

    void DFSUtil(int v, boolean visited[]) {
        visited[v] = true;
        System.out.print(v + " ");

        int n;

        Iterator<Integer> i = adj[v].iterator();
        while (i.hasNext()) {
            n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    Graf getTranspose() {
        Graf graf = new Graf(V);
        for (int v = 0; v < V; v++) {
            Iterator<Integer> i = adj[v].listIterator();
            while (i.hasNext())
                graf.adj[i.next()].add(v);
        }
        return graf;
    }

    void iRekkefoelge(int v, boolean visited[], Stack stack) {
        visited[v] = true;

        Iterator<Integer> i = adj[v].iterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                iRekkefoelge(n, visited, stack);
        }
        stack.push(v);
    }

    void printSterkSammenhengende() {
        Stack stack = new Stack();

        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;


        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                iRekkefoelge(i, visited, stack);

        Graf gr = getTranspose();

        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }

        int j = 1;
        while (stack.empty() == false) {
            int v = (int) stack.pop();
            if (visited[v] == false) {
                System.out.print(j + ". ");
                gr.DFSUtil(v, visited);
                System.out.println();
                j++;
                antallSterktSammenhengende++;
            }
        }
    }
    public static Graf lesGrafUrl(String link) throws IOException {
        URL url = new URL(link);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        String antallnoder = br.readLine();
        String[] array1 = antallnoder.split(" ", 0);
        int antallNoder = Integer.parseInt(array1[0].trim());
        String line;
        Graf graf = new Graf(antallNoder);
        while ((line = br.readLine()) != null) {
            String[] array = line.split(" ", 0);
            ArrayList<String> numArray = new ArrayList<>();
            for (int i = 0; i < array.length; i++) {
                if(!array[i].equals("")){
                    numArray.add(array[i]);
                }
            }
            graf.leggTilKant(Integer.parseInt(numArray.get(0)), Integer.parseInt(numArray.get(1)));
        }
        return graf;
    }

    public int getAntallSterktSammenhengende() {
        return antallSterktSammenhengende;
    }
}