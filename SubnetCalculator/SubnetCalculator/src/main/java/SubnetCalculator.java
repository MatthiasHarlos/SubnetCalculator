import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class SubnetCalculator{
    static List<Integer> ipSequenzList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Willkommen beim Netzwerkrechner!");
        String ipBinary = checkUserIPInput();
        String snmBinary = checkUserSNMInput();
        String iD = calculateIDs(ipBinary, snmBinary);
        System.out.println(iD);
        String bc = calculateBC(ipBinary, snmBinary);
        System.out.println(bc);
    }

    public static String calculateBC(String ipBinary, String snmBinary) {
        int logicalAnd = (snmBinary.lastIndexOf("1")+1);
        ipBinary = ipBinary.substring(0, logicalAnd);
        String iDBinary = ipBinary;
        return turnIntoDecimalBC(iDBinary, snmBinary);
    }

    public static String turnIntoDecimalBC(String binaryAddress, String snmBinary) {
        String[] decimalAdr = decimal(binaryAddress).split("\\/");
        String decimalAdress = decimalAdr[0];
        String decimalAdressTwo = decimalAdr[1];
        String decimalAddressBCs = "";
        if (snmBinary.lastIndexOf("1") == 23) {
            decimalAdress = "ID 0 BroadCast= " + decimalAdressTwo+".255";
        } else if (snmBinary.lastIndexOf("1") == 15) {
            decimalAdress = "ID 0 BroadCast= " + decimalAdressTwo+".255.255";
        } else if (snmBinary.lastIndexOf("1") >=16 && snmBinary.lastIndexOf("1") <23) {
            snmBinary = snmBinary.substring(16, 24);
            String iDsFromSNM = calcSize(snmBinary);
            for (int i = Integer.parseInt(iDsFromSNM); i <= 256; i = i + Integer.parseInt(iDsFromSNM)) {
                decimalAddressBCs += "ID " + (i-Integer.parseInt(iDsFromSNM)) + " BroadCast= " + decimalAdressTwo + "." + (i-1) + ".255" + " ";
            }
            decimalAdress = decimalAddressBCs;
        }
        else if (snmBinary.lastIndexOf("1") == 7) {
            decimalAdress = "ID 0 BroadCast= " + decimalAdressTwo+".255.255.255";
        } else if (snmBinary.lastIndexOf("1") >=8 && snmBinary.lastIndexOf("1") <15) {
            snmBinary = snmBinary.substring(8, 16);
            String iDsFromSNM = calcSize(snmBinary);
            for (int i = Integer.parseInt(iDsFromSNM); i <= 256; i = i + Integer.parseInt(iDsFromSNM)) {
                decimalAddressBCs += "ID " + (i-Integer.parseInt(iDsFromSNM)) + " BroadCast= " + decimalAdressTwo + "." + (i-1) + ".255.255" + " ";
            }
            decimalAdress = decimalAddressBCs;
        }
        if (snmBinary.lastIndexOf("1") >= 24) {
            snmBinary = snmBinary.substring(24);
            String iDsFromSNM = calcSize(snmBinary);
            for (int i = Integer.parseInt(iDsFromSNM); i <= 256; i = i + Integer.parseInt(iDsFromSNM)) {
                decimalAddressBCs += "ID " + (i-Integer.parseInt(iDsFromSNM)) + " BroadCast= " + decimalAdressTwo + "." + (i-1) + " ";
            }
            decimalAdress = decimalAddressBCs;
        }
        return decimalAdress;
    }

    public static String calculateIDs(String ipBinary, String snmBinary) {
        int logicalAnd = (snmBinary.lastIndexOf("1")+1);
        ipBinary = ipBinary.substring(0, logicalAnd);
        String iDBinary = ipBinary;
        return turnIPIntoDecimal(iDBinary, snmBinary);
    }

    public static String turnIPIntoDecimal(String binaryAddress, String snmBinary) {
        String[] decimalAdr = decimal(binaryAddress).split("\\/");
        String decimalAdress = decimalAdr[0];
        String decimalAdressTwo = decimalAdr[1];
        String shortSNM = snmBinary.substring(0, (snmBinary.lastIndexOf("1")+1));
        String shortSNMDecimal = shortSNM.length() + "";
        if (snmBinary.lastIndexOf("1") >= 24) {
            snmBinary = snmBinary.substring(24);
            String iDsFromSNM = calcSize(snmBinary);
            String decimalAddressIDs ="";
            for (int i = Integer.parseInt(iDsFromSNM); i<255; i = i + Integer.parseInt(iDsFromSNM)) {
                decimalAddressIDs += "ID " + i + "= " + decimalAdressTwo+"."+i + " ";
            }
            decimalAdress = "ID 0= " + decimalAdress + "/" + shortSNMDecimal + " " + decimalAddressIDs;
        } else if (snmBinary.lastIndexOf("1") >= 16 && snmBinary.lastIndexOf("1") <23) {
            snmBinary = snmBinary.substring(16, 24);
            String iDsFromSNM = calcSize(snmBinary);
            String decimalAddressIDs ="";
            for (int i = Integer.parseInt(iDsFromSNM); i<255; i = i + Integer.parseInt(iDsFromSNM)) {
                decimalAddressIDs += "ID " + i + "= " + decimalAdressTwo+"."+i + ".0" + " ";
            }
            decimalAdress = "ID 0= " + decimalAdress + "/" + shortSNMDecimal + " " + decimalAddressIDs;
        } else if (snmBinary.lastIndexOf("1") >= 8 && snmBinary.lastIndexOf("1") <15) {
            snmBinary = snmBinary.substring(8, 16);
            String iDsFromSNM = calcSize(snmBinary);
            String decimalAddressIDs ="";
            for (int i = Integer.parseInt(iDsFromSNM); i<255; i = i + Integer.parseInt(iDsFromSNM)) {
                decimalAddressIDs += "ID " + i + "= " + decimalAdressTwo+"."+i + ".0.0" + " ";
            }
            decimalAdress = decimalAdress + "/" + shortSNMDecimal + " " + decimalAddressIDs;
        }
        return "ID 0= " + decimalAdress + "\b" + "/" +  shortSNMDecimal;
    }

    public static String calcSize(String  snmBinary) {
        snmBinary = snmBinary.substring(snmBinary.lastIndexOf("1"));
        String zero = "";
        for (int i = snmBinary.length(); i < 8; i++) {
            zero += "0";
        }
        snmBinary = zero + snmBinary;
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
        String decimalAdress = "";
        for (int i = 0; i < seqList.size(); i++) {
            decimalAdress += Integer.parseInt(seqList.get(i), 2) + ".";
        }
        decimalAdress = decimalAdress.substring(0, (decimalAdress.length() - 1));
        String decimalAdressTwo = decimalAdress;
        int counter = StringUtils.countMatches(decimalAdress, ".");
        if (counter == 2) {
            decimalAdress = decimalAdress + ".0";
        } else if (counter == 1) {
            decimalAdress = decimalAdress + ".0.0";
        } else if (counter == 0) {
            decimalAdress = decimalAdress + ".0.0.0";
        }
        return decimalAdress + "/" +decimalAdressTwo;
    }

    public static String stringtoBinaryString(String userInput) {
        List<String> stringList = Arrays.asList(userInput.split("\\."));
        String binaryStringResult = "";
        for (int i = 3; i >= 0; i--) {
            String binaryString = Integer.toBinaryString(Integer.parseInt(stringList.get(i)));
            String zeros = "";
            for(int y = binaryString.length(); y<8; y++){
                zeros += "0";
            }
            binaryStringResult =  zeros + binaryString + binaryStringResult;
        }
        return binaryStringResult;
    }

    private static String checkUserIPInput() {
        System.out.println("Bitte gib eine IP im Format: 1.1.1.1 ein!");
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        if (!checkDots(userInput)) {
            checkUserIPInput();
        } else if (!isLenghtRight(userInput)) {
            checkUserIPInput();
        } else if (!splitIP(userInput)) {
            checkUserIPInput();
        }
        return stringtoBinaryString(userInput);
    }

    private static String checkUserSNMInput() {
        String userInput;
        while (true) {
            System.out.println("Bitte gib eine Subnetzmaske ein!");
            Scanner scan = new Scanner(System.in);
            userInput = scan.nextLine();
            if (!checkDots(userInput)) {
                checkUserSNMInput();
            } else if (!isLenghtRight(userInput)) {
                checkUserSNMInput();
            } else if (splitSNM(userInput)) {
                break;
            }
        }
        return stringtoBinaryString(userInput);
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
                boolean testSequenzLength = ipSequentLength(sequence);
                if (!testSequenzLength) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean ipSequentLength(int sequent) {
        if (sequent < 256) {
            ipSequenzList.add(sequent);
        } else {
            System.out.println("Zahl zu groß!");
            return false;
        }
        return true;
    }

    public static boolean checkDots(String ip) {
        int count = StringUtils.countMatches(ip, ".");
        return count == 3;
    }

    public static boolean isLenghtRight(String ip) {
        int iplength = ip.length();
        return iplength >= 7 && iplength <= 15;
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
                System.out.println("Zahl zu groß!");
                return false;
            }
        }
        String binarySNM = "";
        for (int i = 0; i < 4; i++) {
            binarySNM += Integer.toBinaryString(snmSequenzList.get(i));
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
            System.out.println("Eingabe ungültig!");
            return false;
        }
        return true;
    }
}
