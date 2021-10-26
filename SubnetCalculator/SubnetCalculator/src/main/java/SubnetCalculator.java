import org.apache.commons.lang3.StringUtils;
import java.util.*;

public class SubnetCalculator{

    public static IPValidator validator = new IPValidator();
    public static Calculator calculate = new Calculator();

    public static void main(String[] args) {
        System.out.println("Willkommen beim Netzwerkrechner!");
        String ipBinary = checkUserIPInput();
        String snmBinary = checkUserSNMInput();
        int logicalAnd = (snmBinary.lastIndexOf("1")+1);
        String iDBinary = ipBinary.substring(0, logicalAnd);
        String[] decimalAdr = calculate.decimal(iDBinary).split("/");
        String decimalAdress = decimalAdr[0];
        String decimalAdressTwo = decimalAdr[1];
        String iDs = calculate.turnIDsIntoDecimal(snmBinary, decimalAdress, decimalAdressTwo);
        String bCs = calculate.turnIntoDecimalBC(snmBinary, decimalAdress, decimalAdressTwo);
        List<List<String>> iPs = calculate.calculateIPs(iDs, bCs);
        int hosts = calculate.calculateHosts(snmBinary);
        resultOutputofIDsBCsIPs(iDs, bCs, iPs, hosts);
    }

    private static String checkUserIPInput() {
        String userInput;
        do {
            System.out.println("Bitte gib eine IP im Format: 1.1.1.1 ein!");
            Scanner scan = new Scanner(System.in);
            userInput = scan.nextLine();
        } while (!validator.checkDots(userInput) || !validator.isLenghtRight(userInput) || !validator.validateIP(userInput));
        return stringtoBinaryString(userInput);
    }

    private static String checkUserSNMInput() {
        String userInput;
        do {
            System.out.println("Bitte gib eine Subnetzmaske ein!");
            Scanner scan = new Scanner(System.in);
            userInput = scan.nextLine();
        } while (!validator.checkDots(userInput) || !validator.isLenghtRight(userInput) || !validator.splitAndValidateSNM(userInput));
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

    public static void resultOutputofIDsBCsIPs(String iDs, String bCs, List<List<String>> iPs, int hosts) {
        List<String> iDList = Arrays.asList(iDs.split("\\|"));
        List<String> bcList = Arrays.asList(bCs.split("\\|"));
        List<List<String>> resultLists = new ArrayList<>();
        for (int i = 0; i < iDList.size(); i++) {
            List<String> result = new ArrayList<>();
            result.add(iDList.get(i));
            result.add(bcList.get(i));
            result.add(iPs.get(i).get(0));
            result.add(iPs.get(i).get(1));
            result.add("Mögliche Hosts= " + hosts);
            resultLists.add(result);
        }
        for (List<String> resultList : resultLists) {
            System.out.println(resultList);
        }
    }
}