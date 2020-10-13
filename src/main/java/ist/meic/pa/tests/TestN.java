package ist.meic.pa.tests;

import ist.meic.pa.tests.domain.Color;
import ist.meic.pa.tests.domain.Red;
import ist.meic.pa.tests.domain.SuperBlack;
//Color-SuperBlack-Color <- WRONG should be: Red-Color-Red
//SuperBlack-Black-Color
//SuperBlack-Black-SuperBlack

public class TestN {
    public static void main(String[] args) {
        Object red1 = new Red(), red2 = new Red(), black = new SuperBlack();
        System.out.println(Color.mix(red1, black, red2));
        System.out.println(Color.mix(black, black, red2));
        System.out.println(Color.mix(black, black, black));
    }
}
