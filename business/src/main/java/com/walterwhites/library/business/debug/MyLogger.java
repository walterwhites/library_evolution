package com.walterwhites.library.business.debug;

import java.io.IOException;
import java.util.logging.*;

public class MyLogger {

    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;

    static private FileHandler fileHTML;
    static private Formatter formatterHTML;

    static public Logger mainLogger;

    public static Logger setup() throws IOException {

        if (mainLogger == null) {
            mainLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        }
        /*
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }*/

        mainLogger.setLevel(Level.INFO);

        fileTxt = new FileHandler("src/main/debug/Logging.txt");
        fileHTML = new FileHandler("src/main/debug/Logging.html");

        // create a TXT formatter
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        mainLogger.addHandler(fileTxt);

        // create an HTML formatter
        formatterHTML = new HtmlFormatter();
        fileHTML.setFormatter(formatterHTML);
        mainLogger.addHandler(fileHTML);

        return MyLogger.mainLogger;
    }

    public static Logger init() {
        try {
            MyLogger.setup();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Problems with creating the log files");
        }
        return MyLogger.mainLogger;
    }
}
