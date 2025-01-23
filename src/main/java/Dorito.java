import java.util.Scanner;

public class Dorito {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = "_________\n"
                + "\\       /\n"
                + " \\     / \n"
                + "  \\   /  \n"
                + "   \\ /   \n";
        String exit = "bye";
        System.out.println(logo + "\nHello! I'm Dorito  >.<\n"
                + "What can I do for you?  o.o");
        while (true) {
            String echo = sc.nextLine();
            if (echo.equals(exit)) {
                System.out.println("Bye. Remember to stay hydrated!  >.<");
                sc.close();
                break;
            }
            System.out.println(echo + "  >.<");
        }
    }
}
