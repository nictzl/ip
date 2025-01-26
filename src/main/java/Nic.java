import java.util.Scanner;

public class Nic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Nic.");
        System.out.println("How can I help you today? (Type 'bye' to exit)");
        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Nic: Hope I was able to help you, goodbye!");
                break;
            }
            System.out.println("Nic: " + userInput);
        }
        scanner.close();
    }
}

