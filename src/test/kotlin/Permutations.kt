import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*
import kotlin.collections.ArrayList

@ExtendWith(TimingExtension::class)
internal class Permutations {

    @Test
    fun go() {
        val r = permute(intArrayOf(1, 2, 3,4))
        println(r.size)
        for (o in r) println(o)
    }

    private fun permute(num: IntArray): List<List<Int>> {

        val ans = IntArray(num.size)
        val result = ArrayList<List<Int>>()
        fun dfs(arr: IntArray) {

            //println("dfs([${arr.joinToString()}], ${arr.size})")
            val len = arr.size
            if (len == 0) result.add(ans.toList())
            else for (i in 0 until len) {
                if (i > 0) arr[0] = arr[i].apply { arr[i] = arr[0] }  //swap
                ans[ans.size - len] = arr[0]
                dfs(arr.copyOfRange(1, len))
            }
        }
        dfs(num)
        return result
    }
}

@ExtendWith(TimingExtension::class)
internal class Combinations {

    @Test
    fun go() {

        fun test(n: Int, k: Int) {
            val r = combine(n, k)
            println("test ${n} get ${k} .....${r.size}")
            for (o in r) println(o)
        }

        test(4, 1)
        test(4, 2)
        test(4, 3)
        test(4, 4)
    }

    private fun combine(n: Int, k: Int): List<List<Int>> {

        val ans = IntArray(k)
        val result = ArrayList<List<Int>>()

        fun dfs(s: Int, l: Int) {
            if (l == k) result.add(ans.toList())
            else for (i in s + 1..n) {
                ans[l] = i
                dfs(i, l + 1)
            }
        }
        dfs(0, 0)
        return result
    }
}