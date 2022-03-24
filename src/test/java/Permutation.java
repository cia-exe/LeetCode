import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Permutation {

    @Test
    public void go() {
        var x = 2;
        var x6 = 6 * x;
        assertEquals(6, x6 / x);

        out.println(this);
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