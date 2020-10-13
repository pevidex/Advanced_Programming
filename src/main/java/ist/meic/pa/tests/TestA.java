package ist.meic.pa.tests;

import ist.meic.pa.tests.domain.*;

//passed
public class TestA {
    public static void main(String[] args) {
        Color[] colors = new Color[] { new Red(), new Blue(), new Black()};
        for(Color c : colors) System.out.println(Color.mix(c));
    }

}
