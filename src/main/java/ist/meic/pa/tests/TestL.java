package ist.meic.pa.tests;

import ist.meic.pa.tests.domain.Com;
//passed
public class TestL {
    public static void main(String[] args) {
        Object[] objs1 = new Object[] { "Hello", 1, 'A' };
        Object[] objs2 = new Object[] { "World", 2, 'B' };

        for (Object o1 : objs1)
            for (Object o2 : objs2)
                System.out.println("Combine(" + o1 + ", " + o2 + ") -> " + Com.bine(o1, o2));
    }
}
