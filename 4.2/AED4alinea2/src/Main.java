import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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

    public static int[] lerInputs(){
        InputReader in = new InputReader(System.in);
        int size = in.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++){
            array[i] = in.nextInt();
        }
        return array;
    }

    public static void main(String[] args){
        int[] array = lerInputs();
        quickSort(array, 0, array.length - 1);
        for (int j : array) {
            System.out.println(j);
        }
    }

    public static void quickSort(int[] array, int bottom, int top){
        int temp;

        if (bottom < top) {
            int pivot = array[bottom + (top - bottom) / 2];
            int i = bottom, j = top;

            while (i <= j) {

                while (array[i] < pivot) {
                    i++;
                }

                while (pivot < array[j]) {
                    j--;
                }

                if (i <= j) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                    i++;
                    j--;
                }
            }

            if (bottom < j) {
                quickSort(array, bottom, j);
            }
            if (top > i) {
                quickSort(array, i, top);
            }
        }
    }
}
