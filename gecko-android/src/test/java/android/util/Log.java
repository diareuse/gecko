package android.util;


public final class Log {


    public static int v(String tag, String msg) {
        Expects.assertEquals(tag, msg);
        return 0;
    }

}
