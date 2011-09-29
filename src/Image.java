import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Image {

    private HashSet<Integer> set = new HashSet<Integer>();

    private Integer[] dimensions;

    private Integer maxValue;

    public Image() {
    }

    public void read(String fileName) {
        try {
            Scanner scanner = new Scanner(new FileReader(fileName));

            if (!Image.readFirstToken(scanner).equals("P2")) {
                throw new BadFormedFileException();
            }

            if (!Image.readFirstToken(scanner).equals("#")) {
                throw new BadFormedFileException();
            }

            this.dimensions = Image.readDimensions(scanner);

            this.maxValue = Image.readValue(scanner);

            Integer reading;
            do {
                reading = Image.readValue(scanner);
                if (reading != null) {
                    this.set.add(reading);
                }
            } while (reading != null);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BadFormedFileException e) {
            e.printStackTrace();
        }
    }

    public static String readFirstToken(Scanner scanner) {
        if (scanner.hasNextLine()) {
            final StringTokenizer brokenLine =
                new StringTokenizer(scanner.nextLine(), " ");
            return brokenLine.nextToken();
        }
        return null;
    }

    public static Integer[] readDimensions(Scanner scanner) {
        if (scanner.hasNextLine()) {
            final StringTokenizer brokenLine =
                new StringTokenizer(scanner.nextLine(), " ");
            Integer i1 = Integer.parseInt(brokenLine.nextToken());
            Integer i2 = Integer.parseInt(brokenLine.nextToken());

            return new Integer[] {i1, i2 };
        }
        return null;
    }

    public static Integer readValue(Scanner scanner) {
        if (scanner.hasNextLine()) {
            final StringTokenizer brokenLine =
                new StringTokenizer(scanner.nextLine(), " ");
            return Integer.parseInt(brokenLine.nextToken());
        }
        return null;
    }

    public void write(String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);

            fileWriter.write("P2");
            fileWriter.write("# Written by Daniel Lefèvre");
            fileWriter.write(this.dimensions[0]);
            fileWriter.write(this.dimensions[1]);
            fileWriter.write(this.maxValue);

            for (Integer i : this.set) {
                fileWriter.write(i);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class BadFormedFileException extends Exception {

        private static final long serialVersionUID = 1L;

        public BadFormedFileException() {
        }
    }
}
