package com.pluralsight;

import com.pluralsight.ui.Console;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] arg) throws IOException {
        String logfile = "logs.txt";
        FileWriter fileWriter = new FileWriter(logfile, true);
        String logType = "launch";
        writeToLogs(fileWriter, "---logging initialized...--- ", logType);
        fileWriter.flush();
        boolean userIsSearching = true;
        do {
            String askForSearch = Console.askForString("Enter your search: (x to exit): " );
            switch (askForSearch){
                case "x":
                    System.out.println("Thank you for using search!");
                    userIsSearching = false;
                    System.out.println("A complete log of your searches has been generated in: " + logfile );
                    logType = "terminate";
                    writeToLogs(fileWriter, "---end of log---", logType);
                    fileWriter.close();
                    break;

                default:
                    userIsSearching = true;
                    logType = "search";
                    writeToLogs(fileWriter, askForSearch, logType);
                    fileWriter.flush();

            }


        } while (userIsSearching);



    }

    public static String writeToLogs(FileWriter fileWriter, String outputLogs, String logType) throws IOException {
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        String formattedDate = today.format(formatter);
        fileWriter.append("\n" + formattedDate + " (type: "+ logType + ")" + " : " + outputLogs);
        return "Added to logs";
    }
}




