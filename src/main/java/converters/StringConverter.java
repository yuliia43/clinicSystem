package converters;

import java.io.UnsupportedEncodingException;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class StringConverter {
    public static String convertToUTF8(String string) throws UnsupportedEncodingException {
        return new String(string.getBytes("ISO-8859-1"), "UTF-8");
    }
}
