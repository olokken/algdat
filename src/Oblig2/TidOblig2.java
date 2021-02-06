package Oblig2;

public class TidOblig2 {
    public static void main(String[] args) {
        Oving2 ov = new Oving2();
        int antallElementer = 10000000;
        int[] normal = ov.tilfeldigListe(antallElementer);
        int[] dualPivot = ov.tilfeldigListe(antallElementer);
        System.out.println("Sum før sortering: " + ov.sjekkSum(normal));
        long start = System.currentTimeMillis();
        ov.quicksort(normal, 0,antallElementer -1);
        long slutt = System.currentTimeMillis();
        System.out.println("Sum etter sortering: " +  ov.sjekkSum(normal));
        System.out.println("Rekken er sortert fra minst til størst: " + ov.sjekkRekkefølge(normal));
        System.out.println("Tid quicksort bruker for en tilfeldig rekke med " + antallElementer + " elementer tar : " + (slutt - start) + "ms");

        System.out.println("\nSum før sortering: " + ov.sjekkSum(dualPivot));
        long start2 = System.currentTimeMillis();
        ov.dualPivotQuickSort(dualPivot, 0,antallElementer -1);
        long slutt2 = System.currentTimeMillis();
        System.out.println("Sum etter sortering: " +  ov.sjekkSum(dualPivot));
        System.out.println("Rekken er sortert fra minst til størst: " + ov.sjekkRekkefølge(normal));
        System.out.println("Tid dual pivot quicksort bruker for en tilfeldig rekke med " + antallElementer + " elementer tar : " + (slutt2 - start2) + "ms");

        int[] normalDuplikater = ov.listeMedDuplikater(antallElementer);
        int[] dualPivotDuplikater = ov.listeMedDuplikater(antallElementer);
        System.out.println("\nSum før sortering: " + ov.sjekkSum(normalDuplikater));
        long start3 = System.currentTimeMillis();
        ov.quicksort(normalDuplikater, 0,antallElementer-1);
        long slutt3 = System.currentTimeMillis();
        System.out.println("Sum etter sortering: " +  ov.sjekkSum(normalDuplikater));
        System.out.println("Rekken er sortert fra minst til størst: " + ov.sjekkRekkefølge(normalDuplikater));
        System.out.println("Tid quicksort bruker på en rekke med mange duplikater med " + antallElementer + " elementer tar : " + (slutt3 - start3) + "ms");

        System.out.println("\nSum før sortering: " + ov.sjekkSum(dualPivotDuplikater));
        long start4 = System.currentTimeMillis();
        ov.dualPivotQuickSort(dualPivotDuplikater, 0,antallElementer -1);
        long slutt4 = System.currentTimeMillis();
        System.out.println("Sum etter sortering: " +  ov.sjekkSum(dualPivotDuplikater));
        System.out.println("Rekken er sortert fra minst til størst: " + ov.sjekkRekkefølge(dualPivotDuplikater));
        System.out.println("Tid dual pivot quicksort bruker på en rekke med mange duplikater " + antallElementer + " elementer tar : " + (slutt4 - start4) + "ms");

        int[] normalSortert = ov.sortertListe(antallElementer);
        int[] dualPivotSortert = ov.sortertListe(antallElementer);
        System.out.println("\nSum før sortering: " + ov.sjekkSum(normalSortert));
        long start5 = System.currentTimeMillis();
        ov.quicksort(normalSortert, 0,antallElementer-1);
        long slutt5 = System.currentTimeMillis();
        System.out.println("Sum etter sortering: " +  ov.sjekkSum(normalSortert));
        System.out.println("Rekken er sortert fra minst til størst: " + ov.sjekkRekkefølge(normalSortert));
        System.out.println("Tid quicksort bruker på en sortert rekke" + antallElementer + " elementer tar : " + (slutt5 - start5) + "ms");

        System.out.println("\nSum før sortering: " + ov.sjekkSum(dualPivotSortert));
        long start6 = System.currentTimeMillis();
        ov.dualPivotQuickSort(dualPivotSortert, 0,antallElementer -1);
        long slutt6 = System.currentTimeMillis();
        System.out.println("Sum etter sortering: " +  ov.sjekkSum(dualPivotSortert));
        System.out.println("Rekken er sortert fra minst til størst: " + ov.sjekkRekkefølge(dualPivotSortert));
        System.out.println("Tid dual pivot quicksort bruker på en sortert rekke " + antallElementer + " elementer tar : " + (slutt6 - start6) + "ms");
    }
}
