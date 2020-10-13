package ist.meic.pa.tests;

import ist.meic.pa.tests.domain.*;

import java.util.NoSuchElementException;
//no after or before if there is no effective method
//passed
public class TestJ {
    public static void main(String[] args) {
        Color blue = new Blue();
        What.is(blue);
    }

}
