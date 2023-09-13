import java.util.ArrayList;

public class Database {
        private ArrayList<Superhelt> superhelte;

    public Database() {
        superhelte = new ArrayList<>();
    }

    public void addSuperhelt(Superhelt superhelt) {
        superhelte.add(superhelt);
    }

    public ArrayList<Superhelt> getAllSuperhelte() {
        return superhelte;
    }
    }

