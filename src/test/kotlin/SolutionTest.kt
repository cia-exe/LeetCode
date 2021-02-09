import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SolutionTest {

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


