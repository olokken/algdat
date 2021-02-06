package Oblig4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Oblig4_2 {
    public static void main(String[] args) {
        Hashtabell_2 hashtabell = new Hashtabell_2(16777216);
        HashMap<Integer, Integer> javaHash = new HashMap<>();
        Random ran = new Random();
        ArrayList<Integer> randomNums = new ArrayList<>();
        while (randomNums.size() != 10000000) randomNums.add(ran.nextInt());
        long start = System.currentTimeMillis();
        randomNums.forEach(x -> {
            hashtabell.leggTilTall(x);
        });
        long slutt = System.currentTimeMillis();
        long start2 = System.currentTimeMillis();
        randomNums.forEach(x -> {
            javaHash.put(x, x);
        });
        long slutt2 = System.currentTimeMillis();
        System.out.println("Tidsmåling for min hashtabell er : " + (slutt - start) + "ms");
        System.out.println("Tidsmåling for min java sin hastabell er : " + (slutt2 - start2) + "ms");
        System.out.println("Antall kollisjoner for min hashmap er " + hashtabell.getAntallKollisjoner());
        System.out.println("Lastfaktoren er : " + ((double)hashtabell.getAntallElementer()/(double) hashtabell.getStørrelse()));
        System.out.println("Antall kollisjoner per element er : " + ((double)hashtabell.getAntallKollisjoner()/(double) hashtabell.getAntallElementer()));

    }
}
