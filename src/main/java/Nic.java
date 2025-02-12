import java.util.Scanner;

public class Nic {


    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        TaskManager taskManager = new TaskManager();
        ui.showWelcomeMessage();
        while (true) {
            String userInput = ui.getUserInput().toLowerCase();

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

            else if (userInput.startsWith("unmark")) {
                int inputIndex = taskManager.getInputIndex(userInput);
                taskManager.markTodo(false, inputIndex);
            }

            else if (userInput.startsWith("todo")) {
                taskManager.addTodo(userInput);
            }

            else if (userInput.startsWith("deadline")) {
                taskManager.addDeadline(userInput);
            }

            else if (userInput.startsWith("event")) {
                taskManager.addEvent(userInput);
            }

            else {
                taskManager.displayTask(userInput);
            }
        }
        ui.closeScanner();
    }
}

