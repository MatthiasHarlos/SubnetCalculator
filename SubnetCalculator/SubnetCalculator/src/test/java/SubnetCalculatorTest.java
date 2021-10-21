import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

public class SubnetCalculatorTest {
    @Test
    void testcheckDots() {
        Assertions.assertThrows(InputMismatchException.class, () -> SubnetCalculator.checkDots(".."));
        Assertions.assertThrows(InputMismatchException.class, () -> SubnetCalculator.checkDots("...."));
        Assertions.assertDoesNotThrow(() -> SubnetCalculator.checkDots("..."));
    }

    @Test
    void testIpLength() {
        Assertions.assertFalse(SubnetCalculator.isLenghtRight("1.1.1"));
        Assertions.assertFalse(SubnetCalculator.isLenghtRight("111111.1.1.111111"));
        Assertions.assertTrue(SubnetCalculator.isLenghtRight("1.1.1.1"));
        Assertions.assertTrue(SubnetCalculator.isLenghtRight("111.111.111.111"));
    }

    @Test
    void testSplitIp() {
        Assertions.assertThrows(InputMismatchException.class, () -> SubnetCalculator.splitID("..1.1"));
        Assertions.assertThrows(InputMismatchException.class, () -> SubnetCalculator.splitID("1.1.a.1"));
        Assertions.assertThrows(InputMismatchException.class, () -> SubnetCalculator.splitID("0.1.1.1"));
        Assertions.assertDoesNotThrow(() -> SubnetCalculator.splitID("1.1.1.1"));
    }

    @Test
    void testSequenzLength (){
        Assertions.assertThrows(InputMismatchException.class, () -> SubnetCalculator.sequentLength(256));
        Assertions.assertDoesNotThrow(() -> SubnetCalculator.sequentLength(0));
    }
}
