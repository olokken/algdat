package Oblig3;

import java.util.Scanner;

public class Tre {

    Node rot;
    String[] temp = new String[]{"", "", "", ""};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tre tre = new Tre();
        String tekst = "";
        System.out.println("Skriv inn ønsket ord, deretter trykk enter for å legge det i treet\n skriv print for å printe hele treet");
        do {
            tekst = sc.next();
            tre.nyNode(tekst);
        }while (!tekst.equalsIgnoreCase("print"));
        tre.skrivUtTre();
    }

    Tre(){
        rot = null;
    }

    public void nyNode(String word){
        if (rot == null) rot = new Node(word);
        else{
            Node parent = rot;
            while (true) {
                int i = word.compareToIgnoreCase(parent.word);
                if (i < 0) {
                    if (parent.left == null) {
                        parent.left = new Node(word);
                        return;
                    }
                    parent = parent.left;
                }
                else if (i > 0) {
                    if (parent.right == null) {
                        parent.right = new Node(word);
                        return;
                    }
                    parent = parent.right;
                }
            }
        }
    }


    private void format(Node node, int row){
        int antallPiksler = 64;
        int length = antallPiksler / (int) (Math.pow(2, row));
        if (node != null) temp[row] += " ".repeat((length-node.word.length())/2) + node.word + " ".repeat((length-node.word.length())/2) ;
        else temp[row] += " ".repeat(length);
        if (row < temp.length-1){
            if (node != null) {
                format(node.left, row + 1);
                format(node.right, row + 1);
            }
            else {
                format(null, row + 1);
                format(null, row + 1);
            }
        }
    }

    public void skrivUtTre(){
        format(rot, 0);
        for (String rad: temp) {
            System.out.println(rad);
        }
    }

    static class Node{
        String word;
        Node left = null;
        Node right = null;
        Node(String word){
            this.word = word;
        }
    }


}

