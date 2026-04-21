package com.pluralsight;

import com.pluralsight.ui.Console;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;

public class Main {
    public static void main(String[] arg) throws IOException {
        String logfile = "logs.txt";
        FileWriter fileWriter = new FileWriter(logfile, true);

        boolean userIsSearching = true;

        String logAction = "launch";
        writeToLogs(fileWriter, "[--- LOG INITIALIZED... ---]", logAction);
        fileWriter.flush();
        do {
            String askForSearch = Console.askForString("Enter your search: (x to exit): " );
            switch (askForSearch){
                case "x":
                    System.out.println("Thank you for using search!");
                    userIsSearching = false;
                    System.out.println("A complete log of your searches has been generated in: " + logfile );

                    logAction = "terminate";
                    writeToLogs(fileWriter, "[--- END OF LOG ---]", logAction);
                    fileWriter.close();
                    break;

                default:
                    userIsSearching = true;
                    logAction = "search";
                    String getCurrentTime = ( getCurrentTime("hh:mm:ss a", TimeZone.getTimeZone("America/New_York")));
                    System.out.printf("(%s) [searched: %s] \n", getCurrentTime, askForSearch);
                    writeToLogs(fileWriter, askForSearch, logAction);
                    fileWriter.flush();

            }

        } while (userIsSearching);
    }

    public static String writeToLogs(FileWriter fileWriter, String outputLogs, String logAction) throws IOException {
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = today.format(formatter);
        if (logAction.equalsIgnoreCase("launch") || logAction.equalsIgnoreCase("terminate")) {
            fileWriter.append("\n" + formattedDate + " (action: "+ logAction + ")" + outputLogs);
        } else {
            fileWriter.append("\n" + formattedDate + " (action: "+ logAction + ")" + " : " + outputLogs);
        }
        return "Added to logs";
    }

    public static String getCurrentTime(String timeFormat, TimeZone timeZone)
    {
        /* Specifying the format */
        DateFormat dateFormat = new SimpleDateFormat(timeFormat);
        /* Setting the Timezone */
        Calendar cal = Calendar.getInstance(timeZone);
        dateFormat.setTimeZone(cal.getTimeZone());
        /* Picking the time value in the required Format */
        String currentTime = dateFormat.format(cal.getTime());
        return currentTime;
    }

}





