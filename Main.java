import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Task 1 output: ");
            String pathname = "task1-input.txt";
            File task1 = new File(pathname);
            int lineCount = lineNum(pathname);
            Task1 jobList = new Task1(lineCount);
            Scanner scanner1 = new Scanner(task1);

            while (scanner1.hasNextLine()) {
                String job = scanner1.nextLine();
                String[] elements = job.split(" ", 2);
                if (!Objects.equals(elements[0], "") && !Objects.equals(elements[1], "")) {
                    jobList.insert(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]));
                } else {
                    break;
                }
            }
            scanner1.close();
            jobList.sort();

            System.out.println("\n\nTask 2 output: ");
            pathname = "task2-input.txt";
            File task2 = new File(pathname);
            lineCount = lineNum(pathname);
            Task2 jobList2 = new Task2(lineCount);
            Scanner scanner2 = new Scanner(task2);
            while (scanner2.hasNextLine()) {
                String job = scanner2.nextLine();
                String[] elements = job.split(" ", 3);
                if (elements.length == 3) {
                    jobList2.insert(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]), Integer.parseInt(elements[2]));
                } else {
                    break;
                }
            }
            scanner2.close();
            jobList2.sort();

            System.out.println("\n\nTask 3 output: ");
            pathname = "task3-input.txt";
            File task3 = new File(pathname);
            lineCount = lineNum(pathname);
            Task3 jobList3 = new Task3(lineCount);
            Scanner scanner3 = new Scanner(task3);
            while (scanner3.hasNextLine()) {
                String job = scanner3.nextLine();
                String[] elements = job.split(" ", 3);
                if (elements.length == 3) {
                    jobList3.insert(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]), Integer.parseInt(elements[2]));
                } else {
                    break;
                }
            }
            scanner3.close();
            jobList3.simulate();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }


    private static int lineNum (String fileName) throws FileNotFoundException {
        int lineCount = 0;
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            scanner.nextLine(); 
            lineCount++; 
        }
        scanner.close();
        return lineCount;
    }
}
