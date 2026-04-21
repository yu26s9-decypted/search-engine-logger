package com.pluralsight;

import com.pluralsight.ui.Console;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] arg) throws IOException {
        String logfile = "logs.txt";
        FileWriter fileWriter = new FileWriter(logfile, true);
        String logAction = "launch";
        writeToLogs(fileWriter, "---logging initialized...--- ", logAction);
        fileWriter.flush();
        boolean userIsSearching = true;
        do {
            String askForSearch = Console.askForString("Enter your search: (x to exit): " );
            switch (askForSearch){
                case "x":
                    System.out.println("Thank you for using search!");
                    userIsSearching = false;
                    System.out.println("A complete log of your searches has been generated in: " + logfile );
                    logAction = "terminate";
                    writeToLogs(fileWriter, "---end of log---", logAction);
                    fileWriter.close();
                    break;

                default:
                    userIsSearching = true;
                    logAction = "search";
                    writeToLogs(fileWriter, askForSearch, logAction);
                    fileWriter.flush();

            }


        } while (userIsSearching);



    }

    public static String writeToLogs(FileWriter fileWriter, String outputLogs, String logType) throws IOException {
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        String formattedDate = today.format(formatter);
        fileWriter.append("\n" + formattedDate + " (action: "+ logType + ")" + " : " + outputLogs);
        return "Added to logs";
    }
}




