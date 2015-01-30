import java.util.Scanner;
import java.io.File;

public class GradeStuff {
    public static void main(String[] args) throws Exception {
    String gradeFile = args[0];
        Scanner grades = new Scanner(new File(gradeFile));
        int counter = 1;
        int line = Integer.parseInt(grades.nextLine());
        while (grades.hasNext()) {
        line = Integer.parseInt(grades.nextLine());
            counter++;
        }
        grades.close();
        double[] gradesArray = new double[counter];
        grades = new Scanner(new File(gradeFile));
        for (int x = 0; x < counter; x++) {
            line = Integer.parseInt(grades.nextLine());
            gradesArray[x] = line;
        }

        System.out.println("Grades Loaded!");
        System.out.println("What bucket size would you like?");
        Scanner keyboard = new Scanner(System.in);
        String size1 = keyboard.next();
        int size = Integer.parseInt(size1);
        int leftNumber = 100;
        int rightNumber = 101 - size;
        int numberOfLines1 = (100 / size);
        int numberOfLines2 = numberOfLines1 + 1;
        String buckets = "";
        System.out.println("");
        for (int x = 0; x < numberOfLines2; x++) {
            int bucketCounter = 0;
            for (int y = 0; y < counter; y++) {
                if ((gradesArray[y] <= leftNumber)
                    && (gradesArray[y] >= rightNumber)) {
                    bucketCounter++;
                }
            }
            System.out.printf("%3d - %2d | " , leftNumber , rightNumber);
            for (int j = 0; j < bucketCounter; j++) {
                System.out.print("[]");
            }
            System.out.println("");
            leftNumber = leftNumber - size;
            if (rightNumber > size) {
                rightNumber = rightNumber - size;
            } else {
                rightNumber = 0;
            }
        }
    }
}