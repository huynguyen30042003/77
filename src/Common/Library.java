package Common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    public static int getInt(String promt, int m, int n) {
        int a = -1;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(promt + ": ");
            try {
                String s = sc.nextLine();
                a = Integer.parseInt(s);
                if (a >= m && a <= n) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Please enter a number between " + m + " and " + n);
            }
        }
        return a;
    }

    public void countWords(String nameFile) throws FileNotFoundException {
        String e = "123";
        int eLen = 3;
        int count = 0;
        File file = new File(nameFile);
        if (file.exists()) {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String senteces = sc.nextLine();
                int n = senteces.lastIndexOf(e);
                if (senteces.indexOf(e) >= 0) {
                    count += 1;
                }
                while (senteces.indexOf(e) != n) {
                    if (senteces.indexOf(e) <= n - 1) {
                        count++;
                        n = senteces.lastIndexOf(e, n - 1);
                    }
                }
            }
        }
        System.out.println(count);
    }

    public List<String> getFileNameContainsWordInDirectory(String source, String word) throws Exception {
        List<String> foundFiles = new ArrayList<>();
        File directory = new File(source);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            if (line.contains(word)) {
                                foundFiles.add(file.getName());
                                break; // Stop searching this file after finding a match
                            }
                        }
                    } catch (IOException e) {
                        throw new Exception("Error reading file " + file.getName() + ": " + e.getMessage());
                    }
                }
            }
        } else {
            throw new Exception("Invalid directory path");
        }
        return foundFiles;
    }

    public void displayFiles(List<String> files) {
        if (files.isEmpty()) {
            System.out.println("No files found.");
        } else {
            System.out.println("Found files:");
            for (String file : files) {
                System.out.println(file);
            }
        }
    }
}
