import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class IPAddress extends NetworkAddress{
    private int first;
    private int second;
    private int third;
    private int fourth;

    public IPAddress(String input) {
        super(input);
        if (!iP(input)) {
            throw new InputMismatchException("no valid IP");
        }
        List<String> componentsString = Arrays.asList(input.split("\\."));
        List<Integer> componentsInt = new ArrayList<>();
        for (String component : componentsString) {
            componentsInt.add(Integer.parseInt(component));
        }
        setFirst(componentsInt.get(0));
        setSecond(componentsInt.get(1));
        setThird(componentsInt.get(2));
        setFourth(componentsInt.get(3));
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

    @Override
    public String toString() {
        return this.first + "." + this.second + "." + this.third + "." + this.fourth;
    }

    public String toBinaryString() {
        String result = "";
        result += componentToBinaryString(this.getFirst());
        result += componentToBinaryString(this.getSecond());
        result += componentToBinaryString(this.getThird());
        result += componentToBinaryString(this.getFourth());

        return result;
    }

    private String componentToBinaryString(int component) {
        String result = Integer.toBinaryString(component);

        while (result.length() <= 7) {
            result = "0" + result;
        }
        return result;
    }


    public void setFirst(int first) {
        validateComponentRange(first, "first");
        this.first = first;
    }

    public void setSecond(int second) {
        validateComponentRange(second, "second");
        this.second = second;
    }

    public void setThird(int third) {
        validateComponentRange(third, "third");
        this.third = third;
    }

    public void setFourth(int fourth) {
        validateComponentRange(fourth, "fourth");
        this.fourth = fourth;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public int getThird() {
        return third;
    }

    public int getFourth() {
        return fourth;
    }

    public void validateComponentRange(int value, String component) {
        if (value < 1 || value > 255) {
            throw new IllegalArgumentException(component + " out of Range");
        }
    }
}
