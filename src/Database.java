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

    public ArrayList<Superhelt> søgSuperhelte(String søgeord) {
        ArrayList<Superhelt> matchendeSuperhelte = new ArrayList<>();

        for (Superhelt superhelt : superhelte) {
            if (superhelt.getNavn().toLowerCase().contains(søgeord.toLowerCase())) {
                matchendeSuperhelte.add(superhelt);
            }
        }

        return matchendeSuperhelte;
    }

    }

