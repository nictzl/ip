import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        ensureDirectoryExists();
    }

    private void ensureDirectoryExists() {
        File file = new File(filePath);
        File directory = file.getParentFile(); // Get the parent folder

        if (directory != null && !directory.exists()) {
            boolean created = directory.mkdirs(); // Create the "data" folder
            if (!created) {
                System.out.println("Warning: Failed to create directory " + directory.getAbsolutePath());
            }
        }
    }

    public void saveTasks(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                fw.write(encodeTask(task) + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks; // Return empty list if file does not exist
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                Task task = decodeTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    private String encodeTask(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.isDone ? "1" : "0") + " | " + task.description;
        } else if (task instanceof Deadline) {
            return "D | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " + ((Deadline) task).by;
        } else if (task instanceof Event) {
            return "E | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " + ((Event) task).from + " | " + ((Event) task).to;
        }
        return "";
    }

    private Task decodeTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) return null;

        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (parts[0]) {
            case "T":
                Todo todo = new Todo(description);
                todo.setDone(isDone);
                return todo;
            case "D":
                if (parts.length < 4) return null;
                Deadline deadline = new Deadline(description, parts[3]);
                deadline.setDone(isDone);
                return deadline;
            case "E":
                if (parts.length < 5) return null;
                Event event = new Event(description, parts[3], parts[4]);
                event.setDone(isDone);
                return event;
            default:
                return null;
        }
    }
}

