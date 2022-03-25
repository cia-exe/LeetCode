import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Permutation {

    @Test
    public void testRun() {

        var r = permute(new int[]{1, 2, 3, 4});

        out.println(r.size());
        for(var o:r)
            out.println(o);
    }

    int len;
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] num) {
        len = num.length;
        var t=new Integer[len];
        for(int i=0;i<len;i++) t[i]=num[i];
        dfs(t, 0);
        return result;
    }

    private void dfs(Integer[] arr, int level) {

        //out.printf("dfs(%s,%d)\n", Arrays.toString(arr), level);
        arr = Arrays.copyOf(arr, arr.length);

        int next = level + 1;
        if (next == len) {
            result.add(Arrays.asList(arr)); // changes the list/arr will be visible in the arr/list!
            return;
        }

        dfs(arr, next);
        for (int i = next; i < len; i++) {

            //swap
            int t=arr[level];
            arr[level]=arr[i];
            arr[i]=t;

            dfs(arr, next);
        }
    }
}


class PermutationX {

    @Test
    public void go() {

        Character[] arr = {'A', 'B', 'C', 'D'};

        printCount = 0;
        run(arr, arr.length);
    }

    public static <T> void run(T[] elements, int n) {

        if (n == 1) printArray(elements);
        else {
            for (int i = 0; i < n - 1; i++) {
                run(elements, n - 1);

                if (n % 2 == 0) swap(elements, i, n - 1);
                else swap(elements, 0, n - 1);

            }
            run(elements, n - 1);
        }
    }

    // helper methods:
    private static <T> void swap(T[] input, int a, int b) {
        T tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }

    private static int printCount;

    private static <T> void printArray(T[] input) {
        for (T t : input) out.print(t);
        out.printf("  (%d)\n", printCount++);
    }
}

class Combination {

    @Test
    public void go() {
        String[] arr = {"A", "B", "C", "D", "E", "F"};

        combinations(arr, 2);
        out.println("---------------");
        combinations(arr, 3);

    }

    static void combinations(String[] arr, int len) {
        combinations(arr, len, 0, new String[len]);
    }

    static void combinations(String[] arr, int len, int startPosition, String[] result) {
        if (len == 0) {
            out.println(Arrays.toString(result));
            return;
        }
        for (int i = startPosition; i <= arr.length - len; i++) {
            result[result.length - len] = arr[i];
            combinations(arr, len - 1, i + 1, result);
        }
    }
}