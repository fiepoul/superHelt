import java.util.ArrayList;

public class Controller {
   private Database database;

   public Controller(Database database) {
       this.database = database;
   }

    public ArrayList<Superhelt> hentAlleSuperhelte() {
        return database.getAllSuperhelte();
    }
}
