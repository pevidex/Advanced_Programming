package ist.meic.pa.tests;


import ist.meic.pa.tests.domain.Com;
//passed
public class TestB {
    public static void main(String[] args) {
        Object[] objects = new Object[] { new Object(), "Foo", 123};
        for(Object c : objects) System.out.println(Com.bine(c));
    }
}
