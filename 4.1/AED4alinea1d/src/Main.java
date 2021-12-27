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
        ShellSort4(array);
        OrdenaFinal(array);
        long fim = System.nanoTime();
        long tempo = (fim - inicio);

        out.println(tempo + " ns.");
        out.close();
    }

    public static int[] generateRandom(){
        Random rand = new Random();
        int size = 10000000;
        int[] array = new int[size];
        for (int i = 0; i < size; i++){
            array[i] = rand.nextInt(10000000);
        }
        return array;
    }

    public static int[] generateSorted(){
        int size = 1000;
        int[] array = new int[size];
        for (int i = size; i > 0; i--){
            array[size - i] = i;
        }
        return array;
    }

    public static int[] generateOrderedSorted(){
        int size = 1000, temp, troca1, troca2;
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

    //Basic Shell Sort
    public static int[] BasicShellSort(int[] array){
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
        return array;
    }

    // (3^k - 1) / 2 OPERATIONAL
    public static void ShellSort1(int[] array){
        int times = Sort1(array);
        int temp, count = 0, count2, gap;
        for (int i = times; i > 0; i--){
            gap = (int) (Math.pow(3, i + 1) - 1) / 2;
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

    // 4^k + 3 * 2^(k-1) + 1 OPERATIONAL
    public static void ShellSort2(int[] array){
        int times = Sort2(array);
        int temp, count = 0, count2, gap;
        for (int i = times; i > 0; i--){
            gap = (int) Math.pow(4, i) + 3 * (int) Math.pow(2, i - 1) + 1;
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

    // 2^k + 1 OPERATIONAL
    public static void ShellSort3(int[] array){
        int times = Sort3(array);
        int temp, count = 0, count2, gap;
        for (int i = times; i > 0; i--){
            gap = (int) Math.pow(2, i) + 1;
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

    // LAST KNOWN
    public static void ShellSort4(int[] array){
        int times = Sort4(array);
        int temp, count = 0, count2, gap;
        for (int i = times; i > 1; i--){
            gap = (int) (0.2 * (9 * Math.pow(2.25, i - 1) - 4)) + 1;
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

    public static int Sort1(int[] array){
        int num = 0, count = 0;

        for (int i = 2; i < array.length; i++){
            num = (int) (Math.pow(3, i) - 1) / 2;
            if (num > array.length){
                break;
            }
            count++;
        }
        return count;
    }

    public static int Sort2(int[] array){
        int num = 0, count = 1;

        for (int i = 2; i < array.length; i++){
            num = (int) Math.pow(4, i) + 3 * (int) Math.pow(2, i - 1) + 1;
            if (num > array.length){
                break;
            }
            count++;
        }
        return count;
    }

    public static int Sort3(int[] array){
        int num = 0, count = 1;

        for (int i = 2; i < array.length; i++){
            num = (int) Math.pow(2, i) + 1;
            if (num > array.length){
                break;
            }
            count++;
        }
        return count;
    }

    public static int Sort4(int[] array){
        int num = 0, count = 1;

        for (int i = 2; i < array.length; i++){
            num = (int) (0.2 * (9 * Math.pow(2.25, i - 1) - 4));
            if (num > array.length){
                break;
            }
            count++;
        }
        return count;
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
