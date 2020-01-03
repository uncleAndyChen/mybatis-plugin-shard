package common.lib;

import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.util.Optional;

public class CommonFunction {
    public static String base64Decode(String source) {
        return new String(new Base64().decode(source));
    }

    public static String urlAndBase64Decode(String source) {
        try {
            String result = URLDecoder.decode(source, "UTF-8");
            result = base64Decode(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return source;
        }
    }

    public static int intEmptyToMinOrMaxValue(String type, String value) {
        int result;

        if (value.isEmpty()) {
            result = type.equals("min") ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        } else {
            result = Integer.parseInt(value);
        }

        return result;
    }

    public static String floatEmptyToMinOrMaxValue(String type, String value) {
        if (value.isEmpty()) {
            value = String.valueOf(type.equals("min") ? Float.MIN_VALUE : Float.MAX_VALUE);
        }

        return value;
    }

    /**
     * 判断对象Optional是否为空
     */
    public static boolean optionalIsNull(Optional<?> optional) {
        if (!optional.isPresent() || optional == Optional.empty()) {
            return true;
        }

        return false;
    }

    /**
     * 获取e.printStackTrace() 的具体信息
     */
    public static String getStackTraceInfo(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;

        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();

            return sw.toString();
        } catch (Exception ex) {
            return "getStackTraceInfo 发生错误";
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if (pw != null) {
                pw.close();
            }
        }
    }

    public static long md5Int(String md5) {
        Long n1 = Long.parseLong(md5.substring(0, 8), 16);
        Long n2 = Long.parseLong(md5.substring(8, 15), 16);
        Long n3 = Long.parseLong(md5.substring(18, 23), 16);
        Long n4 = Long.parseLong(md5.substring(24, 31), 16);
        return Math.abs(n1 - n2 + n3 - n4);
    }
}
