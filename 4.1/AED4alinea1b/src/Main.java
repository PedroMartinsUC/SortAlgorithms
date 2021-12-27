import java.io.*;
import java.util.Random;
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
        PrintWriter out = new PrintWriter(System.out);
        int[] array = generateRandom();
        long inicio = System.nanoTime();
        ShellSort(array);
        OrdenaFinal(array);
        long fim = System.nanoTime();
        long tempo = (fim - inicio);

        out.println(tempo + " ns.");
        out.close();


    }

    public static int[] generateRandom(){
        Random rand = new Random();
        int size = 1000;
        int[] array = new int[size];
        for (int i = 0; i < size; i++){
            array[i] = rand.nextInt(10000000);
        }
        return array;
    }

    public static int[] generateSorted(){
        int size = 100000;
        int[] array = new int[size];
        for (int i = size; i > 0; i--){
            array[size - i] = i;
        }
        return array;
    }

    public static int[] generateOrderedSorted(){
        int size = 10000, temp, troca1, troca2;
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

    public static int[] ShellSort(int[] array){
        int start = (int) Math.floor(log2(array.length));
        int temp, count = 0, count2, gap;
        for (int i = start; i > 0; i--){
            gap = (int) Math.pow(2, i);
            for (int j = 0; j < gap || j < array.length - gap; j++){
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
        return array;
    }

    public static int[] OrdenaFinal(int[] array){
        int count = 0;
        int temp = 0;
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
        return array;
    }
}
