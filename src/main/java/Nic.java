import java.util.Scanner;

public class Nic {

    public static int getInputIndex(String command, int countUserInput) {
        String [] parts = command.split(" ");
        if (parts.length > 1) {
            int inputIndex = Integer.parseInt(parts[1]) - 1;
            if (inputIndex >= 0 && inputIndex <  countUserInput) {
                return inputIndex;
            }
        }
        System.out.println("Invalid input. Please try again");
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String [] userInputList = new String[100];
        boolean [] taskStatus = new boolean[100];
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
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < countUserInput; i++) {
                        System.out.println((i + 1) + ".[" + (taskStatus[i] ? "X" : " ") + "] " + userInputList[i]);
                    }
                }
                System.out.println("____________________________________________________________");
            }

            else if (userInput.startsWith("mark")) {
                int inputIndex = getInputIndex(userInput, countUserInput);
                if (inputIndex != -1) {
                    taskStatus[inputIndex] = true;
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + (taskStatus[inputIndex] ? "X" : " ") + "] " + userInputList[inputIndex]);
                    System.out.println("____________________________________________________________");
                }
            }

            else if (userInput.startsWith("unmark ")) {
                int inputIndex = getInputIndex(userInput, countUserInput);
                if (inputIndex != -1) {
                    taskStatus[inputIndex] = false;
                    System.out.println("____________________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[" + (taskStatus[inputIndex] ? "X" : " ") + "] " + userInputList[inputIndex]);
                    System.out.println("____________________________________________________________");
                }
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

