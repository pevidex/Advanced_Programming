package ist.meic.pa.tests;

import ist.meic.pa.tests.domain.Bug;
import ist.meic.pa.tests.domain.C1;
import ist.meic.pa.tests.domain.C2;

//output: Bar Bar
//passed
public class TestF {
    public static void main(String[] args) {
        Object c1 = new C1(), c2 = new C2();
        Bug.bug(c1);
        Bug.bug(c2);
    }
}
