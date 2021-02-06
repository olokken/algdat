package Oblig7;

import java.io.File;
import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
        Huffman huffman = new Huffman();
        LZ77 lz = new LZ77();
        File file = new File("C:\\Dev\\algdat\\src\\kompHuffman.txt");
        File file2 = new File("C:\\Dev\\algdat\\src\\kompLZ77.txt");
        huffman.huffmannDecompression(file, "C:\\Dev\\algdat\\src\\utpakketHuffman.txt" );
        lz.decompress(file2, "C:\\Dev\\algdat\\src\\utpakketLZ77.txt");
    }

    //Outputpath er en fil etter LZ er unnagjort og outputPath 2 er en fil der det både er brukt LZ77 og Huffman
    static void komprimering(File file, String outputPath, String outputPath2) throws IOException {
        LZ77 lz = new LZ77();
        Huffman huffman = new Huffman();
        lz.compress(file, outputPath);
        File file2 = new File(outputPath);
        huffman.huffmannCompression(file2, outputPath2);
    }

    static void deKomprimering(File file, String outputPath, String outputPath2) throws IOException {
        LZ77 lz = new LZ77();
        Huffman huffman = new Huffman();
        huffman.huffmannDecompression(file,outputPath);
        File file2 = new File(outputPath);
        lz.decompress(file2, outputPath2);
    }
}
