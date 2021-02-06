package Oblig4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hashtabell_2 {
    private List<Integer> hashtabell;
    private int størrelse;
    private int antallKollisjoner;
    private int antallElementer;

    public Hashtabell_2(int størrelse) {
        this.størrelse = størrelse;
        //Bruker denne typen arraylist fordi jeg vil sette alle tallene til null, slik at random.nextint også kan gi
        //tallet null uten at det vill oppstå problemer
        hashtabell = new ArrayList<Integer>(Collections.nCopies(størrelse, null));
        antallKollisjoner = 0;
        antallElementer = 0;
    }

    private long hashNøkkelH1(long k) {
        int A = 1327217885;
        //Fant ut at neste toerpotens over 10mill var 2^24.
        return (k * A >>> (31 - 24)) & (hashtabell.size() - 1);
    }


    private int hashNøkkel(long k) {
        long forsøksIndeks = hashNøkkelH1(k);
        if(hashtabell.get((int) forsøksIndeks) == null && forsøksIndeks < størrelse -1) {
            return (int) forsøksIndeks;
        }
        long h2 = (2*Math.abs(k)+1) % størrelse;
        do {
            antallKollisjoner++;
            forsøksIndeks += h2;
            if (forsøksIndeks > størrelse -1) {
                forsøksIndeks = forsøksIndeks % størrelse;
            }
            if (hashtabell.get((int) forsøksIndeks) == null && forsøksIndeks < størrelse - 1) {
                return (int) forsøksIndeks;
            }
        } while(hashtabell.get((int) forsøksIndeks) != null);
        //I 1/10 av gangene jeg kjører programmet returnerer denne metoden -1, sliter med å skjønne i hvilke tilfeller
        //detter skjer
        return -1;
    }

    public void leggTilTall(int k) {
        hashtabell.set(hashNøkkel(k), k);
        antallElementer++;
    }

    public int getStørrelse() {
        return størrelse;
    }

    public int getAntallKollisjoner() {
        return antallKollisjoner;
    }

    public int getAntallElementer() {
        return antallElementer;
    }
}
