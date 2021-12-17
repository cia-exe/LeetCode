import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TimingExtension::class)
internal class SolutionTest {

    @Test
    fun lengthOfLongestSubstring() {

        class Solution {

            fun lengthOfLongestSubstring(s: String): Int {

                val lastIdx = s.length - 1
                if (lastIdx < 0) return 0
                if (lastIdx == 0) return 1

                var max = 0
                var beginIdx = 0
                var checkIdx = 1
                val set = HashSet<Char>()
                //val set = mutableSetOf<Char>() //==LinkedHashSet

                while (true) if (
                    run {
                        val sBegin = s[beginIdx++]
                        set.add(sBegin)
                        if (checkIdx < beginIdx) checkIdx = beginIdx

                        while (checkIdx <= lastIdx) {
                            val sCheck = s[checkIdx]
                            if (set.contains(sCheck)) break
                            set.add(sCheck)
                            checkIdx++
                        }

                        val n = set.size
                        if (max < n) max = n
                        set.remove(sBegin)

                        checkIdx > lastIdx
                    }
                ) break
                return max
            }
        }

        //--------------------
        class SolutionOk {

            var max = 0
            var lastIdx = 0
            lateinit var s: String
            val set = mutableSetOf<Char>()

            fun lengthOfLongestSubstring(s: String): Int {

                this.s = s
                lastIdx = s.length - 1

                for (i in 0..lastIdx) {
                    fn(i)
                }
                return max
            }

            fun fn(beginIdx: Int) {
                set.clear()

                var i = beginIdx
                set.add(s[i])

                while (++i <= lastIdx)
                    if (!set.add(s[i])) break

                val n = set.size
                if (max < n) max = n
            }
        }

        //--------------------
        class SolutionSlow {

            fun lengthOfLongestSubstring(s: String): Int {

                val set = mutableSetOf<Char>()
                val lastIdx = s.length - 1

                val fn: (Int) -> Int = {
                    set.clear()

                    var i = it
                    set.add(s[i])
                    var x = 1

                    while (++i <= lastIdx) {
                        val c = s[i]
                        if (set.contains(c))
                            break
                        else x++
                        set.add(c)
                    }
                    x
                }

                var max = 0
                for (i in 0..lastIdx) {
                    val n = fn(i)
                    if (max < n)
                        max = n
                }
                return max
            }
        }

        assertEquals(5, Solution().lengthOfLongestSubstring("qrsvbspk"))
        assertEquals(2, Solution().lengthOfLongestSubstring("aab"))
        assertEquals(3, Solution().lengthOfLongestSubstring("dvdf"))
        "pass".log()
    }


    @Test
    fun removeKdigits() {

        fun removeKdigits(number: String, k: Int): String {

            val remove: StringBuilder.() -> StringBuilder = {

                if (length == 1) {
                    setCharAt(0, '0')
                    this
                } else {
                    var i = 0
                    while (++i < length)
                        if (get(i - 1) > get(i)) break

                    deleteCharAt(i - 1)
                    if (length >= 2 && get(0) == '0') //test "10"2,"100"1
                        deleteCharAt(0)
                    this
                }
            }

            var num = StringBuilder(number)
            repeat(k) { num = remove(num) }
            return if (num.isEmpty()) "0" else num.toString()
        }


        removeKdigits("12345654321", 5).log()
    }

    @Test
    fun addTwoNumbers() {

        class ListNode(var `val`: Int = 0) {
            var next: ListNode? = null
        }

        fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
            if (l1 == null && l2 == null) return null
            var p1 = l1
            var p2 = l2
            var c = 0
            return ListNode().apply {
                var r = this
                while (true) {

                    var s = (p1?.`val` ?: 0) + (p2?.`val` ?: 0) + c

                    if (s >= 10) {
                        s -= 10
                        c = 1
                    } else c = 0

                    r.`val` = s

                    p1 = p1?.next
                    p2 = p2?.next

                    if (p1 == null && p2 == null) {
                        if (c != 0) r.next = ListNode(c)
                        break
                    }

                    r.next = ListNode()
                    r = r.next!!
                }
            }
        }

        var a = addTwoNumbers(ListNode(9), ListNode(8))

        while (true) {
            if (a == null) break
            "${a.`val`}".log()
            a = a.next
        }
    }
}


