package Oblig4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Oving4_1 {
    public static void main(String[] args) throws IOException {
        Hashtabell hashtabell = new Hashtabell(61);
        URL url = new URL("https://ait.idi.ntnu.no/fag/_alg/hash/navn20.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String inputLine;
        int antallElever = 0;
        while ((inputLine = in.readLine()) != null) {
            hashtabell.leggTilElement(inputLine);
            antallElever++;
        }
        in.close();
        hashtabell.skrivUtAlleKollisjoner();
        System.out.println("\nHer er lastfaktoren til hashtabellen: " + hashtabell.finnLastfaktor());
        System.out.println("\n Bruker søksfunksjonen og søker opp mitt eget navn, får opp listen med mitt eget navn og de mulige kollisjonene. " +
                "\nKunne valgt å søke i den dobbeltlenke lista, men dette er er en O(n) operajson jeg vil unngå ettersom vi her er opptatt av hastighet");
        System.out.println(hashtabell.søk("Ole,Løkken").toString());
        System.out.println("\n Antallet kollisjoner er : " + hashtabell.antallKollisjoner() + ". Antallet elever er : " + antallElever);
        System.out.println("Kollisjoner per person er derfor :  " + ((double)hashtabell.antallKollisjoner()/(double) antallElever));
    }
}
