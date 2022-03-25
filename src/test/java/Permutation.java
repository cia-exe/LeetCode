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

    public List<List<Integer>> permute(int[] nums) {
        len = nums.length;
        dfs(nums, 0);
        return result;
    }

    private void dfs(int[] nums, int begin) {

        nums = Arrays.copyOf(nums, nums.length);

        //out.printf("%d:%s\n", begin, Arrays.toString(nums));
        int begin1 = begin + 1;
        if (begin1 == len) {
            var l = new ArrayList<Integer>();
            for (var o : nums) l.add(o);
            result.add(l);
            return;
        }

        dfs(nums, begin1);
        for (int i = begin1; i < len; i++) {
            swap(nums, begin, i);
            //out.printf("** %d:%s\n", begin, Arrays.toString(nums));
            dfs(nums, begin1);
        }
    }

    // helper methods:
    private static void swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }

    private static int printCount;

    private static <T> void printArray(T[] input) {
        for (T t : input) out.print(t);
        out.printf("  (%d)\n", printCount++);
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