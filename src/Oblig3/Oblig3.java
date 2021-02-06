package Oblig3;

import java.util.*;
import java.util.stream.Collectors;

public class Oblig3 {

    static Iterator<Integer> lengstTall(LinkedList<Integer> tall, LinkedList<Integer> tall2) {
        if (tall.size() < tall2.size()) {
            return tall2.iterator();
        } return tall.iterator();
    }

    static Iterator<Integer> kortestTall(LinkedList<Integer> tall, LinkedList<Integer> tall2) {
        if (tall.size() < tall2.size()) {
            return tall.iterator();
        } return tall2.iterator();
    }

    static boolean størstTall(LinkedList<Integer> tall, LinkedList<Integer> tall2) {
        if(tall.size() > tall2.size()) {
            return true;
        } else if (tall2.size() > tall.size()) {
            return false;
        } else if (tall.size() == tall2.size()) {
            for (int i = tall.size() - 1; i >= 0; i--) {
                if (tall.get(i) > tall2.get(i)) {
                    return true;
                } else if(tall2.get(i) > tall.get(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    static LinkedList<Integer> subtrahere(LinkedList<Integer> tall, LinkedList<Integer> tall2) {
        LinkedList<Integer> resultat = new LinkedList<>();
        Iterator<Integer> førsteTall = tall.iterator();
        Iterator<Integer> sisteTall = tall2.iterator();
        if (størstTall(tall, tall2)) {
            int counter = 0;
            while(førsteTall.hasNext()) {
                int nummer1 = førsteTall.next();
                int nummer2 = 0;
                if(sisteTall.hasNext()) {
                    nummer2 = sisteTall.next();
                }
                if (nummer1 - nummer2  >= 0) {
                    resultat.add(nummer1 - nummer2);
                } else if(nummer1 - nummer2 < 0) {
                    resultat.add(nummer1 - nummer2 + 10);
                    tall.set(counter + 1, tall.get(counter + 1) - 1);
                }
                counter++;
            }
        } else {
            int counter = 0;
            while(sisteTall.hasNext()) {
                int nummer1 = sisteTall.next();
                int nummer2 = 0;
                if(førsteTall.hasNext()) {
                    nummer2 = førsteTall.next();
                }
                if (nummer1 - nummer2  >= 0) {
                    resultat.add(nummer1 - nummer2);
                } else if(nummer1 - nummer2 < 0) {
                    resultat.add(nummer1 - nummer2 + 10);
                    tall2.set(counter + 1, tall2.get(counter + 1) - 1);
                }
                counter ++;
            }
            resultat.set(resultat.size() - 1, resultat.get(resultat.size()- 1) * -1);
        }
            return resultat;
    }

    static LinkedList<Integer> addere(LinkedList<Integer> tall, LinkedList<Integer> tall2) {
        LinkedList<Integer> resultat = new LinkedList<>();
        Iterator<Integer> lengstTall = lengstTall(tall, tall2);
        Iterator<Integer> kortestTall = kortestTall(tall, tall2);
        int counter = 0;
        while(lengstTall.hasNext()) {
            if (kortestTall.hasNext()) {
                if(counter == 0 || resultat.get(counter - 1) < 10) {
                    resultat.add(lengstTall.next() + kortestTall.next());
                } else {
                    resultat.set(counter - 1, resultat.get(counter-1) - 10);
                    resultat.add(lengstTall.next() + kortestTall.next() + 1);
                }
            } else if (kortestTall.hasNext() == false) {
                resultat.add(lengstTall.next());
            }
            counter++;
        }
        return resultat;
    }



    static void skrivUtListe(LinkedList<Integer> tall) {
        Collections.reverse(tall);
        tall.forEach(x -> System.out.print(x));
    }

    static LinkedList<Integer> skrivTall() {
        LinkedList<Integer> returnere = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Skriv tall: ");
        char[] nummer = sc.next().toCharArray();
        for (int i = nummer.length-1; i >= 0; i--) {
            int a = Character.getNumericValue(nummer[i]);
            returnere.add(a);
        }
        return returnere;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Skriv + for addere og - for subtrahere");
        String operasjon = sc.next();
        if (operasjon.equalsIgnoreCase("+")) {
            skrivUtListe(addere(skrivTall(), skrivTall()));
        } else if (operasjon.equalsIgnoreCase("-")) {
            skrivUtListe(subtrahere(skrivTall(), skrivTall()));
        }
    }
}


