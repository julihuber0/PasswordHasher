import java.util.Random;

public class StringGenerator {

    public static String generateSalt() {
        return generateRandomString('0', 'z', 32);
    }

    /**
     * Generates a verification code, consisting of capital letters and numbers with a total length of 8.
     *
     * @return the verification code.
     */
    public static String generateVerificationCode() {
        return generateRandomString('0', 'Z', 8);
    }

    /**
     * Generates a random String from a given alphabet range and a length.
     *
     * @param start  The starting point in the ASCII table.
     * @param end    The end point in the ASCII table.
     * @param length The length of the generated String.
     * @return a random String.
     */
    public static String generateRandomString(char start, char end, int length) {
        int s = start;
        if (start < '0') {
            s = '0';
        }
        int e = end;
        if (end > 'z') {
            e = 'z';
        }
        int l = length;
        if (l < 1) {
            l = 1;
        }
        final Random random = new Random();
        return random.ints(s, e + 1)
                .filter(i -> Character.isLetter(i) || Character.isDigit(i))
                .limit(l)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
