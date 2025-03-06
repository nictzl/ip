public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        isDone = false;
    }
    public String getDescription() {
        return description;
    }
    public void setDone(boolean input) {
        this.isDone = input;
    }
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
