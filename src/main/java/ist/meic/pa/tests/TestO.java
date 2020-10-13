package ist.meic.pa.tests;

import ist.meic.pa.tests.domain.C1;
import ist.meic.pa.tests.domain.C2;
import ist.meic.pa.tests.domain.MakeIt;
//passed
public class TestO {
    public static void main(String[] args) {
        Object c = new C1();
        MakeIt.ddouble(c);
    }
}
