import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator {

    public String turnIntoDecimalBC(String snmBinary, String decimalAddress, String decimalAddressTwo) {
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

    public String turnIDsIntoDecimal(String snmBinary, String decimalAddress, String decimalAddressTwo) {
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

    public String calcSize(String  snmBinary) {
        snmBinary = snmBinary.substring(snmBinary.lastIndexOf("1"));
        snmBinary = "0".repeat(Math.max(0, 8 - snmBinary.length())) + snmBinary;
        return Integer.parseInt(snmBinary, 2) + "";
    }

    public String decimal(String binaryAddress) {
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

    public List<List<String>> iPs(String iDs, String bCs) {
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

    public int hosts(String snmBinary) {
        return (int) (Math.pow(2, StringUtils.countMatches(snmBinary, "0"))-2);
    }

}
