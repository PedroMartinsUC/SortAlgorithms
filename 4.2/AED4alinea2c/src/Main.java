import java.io.PrintWriter;
import java.util.Random;

public class Main {
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
        int size = 10000000;
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

    public static void main(String[] args){
        PrintWriter out = new PrintWriter(System.out);
        int[] array = generateSorted();

        long inicio = System.nanoTime();

        quickSort(array, 0, array.length - 1);
        //improvedQuickSort(array, 0, array.length - 1);

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

    public static void quickSort(int[] array, int bottom, int top){
        int temp;

        if (bottom >= top){
            return;
        }
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

    public static void improvedQuickSort(int[] array, int bottom, int top){
        int temp, start = 100;

        if (bottom >= top){
            return;
        }
        if (top - bottom > start) {
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
                improvedQuickSort(array, bottom, j);
            }
            if (top > i) {
                improvedQuickSort(array, i, top);
            }
        } else {
            insertionSortA(array, bottom, top + 1);
        }
    }

    public static void insertionSortA(int[] array, int low, int high){
        int count, temp;
        for (int i = low + 1; i < high; i++){
            count = i;
            for (int j = i - 1; j >= low; j--){
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

