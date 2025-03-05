public class Nic {

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        TaskManager taskManager = new TaskManager();
        Parser parser = new Parser(taskManager, ui);

        ui.showWelcomeMessage();
        while (true) {
            String userInput = ui.getUserInput().toLowerCase();
            if (parser.parseInput(userInput)) {
                break;
            }
        }
        ui.closeScanner();
    }
}
