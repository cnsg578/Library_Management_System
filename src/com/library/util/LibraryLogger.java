package com.library.util;

import java.io.IOException;
import java.util.logging.*;

public class LibraryLogger {

    public static final Logger logger = Logger.getLogger("LibraryManagementSystem");

    static {

        try {

            // Disable default console handler
            logger.setUseParentHandlers(false);

            // ================= Console Handler =================
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.INFO);
            consoleHandler.setFormatter(new SimpleFormatter());

            // ================= File Handler =================
            FileHandler fileHandler = new FileHandler("library.log", true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new CustomFormatter());

            // ================= Logger Level =================
            logger.setLevel(Level.ALL);

            logger.addHandler(consoleHandler);
            logger.addHandler(fileHandler);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ================= Custom Formatter =================
    static class CustomFormatter extends Formatter {

        @Override
        public String format(LogRecord record) {

            return String.format(
                    "[%1$tF %1$tT] [%2$s] %3$s %n",
                    record.getMillis(),
                    record.getLevel().getName(),
                    record.getMessage()
            );
        }
    }
}