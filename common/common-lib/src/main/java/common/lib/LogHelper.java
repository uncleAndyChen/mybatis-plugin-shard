package common.lib;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogHelper {
    /**
     * 写文件日志-
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

    public static void add(String bizID, String method, Exception e) {
        String content = "time:" + DateUtils.getCurrentTimeForDefault() + "\r\n" + method + " error:\r\n" + CommonFunction.getStackTraceInfo(e) + "\r\n----------------------------\r\n";
        add(content, bizID + "-" + DateUtils.getCurrentTimeNoHour() + ".log");
    }
}
