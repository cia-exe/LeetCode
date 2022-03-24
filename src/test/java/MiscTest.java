import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.lang.System.out;

public class MiscTest {
    @Test
    public void go() {
        var x = 2;
        var x6 = 6 * x;
        assertEquals(6, x6 / x);

        out.println(this);
    }

    //--------------------------------
    int gcd(int a, int b){ // a must > b
        return b==0?a:gcd(b,a%b);
    }

    int lcm(int a, int b, int gcd){
        return a*b/gcd;
    }

    void testGcdLcm(int a,int b){
        int g=gcd(a,b), l=lcm(a,b,g);
        out.printf("%d,%d: gcd=%d, lcm=%d\n",a,b,g,l);
    }

    @Test
    public void GcdLcm()
    {
        testGcdLcm(12,6);
        testGcdLcm(15,10);
        testGcdLcm(18,12);
    }
}
