import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Permutation {

    @Test
    public void go() {

        Character[] arr = {'A','B','C','D'};

        printCount=0;
        run(arr, arr.length);
    }

    public static <T> void run(T[] elements ,int n) {

        if(n == 1) printArray(elements);
        else {
            for(int i = 0; i < n-1; i++) {
                run(elements,n - 1);

                if(n % 2 == 0) swap(elements, i, n-1);
                else swap(elements, 0, n-1);

            }
            run(elements,n - 1);
        }
    }

    // helper methods:
    private  static <T>  void swap(T[] input, int a, int b) {
        T tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }

    private  static  int printCount;
    private  static <T>  void printArray(T[] input) {
        for (T t : input) out.print(t);
        out.printf("  (%d)\n", printCount++);
    }
}

class Combination {

    @Test
    public void go() {
        String[] arr = {"A","B","C","D","E","F"};

        combinations(arr, 2);
        out.println("---------------");
        combinations(arr, 3);

    }

    static void combinations(String[] arr, int len) {
        combinations(arr, len, 0, new String[len]);
    }

    static void combinations(String[] arr, int len, int startPosition, String[] result){
        if (len == 0){
            out.println(Arrays.toString(result));
            return;
        }
        for (int i = startPosition; i <= arr.length-len; i++){
            result[result.length - len] = arr[i];
            combinations(arr, len-1, i+1, result);
        }
    }
}