import java.util.Scanner;

public class Nic {


    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        TaskManager taskManager = new TaskManager();
        ui.showWelcomeMessage();
        while (true) {
            String userInput = ui.getUserInput();

            if (userInput.equalsIgnoreCase("bye")) {
                ui.showExitMessage();
                break;
            }
            else if (userInput.equalsIgnoreCase("list")) {
                taskManager.listTask();
            }
            else if (userInput.startsWith("mark")) {
                int inputIndex = taskManager.getInputIndex(userInput);
                taskManager.markTodo(true, inputIndex);
            }

            else if (userInput.startsWith("unmark ")) {
                int inputIndex = taskManager.getInputIndex(userInput);
                taskManager.markTodo(false, inputIndex);
            }

            else if (userInput.startsWith("todo ")) {
                String description =  userInput.substring(5).trim();
                taskManager.addTask(new Todo(description));
            }

            else if (userInput.startsWith("deadline ")) {
                String[] parts = userInput.substring(9).split(" /by ",2);
                if (parts.length == 2) {
                    taskManager.addTask(new Deadline(parts[0].trim(), parts[1].trim()));
                } else {
                    System.out.println("Invalid format!");
                }
            }

            else if (userInput.startsWith("event ")) {
                String[] parts = userInput.substring(6).split(" /from | /to ", 3);
                if (parts.length == 3) {
                    taskManager.addTask(new Event(parts[0].trim(), parts[1].trim(), parts[2].trim()));
                } else {
                    System.out.println("Invalid format!");
                }
            }

            else {
                taskManager.displayTask(userInput);
            }
        }
        ui.closeScanner();
    }
}

