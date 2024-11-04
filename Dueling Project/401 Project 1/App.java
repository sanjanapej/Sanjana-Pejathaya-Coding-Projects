import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Input argument check
        if (args.length != 1) {
            System.out.println("Usage: java App <filename>");
            return;
        }

        // Store the cat objects that you read from the file in an array
        Catm[] cats = readCatsFromFile(args[0]);

        // After initializing the array, iterate through it and print each cat’s information
        for (Catm cat : cats) {
            System.out.println(cat);
        }
    }

    private static Catm[] readCatsFromFile(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            int count = scanner.nextInt();
            Catm[] cats = new Catm[count];
            scanner.nextLine(); // Consume the newline character after the count

            for (int i = 0; i < count; i++) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String name = parts[0];
                String breed = parts[1];
                int age = Integer.parseInt(parts[2]);
                String sound = parts[3];
                cats[i] = new Catm(name, breed, age, sound);
            }

            scanner.close();
            return cats;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            return new Catm[0];
        }
    }
}



