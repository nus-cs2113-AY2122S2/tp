package seedu.duke.storage;

import seedu.duke.housekeeperperformancelists.HousekeeperPerformance;
import seedu.duke.housekeeperperformancelists.HousekeeperPerformanceList;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.HousekeeperPerformanceFileNotFoundException;
import seedu.duke.exceptions.InvalidHousekeeperPerformanceRatingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HousekeeperPerformanceFileManager extends FileManager {
    private static final String FILE_PATH = "ListFolder/performance_list.txt";

    private void clearFileContents() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.close();
    }

    public HousekeeperPerformanceList load() throws HotelLiteManagerException {
        HousekeeperPerformanceList housekeeperPerformanceList = new HousekeeperPerformanceList();
        HousekeeperPerformance housekeeperPerformance;
        File file = getFile(FILE_PATH);
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new HousekeeperPerformanceFileNotFoundException();
        }

        while (scan.hasNext()) {
            String line = scan.nextLine();
            String[] splitData = line.split(FILE_SEPARATOR);
            String housekeeperName = splitData[0].trim();
            String performanceString = splitData[1].trim();
            int performanceRating;
            try {
                performanceRating = Integer.parseInt(performanceString);
            } catch (NumberFormatException e) {
                throw new InvalidHousekeeperPerformanceRatingException();
            }
            housekeeperPerformance = new HousekeeperPerformance(housekeeperName, performanceRating);
            housekeeperPerformanceList.addHousekeeperPerformance(housekeeperPerformance);
        }
        return housekeeperPerformanceList;
    }

    public void save(HousekeeperPerformanceList housekeeperPerformanceList) throws IOException {
        clearFileContents();
        FileWriter fw = new FileWriter(FILE_PATH, true);
        int lastIndexOfArrayList = housekeeperPerformanceList.getSize() - 1;
        String housekeeperName;
        int rating;
        HousekeeperPerformance housekeeperPerformance;
        String performanceDetails = "";
        for (int i = 0; i < housekeeperPerformanceList.getSize(); i++) {
            housekeeperPerformance = housekeeperPerformanceList.getPerformance(i);
            housekeeperName = housekeeperPerformance.getName();
            rating = housekeeperPerformance.getRating();
            performanceDetails = housekeeperName + '|' + rating;
            if (i != lastIndexOfArrayList) {
                performanceDetails = performanceDetails + System.lineSeparator();
            }
            fw.write(performanceDetails);
        }
        fw.close();
    }
}
