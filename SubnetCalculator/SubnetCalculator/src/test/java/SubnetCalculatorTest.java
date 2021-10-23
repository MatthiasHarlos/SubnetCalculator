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
        Assertions.assertEquals(SubnetCalculator.calculateBC("11000000101010000000000100000001", "11111111111111111111111111000000"), "ID 0 BroadCast= 192.168.1.63 ID 64 BroadCast= 192.168.1.127 " +
                "ID 128 BroadCast= 192.168.1.191 ID 192 BroadCast= 192.168.1.255 ");
        Assertions.assertEquals(SubnetCalculator.calculateBC("11000000101010000000000100000001", "11111111111111111111111111100000"), "ID 0 BroadCast= 192.168.1.31 ID 32 BroadCast= 192.168.1.63 " +
                "ID 64 BroadCast= 192.168.1.95 ID 96 BroadCast= 192.168.1.127 ID 128 BroadCast= 192.168.1.159 ID 160 BroadCast= 192.168.1.191 ID 192 BroadCast= 192.168.1.223 ID 224 BroadCast= 192.168.1.255 ");
        Assertions.assertEquals(SubnetCalculator.calculateBC("11000000000000010000000100000001", "11111111111111000000000000000000"), "ID 0 BroadCast= 192.3.255.255 ID 4 BroadCast= 192.7.255.255 " +
                "ID 8 BroadCast= 192.11.255.255 ID 12 BroadCast= 192.15.255.255 ID 16 BroadCast= 192.19.255.255 ID 20 BroadCast= 192.23.255.255 ID 24 BroadCast= 192.27.255.255 ID 28 BroadCast= 192.31.255.255 ID 32 BroadCast= 192.35.255.255 " +
                "ID 36 BroadCast= 192.39.255.255 ID 40 BroadCast= 192.43.255.255 ID 44 BroadCast= 192.47.255.255 ID 48 BroadCast= 192.51.255.255 ID 52 BroadCast= 192.55.255.255 ID 56 BroadCast= 192.59.255.255 ID 60 BroadCast= 192.63.255.255" +
                " ID 64 BroadCast= 192.67.255.255 ID 68 BroadCast= 192.71.255.255 ID 72 BroadCast= 192.75.255.255 ID 76 BroadCast= 192.79.255.255 ID 80 BroadCast= 192.83.255.255 ID 84 BroadCast= 192.87.255.255 ID 88 BroadCast= 192.91.255.255 " +
                "ID 92 BroadCast= 192.95.255.255 ID 96 BroadCast= 192.99.255.255 ID 100 BroadCast= 192.103.255.255 ID 104 BroadCast= 192.107.255.255 ID 108 BroadCast= 192.111.255.255 ID 112 BroadCast= 192.115.255.255 ID 116 BroadCast= 192.119.255.255 " +
                "ID 120 BroadCast= 192.123.255.255 ID 124 BroadCast= 192.127.255.255 ID 128 BroadCast= 192.131.255.255 ID 132 BroadCast= 192.135.255.255 ID 136 BroadCast= 192.139.255.255 ID 140 BroadCast= 192.143.255.255 ID 144 BroadCast= 192.147.255.255 ID 148 BroadCast= 192.151.255.255 " +
                "ID 152 BroadCast= 192.155.255.255 ID 156 BroadCast= 192.159.255.255 ID 160 BroadCast= 192.163.255.255 ID 164 BroadCast= 192.167.255.255 ID 168 BroadCast= 192.171.255.255 ID 172 BroadCast= 192.175.255.255 ID 176 BroadCast= 192.179.255.255 ID 180 BroadCast= 192.183.255.255 " +
                "ID 184 BroadCast= 192.187.255.255 ID 188 BroadCast= 192.191.255.255 ID 192 BroadCast= 192.195.255.255 ID 196 BroadCast= 192.199.255.255 ID 200 BroadCast= 192.203.255.255 ID 204 BroadCast= 192.207.255.255 ID 208 BroadCast= 192.211.255.255 ID 212 BroadCast= 192.215.255.255 " +
                "ID 216 BroadCast= 192.219.255.255 ID 220 BroadCast= 192.223.255.255 ID 224 BroadCast= 192.227.255.255 ID 228 BroadCast= 192.231.255.255 ID 232 BroadCast= 192.235.255.255 ID 236 BroadCast= 192.239.255.255 ID 240 BroadCast= 192.243.255.255 ID 244 BroadCast= 192.247.255.255 " +
                "ID 248 BroadCast= 192.251.255.255 ID 252 BroadCast= 192.255.255.255 ");
    }

    @Test
    void testcalculateIDs() {
        Assertions.assertEquals(SubnetCalculator.calculateIDs("00000001000000010000000100000001", "11111111111111111111111100000000"), "ID 0= 1.1.1.0 \b/24");
        Assertions.assertNotEquals(SubnetCalculator.calculateIDs("00000001000000010000000100000001", "11111111111111111111111100000000"), "ID 0= 1.1.1.1");
        Assertions.assertEquals(SubnetCalculator.calculateIDs("11000000101010000000000100000001", "11111111111111111111111100000000"), "ID 0= 192.168.1.0 \b/24");
        Assertions.assertEquals(SubnetCalculator.calculateIDs("11000000101010000000000100000001", "11111111111111110000000000000000"), "ID 0= 192.168.0.0 \b/16");
        Assertions.assertEquals(SubnetCalculator.calculateIDs("11000000101010000000000100000001", "11111111000000000000000000000000"), "ID 0= 192.0.0.0 \b/8");
        Assertions.assertEquals(SubnetCalculator.calculateIDs("11000000101010000000000100000001", "11111111111111111111111110000000"), "ID 0= 192.168.1.0 \b/25 ID 128= 192.168.1.128 \b/25");
        Assertions.assertEquals(SubnetCalculator.calculateIDs("11000000101010000000000100000001", "11111111111111111111111111100000"), "ID 0= 192.168.1.0 \b/27 ID 32= 192.168.1.32 " +
                "ID 64= 192.168.1.64 ID 96= 192.168.1.96 ID 128= 192.168.1.128 ID 160= 192.168.1.160 ID 192= 192.168.1.192 ID 224= 192.168.1.224 \b/27");
        Assertions.assertEquals(SubnetCalculator.calculateIDs("11000000000000010000000100000001", "11111111111111000000000000000000"), "ID 0= 192.0.0.0 \b/14 ID 4= 192.4.0.0 ID 8= 192.8.0.0 " +
                "ID 12= 192.12.0.0 ID 16= 192.16.0.0 ID 20= 192.20.0.0 ID 24= 192.24.0.0 ID 28= 192.28.0.0 ID 32= 192.32.0.0 ID 36= 192.36.0.0 ID 40= 192.40.0.0 ID 44= 192.44.0.0 ID 48= 192.48.0.0 ID 52= 192.52.0.0 " +
                "ID 56= 192.56.0.0 ID 60= 192.60.0.0 ID 64= 192.64.0.0 ID 68= 192.68.0.0 ID 72= 192.72.0.0 ID 76= 192.76.0.0 ID 80= 192.80.0.0 ID 84= 192.84.0.0 ID 88= 192.88.0.0 ID 92= 192.92.0.0 ID 96= 192.96.0.0 " +
                "ID 100= 192.100.0.0 ID 104= 192.104.0.0 ID 108= 192.108.0.0 ID 112= 192.112.0.0 ID 116= 192.116.0.0 ID 120= 192.120.0.0 ID 124= 192.124.0.0 ID 128= 192.128.0.0 ID 132= 192.132.0.0 ID 136= 192.136.0.0 ID 140= 192.140.0.0 " +
                "ID 144= 192.144.0.0 ID 148= 192.148.0.0 ID 152= 192.152.0.0 ID 156= 192.156.0.0 ID 160= 192.160.0.0 ID 164= 192.164.0.0 ID 168= 192.168.0.0 ID 172= 192.172.0.0 ID 176= 192.176.0.0 ID 180= 192.180.0.0 ID 184= 192.184.0.0 ID 188= 192.188.0.0 " +
                "ID 192= 192.192.0.0 ID 196= 192.196.0.0 ID 200= 192.200.0.0 ID 204= 192.204.0.0 ID 208= 192.208.0.0 ID 212= 192.212.0.0 ID 216= 192.216.0.0 ID 220= 192.220.0.0 ID 224= 192.224.0.0 ID 228= 192.228.0.0 ID 232= 192.232.0.0 ID 236= 192.236.0.0 ID 240= 192.240.0.0 " +
                "ID 244= 192.244.0.0 ID 248= 192.248.0.0 ID 252= 192.252.0.0 \b/14");
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
