package common.lib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 提供了常用的日期处理功能
 */
public final class DateHelper {

    public static final String defaultFormat = "yyyy-MM-dd HH:mm:ss";
    public static final String forNewFileFormatNoMillisecond = "yyyyMMddHHmmss";
    public static final String forNewFileFormat = "yyyyMMddHHmmssSSS";
    public static final String yearMonthFormat = "yyyyMM";
    public static final String defaultFormatNoHour = "yyyy-MM-dd";
    public static final int oneDaySeconds = 24 * 60 * 60;
    public static final SimpleDateFormat simpleDateFormatWithMillisecond = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    /**
     * 验证时间字符串格式输入是否正确
     */
    public static boolean isValidDate(String timeStr, String datePattern) {
        SimpleDateFormat format = new SimpleDateFormat(datePattern);

        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2018/02/29会被接受，并转换成2018/03/01
            format.setLenient(false);
            format.parse(timeStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 获取当前时间,格式为yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getCurrentTimeForDefault() {
        return getDate(new Date(), defaultFormat);
    }

    public static String getDateDefaultFormat(Date date) {
        return getDate(date, defaultFormat);
    }

    public static String getCurrentTimeShortDate() {
        return getDate(new Date(), defaultFormatNoHour);
    }

    public static String getCurrentTimeShortDate(int dates) {
        long time = new Date().getTime();
        time += dates * oneDaySeconds * 1000;
        return getDate(new Date(time), defaultFormatNoHour);
    }

    public static String getCurrentTimeForNewFile() {
        return getDate(new Date(), forNewFileFormat);
    }

    public static String getCurrentTimeForNewFileNoMillisecond() {
        return getDate(new Date(), forNewFileFormatNoMillisecond);
    }

    public static String getCurrentTimeForYearAndMonth() {
        return getDate(new Date(), yearMonthFormat);
    }

    public static String getDate(Date date, String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(date);
    }

    public static Date getDate(String dateStr, String format) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.parse(dateStr);
    }

    public static Date getDate(int unixTimestamp) {
        return new Date(unixTimestamp * 1000);
    }

    public static long getTime(String dateStr, String format) {
        Date date;
        try {
            date = getDate(dateStr, format);
            return date.getTime();
        } catch (ParseException e) {
            return -1;
        }
    }

    /**
     * @param dateStr 字符串日期数据 格式为 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static long getTime(String dateStr) {
        return getTime(dateStr, defaultFormat);
    }

    /**
     * 获取当天凌晨时间
     *
     * @return
     */
    public static Date getWeeHours() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当天23:59:59时间
     *
     * @return
     */
    public static Date getTwentyFourHours() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    public static int getCurrentTimeNoHourUnixTimestamp() {
        String dateString = getCurrentTimeNoHour();
        return getUnixTimestampByStringDate(dateString, defaultFormatNoHour);
    }

    public static int getNoHourUnixTimestamp(int dateTime) {
        SimpleDateFormat format = new SimpleDateFormat(defaultFormatNoHour);
        String strDate = format.format(new Date(Long.valueOf(dateTime + "000")));
        return getUnixTimestampByStringDate(strDate, defaultFormatNoHour);
    }

    public static int getUnixTimestampByStringLongDate(String strDate) {
        return getUnixTimestampByStringDate(strDate, defaultFormat);
    }

    public static int getUnixTimestampByStringDateDefault(String strDate) {
        return getUnixTimestampByStringDate(strDate, defaultFormatNoHour);
    }

    public static int getUnixTimestampByStringDate(String strDate, String dateFormat) {
        if (strDate.equals("1970-01-01")) {
            return -1;
        }

        SimpleDateFormat format = new SimpleDateFormat(dateFormat);

        try {
            Date date = format.parse(strDate);
            return Integer.parseInt(String.valueOf(date.getTime()).substring(0, 10));
        } catch (ParseException e) {
            e.printStackTrace();
            System.err.println("strDate:" + strDate);
            return -1;
        }
    }

    public static String getCurrentTimeNoHour() {
        return getDate(new Date(), defaultFormatNoHour);
    }

    public static String getPreviousOrNextDay(String time, int dayNum) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(defaultFormatNoHour);
        Date date = df.parse(time);
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.setTime(date);
        currentDate.add(GregorianCalendar.DATE, dayNum);

        Date monday = currentDate.getTime();
        String preMonday = df.format(monday);
        return preMonday;
    }

    /**
     * Unix时间戳，10位整数
     * 不再使用推荐，以后都调用getCurrnetTimeUnixTimestamp()
     */
    public static int getCurrentTimeUnixTimestamp() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static int getUnixTimestamp(Date date) {
        if (date == null) {
            return 0;
        }
        String dateTime = String.valueOf(date.getTime()).substring(0, 10);
        return Integer.parseInt(dateTime);
    }

    public static int getUnixTimestamp(String date) {
        Date dateTime = StrToDate(date);

        if (dateTime == null) {
            return -1;
        }

        String result = String.valueOf(dateTime.getTime()).substring(0, 10);
        return Integer.parseInt(result);
    }

    public static long DateToLong(String time) {
        Date date;
        try {
            date = new SimpleDateFormat(defaultFormat).parse(time);
            return Long.valueOf(String.valueOf(date.getTime()).substring(0, 10));
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }

    }

    /**
     * 日期转换成字符串
     */
    public static String dateToStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    /**
     * 日期转换成字符串
     */
    public static String dateToStrWithMillisecond(Date date) {
        return simpleDateFormatWithMillisecond.format(date);
    }

    /**
     * 字符串转换成日期
     */
    public static Date StrToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;

        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    /**
     * 将时间戳转换成格式化的日期，精确到天（不带年、月、日）
     */
    public static String timestampToFormatDatePreciseToHour(int timestamp) {
        return timestampToFormatDate(timestamp, "HH:mm:ss");
    }

    /**
     * 将PHP10位时间戳转换为java时间
     */
    public static String timestampToFormatDateYMDHMS(int timestamp) {
        return timestampToFormatDate(timestamp, defaultFormat);
    }

    /**
     * 将时间戳转换成格式化的日期，精确到天（不带小时、分、秒）
     */
    public static String timestampToFormatDatePreciseToDay(int timestamp) {
        return timestampToFormatDate(timestamp, "yyyy-MM-dd");
    }

    private static String timestampToFormatDate(int timestamp, String format) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        //将php10位时间戳变成java13位时间戳格式
        Date date = new Date(timestamp * 1000);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 两个时间戳相差天数
     */
    public static int getDifferentDays(int dateBegin, int dateEnd) {
        return (dateEnd - dateBegin) / (24 * 60 * 60);
    }

    public static int getNextSomeDaysUnixTimestamp(int nextDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, nextDays);
        Date currDate = calendar.getTime();

        return getUnixTimestamp(currDate);
    }

    /**
     * 根据传入天数，返回yyyy-MM-dd格式的日期
     */
    public static String getDateByDayNumber(int dayNumber) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, dayNumber);
        String start = format.format(c.getTime());

        return start;
    }

    /**
     * 根据传入年数，返回yyyy-MM-dd格式的日期
     */
    public static String getDateByYearNumber(int year) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, year);
        String start = format.format(c.getTime());

        return start;
    }

    /**
     * 根据时间戳获取
     */
    public static String getHourAndMinute(int timeStamp) {
        String date = timestampToFormatDateYMDHMS(timeStamp);
        return date.substring(11, 16);
    }

    public static Date monthAddNum(Date date, Integer num){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, num);
        return calendar.getTime();
    }

    public static Date dateAddNum(Date date, Integer num){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, num);
        return calendar.getTime();
    }
}