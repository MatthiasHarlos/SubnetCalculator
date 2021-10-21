import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class SubnetCalculator extends Object {
    static List<Integer> sequenzList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Willkommen beim Netzwerkrechner!");
        checkUserIPInput();
        checkUserSNMInput();
    }

    private static void checkUserIPInput() {
        System.out.println("Bitte geben Sie eine IP ein!");

        String userInput =" ";
        Scanner scan = new Scanner(System.in);
        userInput = scan.nextLine();
        checkDots(userInput);
        isLenghtRight(userInput);
        splitID(userInput);
    }
    
    private static void checkUserSNMInput() {
        String userInput =" ";
        Scanner scan = new Scanner(System.in);
        userInput = scan.nextLine();
        checkDots(userInput);
        isLenghtRight(userInput);
        splitID(userInput);
    }

    public static void splitID(String ip) {
            List<String> sequentList = Arrays.asList(ip.split("\\."));
                for (int i = 0; i < 4; i++) {
                    if (sequentList.get(i).isEmpty()) {
                        try {
                            throw new InputMismatchException("Zuwenig Sequent Inhalt!");
                        } catch (InputMismatchException e) {
                        checkUserIPInput();
                    }
                    }
                    if (sequentList.get(0).equals("0")) {
                        try {
                            throw new InputMismatchException("Erste Sequenz darf nicht 0 sein!");
                        } catch (InputMismatchException e) {
                            checkUserIPInput();
                        }
                    }
                    if (!StringUtils.isNumeric(sequentList.get(i))) {
                        try {
                            throw new InputMismatchException("Ungültige Eingabe! Gib bitte eine gültige IP ein!");
                        } catch (InputMismatchException e) {
                            checkUserIPInput();
                        }
                    } else {
                        int sequence = Integer.parseInt(sequentList.get(i));
                        sequentLength(sequence);
                    }
                }

    }

    public static void sequentLength(int sequent) {
        if(sequent < 256){
            sequenzList.add(sequent);
        } else {
            throw new InputMismatchException("Zahl zu groß");
        }
    }

    public static void checkDots(String ip) {
        int count = StringUtils.countMatches(ip, ".");
        if (count != 3) {
            try {
                throw new InputMismatchException("Falscher Input");
            } catch (InputMismatchException e) {
            checkUserIPInput();
        }
        }
    }

    public static boolean isLenghtRight(String ip) {
        int iplength = ip.length();
        if (iplength < 7 || iplength > 15) {
            return false;
        }
        return true;
    }
}
