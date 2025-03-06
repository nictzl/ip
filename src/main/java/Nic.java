public class Nic {
    /***
     *Entry point of chatbot program
     *Program will continue until a 'bye' command is typed into the CLI
     * no args being used
     */
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
