import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class JavaTest {

    class Solution {

        char[] s;
        int lastIdx;
        int max=0;
        int beginIdx=0;
        int checkIdx=1;
        HashSet<Character> set = new HashSet<>();

        public int lengthOfLongestSubstring(String s){

            lastIdx = s.length() - 1;
            if (lastIdx < 0) return 0;
            if (lastIdx == 0) return 1;

            this.s=s.toCharArray();

            while (true) if(fn()) break;
            return max;
        }

        private boolean fn(){

                char sBegin = s[beginIdx++];
                set.add(sBegin);
                if (checkIdx < beginIdx) checkIdx = beginIdx;

                while (checkIdx <= lastIdx) {
                    char sCheck = s[checkIdx];
                    if (set.contains(sCheck)) break;
                    set.add(sCheck);
                    checkIdx++;
                }

                int n = set.size();
                if (max < n) max = n;
                set.remove(sBegin);

                return  checkIdx > lastIdx;
        }
    }

    @Test
    public void lengthOfLongestSubstring(){

    }



}
