package elementaryMath

import TimingExtension
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
}