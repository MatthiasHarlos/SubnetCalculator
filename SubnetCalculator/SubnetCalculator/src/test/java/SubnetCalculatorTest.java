import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;
import java.util.List;

public class SubnetCalculatorTest {

    @Test
    void testcalculateBC() {
        Assertions.assertEquals(SubnetCalculator.calculateBC("00000001000000010000000100000001", "11111111111111111111111100000000"), "ID 0 BroadCast= 1.1.1.255");
        Assertions.assertNotEquals(SubnetCalculator.calculateBC("00000001000000010000000100000001", "11111111111111111111111100000000"), "ID 0 BroadCast= 1.1.1.1");
        Assertions.assertEquals(SubnetCalculator.calculateBC("11000000101010000000000100000001", "11111111111111111111111100000000"), "ID 0 BroadCast= 192.168.1.255");
        Assertions.assertEquals(SubnetCalculator.calculateBC("11000000101010000000000100000001", "11111111111111110000000000000000"), "ID 0 BroadCast= 192.168.255.255");
        Assertions.assertEquals(SubnetCalculator.calculateBC("11000000101010000000000100000001", "11111111000000000000000000000000"), "ID 0 BroadCast= 192.255.255.255");
        Assertions.assertEquals(SubnetCalculator.calculateBC("11000000101010000000000100000001", "11111111111111111111111110000000"), "ID 0 BroadCast= 192.168.1.127 ID 128 BroadCast= 192.168.1.255 ");
        Assertions.assertEquals(SubnetCalculator.calculateBC("11000000101010000000000100000001", "11111111111111111111111111000000"), "ID 0 BroadCast= 192.168.1.63 ID 64 BroadCast= 192.168.1.127 ID 128 BroadCast= 192.168.1.191 ID 192 BroadCast= 192.168.1.255 ");
        Assertions.assertEquals(SubnetCalculator.calculateBC("11000000101010000000000100000001", "11111111111111111111111111100000"), "ID 0 BroadCast= 192.168.1.31 ID 32 BroadCast= 192.168.1.63 ID 64 BroadCast= 192.168.1.95 ID 96 BroadCast= 192.168.1.127 ID 128 BroadCast= 192.168.1.159 ID 160 BroadCast= 192.168.1.191 ID 192 BroadCast= 192.168.1.223 ID 224 BroadCast= 192.168.1.255 ");
    }

    @Test
    void testcalculateIDs() {
        Assertions.assertEquals(SubnetCalculator.calculateIDs("00000001000000010000000100000001", "11111111111111111111111100000000"), "ID 0= 1.1.1.0\b/24");
        Assertions.assertNotEquals(SubnetCalculator.calculateIDs("00000001000000010000000100000001", "11111111111111111111111100000000"), "ID 0= 1.1.1.1");
        Assertions.assertEquals(SubnetCalculator.calculateIDs("11000000101010000000000100000001", "11111111111111111111111100000000"), "ID 0= 192.168.1.0\b/24");
        Assertions.assertEquals(SubnetCalculator.calculateIDs("11000000101010000000000100000001", "11111111111111110000000000000000"), "ID 0= 192.168.0.0\b/16");
        Assertions.assertEquals(SubnetCalculator.calculateIDs("11000000101010000000000100000001", "11111111000000000000000000000000"), "ID 0= 192.0.0.0\b/8");
        Assertions.assertEquals(SubnetCalculator.calculateIDs("11000000101010000000000100000001", "11111111111111111111111110000000"), "ID 0= 192.168.1.0/25 ID 128= 192.168.1.128 \b/25");
        Assertions.assertEquals(SubnetCalculator.calculateIDs("11000000101010000000000100000001", "11111111111111111111111111100000"), "ID 0= 192.168.1.0/27 ID 32= 192.168.1.32 ID 64= 192.168.1.64 ID 96= 192.168.1.96 ID 128= 192.168.1.128 ID 160= 192.168.1.160 ID 192= 192.168.1.192 ID 224= 192.168.1.224 \b/27");
    }

    @Test
    void testcheckDots() {
        Assertions.assertFalse(SubnetCalculator.checkDots(".."));
        Assertions.assertFalse(SubnetCalculator.checkDots("...."));
        Assertions.assertTrue(SubnetCalculator.checkDots("..."));
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
        Assertions.assertFalse(SubnetCalculator.splitIP("..1.1"));
        Assertions.assertFalse(SubnetCalculator.splitIP("a.1.a.1"));
        Assertions.assertFalse(SubnetCalculator.splitIP("0.1.1.1"));
        Assertions.assertTrue (SubnetCalculator.splitIP("1.1.1.1"));
        Assertions.assertTrue (SubnetCalculator.splitIP("255.255.255.255"));
    }

    @Test
    void testipSequenzLength (){
        Assertions.assertFalse(SubnetCalculator.ipSequentLength(256));
        Assertions.assertTrue(SubnetCalculator.ipSequentLength(0));
    }

    @Test
    void testSplitSNM() {
        Assertions.assertFalse(SubnetCalculator.splitSNM("..1.1"));
        Assertions.assertFalse(SubnetCalculator.splitSNM("a.1.a.1"));
        Assertions.assertFalse(SubnetCalculator.splitSNM("254.1.1.1"));
        Assertions.assertTrue (SubnetCalculator.splitSNM("255.0.0.0"));
        Assertions.assertTrue (SubnetCalculator.splitSNM("255.255.255.255"));
    }

    @Test
    void testsnmValidation (){
        List<Integer> testList = List.of(256, 256, 256, 256);
        Assertions.assertFalse(SubnetCalculator.snmValidation(testList));
        testList = List.of(255, 0, 255, 0);
        Assertions.assertFalse(SubnetCalculator.snmValidation(testList));
        testList = List.of(255, 255, 255, 1);
        Assertions.assertFalse(SubnetCalculator.snmValidation(testList));
        testList = List.of(255, 255, 255, 127);
        Assertions.assertFalse(SubnetCalculator.snmValidation(testList));
        testList = List.of(255, 0, 0, 0);
        Assertions.assertTrue(SubnetCalculator.snmValidation(testList));
    }
}
