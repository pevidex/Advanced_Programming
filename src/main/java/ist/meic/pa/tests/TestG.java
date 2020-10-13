package ist.meic.pa.tests;

import ist.meic.pa.tests.domain.ArrayCom;
import java.util.Arrays;

//passed
public class TestG {
    public static void main(String[] args) {
        println(ArrayCom.bine(1, 3));
        println(ArrayCom.bine(new Object[] { 1, 2, 3 }, new Object[] { 4, 5, 6 }));
        println(ArrayCom.bine(new Object[] { new Object[] { 1, 2 }, 3 },
                new Object[] { new Object[] { 3, 4 }, 5 }));
    }
    public static void println(Object obj) {
        if (obj instanceof Object[]) {
            System.out.println(Arrays.deepToString((Object[])obj));
        } else {
            System.out.println(obj);
        }
    }
}
