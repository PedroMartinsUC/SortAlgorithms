import java.io.*;
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

    public static int log2(int N){
        return (int)(Math.log(N) / Math.log(2));
    }

    public static void main(String[] args){
        int[] array = lerInputs();
        ShellSort(array);
        OrdenaFinal(array);
        for (int j : array) {
            System.out.println(j);
        }
    }

    public static void ShellSort(int[] array){
        int start = (int) Math.floor(log2(array.length));
        int temp, count = 0, count2, gap;
        for (int i = start; i > 0; i--){
            gap = (int) Math.pow(2, i);
            for (int j = 0; j < gap && j < array.length - gap; j++){
                for (int k = count; k < array.length; k+=gap){
                    count2 = k;
                    for (int h = k - gap; h > 0; h-=gap){
                        if (array[count2] < array[h]){
                            temp = array[count2];
                            array[count2] = array[h];
                            array[h] = temp;
                            count2-=gap;
                        } else {
                            break;
                        }
                    }
                }
                count++;
            }
            count = 0;
        }
    }

    public static void OrdenaFinal(int[] array){
        int count, temp;
        for (int i = 0; i < array.length; i++){
            count = i;
            for (int j = i - 1; j >= 0; j--){
                if (array[count] < array[j]){
                    temp = array[count];
                    array[count] = array[j];
                    array[j] = temp;
                    count--;
                } else {
                    break;
                }
            }
        }
    }
}
