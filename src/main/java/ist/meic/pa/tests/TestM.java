package ist.meic.pa.tests;

import ist.meic.pa.tests.domain.Blue;
import ist.meic.pa.tests.domain.Color;
import ist.meic.pa.tests.domain.Red;
import ist.meic.pa.tests.domain.Yellow;
//passed
public class TestM {
    public static void main(String[] args) {
        Color[] colors = new Color[]{new Red(), new Blue(), new Yellow()};
        for (Color c1 : colors)
            for (Color c2 : colors)
                System.out.println(Color.mix(c1, c2));
    }
}
