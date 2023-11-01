package Manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import Common.Library;
import Views.Menu;

public class ModuleFile extends Menu<String> {
    static String[] options = { "Count number of occurrences of a word in a file.",
            "Search file have content which contain inputted word", "exit" };
    Library library = new Library();

    public ModuleFile() {
        super("============ Word Program =========", options);
    }

    public void execute(int n) throws FileNotFoundException {
        switch (n) {
            case 1:
                library.countWords("test.txt");
                break;
            case 2:
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter folder path: ");
                String folderPath = scanner.nextLine();
                System.out.print("Enter word to search: ");
                String wordToSearch = scanner.nextLine();
                try {
                    List<String> files = library.getFileNameContainsWordInDirectory(folderPath, wordToSearch);
                    library.displayFiles(files);
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
                break;
            case 3:
                System.exit(0);
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        new ModuleFile().run();
    }
}
