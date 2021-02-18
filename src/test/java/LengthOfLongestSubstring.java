import org.junit.jupiter.api.Test;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Solution0 {

    String s;
    int lastIdx;
    int max = 0;
    int beginIdx = 0;
    int checkIdx = 1;
    HashSet<Character> set = new HashSet<>();

    public int lengthOfLongestSubstring(String s) {

        lastIdx = s.length() - 1;
        if (lastIdx < 0) return 0;
        if (lastIdx == 0) return 1;

        this.s = s;
        while (true) if (fn()) break;
        return max;
    }

    private boolean fn() {

        Character sBegin = s.charAt(beginIdx++);
        set.add(sBegin);
        if (checkIdx < beginIdx) checkIdx = beginIdx;

        while (checkIdx <= lastIdx) {
            if (!set.add(s.charAt(checkIdx))) break;
            else checkIdx++;
        }

        int n = set.size();
        if (max < n) max = n;
        set.remove(sBegin);

        return checkIdx > lastIdx;
    }
}


class Solution1 {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1)
            return s.length();
        int n = s.length();
        int i = 0, j = 1, res = 1;
        while (j < n) {
            if (i > j) break;
            if (s.charAt(i) != s.charAt(j)) {
                for (int k = i; k < j; k++) {
                    if (s.charAt(k) == s.charAt(j)) {
                        i = k + 1;
                        break;
                    }
                }
                res = Math.max(res, j - i + 1);
            } else {
                i++;
            }
            j++;
        }
        return res;
    }
}

class Solution2 {

    public int lengthOfLongestSubstring(String s) {

        int[] freqArr = new int[256];
        int i = 0, j = 0, max = 0;

        while (i < s.length()) {
            int charI = s.charAt(i);
            freqArr[charI]++; // increment frequency
            if (freqArr[charI] == 2) { // duplicate found
                while (freqArr[charI] > 1) { // move the window's start position after previously visited index of the char
                    freqArr[s.charAt(j++)]--;
                }
            }
            max = Math.max(max, i - j + 1);
            i++;
        }

        return max;
    }
}

public class LengthOfLongestSubstring {

    @Test
    public void go() {

        assertEquals(2, new Solution2().lengthOfLongestSubstring("aba"));
        assertEquals(5, new Solution2().lengthOfLongestSubstring("qrsvbspk"));
        assertEquals(2, new Solution2().lengthOfLongestSubstring("aab"));
        assertEquals(3, new Solution2().lengthOfLongestSubstring("dvdf"));
    }
}
