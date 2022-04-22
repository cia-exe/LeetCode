import org.junit.jupiter.api.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import java.util.*
import java.util.AbstractMap.SimpleEntry

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
        nums.forEach {
            if (s < it) s = it
            e += it
        }

        var sp_num = 0
        var sp_sum = 0
        fun spilt(max: Int) {
            sp_num = 1
            sp_sum = Int.MIN_VALUE
            var sum = 0
            nums.forEach {
                if (sum + it > max) {
                    sp_num++
                    if (sp_sum < sum) sp_sum = sum
                    sum = it
                } else sum += it
            }
            if (sp_sum < sum) sp_sum = sum
        }

        // spilt(nums,18); // for debug
        // return sp_num*1000 + sp_sum;
        while (s < e) {
            m = (s + e) / 2
            spilt(m)
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
        nums.forEach { map[it] = map.getOrDefault(it, 0) + 1 }
        return map.toList().sortedByDescending { it.second }.take(k).map { it.first }.toIntArray()
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
        nums.forEach { map[it] = map.getOrDefault(it, 0) + 1 }
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

@ExtendWith(TimingExtension::class)
internal class TimingExtensionTests {
    @Test
    @Throws(Exception::class)
    fun sleep20ms() {
        Thread.sleep(20)
    }

    @Test
    @Throws(Exception::class)
    fun sleep50ms() {

        // Arrays are optimized for primitives: there are separate IntArray, DoubleArray, CharArray etc.
        // which are mapped to Java primitive arrays (int[], double[], char[]),

        val arr = arrayOf(1, 2, 3) // Array<Int>
        val arr3 = Array(3) { it } // Array<Int> [0, 1, 2]
        println("arr3: ${arr3.contentToString()}")
        println(arr) //[Ljava.lang.Integer;@17f62e33
        println(arr.contentToString()) // [1, 2, 3]

        val i = intArrayOf(1, 2, 3) // IntArray
        val f = floatArrayOf(1f, 2f, 3f)
        val c = charArrayOf('1', '2', '3')
        println(i) //[I@76b1e9b8
        println(i.contentToString()) // [1, 2, 3]

        val l = listOf(1, 2, 3)
        println(l) // [1, 2, 3]
        //println(l.contentToString()) // list doesn't have contentToString

        val lp = listOf(1 to 10, 2 to 20, 3 to 30)
        println(lp) // [(1, 10), (2, 20), (3, 30)]

        Thread.sleep(50)
    }
}

@TestMethodOrder(OrderAnnotation::class)
@ExtendWith(TimingExtension::class)
internal class Solution692 {

    @Test
    @Order(0) // 1st function call will take more time to run
    fun warmUp() {
        println("warm Up...")
        println(topKFrequent(arrayOf("a", "b", "c", "c", "b", "a", "d"), 3))
        println(topKFrequent0(arrayOf("a", "b", "c", "c", "b", "a", "d"), 3))
        println("warm Up...done")
    }

    // ["the","day","is","sunny","the","the","the","sunny","is","is"] 4
    // Expected ["the","is","sunny","day"]
    // ["i","love","leetcode","i","love","coding"] 3
    // Expected ["i","love","coding"]


    @Test
    @Order(3)
    fun go() {
        val f = ::topKFrequent
        println(f(arrayOf("the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is", "cday"), 4))
        println(f(arrayOf("i", "love", "leetcode", "i", "love", "coding"), 4))
        println(f(arrayOf("a", "b", "c", "c", "b", "a", "d"), 3))
    }

    @Test
    @Order(2)
    fun go0() {
        val f = ::topKFrequent0
        println(f(arrayOf("the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is", "cday"), 4))
        println(f(arrayOf("i", "love", "leetcode", "i", "love", "coding"), 4))
        println(f(arrayOf("a", "b", "c", "c", "b", "a", "d"), 3))
    }

    fun topKFrequent0(words: Array<String>, k: Int): List<String> {

        val counts = words.groupBy { it }.map { it.key to it.value.size }   // slower?

        //Sort the words with the same frequency by their lexicographical order.
        val q = PriorityQueue<Pair<String, Int>> { a, b ->          // min heap
            if (a.second == b.second) b.first.compareTo(a.first)    // compare reversely so that 'a' > 'b'
            else a.second.compareTo(b.second)
        }

        repeat(k) { q.offer(("?" to -1)) }
        counts.forEach { q.offer(it); q.poll() }
        return ArrayDeque<String>().apply { while (q.size > 0) offerFirst(q.poll().first) }.toList()
        //return q.map { it.key } // iteration q is un-ordered!!
    }

    fun topKFrequent(words: Array<String>, k: Int): List<String> {

        val counts = HashMap<String, Int>().apply { words.forEach { this[it] = getOrDefault(it, 0) + 1 } }
        //val counts = words.groupBy { it }.mapValues { it.value.count() } // slower ?

        //Sort the words with the same frequency by their lexicographical order.
        val q = PriorityQueue<Map.Entry<String, Int>> { a, b ->    // min heap
            if (a.value == b.value) b.key.compareTo(a.key)      // compare reversely so that 'a' > 'b'
            else a.value.compareTo(b.value)
        }

        repeat(k) { q.offer(SimpleEntry("?", -1)) } //error: unresolved reference: SimpleEntry on leetcode
        //q.addAll(buildMap { repeat(k) { put("??$it", -1) } }.entries)
        counts.forEach { q.offer(it); q.poll() }
        return ArrayDeque<String>().apply { while (q.size > 0) offerFirst(q.poll().key) }.toList()
    }
}

internal class Solution1260 {
    @Test
    fun go() {

        val data = arrayOf(
            intArrayOf(1, 2, 3, 4, 5),
            intArrayOf(11, 12, 13, 14, 15),
            intArrayOf(21, 22, 23, 24, 25)
        )
        println(shiftGrid(data, 3))
        println(shiftGrid4(data, 3))
        println(shiftGrid0(data, 3))
    }

    fun shiftGrid4(g: Array<IntArray>, _k: Int): List<List<Int>> { //5ms (92.64%)
        var k = _k
        val y = g.size
        val x = g[0].size
        val total = x * y
        k = (total - k % total) % total // check total=9, k=9
        val ans = ArrayList<List<Int>>(y)
        repeat(y) {
            val l = IntArray(x)
            repeat(x) {
                l[it] = g[k / x][k % x]
                k = (k + 1) % total
            }
            ans.add(l.toList())
        }
        return ans
    }

    fun shiftGrid(g: Array<IntArray>, _k: Int): List<List<Int>> { //3ms (98.84%)
        var k = _k
        val y = g.size
        val x = g[0].size
        val total = x * y
        k = (total - k % total) % total // check total=9, k=9
        val ans = ArrayList<List<Int>>(y)
        repeat(y) {
            val l = ArrayList<Int>(x)
            ans.add(l)
            repeat(x) {
                l.add(g[k / x][k % x])
                k = (k + 1) % total
            }
        }
        return ans
    }


    fun shiftGrid0(grid: Array<IntArray>, _k: Int): List<List<Int>> { //8ms (65%)
        var k = _k
        k %= (grid.size * grid[0].size)
        val g = grid.map { LinkedList(it.asList()) }
        val q = ArrayDeque<Int>()
        while (k-- > 0) {
            for (l in g) q.offer(l.pollLast())
            q.offerFirst(q.pollLast())
            for (l in g) (l.offerFirst(q.poll()))
        }
        return g
    }
}

internal class Solution17 {
    @Test
    fun go() {
//        0 <= digits.length <= 4
//        digits[i] is a digit in the range ['2', '9'].
        val a = letterCombinations("234")
        println("${a.size}\n${a.joinToString("\n")}")
        println(a) // [adg, adh, adi, aeg...]
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

class Solution289 {
    // in:       [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
    // expected: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]

    @Test
    fun go() {

        val s = arrayOf(intArrayOf(0, 1, 0), intArrayOf(0, 0, 1), intArrayOf(1, 1, 1), intArrayOf(0, 0, 0))
        println(s.joinToString { it.contentToString() })
        gameOfLife(s)
        println(s.joinToString { it.contentToString() })
    }

    fun gameOfLife(b: Array<IntArray>) {

        val h = b.size
        val w = b[0].size

        fun check(y: Int, x: Int) = if (x in 0 until w && y in 0 until h) b[y][x] and 1 else 0
        fun sum(y: Int, x: Int) = check(y, x - 1) + check(y, x + 1) +
                check(y - 1, x - 1) + check(y - 1, x) + check(y - 1, x + 1) +
                check(y + 1, x - 1) + check(y + 1, x) + check(y + 1, x + 1)

        repeat(h) { y ->
            repeat(w) { x ->
                val s = sum(y, x)
                val v = b[y][x]
                if (v and 1 == 1) {
                    if (s in 2..3) b[y][x] = v or 2
                } else if (s == 3) b[y][x] = v or 2
            }
        }
        repeat(h) { y -> repeat(w) { b[y][it].apply { b[y][it] = this ushr 1 } } }
    }
}

class Solution59 {
// 123
// 894
// 765

// 12
// 43

//  1  2  3  4
//  5  6  7  8
//  9 10 11 12
// 13 14 15 16

//  1  2  3  4
// 12 13 14  5
// 11 16 15  6
// 10  9  8  7

    //  1  2  3  4 5
    // 16 17 18 19 6
    // 15 24 25 20 7
    // 14 23 22 21 8
    // 13 12 11 10 9

//    Input: n = 3
//    Output: [[1,2,3],[8,9,4],[7,6,5]]

//    Input: n = 1
//    Output: [[1]]

    @Test
    fun go() {
        println(generateMatrix(1).joinToString { it.contentToString() })
        println(generateMatrix(2).joinToString { it.contentToString() })
        println(generateMatrix(3).joinToString { it.contentToString() })
        println(generateMatrix(4).joinToString { it.contentToString() })
        println(generateMatrix(5).joinToString { it.contentToString() })
    }

    fun generateMatrix(n: Int): Array<IntArray> {

        val dir = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(-1, 0))
        val ans = Array(n) { IntArray(n) }
        var idx = 0
        var x = -1
        var y = 0
        var d = dir[0]
        repeat(n * n) {
            val ny = y + d[0]
            val nx = x + d[1]
            if (nx in 0 until n && ny in 0 until n && ans[ny][nx] == 0) {
                x = nx; y = ny
            } else { // If the next position is out of range or its value has already been set
                d = dir[++idx % 4]
                x += d[1]; y += d[0]
            }
            ans[y][x] = it + 1
        }
        return ans
    }
}


class Solution19 {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }


    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        var h: ListNode? = ListNode(0).apply { next = head }
        var r = head
        var l = head
        repeat(n) { r = r?.next }
        while (r?.next != null) {
            r = r?.next
            l = l?.next
        }
        l?.next = r
        return head
    }
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution700 {

    fun searchBST(root: TreeNode?, v: Int): TreeNode? = root?.`val`?.run {
        when {
            this == v -> root
            this > v -> searchBST(root.left, v)
            else -> searchBST(root.right, v)
        }
    }
}

class Solution1383 {
    @Test
    fun go() {

        val speed = intArrayOf(2, 10, 3, 1, 5, 8)
        val efficiency = intArrayOf(5, 4, 3, 9, 7, 2)
        println(maxPerformance(speed.size, speed, efficiency, 2)) // 60
        println(maxPerformance(3, intArrayOf(3, 8, 2), intArrayOf(2, 7, 1), 2)) // 56
    }

    fun maxPerformance(n: Int, speed: IntArray, efficiency: IntArray, k: Int): Int {

        val data = speed.mapIndexed { idx, s -> s to efficiency[idx] }.sortedByDescending { it.second }
        val hp = PriorityQueue<Int>().apply { repeat(k) { offer(0) } }
        var sum = 0L
        var ans = 0L
        data.forEach {
            sum = sum + it.first - hp.poll()
            hp.offer(it.first)
            ans = ans.coerceAtLeast(it.second * sum)
        }
        return (ans % 1000000007L).toInt() // 1e9+7 is a Double!!!
    }
}


class Solution110XXX { // wrong! it is a completed bin tree

// lv leaf
// 0  0  X!!
// 1  1  2^0
// 2  2  2^1
// 3  4  2^2
// 4  8  2^3
//
// leaf=2^(lv-1)
// inner=leaf-1;
// nodes= inner+leaf= 2*leaf-1 // completed tree
// balance nodes is in inner+1..nodes
// -> leaf..2*leaf-1
// -> 2^(lv-1) . 2^lv-1

    fun isBalanced(root: TreeNode?): Boolean {
        if (root == null) return true
        var deep = 0
        var count = 0
        fun TreeNode.dfs(lvl: Int) {

            if (deep < lvl) deep = lvl
            count++
            left?.dfs(lvl + 1)
            right?.dfs(lvl + 1)
        }
        root.dfs(1)
        val leaf = Math.pow(2.0, deep - 1.0).toInt()
        return count in leaf until 2 * leaf // = count in leaf..2*leaf-1
    }
}

class Solution110 {
    fun isBalanced(root: TreeNode?): Boolean {
        var balanced = true;
        fun TreeNode?.depth(): Int {
            if (!balanced) return 0
            if (this == null) return 0
            val l = left.depth()
            val r = right.depth()
            if (Math.abs(l - r) > 1) balanced = false
            return Math.max(l, r) + 1
        }

        root?.depth()
        return balanced
    }
}

class Solution94 {
    fun inorderTraversal(root: TreeNode?) =
        mutableListOf<Int>().also {
            fun TreeNode.dfs() {
                left?.dfs()
                it.add(`val`)
                right?.dfs()
            }
            root?.dfs()
        }
}

// This is NOT a Binary 'Search' Tree!!! So it's left might larger than right!!!
// e.g:    3
//        3 4
//      8 3 4 8
//
// root.val = min(root.left.val, root.right.val)
// so each node must <= all it's children, so the root is the smallest.
// compare 3 vals
// 1. val -> same as min(l.val, r.val)
// 2. if(val!=sub.val) sub.val
// 3. else 2ndMin(x) both l and r might equal!

// thus 1 is the 1st min, min(2,3) is the 2nd min
// ps: 1 <= Node.val <= 231 - 1, we can't use MAX_VAL if not found
class Solution671 {

    @Test
    fun go() {
        findSecondMinimumValue(null)
    }

    fun findSecondMinimumValue(root: TreeNode?): Int = root.run {
        if (this == null || left == null) return -1 // node has two or zero sub-node.
        val l = left!!.`val`.let { if (it == `val`) findSecondMinimumValue(left) else it }
        val r = right!!.`val`.let { if (it == `val`) findSecondMinimumValue(right) else it }
//        val l = if (left!!.`val` == `val`) findSecondMinimumValue(left) else left!!.`val`
//        val r = if (right!!.`val` == `val`) findSecondMinimumValue(right) else right!!.`val`

        return if (l < 0) r else if (r < 0) l else Math.min(l, r)

//        val l=(left!!.`val` == `val`) findSecondMinimumValue(left).let {
//            if (it < 0) right!!.`val`
//            else Math.min(it, right!!.`val`)
//        }
//        else  findSecondMinimumValue(right).let {
//            if (it < 0) left!!.`val`
//            else Math.min(it, left!!.`val`)
//        }
    }
}


class Solution671X {
    // This is NOT a Binary 'Search' Tree!!! So it's left might larger than right!!!

    fun findSecondMinimumValue(root: TreeNode?): Int {
        var ans = 0
        val set = mutableSetOf<Int>()
        fun TreeNode.dfs() {
            left?.dfs()
            if (ans > 0) return
            set.add(`val`)
            if (set.size == 2) {
                ans = `val`
                return
            }
            right?.dfs()
        }
        root?.dfs()
        return ans
    }
}

class Solution814 {
    fun pruneTree(root: TreeNode?): TreeNode? {

        // return sum of val
        fun TreeNode.dfs(): Int = `val` +
                (left?.dfs()?.also { if (it == 0) left = null } ?: 0) +
                (right?.dfs()?.also { if (it == 0) right = null } ?: 0)

        return if (root?.dfs() == 0) null else root
    }
}

class Solution99 {
// try
// 2134  ->21  i>i+1: 1
// 3214  ->31  i>i+1: 21
// 1324  ->32  i>i+1: 2
// 4231  ->41  i>i+1: 21

    fun recoverTree(root: TreeNode?): Unit {
        var n1: TreeNode? = null
        var n2: TreeNode? = null
        var n3: TreeNode? = null
        var prev = TreeNode(Int.MIN_VALUE)

        fun TreeNode.dfs() {
            left?.dfs()
            if (prev.`val` > `val`) {
                if (n1 == null) {
                    n1 = prev
                    n2 = this
                } else n3 = this
            }
            prev = this
            right?.dfs()
        }

        root?.dfs()
        if (n3 == null) n3 = n2
        val t = n1!!.`val`; n1?.`val` = n3!!.`val`; n3?.`val` = t //swap n1 and n3
    }
}

class Solution705 {

    class MyHashSet() {
        // 0 <= key <= 10^6
        // At most 10^4 calls will be made to add, remove, and contains.

        private val table = IntArray(1000000 / Int.SIZE_BITS + 1)
        fun add(key: Int) {
            val i = key / Int.SIZE_BITS
            val x = table[i]
            table[i] = 1 shl key % Int.SIZE_BITS or x
        }

        fun remove(key: Int) {
            val i = key / Int.SIZE_BITS
            val x = table[i]
            table[i] = (1 shl key % Int.SIZE_BITS).inv() and x
        }

        fun contains(key: Int): Boolean {
            val i = key / Int.SIZE_BITS
            val x = table[i]
            return 1 shl key % Int.SIZE_BITS and x != 0 // x can < 0!
        }
    }
}

class Solution706 {

    @Test
    fun run() {
//        ["MyHashMap","put","put","get","get","put","get","remove","get"]
//        [[],[1,1],[2,2],[1],[3],[2,1],[2],[2],[2]]
//        Expected [null,null,null,1,-1,null,1,null,-1]

        MyHashMap().apply {
            put(1, 1)
            put(2, 2)
            println(get(1)) //1
            println(get(3)) //-1
            put(2, 1)
            println(get(2)) //1
            remove(2)
            println(get(2)) //-1
        }
        // java int=32, long=64
        // c++:
        // unix:LP64 int=32, long=longlong=pointer=64
        // win:LLP64 int=long=32, longlong=pointer=64
        println(Int.SIZE_BITS)
    }

    //0 <= key, value <= 10^6
    //At most 10^4 calls will be made to put, get, and remove.

    class MyHashMap {

        private val size = 0x1fff //13bits= 8192
        private val slot = arrayOfNulls<LinkedList<Pair<Int, Int>>>(size)

        fun put(key: Int, value: Int) {
            val idx = key xor key shr size xor key shr 2 * size
            slot[idx].apply {
                if (this == null) slot[idx] = LinkedList(listOf(key to value))
                else {
                    removeIf { key == it.first }
                    add(key to value)
                }
            }
        }

        fun get(key: Int): Int {
            val idx = key xor key shr size xor key shr 2 * size
            return slot[idx]?.firstOrNull { it.first == key }?.second ?: -1
        }

        fun remove(key: Int) {
            val idx = key xor key shr size xor key shr 2 * size

            slot[idx]?.apply { //1633ms 6.5%, 1569ms 6.5%
                iterator().apply {
                    while (hasNext()) { // remove FIRST elements if satisfy
                        if (next().first == key) {
                            remove()
                            break
                        }
                    }
                }
                if (size == 0) slot[idx] = null
            }

//            slot[idx]?.apply { //1718ms 6.5%, 1260ms 9.1%
//                removeIf { it.first == key } // remove ALL elements if satisfy
//                if (size == 0) slot[idx] = null
//            }
        }
    }

    //=========
    class MyHashMap0 { //419ms 92%
        private val map = mutableMapOf<Int, Int>()
        fun put(key: Int, value: Int) = map.put(key, value)
        fun get(key: Int): Int = map.getOrDefault(key, -1)
        fun remove(key: Int) = map.remove(key)
    }
}