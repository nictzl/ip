import java.util.ArrayList;
import java.util.List;

/***
 * userlist represents list displayed to the user
 * tasklist represents the ArrayList holding the task objects in the backend
 * userlist index of task will be plus 1 of the actual index of the task in the tasklist
 */
public class TaskManager {
    private static final int MAX_LIST_SIZE = 100;
    private static final int INDEX_OFFSET = 1;
    private static final String LINE_BREAK = "____________________________________________________________";
    private static final int OUT_OF_BOUNDS = -1;
    private static final String FILE_PATH = "data/Nicholas.txt";
    private final ArrayList<Task> tasks;
    private final Storage storage;

    public TaskManager() {
        this.storage = new Storage(FILE_PATH);
        this.tasks = storage.loadTasks();
    }

    /***
     * The methods below are to add the different types of tasks: todo, deadline, event
     * Returns nothing
     * @param userInput command entered in from CLI
     */
    public void addTodo(String userInput) {
        String[] description = userInput.split(" "); //extracts out the task to add
        if (description.length <= 1) {
            System.out.println("Invalid input. Use: todo <description>.");
            return;
        }
        addTask(new Todo(description[1]));
    }

    public void addDeadline(String userInput) {
        String[] parts = userInput.substring(9).split(" /by ", 2); //extracts out the task to add
        if (parts.length != 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            System.out.println("Invalid format! Use: deadline <description> /by <time>");
            return;
        }
        addTask(new Deadline(parts[0].trim(), parts[1].trim()));
    }

    public void addEvent(String userInput) {
        String[] parts = userInput.substring(6).split(" /from | /to ", 3);
        //extracts out the task to add
        if (parts.length != 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            System.out.println("Invalid format! Use: event <description> /from <start> /to <end>");
            return;
        }
        addTask(new Event(parts[0].trim(), parts[1].trim(), parts[2].trim()));
    }

    /***
     *Checks if tasklist is full, adds task to it if not full
     * Storage is updated to contain new updated tasklist
     * Returns nothing
     * @param task A Task object containing description and status of task
     */
    public void addTask(Task task) {
        if (tasks.size() >= MAX_LIST_SIZE) {
            System.out.println("Task list is full!");
            return;
        }
        tasks.add(task);
        storage.saveTasks(tasks);
        System.out.println(LINE_BREAK);
        System.out.println("Got it. I've added the task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE_BREAK);
    }

    /***
     * Returns nothing, no parameters
     * Lists out all the task from tasklist if it is not empty
     */
    public void listTask() {
        System.out.println();
        if (tasks.isEmpty()) {
            System.out.println("No entries yet.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + INDEX_OFFSET) + ". " + tasks.get(i));
            }
        }
        System.out.println(LINE_BREAK);
    }

    /***
     * Marks or unmarks the task in the tasklist
     * @param done a flag to indicate the task is to be marked or unmarked. True means mark, False means unmarked
     * @param inputIndex An integer representing the numbered task on the userlist
     */
    public void markTodo(boolean done, int inputIndex) {
        try {
            if (inputIndex < 0 || inputIndex >= tasks.size()) {
                throw new IndexOutOfBoundsException("Invalid input, Use: mark <number>. " +
                        "Please make sure number is in list!");
            }
            tasks.get(inputIndex).setDone(done);
            storage.saveTasks(tasks);
            System.out.println(LINE_BREAK);
            System.out.println(done ? "Nice! I've marked this task as done:" :
                    "OK, I've marked this task as not done yet:");
            System.out.println(" " + tasks.get(inputIndex));
            System.out.println(LINE_BREAK);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    /***
     * Converts a string input from user about the number of the task in userlist
     * to its integer value of it in the tasklist
     * @param command user command entered in CLI
     * @return index of task in list
     */
    public int getInputIndex(String command) {
        String [] parts = command.split(" "); //extracts the input number from user
        if (parts.length > 1) {
            try {
                int inputIndex = Integer.parseInt(parts[1]) - INDEX_OFFSET;
                if (inputIndex >= 0 && inputIndex < tasks.size()) {
                    return inputIndex;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, Use: command <number>. " +
                        "Please make sure number is in list!");
            }

        }
        return OUT_OF_BOUNDS;
    }


    /***
     * Returns nothing
     * Removes task from tasklist and updates the text file storage to contain the new tasklist
     * @param userInput input from user in CLI
     */
    public void deleteTask(String userInput) {
        int inputIndex = getInputIndex(userInput);
        try {
            if (inputIndex < 0 || inputIndex >= tasks.size()) {
                throw new IndexOutOfBoundsException("Invalid input, Use: delete <number>. " +
                        "Please make sure number is in list!");
            }
            Task removedTask = tasks.remove(inputIndex);
            storage.saveTasks(tasks);
            System.out.println(LINE_BREAK);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(LINE_BREAK);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    /***
     * Returns nothing
     * Finds all tasks in the tasklist containing keywords from userInput and displays them
     * @param userInput Input from user at CLI
     */
    public void findTask(String userInput) {
        try {
            String[] parts = userInput.split(" ", 2);
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new IllegalArgumentException("Keyword cannot be empty. Use: find keyword");
            }
            String keyword = parts[1];
            List<Task> found = new ArrayList<>();
            for (Task task : tasks) {
                if (task.getDescription().contains(keyword)) {
                    found.add(task);
                }
            }
            if (found.isEmpty()) {
                System.out.println("No matching tasks found.");
            } else {
                System.out.println("Here are the matching tasks in your list:");
                for (int i = 0; i < found.size(); i++) {
                    System.out.println((i + 1) + ". " + found.get(i));
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }
}
