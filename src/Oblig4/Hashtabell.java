package Oblig4;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

public class Hashtabell{
    private LinkedList<String>[] hashtabell;
    private int størrelse;
    public Hashtabell(int størrelse) {
        this.størrelse = størrelse;
        this.hashtabell = new LinkedList[størrelse];
    }

    public void leggTilElement(String navn) {
        int indeks = tabellIndeks(hashNøkkel(navn));
        if (hashtabell[indeks] == null) {
            hashtabell[indeks] = new LinkedList<String>();
        }
        hashtabell[indeks].add(navn);
    }

    public LinkedList<String> søk(String navn) {
        int indeks = tabellIndeks(hashNøkkel(navn));
        return hashtabell[indeks];
    }

    private long hashNøkkel(String navn) {
        long hash = 1;
        int grunntall = 7;
        for(int i = 0; i < navn.length(); i++) {
            Character ch = navn.charAt(i);
            int asciiVerdi = (int)ch;
            hash += asciiVerdi * grunntall;
            grunntall = grunntall * 7;
        }
        if(hash < 0) {
            return hash * -1;
        }
        return hash;
    }

    private int tabellIndeks(long hashVerdi) {
        long indeks = hashVerdi % this.størrelse;
        return (int) indeks;
    }

    public int antallKollisjoner() {
        return Arrays.stream(hashtabell)
                .filter(x -> x != null && x.size() > 1)
                .mapToInt(x -> x.size() - 1)
                .sum();
    }

    public double finnLastfaktor() {
        int antallElementer = (int) Arrays
                .stream(hashtabell)
                .filter(Objects::nonNull)
                .count();
        return (double) antallElementer/(double) this.størrelse;
    }

    public void skrivUtAlleKollisjoner() {
        System.out.println("Her er alle listene med størrelse over 1, som betyr at de som er i samme liste har fått tildelt samme indeks av hashfunksjonen" +
                " og dermed kolliderer");
        Arrays.stream(hashtabell).filter(x -> x != null && x.size() > 1)
                .forEach(e -> System.out.println(e.toString()));
    }
}
