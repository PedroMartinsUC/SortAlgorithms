import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.*;
import java.util.*;

public class Main {
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static int[] lerInputs() {
        InputReader in = new InputReader(System.in);
        int size = in.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = in.nextInt();
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = lerInputs();
        radixsort(array, array.length);
        for (int j : array) {
            System.out.println(j);
        }
    }

    public static void countSort(int[] array, int len, int exp){
        int[] output = new int[len];
        int[] count = new int[10];
        int i;

        for (i = 0; i < len; i++) {
            count[(array[i] / exp) % 10]++;
        }

        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (i = len - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }

        for (i = 0; i < len; i++) {
            array[i] = output[i];
        }
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