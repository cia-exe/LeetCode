import org.junit.jupiter.api.Test
import java.util.*

internal class Solution410 {
    @Test
    fun go() {
//        Input: nums = [7,2,5,10,8], m = 2
//        Output: 18
//        Explanation:
//        There are four ways to split nums into two subarrays.
//        The best way is to split it into [7,2,5] and [10,8],
//        where the largest sum among the two subarrays is only 18.
        println(splitArray(intArrayOf(7, 2, 5, 10, 8), 2))
    }

    fun splitArray(nums: IntArray, g: Int): Int {
        var s = Int.MIN_VALUE
        var e = 0
        var m: Int // start , end, mid
        for (n in nums) {
            if (s < n) s = n
            e += n
        }

        var sp_num = 0
        var sp_sum = 0
        fun spilt(nums: IntArray, max: Int) {
            sp_num = 1
            sp_sum = Int.MIN_VALUE
            var sum = 0
            for (n in nums) if (sum + n > max) {
                sp_num++
                if (sp_sum < sum) sp_sum = sum
                sum = n
            } else sum += n
            if (sp_sum < sum) sp_sum = sum
        }

        // spilt(nums,18); // for debug
        // return sp_num*1000 + sp_sum;
        while (s < e) {
            m = (s + e) / 2
            spilt(nums, m)
            System.out.printf("%d,%d,%d,%d,%d%n", s, e, m, sp_num, sp_sum)
            if (sp_num <= g) e = sp_sum // m is too large
            else s = m + 1 // m is too small, exclude it //m+1=(s+e)/2, m+1=s/2+e/2, s/2=m+1-e/2, s=2m+2-e
        }
        return e
    }
}

internal class Solution347 {
    @Test
    fun go() {
        println(topKFrequent(intArrayOf(40, 30, 20, 30, 20, 30, 10, 20, 30, 40, 50), 2).contentToString())
    }

    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val map = HashMap<Int, Int>()
        for (i in nums) map[i] = map.getOrDefault(i, 0) + 1
        val vks = ArrayList<Array<Int>>()
        for ((key, value) in map) vks.add(arrayOf(value, key))
        vks.sortWith { a, b -> b[0] - a[0] } // sort by desc
        val r = IntArray(k)
        for (i in 0 until k) r[i] = vks[i][1]
        return r
    }
}

internal class Solution347a {
    @Test
    fun go() {
        println(topKFrequent(intArrayOf(40, 40, 40, 40, 30, 30, 30, 20, 20, 50), 3).contentToString())
    }

    fun topKFrequent(nums: IntArray, _k: Int): IntArray {
        var k = _k
        val map = HashMap<Int, Int>()
        for (i in nums) map[i] = map.getOrDefault(i, 0) + 1
        val q = PriorityQueue<Array<Int>> { a, b -> a[0] - b[0] } // min heap
        for (i in 0 until k) q.offer(arrayOf(-1, -1))
        for ((key, value) in map) {
            q.offer(arrayOf(value, key))
            q.poll()
        }
        val r = IntArray(k)
        //for(var x:q) r[i++]=x[1]; // iteration q is un-ordered!!
        while (q.size > 0) r[--k] = q.poll()[1]
        return r
    }
}

internal class Solution692 {
    @Test
    fun go() {
        // ["the","day","is","sunny","the","the","the","sunny","is","is"] 4
        // Expected ["the","is","sunny","day"]
        // ["i","love","leetcode","i","love","coding"] 3
        // Expected ["i","love","coding"]
        println(topKFrequent(arrayOf("the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is", "cday"), 4))
        println(topKFrequent(arrayOf("i", "love", "leetcode", "i", "love", "coding"), 4))
        println(topKFrequent(arrayOf("a", "b", "c", "c", "b", "a", "d"), 3))
    }

    fun topKFrequent(words: Array<String>, k: Int): List<String> {
        val map = HashMap<String, Int>()
        for (i in words) map[i] = map.getOrDefault(i, 0) + 1

        //Sort the words with the same frequency by their lexicographical order.
        val q = PriorityQueue<Pair<String, Int>>() // min heap
        { a, b ->
            if (a.second == b.second) b.first.compareTo(a.first) // reverse compare so that 'a' > 'b'
            else a.second.compareTo(b.second)
        }

        for (i in 0 until k) q.offer(Pair("?", -1))
        for ((key, value) in map) {
            q.offer(Pair(key, value))
            q.poll()
            //out.println(q);
        }
        val r = ArrayDeque<String>()
        //for(var x:q) r.offerFirst(x.getFirst()); // iteration q is un-ordered!!
        while (q.size > 0) r.offerFirst(q.poll().first)
        return ArrayList(r)
    }
}

internal class Solution1260 {
    @Test
    fun go() {
        println(
            shiftGrid(
                arrayOf(
                    intArrayOf(1, 2, 3, 4, 5),
                    intArrayOf(11, 12, 13, 14, 15),
                    intArrayOf(21, 22, 23, 24, 25)
                ), 3
            )
        )
    }

    fun shiftGrid(grid: Array<IntArray>, _k: Int): List<List<Int>> {

        //var g=new ArrayList<LinkedList<Integer>>();
        var k = _k
        val g = ArrayList<List<Int>>()
        for (ints in grid) {
            val l = LinkedList<Int>()
            for (i in ints) l.add(i)
            g.add(l)
        }
        val q = ArrayDeque<Int>()
        while (k-- > 0) {
            for (l in g) q.offer((l as LinkedList<Int>).pollLast())
            q.offerFirst(q.pollLast())
            for (l in g) (l as LinkedList<Int>).offerFirst(q.poll())
        }
        return g

//        List<List<Integer>> gg=new ArrayList<List<Integer>>();
//        for(var l:g) gg.add(l);
//        return gg;
    }
}

internal class Solution17 {
    @Test
    fun go() {
//        0 <= digits.length <= 4
//        digits[i] is a digit in the range ['2', '9'].
        val a = letterCombinations("234")
        println(a.size)
        for (i in a) println(i)
    }

    val map = arrayOf(
        charArrayOf('a', 'b', 'c'),
        charArrayOf('d', 'e', 'f'),
        charArrayOf('g', 'h', 'i'),
        charArrayOf('j', 'k', 'l'),
        charArrayOf('m', 'n', 'o'),
        charArrayOf('p', 'q', 'r', 's'),
        charArrayOf('t', 'u', 'v'),
        charArrayOf('w', 'x', 'y', 'z')
    )

    fun letterCombinations(digits: String): List<String> {
        val ans = ArrayList<String>()
        if (digits.isEmpty()) return ans
        val input = digits.toCharArray()
        val sb = CharArray(digits.length)

        fun dfs(level: Int) {
            if (level == input.size) {
                ans.add(String(sb))
                return
            }
            for (d in map[input[level] - '2']) {
                sb[level] = d
                dfs(level + 1)
            }
        }
        dfs(0)
        return ans
    }


}