package elementaryMath

import TimingExtension
import TreeNode
import getMethodName
import log
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation

@ExtendWith(TimingExtension::class)
@TestMethodOrder(OrderAnnotation::class)
internal class Summation {

    companion object {
        init {
            fun fnR(x: Int): Int = if (x == 0) 0 else fnR(x - 1) + x
            "*** ${getMethodName()} ok ${fnR(66)}".log()
        }

        @JvmStatic
        @BeforeAll
        fun setup() {
            "*** ${getMethodName()}\n".log()
        }

        @JvmStatic
        @AfterAll
        fun tearDown() {
            "\n*** ${getMethodName()}".log()
        }

    }

    @Order(0) // 1st function call will take more time to run
    @Test
    fun warmUp() {
        loop(999)
        recursion(999)
        "** ${getMethodName()} done".log()
    }

    //------------------------

    private fun fn(n: Int) = n

    private fun loop(h: Int) {
        var ans = 0
        for (i in 1..h) {
            val f = fn(i)
            ans += f
            //"fn($i) -> $f".log()
        }

        "# ${getMethodName()}($h) = $ans".log()
    }

    private fun recursion(h: Int) {

        fun fnR(x: Int): Int = if (x == 0) 0 else fnR(x - 1) + fn(x)
        val ans = fnR(h)

        "# ${getMethodName()}($h) = $ans".log()
    }

    @Test
    fun testLoop() {
        "\n${getMethodName()} begin".log()
        loop(10)
        loop(11000)
        loop(15000)
        "end".log()
    }

    @Test
    fun testRecursion() {
        "\n${getMethodName()} begin".log()
        recursion(10)
        recursion(11000)
        recursion(15000)
        //recursion(16000) //java.lang.StackOverflowError
        "end".log()
    }

    @Test
    fun dataStructure() {

        val a = sortedSetOf<Int>()
        a.add(1)
        a.remove(2)

        val b = sortedMapOf(1 to 2)
        b[2] = 3 // = b.put(2,3)
        b.remove(2)

        //---------
        val d = arrayOf(1, 2) // Array<Int>
        d[1] = 2

        val e = IntArray(3)
        e[1] = 2

        //---------
        val c = arrayListOf<Int>()
        c.add(3)
        c[0] = 1
        c.add(1, 3)

        val f = mutableListOf(1, 2)
        f.add(3)
        f[3] = 0
        f.add(1, 3) //insert

        val g = ArrayDeque<Int>()
        g.add(1)
        g[3] = 2
        g.add(1, 3)
        g.addFirst(1)
        g.addLast(9)
    }
}

internal class IteratorTest0 {

    @Test
    fun run() {

        // fun returns a sequence
        println(fibSeq().take(6).toList()) //[0, 1, 1, 2, 3, 5]
        println(fibSeq().take(6).toList()) //[0, 1, 1, 2, 3, 5]

        // fun returns an iterator
        fibItFn().apply { repeat(6) { print("${next()}, ") } }; println() //0, 1, 1, 2, 3, 5,
        fibItFn().apply { repeat(6) { print("${next()}, ") } }; println() //0, 1, 1, 2, 3, 5,

        // access the same iterator
        repeat(6) { print("${fibIt.next()}, ") }; println() //0, 1, 1, 2, 3, 5,
        repeat(6) { print("${fibIt.next()}, ") }; println() //8, 13, 21, 34, 55, 89,
    }

    private fun fibSeq() = sequence {
        fib()
    }

    private fun fibItFn() = iterator {
        fib()
    }

    private val fibIt = iterator {
        fib()
    }

    private suspend fun SequenceScope<Int>.fib() {
        var terms = Pair(0, 1)

        // this sequence is infinite
        while (true) {
            yield(terms.first)
            terms = Pair(terms.second, terms.first + terms.second)
        }
    }

}


internal class IteratorTest {

    @Test
    fun run() {

        val sq = generateSequence(1) {
            (it + 10).apply { println(this) }
        }

        val i = sq.iterator()
        repeat(9) {
            println("+++")
            println(" ~~ ${i.next()}")
            println("---")
        }

    }

    @Test
    fun run2() {
        // fun returns a sequence
        println(fibSeq().take(6).toList())
        println(fibSeq().take(6).toList())
    }

    var count = 0
    private fun fibSeq(): Sequence<Int> = sequence {

        if (count++ > 3) {
            println("stop: $count")
            return@sequence
        }
        repeat(9) {
            //count=0
            println("$it: $count")
            yield(it * 1000 + count)
            fibSeq()
//            println("+++")
//            yieldAll(fibSeq()) //StackOverflowError
//            println("---")
        }
    }
}

internal class IteratorTree {

    @Test
    fun run() {
        val t = TreeNode(3)
        t.left = TreeNode(1)
        t.right = TreeNode(5)
        BSTIterator(t).apply { while (hasNext()) println(" ~~ ${next()}") }
        BSTIterator(null).apply { while (hasNext()) println(" ~~ ${next()}") }
        println("------")
        BSTIterator2(t).apply { while (hasNext()) println(" ~~ ${next()}") }
        BSTIterator2(null).apply { while (hasNext()) println(" ~~ ${next()}") }
    }

    class BSTIterator(root: TreeNode?) {

        private suspend fun SequenceScope<Int>.fn(h: TreeNode) {
            println("fn(${h.`val`})")
            h.left?.apply { fn(this) }
            yield(h.`val`)
            h.right?.apply { fn(this) }
        }

        private val iter = iterator<Int> { root?.apply { fn(root) } }
        fun hasNext() = iter.hasNext()
        fun next() = iter.next()
    }

    class BSTIterator2(root: TreeNode?) {

        val q = ArrayDeque<Int>().also {
            fun TreeNode.dfs() {
                left?.dfs()
                it.addLast(`val`)
                right?.dfs()
            }
            root?.dfs()
        }

        fun hasNext() = !q.isEmpty()
        fun next() = q.removeFirst()
    }
}