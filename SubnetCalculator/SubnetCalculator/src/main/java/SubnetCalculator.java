import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class SubnetCalculator{
    static List<Integer> ipSequenzList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Willkommen beim Netzwerkrechner!");
        String ipBinary = checkUserIPInput();
        String snmBinary = checkUserSNMInput();
        int logicalAnd = (snmBinary.lastIndexOf("1")+1);
        String iDBinary = ipBinary.substring(0, logicalAnd);
        String[] decimalAdr = decimal(iDBinary).split("/");
        String decimalAdress = decimalAdr[0];
        String decimalAdressTwo = decimalAdr[1];
        String iDs = turnIDsIntoDecimal(snmBinary, decimalAdress, decimalAdressTwo);
        String bCs = turnIntoDecimalBC(snmBinary, decimalAdress, decimalAdressTwo);
        List<List<String>> iPs = calculateIPs(iDs, bCs);
        resultOutputofIDsBCsIPs(iDs, bCs, iPs);
    }

    private static String checkUserIPInput() {
        String userInput;
        do {
            System.out.println("Bitte gib eine IP im Format: 1.1.1.1 ein!");
            Scanner scan = new Scanner(System.in);
            userInput = scan.nextLine();
        } while (!checkDots(userInput) || !isLenghtRight(userInput) || !splitIP(userInput));
        return stringtoBinaryString(userInput);
    }

    private static String checkUserSNMInput() {
        String userInput;
        do {
            System.out.println("Bitte gib eine Subnetzmaske ein!");
            Scanner scan = new Scanner(System.in);
            userInput = scan.nextLine();
        } while (!checkDots(userInput) || !isLenghtRight(userInput) || !splitSNM(userInput));
        return stringtoBinaryString(userInput);
    }

    public static String stringtoBinaryString(String userInput) {
        List<String> stringList = Arrays.asList(userInput.split("\\."));
        StringBuilder binaryStringResult = new StringBuilder();
        for (int i = 3; i >= 0; i--) {
            String binaryString = Integer.toBinaryString(Integer.parseInt(stringList.get(i)));
            binaryStringResult.insert(0, "0".repeat(Math.max(0, 8 - binaryString.length())) + binaryString);
        }
        return binaryStringResult.toString();
    }

    public static boolean checkDots(String value) {
        int count = StringUtils.countMatches(value, ".");
        return count == 3;
    }

    public static boolean isLenghtRight(String value) {
        int valuelength = value.length();
        return valuelength >= 7 && valuelength <= 15;
    }

    public static boolean splitIP(String ip) {
        List<String> sequentList = Arrays.asList(ip.split("\\."));
        for (int i = 0; i < 4; i++) {
            if (sequentList.get(i).isEmpty()) {
                System.out.println("Zuwenig Sequenz Inhalt!");
                return false;
            }
            if (sequentList.get(0).equals("0")) {
                System.out.println("Erste Sequenz darf nicht 0 sein!");
                return false;
            }
            if (!StringUtils.isNumeric(sequentList.get(i))) {
                System.out.println("Ungültige Eingabe! Gib bitte eine gültige IP ein!");
                return false;
            } else {
                int sequence = Integer.parseInt(sequentList.get(i));
                if (sequence < 256) {
                    ipSequenzList.add(sequence);
                } else {
                    System.out.println("Zahl zu groß!");
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean splitSNM(String snm) {
        List<Integer> snmSequenzList = new ArrayList<>();
        List<String> sequentList = Arrays.asList(snm.split("\\."));
        for (int i = 0; i < 4; i++) {
            if (sequentList.get(i).isEmpty()) {
                System.out.println("Zuwenig Sequent Inhalt!");
                return false;
            }
            if (!sequentList.get(0).equals("255")) {
                System.out.println("Erste Sequenz muss 255 sein!");
                return false;
            }
            if (!StringUtils.isNumeric(sequentList.get(i))) {
                System.out.println("Ungültige Eingabe! Gib bitte eine gültige IP ein!");
                return false;
            } else {
                snmSequenzList.add(Integer.parseInt(sequentList.get(i)));
            }
        }
        return snmValidation(snmSequenzList);
    }

    public static boolean snmValidation(List<Integer> snmSequenzList) {
        for (int i = 0; i < 4; i++) {
            if (snmSequenzList.get(i) > 255  || snmSequenzList.get(i) < 128 && snmSequenzList.get(i) >0 ) {
                System.out.println("Bitte geben Sie Zahlen zwischen 128 und 255 oder 0 an!");
                return false;
            }
            if (snmSequenzList.get(3) >252) {
                System.out.println("Diese Subnetzmaske lässt Sie kein kommunikatives Netzwerk aufbauen");
                return false;
            }
        }
        StringBuilder binarySNM = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            binarySNM.append(Integer.toBinaryString(snmSequenzList.get(i)));
        }
        boolean isZeroFound = false;
        boolean isAfterZeroAOne = false;
        for (int i = 0; i < binarySNM.length(); i++) {
            char charr = binarySNM.charAt(i);
            if (charr == '0') {
                isZeroFound = true;
            }
            if (isZeroFound) {
                if (charr == '1') {
                    isAfterZeroAOne = true;
                }
            }
        }
        if (isZeroFound && isAfterZeroAOne) {
            System.out.println("Eingabe ungültig! Für die SNM sind lediglich die Zahlen 0, 128, 192, 224, 240, 248, 252, 254, 255 erlaubt!");
            return false;
        }
        return true;
    }

    public static String turnIntoDecimalBC(String snmBinary, String decimalAddress, String decimalAddressTwo) {
        StringBuilder decimalAddressBCs = new StringBuilder();
        if (snmBinary.lastIndexOf("1") == 23) {
            decimalAddress = "BroadCast= " + decimalAddressTwo+".255|";
        } else if (snmBinary.lastIndexOf("1") == 15) {
            decimalAddress = "BroadCast= " + decimalAddressTwo+".255.255|";
        } else if (snmBinary.lastIndexOf("1") >=16 && snmBinary.lastIndexOf("1") <23) {
            snmBinary = snmBinary.substring(16, 24);
            String iDsFromSNM = calcSize(snmBinary);
            for (int i = Integer.parseInt(iDsFromSNM); i <= 256; i = i + Integer.parseInt(iDsFromSNM)) {
                decimalAddressBCs.append("BroadCast= ").append(decimalAddressTwo).append(".").append(i - 1).append(".255").append("|");
            }
            decimalAddress = decimalAddressBCs.toString();
        }
        else if (snmBinary.lastIndexOf("1") == 7) {
            decimalAddress = "BroadCast= " + decimalAddressTwo+".255.255.255|";
        } else if (snmBinary.lastIndexOf("1") >=8 && snmBinary.lastIndexOf("1") <15) {
            snmBinary = snmBinary.substring(8, 16);
            String iDsFromSNM = calcSize(snmBinary);
            for (int i = Integer.parseInt(iDsFromSNM); i <= 256; i = i + Integer.parseInt(iDsFromSNM)) {
                decimalAddressBCs.append("BroadCast= ").append(decimalAddressTwo).append(".").append(i - 1).append(".255.255").append("|");
            }
            decimalAddress = decimalAddressBCs.toString();
        }
        if (snmBinary.lastIndexOf("1") >= 24) {
            snmBinary = snmBinary.substring(24);
            String iDsFromSNM = calcSize(snmBinary);
            for (int i = Integer.parseInt(iDsFromSNM); i <= 256; i = i + Integer.parseInt(iDsFromSNM)) {
                decimalAddressBCs.append("BroadCast= ").append(decimalAddressTwo).append(".").append(i - 1).append("|");
            }
            decimalAddress = decimalAddressBCs.toString();
        }
        return decimalAddress;
    }

    public static String turnIDsIntoDecimal(String snmBinary, String decimalAddress, String decimalAddressTwo) {
        String shortSNM = snmBinary.substring(0, (snmBinary.lastIndexOf("1")+1));
        String shortSNMDecimal = shortSNM.length() + "";
        if (snmBinary.lastIndexOf("1") >= 24) {
            snmBinary = snmBinary.substring(24);
            String iDsFromSNM = calcSize(snmBinary);
            StringBuilder decimalAddressIDs = new StringBuilder();
            for (int i = Integer.parseInt(iDsFromSNM); i<255; i = i + Integer.parseInt(iDsFromSNM)) {
                decimalAddressIDs.append("ID ").append(i).append("= ").append(decimalAddressTwo).append(".").append(i).append("/").append(shortSNMDecimal).append("|");
            }
            decimalAddress = decimalAddress + "/" + shortSNMDecimal + "|" + decimalAddressIDs;
            decimalAddress = decimalAddress.substring(0,decimalAddress.lastIndexOf("/"));
        } else if (snmBinary.lastIndexOf("1") >= 16 && snmBinary.lastIndexOf("1") <23) {
            snmBinary = snmBinary.substring(16, 24);
            String iDsFromSNM = calcSize(snmBinary);
            StringBuilder decimalAddressIDs = new StringBuilder();
            for (int i = Integer.parseInt(iDsFromSNM); i<255; i = i + Integer.parseInt(iDsFromSNM)) {
                decimalAddressIDs.append("ID ").append(i).append("= ").append(decimalAddressTwo).append(".").append(i).append(".0/").append(shortSNMDecimal).append("|");
            }
            decimalAddress = decimalAddress + "/" + shortSNMDecimal + "|" + decimalAddressIDs;
            decimalAddress = decimalAddress.substring(0,decimalAddress.lastIndexOf("/"));
        } else if (snmBinary.lastIndexOf("1") >= 8 && snmBinary.lastIndexOf("1") <15) {
            snmBinary = snmBinary.substring(8, 16);
            String iDsFromSNM = calcSize(snmBinary);
            StringBuilder decimalAddressIDs = new StringBuilder();
            for (int i = Integer.parseInt(iDsFromSNM); i<255; i = i + Integer.parseInt(iDsFromSNM)) {
                decimalAddressIDs.append("ID ").append(i).append("= ").append(decimalAddressTwo).append(".").append(i).append(".0.0/").append(shortSNMDecimal).append("|");
            }
            decimalAddress = decimalAddress + "/" + shortSNMDecimal + "|" + decimalAddressIDs;
            decimalAddress = decimalAddress.substring(0,decimalAddress.lastIndexOf("/"));
        }
        return "ID 0= " + decimalAddress + "/" +  shortSNMDecimal + "|";
    }

    public static String calcSize(String  snmBinary) {
        snmBinary = snmBinary.substring(snmBinary.lastIndexOf("1"));
        snmBinary = "0".repeat(Math.max(0, 8 - snmBinary.length())) + snmBinary;
        return Integer.parseInt(snmBinary, 2) + "";
    }

    public static String decimal(String binaryAddress) {
        List<String> seqList = new ArrayList<>();
        for (int i = 0; i < 32; i = i + 8) {
            try {
                seqList.add(binaryAddress.substring(i, (i + 8)));
            } catch (StringIndexOutOfBoundsException ignored) {
            }
        }
        StringBuilder decimalAdress = new StringBuilder();
        for (String s : seqList) {
            decimalAdress.append(Integer.parseInt(s, 2)).append(".");
        }
        decimalAdress = new StringBuilder(decimalAdress.substring(0, (decimalAdress.length() - 1)));
        String decimalAdressTwo = decimalAdress.toString();
        int counter = StringUtils.countMatches(decimalAdress.toString(), ".");
        if (counter == 2) {
            decimalAdress.append(".0");
        } else if (counter == 1) {
            decimalAdress.append(".0.0");
        } else if (counter == 0) {
            decimalAdress.append(".0.0.0");
        }
        return decimalAdress + "/" +decimalAdressTwo;
    }

    public static List<List<String>> calculateIPs(String iDs, String bCs) {
        List<String> iDList = Arrays.asList(iDs.split("\\|"));
        List<String> bcList = Arrays.asList(bCs.split("\\|"));
        List<List<String>> resultLists = new ArrayList<>();
        for (int i = 0; i < iDList.size(); i++) {
            List<String> result = new ArrayList<>();
            if ( Integer.parseInt(iDList.get(i).substring(iDList.get(i).lastIndexOf("/")+1)) >= 24) {
                int firstIPs = Integer.parseInt(iDList.get(i).substring(iDList.get(i).indexOf(" ")+1, iDList.get(i).indexOf(("="))))+1;
                iDList.set(i, ("Erste IP = " + iDList.get(i).substring(iDList.get(i).indexOf("=")+2, iDList.get(i).lastIndexOf(".")+1)+firstIPs));
                result.add(iDList.get(i));
            } else if ( Integer.parseInt(iDList.get(i).substring(iDList.get(i).lastIndexOf("/")+1)) < 24) {
                iDList.set(i, ("Erste IP = " + iDList.get(i).substring(iDList.get(i).indexOf("=")+2, iDList.get(i).lastIndexOf(".")+1)+"1"));
                result.add(iDList.get(i));
            }
            int lastIPs = Integer.parseInt(bcList.get(i).substring(bcList.get(i).lastIndexOf(".") + 1))-1;
            bcList.set(i, ("Letzte IP = " + bcList.get(i).substring(bcList.get(i).indexOf("=") + 2, bcList.get(i).lastIndexOf(".") + 1) + lastIPs));
            result.add(bcList.get(i));
            resultLists.add(result);
        }
        return resultLists;
    }

    public static void resultOutputofIDsBCsIPs(String iDs, String bCs, List<List<String>> iPs) {
        List<String> iDList = Arrays.asList(iDs.split("\\|"));
        List<String> bcList = Arrays.asList(bCs.split("\\|"));
        List<List<String>> resultLists = new ArrayList<>();
        for (int i = 0; i < iDList.size(); i++) {
            List<String> result = new ArrayList<>();
            result.add(iDList.get(i));
            result.add(bcList.get(i));
            result.add(iPs.get(i).get(0));
            result.add(iPs.get(i).get(1));
            resultLists.add(result);
        }
        for (List<String> resultList : resultLists) {
            System.out.println(resultList);
        }
    }
}