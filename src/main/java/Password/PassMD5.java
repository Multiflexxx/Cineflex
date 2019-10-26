package Password;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Hash a given String with MD5 Algorithm
public class PassMD5 {
    /**
     *
     * @param pass
     * @return hash
     * @throws NoSuchAlgorithmException
     */
    public static String hash(String pass) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = md.digest(pass.getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
