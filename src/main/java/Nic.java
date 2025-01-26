import java.util.Scanner;

public class Nic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String [] userInputList = new String[100];
        int countUserInput = 0;

        System.out.println("Hello! I'm Nic.");
        System.out.println("How can I help you today? (Type 'bye' to exit)");
        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Nic: Hope I was able to help you, goodbye!");
                break;
            }
            else if (userInput.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                if (countUserInput == 0) {
                    System.out.println("No entries yet.");
                } else {
                    for (int i = 0; i < countUserInput; i++) {
                        System.out.println((i + 1) + ". " + userInputList[i]);
                    }
                }
                System.out.println("____________________________________________________________");
            }
            else {
                if (countUserInput < 100) {
                    userInputList[countUserInput] = userInput; // Store the input
                    countUserInput++; // Increment task count
                    System.out.println("____________________________________________________________");
                    System.out.println("Added: " + userInput);
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("Task list is full! Cannot add more than 100 tasks.");
                    System.out.println("____________________________________________________________");
                }
            }
        }
        scanner.close();
    }
}

