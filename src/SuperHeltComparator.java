import java.util.Comparator;

public class SuperHeltComparator implements Comparator<Superhelt> {
    private final Comparator<Superhelt> primaryComparator;
    private final Comparator<Superhelt> secondaryComparator;

    public SuperHeltComparator(Comparator<Superhelt> primary, Comparator<Superhelt> secondary) {
        this.primaryComparator = primary;
        this.secondaryComparator = secondary;
    }

    @Override
    public int compare(Superhelt superhelt1, Superhelt superhelt2) {
        int primaryResult = primaryComparator.compare(superhelt1, superhelt2);
        return (primaryResult != 0) ? primaryResult : secondaryComparator.compare(superhelt1, superhelt2);
    }

    public static Comparator<Superhelt> getComparatorBasedOnAttribute(String attribute) {
        return switch (attribute.toLowerCase()) {
            case "navn" -> Comparator.comparing(Superhelt::getNavn);
            case "oprettelsesår" -> Comparator.comparing(Superhelt::getOprettelsesår);
            case "styrke" -> Comparator.comparing(Superhelt::getStyrke);
            default -> Comparator.comparing(Superhelt::getNavn);
        };
    }
}
