import org.junit.jupiter.api.Test;

import java.util.*;

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
    int gcd(int a, int b) { // a must > b
        return b == 0 ? a : gcd(b, a % b);
    }

    int lcm(int a, int b, int gcd) {
        return a * b / gcd;
    }

    void testGcdLcm(int a, int b) {
        int g = gcd(a, b), l = lcm(a, b, g);
        out.printf("%d,%d: gcd=%d, lcm=%d\n", a, b, g, l);
    }

    @Test
    public void GcdLcm() {
        testGcdLcm(12, 6);
        testGcdLcm(15, 10);
        testGcdLcm(18, 12);
    }

    @Test
    public void Arrays() {

        // List is an interface of ArrayList,LinkedList,Vector & Stack
        // size(),add(o),add(idx,o),get(idx),indexOf(o),isEmpty(),clear(),clone(),remove(idx),remove(obj),
        // set(idx,o)//replace, toArray

        var l1 = List.of(1, 2, 3); //Immutable
//      l1.add(5);
        out.println(l1);

        var l2 = Arrays.asList(4, 5, 6);  //Immutable
//      l2.add(5);
        out.println(l2);

        var l3 = new ArrayList<>(Arrays.asList(9, 7, 8));
        l3.add(5);
        out.println(l3);

        Collections.sort(l3);
        out.println(l3);
        Collections.sort(l3, Collections.reverseOrder());
        out.println(l3);
//        l3.sort(Collections.reverseOrder()); // no naturalOrder()
        l3.sort(Comparator.naturalOrder());
        out.println(l3);
        l3.sort(Comparator.reverseOrder());
        out.println(l3);
        var arrObj =l3.toArray();
        var arrInt =l3.toArray(new Integer[0]);
        out.println(Arrays.toString(arrObj));
        out.println(Arrays.toString(arrInt));

        for (var e : l3)
            out.print(e);


        List<int[]> l4 = new ArrayList<>();

        var arr1 = new int[]{4, 3, 2, 1};
        int[] arr2 = {5, 6, 7, 8}; // It is syntactic convenience of arr1

        out.println("\nsort ascending.....");
        Arrays.sort(arr1); // only support ascending
        out.println(Arrays.toString(arr1));

        out.println("sort descending......");
        for (int i = 0; i < arr2.length; i++) arr2[i] *= -1;
        Arrays.sort(arr2);
        for (int i = 0; i < arr2.length; i++) arr2[i] *= -1;
        out.println(Arrays.toString(arr2));

        l4.add(arr1);
        l4.add(arr2);
        l4.add(new int[]{3, 2, 3, 4});
        l4.isEmpty();
        //l4.add({3,2,3,4});    //syntax error

        out.println(l4);


        //Stack : push(e), pop(), peek()
        //Queue : add(e), offer(e), remove(), poll(), element(), peek()
        //
        // add()/remove()/push()/pop() throws Exception if failed.,
        // but offer()/poll() returns false/null if failed.
        // (add to capacity-restricted queue, or remove from empty queue.)

        out.println("stack------------");
        var s = new Stack<Integer>();
        s.push(1);
        s.add(2);
        s.push(3);
        s.add(4);
        out.println(s);

        s.peek();
        s.pop();
        s.remove(s.size() - 1);
        s.pop();
        s.remove(s.size() - 1);
        out.println(s.isEmpty()); //Collection
        out.println(s.empty()); //stack

        out.println(s);

        out.println("queue-----------");
        var q = new LinkedList<Integer>();
        q.offer(1);

        q.push(1);
        q.pop();
        q.poll();

        q.peek();
        q.peekFirst(); //q.getFirst(); // NoSuchElementException when empty
        q.peekLast();



        out.println("heap-----------");
        var h = new PriorityQueue<Integer>(); //minHeap
        //var h = new PriorityQueue<Integer>(Comparator.reverseOrder()); //maxHeap

        int o;
        h.add(1); // exception if failed
        h.offer(2); // false if failed
        o=h.remove(); // exception if empty
        o=h.poll(); // null if empty

        h.peek();
        h.contains(2);

        // HashSet non-ordered
        // TreeSet ordered

        var tSet= new TreeSet<Integer>();
        var hSet=new HashSet<Integer>();
        var tMap=new TreeMap<Integer,Integer>();
        var hMap=new HashMap<Integer,Integer>();
        var map=Map.of("Justin", 95, "Monica", 100);
        var set = Set.of(1,2,3);

    }
}
