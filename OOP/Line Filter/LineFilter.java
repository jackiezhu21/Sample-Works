import java.util.Scanner;

public class LineFilter {

	public static void main(String[] args) {
		if(args.length == 0 ) {
			Scanner scan = new Scanner(System.in);
			while (scan.hasNext()) {
				System.out.println(scan.nextLine());
			}
		}
		else {
			String answer = args[0];
			Scanner scan = new Scanner(System.in);
			while (scan.hasNext()) {
				String line = scan.nextLine();
				if (line.contains(answer)) {
					System.out.println(line);
				}
			}
		}	
	}
}
