package DataMigrator;

import Food.Aliment;
import util.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Migrator {
    private static final int EXPECTED_COLUMNS = 47;
    private static String[] FoodGroupsLabels = {
            "Dairy Foods",
            "Vegetables",
            "Meet, Poultry,Fish,Seafood and eggs",
            "Grains, Beans and Nuts",
            "Fruits"
    };

    private CSVReader reader;

    public Migrator(CSVReader reader) {
        this.reader = reader;
    }

    /**
     * Import aliments from a CSV file.
     *
     * @param csvPath the path to the CSV file.
     * @return the list of aliments in the CSV file.
     * @throws FileNotFoundException    if the file is not found.
     * @throws IncorrectFormatException if the file has incorrect formatting.
     */
    public List<Aliment> importData(String csvPath) throws FileNotFoundException, IncorrectFormatException {
        List<Aliment> data = new ArrayList<>();
        AlimentFactory af = new AlimentFactory();

        Scanner scanner = new Scanner(new File(csvPath));

        if (!scanner.hasNext()) { // Empty file
            return null;
        }

        // The first line is the columns' headers
        List<String> headers = reader.parseLine(scanner.nextLine());

        if (headers.size() != EXPECTED_COLUMNS) {
            throw new IncorrectFormatException("Incorrect number of headers in file.");
        }

        while (scanner.hasNext()) {
            List<String> line = reader.parseLine(scanner.nextLine());

            Aliment a;

            try {
                a = af.createAliment(line);
            } catch (IncorrectFormatException ife) {
                a = null;
            }

            if (a != null) {
                data.add(a);
            }

        }
        scanner.close();

        return data;
    }

    /**
     * Get all aliments from a given group.
     *
     * @param aliments the complete list of aliments.
     * @param group    the food group to get?
     * @return the fraction of the complete list of aliments corresponding to the given food group.
     */
    public List<Aliment> getAlimentGroup(List<Aliment> aliments, FoodGroup group) {
        List<Aliment> alimentGroup = new ArrayList<>();

        for (Aliment a : aliments) {
            if (a.getFoodGroup().trim().equals(FoodGroupsLabels[group.ordinal()].trim())) {
                alimentGroup.add(a);
            }
        }

        return alimentGroup;
    }
}
