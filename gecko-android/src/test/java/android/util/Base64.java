package android.util;

public final class Base64 {
    public static final int NO_WRAP = 2;
    public static final int URL_SAFE = 8;

    public static byte[] encode(byte[] input, int flags) {
        if ((flags & URL_SAFE) == URL_SAFE) {
            return java.util.Base64.getUrlEncoder().encode(input);
        } else {
            return java.util.Base64.getEncoder().encode(input);
        }
    }

}
