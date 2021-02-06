package Oblig2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Oving2 {
    public Oving2 () {}

    public int sjekkSum(int[] liste) {
        return Arrays.stream(liste).sum();
    }

    public boolean sjekkRekkefølge(int[] liste) {
        for (int i = 0; i < liste.length - 2; i++) {
            if(liste[i+1] < liste[i]) {
                 return false;
            }
        }
        return true;
    }

    public int[] tilfeldigListe(int antallElementer) {
        int[] liste = new int[antallElementer];
        Random r = new Random();
        for (int i = 0; i < antallElementer; i++) {
            liste[i] = r.nextInt();
        }
        return liste;
    }

    public int[] sortertListe(int antallElementer) {
        int[] liste = tilfeldigListe(antallElementer);
        Arrays.sort(liste);
        return liste;
    }

    public int[] listeMedDuplikater(int antallElementer) {
        int[] liste = new int[antallElementer];
        for (int i = 0; i < antallElementer; i++) {
            if (i % 2 == 0) {
                liste[i] = 3;
            } else {
                liste[i] = 5;
            }
        }
        return liste;
    }

    public static void
    bytt(int []t, int i, int j) {
        int k = t[j];
        t[j] = t[i];
        t[i] = k;
    }

    public static int
    median3sort(int []t, int v, int h) {
        int m = (v + h) / 2;
        if (t[v] > t[m]) bytt(t, v, m);
        if (t[m] > t[h]) {
            bytt(t, m, h);
            if (t[v] > t[m]) bytt(t, v, m);
        }
        return m;
    }


    public int
    splitt(int []t, int v, int h) {
        int iv, ih;
        int m = median3sort(t, v, h);
        int dv = t[m];
        bytt(t, m, h - 1);
        for (iv = v, ih = h - 1;;) {
            while (t[++iv] < dv) ;
            while (t[--ih] > dv) ;
            if (iv >= ih) break;
            bytt(t, iv, ih);
        }
        bytt(t, iv, h-1);
        return iv;
    }

    public void
    quicksort(int []t, int v, int h) {
        if (h - v > 2) {
            int delepos = splitt(t, v, h);
            quicksort(t, v, delepos - 1);
            quicksort(t, delepos + 1, h);
        } else median3sort(t, v, h);
    }

    static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void dualPivotQuickSort(int[] arr,
                                   int low, int high)
    {
        swap(arr, low, low+(high - low)/3);
        swap(arr, high, high - (high - low)/3);

        if (low < high)
        {

            // piv[] stores left pivot and right pivot.
            // piv[0] means left pivot and
            // piv[1] means right pivot
            int[] piv;
            piv = partition(arr, low, high);

            dualPivotQuickSort(arr, low, piv[0] - 1);
            dualPivotQuickSort(arr, piv[0] + 1, piv[1] - 1);
            dualPivotQuickSort(arr, piv[1] + 1, high);
        }
    }



    static int[] partition(int[] arr, int low, int high)
    {
        if (arr[low] > arr[high])
            swap(arr, low, high);

        // p is the left pivot, and q
        // is the right pivot.
        int j = low + 1;
        int g = high - 1, k = low + 1,
                p = arr[low], q = arr[high];

        while (k <= g)
        {

            // If elements are less than the left pivot
            if (arr[k] < p)
            {
                swap(arr, k, j);
                j++;
            }

            // If elements are greater than or equal
            // to the right pivot
            else if (arr[k] >= q)
            {
                while (arr[g] > q && k < g)
                    g--;

                swap(arr, k, g);
                g--;

                if (arr[k] < p)
                {
                    swap(arr, k, j);
                    j++;
                }
            }
            k++;
        }
        j--;
        g++;

        // Bring pivots to their appropriate positions.
        swap(arr, low, j);
        swap(arr, high, g);

        // Returning the indices of the pivots
        // because we cannot return two elements
        // from a function, we do that using an array.
        return new int[] { j, g };
    }
}
