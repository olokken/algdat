package Oblig1;

public class TidOblig1 {
    public static void main(String[] args) {
        Oving1 oving = new Oving1();

        System.out.println("Testkode til algoritme 1: " + oving.regnPotens(5,5));
        System.out.println("Testkode til algoritme 2: " + oving.regnPotens(8,12));
        System.out.println("Testkode til Math.Poew: " + Math.pow(3.5, 6));

        System.out.println("\nTid algoritme 1 med n = 5 : " + oving.tidAlg1(1.02,5));
        System.out.println("Tid algoritme 1 med n = 50 : " + oving.tidAlg1(1.02,50));
        System.out.println("Tid algoritme 1 med n = 500 : " + oving.tidAlg1(1.02,500));
        System.out.println("Tid algoritme 1 med n = 5000 : " + oving.tidAlg1(1.02,5000));

        System.out.println("\nTid algoritme 2 med n = 5 : " + oving.tidAlg2(1.02, 5));
        System.out.println("Tid algoritme 2 med n = 50 : " + oving.tidAlg2(1.02, 50));
        System.out.println("Tid algoritme 2 med n = 500 : " + oving.tidAlg2(1.02, 500));
        System.out.println("Tid algoritme 2 med n = 5000 : " + oving.tidAlg2(1.02, 5000));

        System.out.println("\nTid Math.pow med n = 5 : " + oving.tidMathPow(1.02, 5));
        System.out.println("Tid Math.pow med n = 50 : " + oving.tidMathPow(1.02, 50));
        System.out.println("Tid Math.pow med n = 500 : " + oving.tidMathPow(1.02, 500));
        System.out.println("Tid Math.pow med n = 5000 : " + oving.tidMathPow(1.02, 5000));
    }
}
