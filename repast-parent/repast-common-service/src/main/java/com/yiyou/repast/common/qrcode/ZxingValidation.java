package com.yiyou.repast.common.qrcode;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

/**
 * Created by leonard on 03/28/16.
 */
public class ZxingValidation {
    public static boolean checkEncoding(String encoding) {
        return StandardCharsets.ISO_8859_1.name().equals(encoding) || StandardCharsets.UTF_8.name().equals(encoding)
                || Charset.forName("Shift_JIS").name().equals(encoding);
    }
    public static boolean checkEcLevel (String level) {
        return "l".equals(level.toLowerCase()) || "m".equals(level.toLowerCase()) || "q".equals(level.toLowerCase())
                || "h".equals(level.toLowerCase());
    }
    public static boolean isNum (String num) {
        return Pattern.compile("^[0-9]+$").matcher(num).matches();
    }
    public static boolean isHex (String hex) {
        return hex.startsWith("0x") && Pattern.compile("^0x[0-9A-fa-f]{6}$").matcher(hex).matches();
    }
    public static boolean checkFormat (String format) {
        return "png".equals(format) || "jpg".equals(format) || "jpeg".equals(format)
                || "gif".equals(format) || "text".equals(format) || "svg".equals(format);
    }
}
