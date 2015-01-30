import java.util.Scanner;
import java.io.File;

public class GradeHistogram {
    public static void main(String []args) throws Exception {
        String gradesFile = args[0];
        File inputFile = new File(gradesFile);
        Scanner scan = new Scanner(inputFile);
        int x = 0;
        int element = scan.nextInt();
        while (scan.hasNext()) {
            element = scan.nextInt();
            x++;
        }
        scan.close();
        double[] gradeArray = new double[x];
        scan = new Scanner(inputFile);
        for (int y = 0; y < x; y++) {
            element = scan.nextInt();
            gradeArray[y] = element;
        }
        System.out.println("Grades loaded!");
        System.out.println("What bucket size would you like?");
        Scanner bin = new Scanner(System.in);
        int newBin = bin.nextInt();
        bin.close();
        for (int scale = 100; scale >= 0; scale -= newBin)  {
            int bottomRange = (scale - (newBin + 1));
            int brackets = 0;
            for (int counter = 0; counter < x; counter++) {
                double grade = gradeArray[counter];
                if ((grade <= scale) && (grade >= bottomRange)) {
                    brackets = brackets + 1;
                }
            }
                String strbrackets = "";
            if (bottomRange < 0) {
                bottomRange = 0;
            }
                for (int counter = 0; counter < brackets; counter++) {
                    strbrackets = strbrackets.concat("[]");
            }
            System.out.printf("%3d - %2d | " , scale, bottomRange);
            System.out.print(strbrackets + "\n");
        }
    }
}