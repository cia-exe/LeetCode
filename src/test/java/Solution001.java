import kotlin.Pair;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SplitArray {

    @Test
    public void go() {
//        var x = 2;
//        var x6 = 6 * x;
//        assertEquals(6, x6 / x);

        out.println(splitArray(new int[]{7, 2, 5, 10, 8}, 2));
    }

    public int splitArray(int[] nums, int g) {

        int s = Integer.MIN_VALUE, e = 0, m; // start , end, mid
        for (var n : nums) {
            if (s < n) s = n;
            e += n;
        }

        // spilt(nums,18); // for debug
        // return sp_num*1000 + sp_sum;

        while (s < e) {
            m = (s + e) / 2;
            spilt(nums, m);
            out.printf("%d,%d,%d,%d,%d%n", s, e, m, sp_num, sp_sum);
            if (sp_num <= g) e = sp_sum;// m is too large
            else s = m + 1; // m is too small, exclude it //m+1=(s+e)/2, m+1=s/2+e/2, s/2=m+1-e/2, s=2m+2-e
        }

        return e;
    }

    int sp_num, sp_sum;

    void spilt(int[] nums, int max) {

        sp_num = 1;
        sp_sum = Integer.MIN_VALUE;
        int sum = 0;
        for (int n : nums)
            if ((sum + n) > max) {
                sp_num++;
                if (sp_sum < sum) sp_sum = sum;
                sum = n;
            } else sum += n;

        if (sp_sum < sum) sp_sum = sum;
    }
}


class TopKFrequent {

    @Test
    public void go() {
        out.println(Arrays.toString(topKFrequent(new int[]{40, 30, 20, 30, 20, 30, 10, 20, 30, 40, 50}, 2)));
    }

    public int[] topKFrequent(int[] nums, int k) {
        var map = new HashMap<Integer, Integer>();
        for (var i : nums) map.put(i, map.getOrDefault(i, 0) + 1);
        var vks = new ArrayList<Integer[]>();
        for (var e : map.entrySet()) vks.add(new Integer[]{e.getValue(), e.getKey()});
        vks.sort((a, b) -> b[0] - a[0]); // sort by desc
        var r = new int[k];
        for (int i = 0; i < k; i++) r[i] = vks.get(i)[1];

        return r;

    }
}

class TopKFrequent2 {

    @Test
    public void go() {
        out.println(Arrays.toString(topKFrequent(new int[]{40, 40, 40, 40, 30, 30, 30, 20, 20, 50}, 3)));
    }

    public int[] topKFrequent(int[] nums, int k) {
        var map = new HashMap<Integer, Integer>();
        for (var i : nums) map.put(i, map.getOrDefault(i, 0) + 1);
        var q = new PriorityQueue<Integer[]>((a, b) -> a[0] - b[0]); // min heap
        for (int i = 0; i < k; i++) q.offer(new Integer[]{-1, -1});
        for (var e : map.entrySet()) {
            q.offer(new Integer[]{e.getValue(), e.getKey()});
            q.poll();
        }
        var r = new int[k];
        //for(var x:q) r[i++]=x[1]; // iteration q is un-ordered!!
        while (q.size() > 0) r[--k] = q.poll()[1];
        return r;
    }
}

class TopKFrequentStr {

    @Test
    public void go() {
        // ["the","day","is","sunny","the","the","the","sunny","is","is"] 4
        // Expected ["the","is","sunny","day"]
        // ["i","love","leetcode","i","love","coding"] 3
        // Expected ["i","love","coding"]
        out.println(topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is", "cday"}, 4));
        out.println(topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 4));
        out.println(topKFrequent(new String[]{"a", "b", "c", "c", "b", "a", "d"}, 3));

    }

    public List<String> topKFrequent(String[] words, int k) {

        var map = new HashMap<String, Integer>();
        for (var i : words) map.put(i, map.getOrDefault(i, 0) + 1);

        //Sort the words with the same frequency by their lexicographical order.
        var q = new PriorityQueue<Pair<String, Integer>>(       // min heap
                (a, b) -> a.getSecond().equals(b.getSecond()) ?
                        b.getFirst().compareTo(a.getFirst()) // reverse compare so that 'a' > 'b'
                        : a.getSecond().compareTo(b.getSecond())
        );

        for (int i = 0; i < k; i++) q.offer(new Pair<>("?", -1));
        for (var e : map.entrySet()) {
            q.offer(new Pair<>(e.getKey(), e.getValue()));
            q.poll();
            //out.println(q);
        }
        var r = new ArrayDeque<String>();
        //for(var x:q) r.offerFirst(x.getFirst()); // iteration q is un-ordered!!
        while (q.size() > 0) r.offerFirst(q.poll().getFirst());
        return new ArrayList<>(r);
    }
}

class Solution1260j {

    @Test
    public void go() {
        out.println(shiftGrid(new int[][]{{1, 2, 3, 4, 5}, {11, 12, 13, 14, 15}, {21, 22, 23, 24, 25}}, 3));
    }

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {

        //var g=new ArrayList<LinkedList<Integer>>();
        var g = new ArrayList<List<Integer>>();
        for (var ints : grid) {
            var l = new LinkedList<Integer>();
            for (var i : ints) l.add(i);
            g.add(l);
        }

        var q = new ArrayDeque<Integer>();
        while (k-- > 0) {
            for (var l : g) q.offer(((LinkedList<Integer>) l).pollLast());
            q.offerFirst(q.pollLast());
            for (var l : g) ((LinkedList<Integer>) l).offerFirst(q.poll());
        }

        return g;

//        List<List<Integer>> gg=new ArrayList<List<Integer>>();
//        for(var l:g) gg.add(l);
//        return gg;

    }


}

class Solution17j {

    @Test
    public void go() {

//        0 <= digits.length <= 4
//        digits[i] is a digit in the range ['2', '9'].

        var a = letterCombinations("234");
        out.println(a.size());
        for (var i : a) out.println(i);
    }

    final char[][] map = new char[][]{
            {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'},
            {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}
    };

    char[] input;
    char[] sb;
    ArrayList<String> ans;

    public List<String> letterCombinations(String digits) {

        ans = new ArrayList<>();
        if (digits.isEmpty()) return ans;

        input = digits.toCharArray();
        sb = new char[digits.length()];
        dfs(0);
        return ans;
    }

    void dfs(int level) {
        if (level == input.length) {
            ans.add(String.valueOf(sb));
            return;
        }

        for (var d : map[input[level] - '2']) {
            sb[level] = d;
            dfs(level + 1);
        }
    }
}