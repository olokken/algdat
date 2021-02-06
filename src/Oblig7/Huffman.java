package Oblig7;

import java.io.*;
import java.util.*;

public class Huffman {
    String codedString = "";

    public Huffman() {}

    public void huffmannCompression(File file, String outputPath) throws IOException {
        DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(new File(outputPath)));
        byte[] array = new byte[dis.available()];
        dis.readFully(array);
        int[] frequencies =  frequencies(array);
        Node root = buildHuffmanTree(frequencies);
        String[] st = new String[256];
        buildCode(st, root, "");
        for(int i = 0; i < array.length; i++) {
            int j  = array[i] & 0xff;
            if(j >= 0 && j < 256) {
                String characterCode = st[j];
                if(characterCode != null) codedString += characterCode;
            }
        }
        for (int i  = 0; i < frequencies.length; i++) {
            dos.writeInt(frequencies[i]);
        }
        writeToFile(codedString, outputPath);
    }

    public void huffmannDecompression(File file, String outputPath) throws IOException {
        DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(new File(outputPath)));
        int[] frekvenser = frequenciesDecompression(dis);
        byte[] array = new byte[dis.available()];
        dis.read(array);
        Node root = buildHuffmanTree(frekvenser);
        int padding = array[0];
        String[] st = new String[256];
        buildCode(st, root, "");
         for (int i = 1; i < array.length ; i++) {
            codedString += String.format("%8s", Integer.toBinaryString(array[i] & 0xFF)).replace(' ', '0');
         }
         String s = decompTekst(codedString, st, padding);
         dos.writeChars(s);
         dos.flush();
         dos.close();
    }

    private String decompTekst(String codedString, String[] st, int padding) {
        String returnere = "";
        String s = "";
        for (int i = 0; i < codedString.length() - padding; i++) {
            s += codedString.charAt(i);
            if(findLetter(s, st) >= 0) {
                char ja = (char)(findLetter(s, st));
                if(ja == '¸') ja = 'ø';
                if(ja == '¦') ja = 'æ';
                if(ja == '¥') ja = 'å';
                if(ja != 'Ã' )returnere += ja;
                s = "";
            }
        }
        return returnere;
    }

    private int findLetter(String s, String[] st) {
        for(int i = 0; i < st.length; i++) {
            if (s.equalsIgnoreCase(st[i])) return i;
        }
        return -1;
    }


    static void writeToFile(String outputStr, String outputFileName) {
        int strLen = outputStr.length();
        int padding = 0;
        if(strLen % 8 != 0) {
            padding =  8 - (strLen % 8);
        }
        if (padding > 0) {
            for (int i = 0; i < padding; i++) {
                outputStr += "0";
            }
        }

        byte[] toWrite = new byte[outputStr.length() / 8];
        for (int i = 0; i < outputStr.length() / 8; i++) {
            toWrite[i] = (byte) Integer.parseInt(
                    outputStr.substring(i * 8, (i + 1) * 8), 2);
        }

        FileOutputStream output;
        try {
            output = new FileOutputStream(outputFileName, true);
            output.write(padding);
            output.write(toWrite);
            output.flush();
            output.close();
        } catch (IOException e) {
            System.err.println("Feilskriving");
        }
    }

    public int[] frequenciesDecompression(DataInputStream dis) throws IOException {
        int [] frequencies = new int[256];
        for(int i = 0; i < frequencies.length; i++) {
            frequencies[i] = dis.readInt();
        }
        return frequencies;
    }


    private static void buildCode(String[] st, Node x, String s) {
        if (!x.isLeaf()) {
            buildCode(st, x.leftChild,  s + '0');
            buildCode(st, x.rightChild, s + '1');
        }
        else {
            st[x.character] = s;
        }
    }

    private Node buildHuffmanTree(int[] frequencies) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] != 0) {
                char c = (char) i;
                Node node = new Node(c, frequencies[i]);
                pq.add(node);
            }
        }
        if(pq.size() == 1) pq.add(new Node('\0', 1));
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node parent = new Node('\0', left.count + right.count, left, right);
            pq.add(parent);
        }
        return pq.poll();
    }

    private static int[] frequencies(byte[] array) {
        int[] frequencies = new int[256];
        for(byte b : array){
            frequencies[b & 0xff]++;
        }
        return frequencies;
    }

}
