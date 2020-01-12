package common.lib;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {
    /*
     * 获取字符串中第N次出现的字符位置 没有出现N次，则返回-1
     */
    public static int getCharacterPosition(String original, String character, int n) {
        Matcher matcher = Pattern.compile(character).matcher(original);
        int index = 0;

        while (matcher.find()) {
            index++;

            if (index == n) {
                break;
            }
        }

        if (index < n) {
            return -1;
        }

        index = -1;
        int times = 0;

        do {
            index = original.indexOf(character, index + 1);
            times++;
        } while (times < n);

        return index;
    }

    /**
     * 短信字数
     */
    public static int getSmsWords(String smsContent) {
        if (smsContent != null) {
            return smsContent.length();
        } else {
            return 0;
        }
    }

    /**
     * 实际扣费（条） 计费规则：
     * 1、单条70个字,超出70个字将按照67个字每条计算
     * 2、一个汉字,数字,字母,空格都算一个字
     * 3、带标签的短信按实际发出的长度计算!
     */
    public static int getSmsCounts(String smsContent) {
        int smsWords = getSmsWords(smsContent);
        int smsCounts = 1;
        if (smsWords > 70) {
            if (smsWords % 67 == 0) {
                smsCounts = smsWords / 67;
            } else {
                smsCounts = (smsWords / 67) + 1;
            }
        }

        return smsCounts;
    }

    /**
     * 判断是否为手机号码
     */
    public static boolean isMobile(final String str) {
        Pattern p = Pattern.compile("^[1][3-9]+\\d{9}$"); // 验证手机号
        Matcher m = p.matcher(str);

        return m.matches();
    }

    /**
     * 判断字符串是否为数字，匹配所有的数字，包括负数
     * 不适用于科学计数法
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        //Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    /**
     * 判断字符串是否为数字，匹配所有的数字，包括负数
     * 适用于科学计数法
     */
    public static boolean isNumericWithScientificNotation(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        String bigStr;

        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }

        Matcher isNum = pattern.matcher(bigStr); // matcher是全匹配
        return isNum.matches();
    }

    public static boolean isInteger(String str) {
        //Pattern pattern = Pattern.compile("[0-9]*");
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    public static void setListByStringSource(List<String> targetList, String sourceKeys) {
        if (sourceKeys.length() == 0) {
            return;
        }

        String[] keyArray = sourceKeys.split(",");
        targetList.addAll(Arrays.asList(keyArray));
    }
}
