import java.util.Scanner;

public class Nic {

    private static final Task[] taskList = new Task[100];
    private static int taskCount = 0;
    private static final String lineBreak = "____________________________________________________________";
    private static final int OUT_OF_BOUNDS = -1;
    private static final int MAX_LIST_SIZE = 100;
    private static final int INDEX_OFFSET = 1;

    private static int getInputIndex(String command, int countUserInput) {
        String [] parts = command.split(" ");
        if (parts.length > 1) {
            int inputIndex = Integer.parseInt(parts[1]) - INDEX_OFFSET;
            if (inputIndex >= 0 && inputIndex <  countUserInput) {
                return inputIndex;
            }
        }
        System.out.println("Invalid input. Please try again");
        return OUT_OF_BOUNDS;
    }

    private static void listTask() {
        System.out.println();
        if (taskCount == 0) {
            System.out.println("No entries yet.");
        }
        else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + INDEX_OFFSET) + "." + taskList[i]);
            }
        }
        System.out.println(lineBreak);
    }

    private static void displayTask(String userInput) {
        if (taskCount < MAX_LIST_SIZE) {
            taskList[taskCount] = new Task(userInput); // Store the input
            taskCount++; // Increment task count
            System.out.println(lineBreak);
            System.out.println("Added: " + userInput);
            System.out.println(lineBreak);
        }
        else {
            System.out.println(lineBreak);
            System.out.println("Task list is full! Cannot add more than 100 tasks.");
            System.out.println(lineBreak);
        }
    }

    private static void addTask(Task task) {
        if (taskCount < MAX_LIST_SIZE) {
            taskList[taskCount] = task;
            taskCount++;
            System.out.println(lineBreak);
            System.out.println("Got it. I've added the task:");
            System.out.println(" " + task);
            System.out.println("Now you have " + taskCount + " tasks in the list.");
            System.out.println(lineBreak);

        }
        else {
            System.out.println("Task list is full!");
        }
    }

    private static void markTodo(boolean done, int inputIndex) {
        taskList[inputIndex].setDone(done);
        System.out.println(lineBreak);
        System.out.println(done ? "Nice! I've marked this task as done:" : "OK, I've marked this task as not done yet:");
        System.out.println(" " + taskList[inputIndex]);
        System.out.println(lineBreak);
    }

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
            else if (userInput.equalsIgnoreCase("list")) {
                listTask();
            }
            else if (userInput.startsWith("mark")) {
                int inputIndex = getInputIndex(userInput, taskCount);
                if (inputIndex != OUT_OF_BOUNDS) {
                    markTodo(true, inputIndex);
                }
            }
            else if (userInput.startsWith("unmark ")) {
                int inputIndex = getInputIndex(userInput, taskCount);
                if (inputIndex != OUT_OF_BOUNDS) {
                    markTodo(false, inputIndex);
                }
            }

            else if (userInput.startsWith("todo ")) {
                String description =  userInput.substring(5).trim();
                addTask(new Todo(description));
            }

            else if (userInput.startsWith("deadline ")) {
                String[] parts = userInput.substring(9).split(" /by ",2);
                if (parts.length == 2) {
                    addTask(new Deadline(parts[0].trim(), parts[1].trim()));
                }
                else {
                    System.out.println("Invalid format!");
                }
            }

            else if (userInput.startsWith("event ")) {
                String[] parts = userInput.substring(6).split(" /from | /to ", 3);
                if (parts.length == 3) {
                    addTask(new Event(parts[0].trim(), parts[1].trim(), parts[2].trim()));
                }
                else {
                    System.out.println("Invalid format!");
                }
            }

            else {
                displayTask(userInput);
            }
        }
        scanner.close();
    }
}

