import java.io.FileNotFoundException;
import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        HashClosed hash = Concert.registerCrowd("C:\\Users\\1\\Desktop\\Example2\\input2.txt");
//        System.out.println(hash.getSize());
//        HashOpen hash1 = new HashOpen(5);
        HashClosed h2 = new HashClosed(0);
        int[] arrived = Concert.reception("C:\\Users\\1\\Desktop\\Example2\\input1.txt", hash);
        int avg = Concert.reception_AverageSteps("C:\\Users\\1\\Desktop\\Example2\\input1.txt", hash);
        int[] steps1 = Concert.seatingArrangement(arrived, hash, 1);
        int[] steps2 = Concert.seatingArrangement(arrived, hash, 2);
        System.out.println(Arrays.toString(steps1));
        System.out.println(Arrays.toString(steps2));
        System.out.println(avg);
        System.out.println(Arrays.toString(arrived));
        System.out.println(Arrays.toString(hash.getNodesSize()));

    }
}