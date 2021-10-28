import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class NetworkAddress {

    public NetworkAddress(String input) {
        if (!checkDots(input) || !isLenghtRight(input)) {
            throw new InputMismatchException("no valid IP");
        }
    }

    public boolean checkDots(String value) {
        int count = StringUtils.countMatches(value, ".");
        return count == 3;
    }

    public boolean isLenghtRight(String value) {
        int valuelength = value.length();
        return valuelength >= 7 && valuelength <= 15;
    }
}
