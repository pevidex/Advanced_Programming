package ist.meic.pa.tests;


import ist.meic.pa.tests.domain.ArrayCom;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//passed
public class TestK {
    public static void main(String[] args) {
        List<Object> a = new ArrayList<>();
        a.add("Hello"); a.add(1); a.add('A');

        List<Object> b = new LinkedList<>();
        b.add(2); b.add('B');

        System.out.println(ArrayCom.bine(a, b));
    }
}
