public class Parser {
    private final TaskManager taskManager;
    private final UserInterface ui;

    public Parser(TaskManager taskManager, UserInterface ui) {
        this.taskManager = taskManager;
        this.ui = ui;
    }

    /***
     * Method checks for command keyword entered by the user and calls corresponding method from task manager
     * If command keyword is not recognised, 'Invalid command' will be output
     * @param userInput The command entered in from CLI
     * @return false unless a 'bye' command is entered
     */
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