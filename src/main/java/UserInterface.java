import java.util.Scanner;

public class UserInterface {
    private  final Scanner scanner;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        System.out.print("You: ");
        return scanner.nextLine();
    }

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Nic.");
        System.out.println("How can I help you today? (Type 'bye' to exit");
    }

    public void showExitMessage() {
        System.out.println("Nic: Hope I was able to help you, goodbye!");
    }

    public void closeScanner() {
        scanner.close();
    }
}
