import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validator {

    public boolean checkDots(String value) {
        int count = StringUtils.countMatches(value, ".");
        return count == 3;
    }

    public boolean isLenghtRight(String value) {
        int valuelength = value.length();
        return valuelength >= 7 && valuelength <= 15;
    }

    public boolean iP(String ip) {
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
                if (sequence > 255) {
                    System.out.println("Zahl zu groß!");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean sNM(String snm) {
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

    public boolean snmValidation(List<Integer> snmSequenzList) {
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
}
