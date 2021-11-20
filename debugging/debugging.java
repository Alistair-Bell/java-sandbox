import java.util.ArrayList;
import java.util.Scanner;

class debugging_program {

    private static ArrayList<String> students = new ArrayList<>();

    public static void main(String[] args) {
        char again = 'Y';
        while (again == 'Y'){
            System.out.println("Enter a Student");
            Scanner input = new Scanner(System.in);
            students.add(input.nextLine());
            System.out.println("Do you want to add another? Y or N");
            again = input.next().charAt(0);
        }
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i));
        }

    }
}
