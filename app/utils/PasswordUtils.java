package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * PasswordUtils Class help to deal with managing Password.
 */
public final class PasswordUtils {

    /**
     * Encode String with digest : SHA-512.
     *
     * @param value the cleared String
     * @return Byte of the digest String
     */
    public static byte[] getSha512(String value) {
        try {
            return MessageDigest.getInstance("SHA-512").digest(value.getBytes("UTF-8"));
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
