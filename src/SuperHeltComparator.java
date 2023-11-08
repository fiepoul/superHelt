import java.util.Comparator;

public class SuperHeltComparator implements Comparator<Superhelt> {
    @Override
    public int compare(Superhelt superhelt1, Superhelt superhelt2) {
        // Sammenlign superhelte baseret på navnet (alfabetisk)
        return superhelt1.getNavn().compareTo(superhelt2.getNavn());
    }
}
