import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.*;
import java.util.*;

public class Main {

    public static int[] generateRandom(){
        Random rand = new Random();
        int size = 50000;
        int[] array = new int[size];
        for (int i = 0; i < size; i++){
            array[i] = rand.nextInt(10000000);
        }
        return array;
    }

    public static int[] generateSorted(){
        int size = 10000000;
        int[] array = new int[size];
        for (int i = size; i > 0; i--){
            array[size - i] = i;
        }
        return array;
    }

    public static int[] generateOrderedSorted(){
        int size = 50000, temp, troca1, troca2;
        Random rand = new Random();
        int[] array = new int[size];
        double percent = 0.05;
        for (int i = 0; i < size; i++){
            array[i] = i;
        }
        for (int j = 0; j < size * percent; j++){
            troca1 = rand.nextInt(size);
            troca2 = rand.nextInt(size);
            temp = array[troca1];
            array[troca1] = array[troca2];
            array[troca2] = temp;
        }
        return array;
    }

    public static void main(String[] args){
        PrintWriter out = new PrintWriter(System.out);
        int[] array = generateOrderedSorted();

        long inicio = System.nanoTime();

        radixsort(array, array.length);

        long fim = System.nanoTime();
        long tempo = (fim - inicio);

        /*
        for (int j : array) {
            System.out.println(j);
        }
        */

        out.println("Normal: " + tempo + " ns.");
        out.close();
    }

    public static void countSort(int arr[], int n, int exp){
        int[] output = new int[n];
        int i;
        int[] count = new int[10];

        for (i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;

        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }

    public static void radixsort(int[] array, int len){
        int bigger = array[0];

        for (int i = 1; i < len; i++) {
            if (array[i] > bigger) {
                bigger = array[i];
            }
        }

        for (int exp = 1; bigger / exp > 0; exp *= 10)
            countSort(array, len, exp);
    }
}