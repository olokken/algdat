package Oblig5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class oblig5 {
    public static void main(String args[]) throws IOException {
        System.out.println("Dette er de sterkt sammenhengende komponentene til grafen l7g1:");
        Graf graf1 = Graf.lesGrafUrl("http://www.iie.ntnu.no/fag/_alg/uv-graf/L7g1");
        graf1.printSterkSammenhengende();
        System.out.println("Grafen l7g1 har " + graf1.getAntallSterktSammenhengende() + " komponenter");

        System.out.println("\nDette er de sterkt sammenhengende komponentene til grafen l7g2:");
        Graf graf2 = Graf.lesGrafUrl("http://www.iie.ntnu.no/fag/_alg/uv-graf/L7g2");
        graf2.printSterkSammenhengende();
        System.out.println("Grafen l7g2 har " + graf2.getAntallSterktSammenhengende() + " komponenter");

        System.out.println("\nDette er de sterkt sammenhengende komponentene til grafen l7g6:");
        Graf graf3 = Graf.lesGrafUrl("http://www.iie.ntnu.no/fag/_alg/uv-graf/L7g6");
        graf3.printSterkSammenhengende();
        System.out.println("Grafen l7g6 har " + graf3.getAntallSterktSammenhengende() + " komponenter");

    }
}
