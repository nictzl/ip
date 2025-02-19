import java.util.ArrayList;

public class TaskManager {
    private static final int MAX_LIST_SIZE = 100;
    private static final int INDEX_OFFSET = 1;
    private static final String lineBreak = "____________________________________________________________";
    private static final int OUT_OF_BOUNDS = -1;
    private final ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTodo(String userInput) {
        String description = userInput.substring(5).trim();
        if (description.isEmpty()) {
            System.out.println("Invalid input. Description cannot be empty.");
            return;
        }
        addTask(new Todo(description));
    }

    public void addDeadline(String userInput) {
        String[] parts = userInput.substring(9).split(" /by ", 2);
        if (parts.length != 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            System.out.println("Invalid format! Use: deadline <description> /by <time>");
            return;
        }
        addTask(new Deadline(parts[0].trim(), parts[1].trim()));
    }

    public void addEvent(String userInput) {
        String[] parts = userInput.substring(6).split(" /from | /to ", 3);
        if (parts.length != 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            System.out.println("Invalid format! Use: event <description> /from <start> /to <end>");
            return;
        }
        addTask(new Event(parts[0].trim(), parts[1].trim(), parts[2].trim()));
    }

    public void addTask(Task task) {
        if (tasks.size() < MAX_LIST_SIZE) {
            tasks.add(task);
            System.out.println(lineBreak);
            System.out.println("Got it. I've added the task:");
            System.out.println(" " + task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(lineBreak);

        } else {
            System.out.println("Task list is full!");
        }
    }

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
        System.out.println(lineBreak);
    }

    public void markTodo(boolean done, int inputIndex) {
        if (inputIndex >= 0 && inputIndex < tasks.size()) {
            tasks.get(inputIndex).setDone(done);
            System.out.println(lineBreak);
            System.out.println(done ? "Nice! I've marked this task as done:" : "OK, I've marked this task as not done yet:");
            System.out.println(" " + tasks.get(inputIndex));
            System.out.println(lineBreak);
        } else {
            System.out.println("Invalid task number!");
        }
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public int getInputIndex(String command) {
        String [] parts = command.split(" ");
        if (parts.length > 1) {
            try {
                int inputIndex = Integer.parseInt(parts[1]) - INDEX_OFFSET;
                if (inputIndex >= 0 && inputIndex < tasks.size()) {
                    return inputIndex;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid index");
            }

        }
        return OUT_OF_BOUNDS;
    }

    public void displayTask(String userInput) {
        if (tasks.size() < MAX_LIST_SIZE) {
            Task newTask = new Task(userInput); // Store the input
            tasks.add(newTask);
            System.out.println(lineBreak);
            System.out.println("Added: " + userInput);
            System.out.println(lineBreak);
        } else {
            System.out.println(lineBreak);
            System.out.println("Task list is full! Cannot add more than 100 tasks.");
            System.out.println(lineBreak);
        }
    }

    public void deleteTask(String userInput) {
        int inputIndex = getInputIndex(userInput);
        if (inputIndex >= 0 && inputIndex < tasks.size()) {
            Task removedTask = tasks.remove(inputIndex);
            System.out.println(lineBreak);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(lineBreak);
        } else {
            System.out.println("Invalid task number!");
        }
    }

}
