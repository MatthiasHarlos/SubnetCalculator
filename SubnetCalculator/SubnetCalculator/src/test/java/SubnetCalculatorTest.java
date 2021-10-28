import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SubnetCalculatorTest {
/*
    Validator validate = new Validator();
    Calculator calculate = new Calculator();

    @Test
    void testCalculateHosts() {
        Assertions.assertEquals(calculate.hosts("11111111111111111111111100000000"), 254);
        Assertions.assertEquals(calculate.hosts("11111111111111111111111111111100"), 2);
        Assertions.assertEquals(calculate.hosts("11111111000000000000000000000000"), 16777214);
    }

    @Test
    void testCalculateIPs() {
        List<List<String>> resultLists = new ArrayList<>();
        for (int i = 0; i<256; i=i+4) {
            List<String> iPs = new ArrayList<>();
            iPs.add("Erste IP = 192."+i+".0.1");
            iPs.add("Letzte IP = 192."+(i+3)+".255.254");
            resultLists.add(iPs);
        }
        Assertions.assertEquals(calculate.iPs("ID 0= 192.0.0.0/14|ID 4= 192.4.0.0/14|ID 8= 192.8.0.0/14|ID 12= 192.12.0.0/14|\" +\n" +
                "                \"ID 16= 192.16.0.0/14|ID 20= 192.20.0.0/14|ID 24= 192.24.0.0/14|ID 28= 192.28.0.0/14|ID 32= 192.32.0.0/14|ID 36= 192.36.0.0/14|ID 40= 192.40.0.0/14|ID 44= 192.44.0.0/14|ID 48= 192.48.0.0/14|ID 52= 192.52.0.0/14|ID 56= 192.56.0.0/14|\" +\n" +
                "                \"ID 60= 192.60.0.0/14|ID 64= 192.64.0.0/14|ID 68= 192.68.0.0/14|ID 72= 192.72.0.0/14|ID 76= 192.76.0.0/14|ID 80= 192.80.0.0/14|ID 84= 192.84.0.0/14|ID 88= 192.88.0.0/14|ID 92= 192.92.0.0/14|ID 96= 192.96.0.0/14|ID 100= 192.100.0.0/14|\" +\n" +
                "                \"ID 104= 192.104.0.0/14|ID 108= 192.108.0.0/14|ID 112= 192.112.0.0/14|ID 116= 192.116.0.0/14|ID 120= 192.120.0.0/14|ID 124= 192.124.0.0/14|ID 128= 192.128.0.0/14|ID 132= 192.132.0.0/14|ID 136= 192.136.0.0/14|ID 140= 192.140.0.0/14|\" +\n" +
                "                \"ID 144= 192.144.0.0/14|ID 148= 192.148.0.0/14|ID 152= 192.152.0.0/14|ID 156= 192.156.0.0/14|ID 160= 192.160.0.0/14|ID 164= 192.164.0.0/14|ID 168= 192.168.0.0/14|ID 172= 192.172.0.0/14|ID 176= 192.176.0.0/14|ID 180= 192.180.0.0/14|\" +\n" +
                "                \"ID 184= 192.184.0.0/14|ID 188= 192.188.0.0/14|ID 192= 192.192.0.0/14|ID 196= 192.196.0.0/14|ID 200= 192.200.0.0/14|ID 204= 192.204.0.0/14|ID 208= 192.208.0.0/14|ID 212= 192.212.0.0/14|ID 216= 192.216.0.0/14|ID 220= 192.220.0.0/14|\" +\n" +
                "                \"ID 224= 192.224.0.0/14|ID 228= 192.228.0.0/14|ID 232= 192.232.0.0/14|ID 236= 192.236.0.0/14|ID 240= 192.240.0.0/14|ID 244= 192.244.0.0/14|ID 248= 192.248.0.0/14|ID 252= 192.252.0.0/14|", "BroadCast= 192.3.255.255|BroadCast= 192.7.255.255|\" +\n" +
                "                \"BroadCast= 192.11.255.255|BroadCast= 192.15.255.255|BroadCast= 192.19.255.255|BroadCast= 192.23.255.255|BroadCast= 192.27.255.255|BroadCast= 192.31.255.255|BroadCast= 192.35.255.255|BroadCast= 192.39.255.255|\" +\n" +
                "                \"BroadCast= 192.43.255.255|BroadCast= 192.47.255.255|BroadCast= 192.51.255.255|BroadCast= 192.55.255.255|BroadCast= 192.59.255.255|BroadCast= 192.63.255.255|BroadCast= 192.67.255.255|BroadCast= 192.71.255.255|\" +\n" +
                "                \"BroadCast= 192.75.255.255|BroadCast= 192.79.255.255|BroadCast= 192.83.255.255|BroadCast= 192.87.255.255|BroadCast= 192.91.255.255|BroadCast= 192.95.255.255|BroadCast= 192.99.255.255|BroadCast= 192.103.255.255|\" +\n" +
                "                \"BroadCast= 192.107.255.255|BroadCast= 192.111.255.255|BroadCast= 192.115.255.255|BroadCast= 192.119.255.255|BroadCast= 192.123.255.255|BroadCast= 192.127.255.255|BroadCast= 192.131.255.255|BroadCast= 192.135.255.255|\" +\n" +
                "                \"BroadCast= 192.139.255.255|BroadCast= 192.143.255.255|BroadCast= 192.147.255.255|BroadCast= 192.151.255.255|BroadCast= 192.155.255.255|BroadCast= 192.159.255.255|BroadCast= 192.163.255.255|BroadCast= 192.167.255.255|\" +\n" +
                "                \"BroadCast= 192.171.255.255|BroadCast= 192.175.255.255|BroadCast= 192.179.255.255|BroadCast= 192.183.255.255|BroadCast= 192.187.255.255|BroadCast= 192.191.255.255|BroadCast= 192.195.255.255|BroadCast= 192.199.255.255|\" +\n" +
                "                \"BroadCast= 192.203.255.255|BroadCast= 192.207.255.255|BroadCast= 192.211.255.255|BroadCast= 192.215.255.255|BroadCast= 192.219.255.255|BroadCast= 192.223.255.255|BroadCast= 192.227.255.255|BroadCast= 192.231.255.255|\" +\n" +
                "                \"BroadCast= 192.235.255.255|BroadCast= 192.239.255.255|BroadCast= 192.243.255.255|BroadCast= 192.247.255.255|BroadCast= 192.251.255.255|BroadCast= 192.255.255.255|"), resultLists);
        List<List<String>> resultLists2 = new ArrayList<>();
        for (int i = 0; i<256; i=i+32) {
            List<String> iPs = new ArrayList<>();
            iPs.add("Erste IP = 192.168.1."+(i+1));
            iPs.add("Letzte IP = 192.168.1."+(i+30));
            resultLists2.add(iPs);
        }
        Assertions.assertEquals(calculate.iPs("ID 0= 192.168.1.0/27|ID 32= 192.168.1.32/27|ID 64= 192.168.1.64/27|" +
                "ID 96= 192.168.1.96/27|ID 128= 192.168.1.128/27|ID 160= 192.168.1.160/27|ID 192= 192.168.1.192/27|ID 224= 192.168.1.224/27|" ,
                "BroadCast= 192.168.1.31|BroadCast= 192.168.1.63|BroadCast= 192.168.1.95|BroadCast= 192.168.1.127|BroadCast= 192.168.1.159|" +
                        "BroadCast= 192.168.1.191|BroadCast= 192.168.1.223|BroadCast= 192.168.1.255|"), resultLists2);

    }

    @Test
    void testturnIntoDecimalBC() {
        Assertions.assertEquals(calculate.turnIntoDecimalBC("11111111111111111111111100000000", "1.1.1.0", "1.1.1"), "BroadCast= 1.1.1.255|");
        Assertions.assertNotEquals(calculate.turnIntoDecimalBC( "11111111111111111111111100000000","1.1.1.0", "1.1.1"), "BroadCast= 1.1.1.1|");
        Assertions.assertEquals(calculate.turnIntoDecimalBC( "11111111111111111111111100000000", "192.168.1.0", "192.168.1"), "BroadCast= 192.168.1.255|");
        Assertions.assertEquals(calculate.turnIntoDecimalBC( "11111111111111110000000000000000", "192.168.0.0", "192.168"), "BroadCast= 192.168.255.255|");
        Assertions.assertEquals(calculate.turnIntoDecimalBC( "11111111000000000000000000000000", "192.0.0.0", "192"), "BroadCast= 192.255.255.255|");
        Assertions.assertEquals(calculate.turnIntoDecimalBC( "11111111111111111111111110000000", "192.168.1.0", "192.168.1"), "BroadCast= 192.168.1.127|BroadCast= 192.168.1.255|");
        Assertions.assertEquals(calculate.turnIntoDecimalBC( "11111111111111111111111111000000", "192.168.1.0", "192.168.1"), "BroadCast= 192.168.1.63|BroadCast= 192.168.1.127|" +
                "BroadCast= 192.168.1.191|BroadCast= 192.168.1.255|");
        Assertions.assertEquals(calculate.turnIntoDecimalBC( "11111111111111111111111111100000", "192.168.1.0", "192.168.1"), "BroadCast= 192.168.1.31|BroadCast= 192.168.1.63|" +
                "BroadCast= 192.168.1.95|BroadCast= 192.168.1.127|BroadCast= 192.168.1.159|BroadCast= 192.168.1.191|BroadCast= 192.168.1.223|BroadCast= 192.168.1.255|");
        Assertions.assertEquals(calculate.turnIntoDecimalBC( "11111111111111000000000000000000", "192.0.0.0", "192"), "BroadCast= 192.3.255.255|BroadCast= 192.7.255.255|" +
                "BroadCast= 192.11.255.255|BroadCast= 192.15.255.255|BroadCast= 192.19.255.255|BroadCast= 192.23.255.255|BroadCast= 192.27.255.255|BroadCast= 192.31.255.255|BroadCast= 192.35.255.255|BroadCast= 192.39.255.255|" +
                "BroadCast= 192.43.255.255|BroadCast= 192.47.255.255|BroadCast= 192.51.255.255|BroadCast= 192.55.255.255|BroadCast= 192.59.255.255|BroadCast= 192.63.255.255|BroadCast= 192.67.255.255|BroadCast= 192.71.255.255|" +
                "BroadCast= 192.75.255.255|BroadCast= 192.79.255.255|BroadCast= 192.83.255.255|BroadCast= 192.87.255.255|BroadCast= 192.91.255.255|BroadCast= 192.95.255.255|BroadCast= 192.99.255.255|BroadCast= 192.103.255.255|" +
                "BroadCast= 192.107.255.255|BroadCast= 192.111.255.255|BroadCast= 192.115.255.255|BroadCast= 192.119.255.255|BroadCast= 192.123.255.255|BroadCast= 192.127.255.255|BroadCast= 192.131.255.255|BroadCast= 192.135.255.255|" +
                "BroadCast= 192.139.255.255|BroadCast= 192.143.255.255|BroadCast= 192.147.255.255|BroadCast= 192.151.255.255|BroadCast= 192.155.255.255|BroadCast= 192.159.255.255|BroadCast= 192.163.255.255|BroadCast= 192.167.255.255|" +
                "BroadCast= 192.171.255.255|BroadCast= 192.175.255.255|BroadCast= 192.179.255.255|BroadCast= 192.183.255.255|BroadCast= 192.187.255.255|BroadCast= 192.191.255.255|BroadCast= 192.195.255.255|BroadCast= 192.199.255.255|" +
                "BroadCast= 192.203.255.255|BroadCast= 192.207.255.255|BroadCast= 192.211.255.255|BroadCast= 192.215.255.255|BroadCast= 192.219.255.255|BroadCast= 192.223.255.255|BroadCast= 192.227.255.255|BroadCast= 192.231.255.255|" +
                "BroadCast= 192.235.255.255|BroadCast= 192.239.255.255|BroadCast= 192.243.255.255|BroadCast= 192.247.255.255|BroadCast= 192.251.255.255|BroadCast= 192.255.255.255|");
    }

    @Test
    void testturnIDsIntoDecimal() {
        Assertions.assertEquals(calculate.turnIDsIntoDecimal( "11111111111111111111111100000000", "1.1.1.0", "1.1.1"), "ID 0= 1.1.1.0/24|");
        Assertions.assertNotEquals(calculate.turnIDsIntoDecimal( "11111111111111111111111100000000", "1.1.1.0", "1.1.1"), "ID 0= 1.1.1.1");
        Assertions.assertEquals(calculate.turnIDsIntoDecimal( "11111111111111111111111100000000", "192.168.1.0", "192.168.1"), "ID 0= 192.168.1.0/24|");
        Assertions.assertEquals(calculate.turnIDsIntoDecimal( "11111111111111110000000000000000", "192.168.0.0", "192.168"), "ID 0= 192.168.0.0/16|");
        Assertions.assertEquals(calculate.turnIDsIntoDecimal( "11111111000000000000000000000000", "192.0.0.0", "192"), "ID 0= 192.0.0.0/8|");
        Assertions.assertEquals(calculate.turnIDsIntoDecimal( "11111111111111111111111110000000", "192.168.1.0", "192.168.1"), "ID 0= 192.168.1.0/25|ID 128= 192.168.1.128/25|");
        Assertions.assertEquals(calculate.turnIDsIntoDecimal( "11111111111111111111111111100000", "192.168.1.0", "192.168.1"), "ID 0= 192.168.1.0/27|ID 32= 192.168.1.32/27|ID 64= 192.168.1.64/27|" +
                "ID 96= 192.168.1.96/27|ID 128= 192.168.1.128/27|ID 160= 192.168.1.160/27|ID 192= 192.168.1.192/27|ID 224= 192.168.1.224/27|");
        Assertions.assertEquals(calculate.turnIDsIntoDecimal( "11111111111111000000000000000000", "192.0.0.0", "192"), "ID 0= 192.0.0.0/14|ID 4= 192.4.0.0/14|ID 8= 192.8.0.0/14|ID 12= 192.12.0.0/14|" +
                "ID 16= 192.16.0.0/14|ID 20= 192.20.0.0/14|ID 24= 192.24.0.0/14|ID 28= 192.28.0.0/14|ID 32= 192.32.0.0/14|ID 36= 192.36.0.0/14|ID 40= 192.40.0.0/14|ID 44= 192.44.0.0/14|ID 48= 192.48.0.0/14|ID 52= 192.52.0.0/14|ID 56= 192.56.0.0/14|" +
                "ID 60= 192.60.0.0/14|ID 64= 192.64.0.0/14|ID 68= 192.68.0.0/14|ID 72= 192.72.0.0/14|ID 76= 192.76.0.0/14|ID 80= 192.80.0.0/14|ID 84= 192.84.0.0/14|ID 88= 192.88.0.0/14|ID 92= 192.92.0.0/14|ID 96= 192.96.0.0/14|ID 100= 192.100.0.0/14|" +
                "ID 104= 192.104.0.0/14|ID 108= 192.108.0.0/14|ID 112= 192.112.0.0/14|ID 116= 192.116.0.0/14|ID 120= 192.120.0.0/14|ID 124= 192.124.0.0/14|ID 128= 192.128.0.0/14|ID 132= 192.132.0.0/14|ID 136= 192.136.0.0/14|ID 140= 192.140.0.0/14|" +
                "ID 144= 192.144.0.0/14|ID 148= 192.148.0.0/14|ID 152= 192.152.0.0/14|ID 156= 192.156.0.0/14|ID 160= 192.160.0.0/14|ID 164= 192.164.0.0/14|ID 168= 192.168.0.0/14|ID 172= 192.172.0.0/14|ID 176= 192.176.0.0/14|ID 180= 192.180.0.0/14|" +
                "ID 184= 192.184.0.0/14|ID 188= 192.188.0.0/14|ID 192= 192.192.0.0/14|ID 196= 192.196.0.0/14|ID 200= 192.200.0.0/14|ID 204= 192.204.0.0/14|ID 208= 192.208.0.0/14|ID 212= 192.212.0.0/14|ID 216= 192.216.0.0/14|ID 220= 192.220.0.0/14|" +
                "ID 224= 192.224.0.0/14|ID 228= 192.228.0.0/14|ID 232= 192.232.0.0/14|ID 236= 192.236.0.0/14|ID 240= 192.240.0.0/14|ID 244= 192.244.0.0/14|ID 248= 192.248.0.0/14|ID 252= 192.252.0.0/14|");
    }

    @Test
    void testcheckDots() {
        Assertions.assertFalse(validate.checkDots(".."));
        Assertions.assertFalse(validate.checkDots("...."));
        Assertions.assertTrue(validate.checkDots("..."));
    }

    @Test
    void testIpLength() {
        Assertions.assertFalse(validate.isLenghtRight("1.1.1"));
        Assertions.assertFalse(validate.isLenghtRight("111111.1.1.111111"));
        Assertions.assertTrue(validate.isLenghtRight("1.1.1.1"));
        Assertions.assertTrue(validate.isLenghtRight("111.111.111.111"));
    }

    @Test
    void testSplitIp() {
        Assertions.assertFalse(validate.iP("..1.1"));
        Assertions.assertFalse(validate.iP("a.1.a.1"));
        Assertions.assertFalse(validate.iP("0.1.1.1"));
        Assertions.assertTrue (validate.iP("1.1.1.1"));
        Assertions.assertTrue (validate.iP("255.255.255.255"));
        Assertions.assertFalse(validate.iP("256.256.256.256"));
        Assertions.assertTrue(validate.iP("1.0.0.0"));
    }

    @Test
    void testSplitSNM() {
        Assertions.assertFalse(validate.sNM("..1.1"));
        Assertions.assertFalse(validate.sNM("a.1.a.1"));
        Assertions.assertFalse(validate.sNM("254.1.1.1"));
        Assertions.assertTrue(validate.sNM("255.0.0.0"));
        Assertions.assertTrue(validate.sNM("255.255.255.252"));
        Assertions.assertFalse(validate.sNM("255.255.255.254"));
    }

    @Test
    void testsnmValidation (){
        List<Integer> testList = List.of(256, 256, 256, 256);
        Assertions.assertFalse(validate.snmValidation(testList));
        testList = List.of(255, 0, 255, 0);
        Assertions.assertFalse(validate.snmValidation(testList));
        testList = List.of(255, 255, 255, 254);
        Assertions.assertFalse(validate.snmValidation(testList));
        testList = List.of(255, 255, 255, 252);
        Assertions.assertTrue(validate.snmValidation(testList));
        testList = List.of(255, 255, 255, 1);
        Assertions.assertFalse(validate.snmValidation(testList));
        testList = List.of(255, 255, 255, 127);
        Assertions.assertFalse(validate.snmValidation(testList));
        testList = List.of(255, 0, 0, 0);
        Assertions.assertTrue(validate.snmValidation(testList));
    } */
}
