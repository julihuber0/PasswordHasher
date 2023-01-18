import org.apache.commons.codec.binary.Hex;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Logger;

public class PasswordHash {


    private static final String HASH_ALGORITHM = "PBKDF2WithHmacSHA512";

    /**
     * The local logger of the password security class.
     */
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Generates the hash value of a given password and a salt by using the PBKDF2 algorithm.
     *
     * @param password The password to be hashed.
     * @param salt     The salt the password will be concatenated with.
     * @return the hashed password.
     */
    public static String hash(char[] password, String salt) {
        byte[] bSalt = salt.getBytes();
        byte[] h = generatePasswordHash(password, bSalt);
        assert h != null;
        return Hex.encodeHexString(h);
    }

    /**
     * Calculates the hash value from a password and a salt with the PBKDF2 hash algorithm. The default values for
     * iterations and length are 65565 and 512.
     *
     * @param password The password to be hashed.
     * @param salt     The salt to be used for salting the password.
     * @return The hashed password as byte array.
     */
    private static byte[] generatePasswordHash(char[] password, byte[] salt) {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance(HASH_ALGORITHM);
            PBEKeySpec spec = new PBEKeySpec(password, salt, Integer.parseInt("65536"), Integer.parseInt("512"));
            SecretKey key = skf.generateSecret(spec);
            return key.getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        }
    }
}
