
public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        Controller controller = new Controller(database);
        UserInterface ui = new UserInterface(controller);
        ui.start();

    }
}
