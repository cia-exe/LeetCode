import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TimingExtension::class)
internal class RomanToInt {

    @Test
    fun go() {
        println(romanToInt("III")) //3
        println(romanToInt("LVIII")) //58
        println(romanToInt("MCMXCIV")) //1994
    }

    // Roman numerals are usually written largest to smallest from left to right.
    fun romanToInt(s: String): Int {
        val dict = mapOf('I' to 1, 'V' to 5, 'X' to 10, 'L' to 50, 'C' to 100, 'D' to 500, 'M' to 1000)

        //val arr = s.map { c -> dict[c]!! }.toIntArray()
        //var arr = IntArray(s.length)  // recommended
        //var arr = Array<Int>(s.length){0}

        var prev = dict[s.last()]!!
        var sum = prev
        for (i in s.length - 2 downTo 0) {
            val t = dict[s[i]]!!
            sum+= if (t < prev) -t else t
            prev=t
        }
        return sum
    }
}