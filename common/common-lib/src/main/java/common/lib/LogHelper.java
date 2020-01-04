package common.lib;

import java.io.*;

/**
 * 需要在 config.properties 配置 key:logDirectoryPath
 */
public class LogHelper {
    public static String getStackTraceInfoThrowable(Throwable e) {
        return getStackTraceInfo((Exception)e);
    };

    /**
     * 获取e.printStackTrace() 的具体信息
     */
    public static String getStackTraceInfo(Exception e) {
        StringWriter stringWriter = null;
        PrintWriter printWriter = null;

        try {
            stringWriter = new StringWriter();
            printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            printWriter.flush();
            stringWriter.flush();

            return stringWriter.toString();
        } catch (Exception ex) {
            return "getStackTraceInfo 发生错误";
        } finally {
            if (stringWriter != null) {
                try {
                    stringWriter.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

    /**
     * 写文件日志-
     *
     * @param content
     * @param fileName 不需要传路径，只传 log 文件名即可。
     */
    public static void add(String content, String fileName) {
        String directoryPath = ConfigProperties.getValue("logDirectoryPath");
        FileWriter fileWriter;
        fileName = directoryPath + "/" + fileName;

        try {
            File file = new File(directoryPath);

            if (!file.isDirectory()) {
                file.mkdir();
            }

            //如果文件存在，则追加内容；如果文件不存在，则创建文件
            file = new File(fileName);
            fileWriter = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(content);
        printWriter.flush();

        try {
            fileWriter.flush();
            printWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void add(String method, Exception e) {
        add(method, "", e);
    }

    public static void add(String method, String parameter, Exception e) {
        String env = ConfigProperties.getValue("evn");
        String content = "     time:" + DateHelper.getCurrentTimeForDefault() + "\r\n"
                + "      env: " + env + "\r\n"
                + "   method: " + method + "\r\n"
                + "parameter: " + parameter + "\r\n"
                + "    error:\r\n" + getStackTraceInfo(e)
                + "\r\n----------------------------\r\n";
        add(content, DateHelper.getCurrentTimeNoHour() + ".log");
    }

    public static String getExceptionMessage(Exception e) {
        Throwable throwable = e.getCause();
        String errMessage = "";

        if (throwable != null) {
            errMessage = e.getCause().toString();
        }

        if (errMessage == null || errMessage.length() == 0) {
            errMessage = e.getMessage();
        } else {
            errMessage += e.getMessage() == null ?  "" : "\n" + e.getMessage();
        }

        return errMessage;
    }
}
