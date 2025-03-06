public class Parser {
    private final TaskManager taskManager;
    private final UserInterface ui;

    public Parser(TaskManager taskManager, UserInterface ui) {
        this.taskManager = taskManager;
        this.ui = ui;
    }

    public boolean parseInput(String userInput) {
        if (userInput.equalsIgnoreCase("bye")) {
            ui.showExitMessage();
            return true;
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
        else if (userInput.startsWith("delete")) {
            taskManager.deleteTask(userInput);
        }
        else if (userInput.startsWith("find")) {
            taskManager.findTask(userInput);
        }
        else {
            System.out.println("Invalid command");
        }
        return false;
    }
}