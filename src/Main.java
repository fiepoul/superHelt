import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Database database = new Database();
        Controller controller = new Controller(database);
        boolean fortsæt = true;

        while (fortsæt) {

            System.out.print("VELKOMMEN TIL SUPERHELTE UNIVERSET <3");
            System.out.println("\nMENU: ");
            System.out.println("1. Opret superhelte");
            System.out.println("2. Vis alle superhelte");
            System.out.println("3. Søg en superhelt");
            System.out.println("4. Forlad programmet");

            System.out.println("Vælg en af valgmulighederne: ");
            int menuValg = scanner.nextInt();

            scanner.nextLine();

            switch (menuValg) {
                case 1:
                    while (true) {
                        System.out.print("\nIndtast superheltnavn: ");
                        String navn = scanner.nextLine();

                        System.out.print("Er superhelten et menneske? (ja/nej): ");
                        boolean erMenneske = scanner.nextLine().equalsIgnoreCase("ja");

                        System.out.print("Hvilken superkraft har superhelten: ");
                        String superkraft = scanner.nextLine();
                        // Løkke for at teste om input er firecifret
                        int oprettelsesår = 0;

                        while (true) {
                            System.out.print("Indtast oprettelsesår (fire cifre): ");
                            String input = scanner.nextLine();

                            if (input.length() == 4 && input.matches("\\d+")) {
                                oprettelsesår = Integer.parseInt(input);
                                break;
                            } else {
                                System.out.print("Ugyldigt input. ");
                            }
                        }

                        System.out.print("Hvilken styrke har din superhelt: ");
                        String styrke = scanner.nextLine();

                        Superhelt superhelt = new Superhelt(navn, erMenneske, superkraft, oprettelsesår, styrke);

                        database.addSuperhelt(superhelt);

                        System.out.println("Vil du lave en ny superhelt? (ja/nej): ");
                        String igen = scanner.nextLine();
                        if (!igen.equalsIgnoreCase("ja")) {
                            break;
                        }
                    }
                    break;
                case 2:
                    ArrayList<Superhelt> alleSuperhelte = controller.hentAlleSuperhelte();
                    for (Superhelt superhelt: alleSuperhelte){
                        System.out.println(superhelt);
                    }
                    fortsæt = false;
                    break;
                case 3:
                    System.out.println("Søg ved hjælp af et eller flere bogstaver på din superhelt: ");

                    String søgeord = scanner.nextLine();
                    ArrayList<Superhelt> matchendeSuperhelte = database.søgSuperhelte(søgeord);


                    if (matchendeSuperhelte.isEmpty()) {
                        System.out.println("Ingen matchende superhelte blev fundet.");
                    } else {
                        System.out.println("Matchende superhelte:");
                        for (int i = 0; i < matchendeSuperhelte.size(); i++) {
                            System.out.println((i + 1) + ". " + matchendeSuperhelte.get(i).getNavn());
                        }
                    }
                    fortsæt = false;
                    break;

                case 4:
                    System.out.println("Forlader superheltene");
                    fortsæt = false;
                    scanner.close();

                default:
                    System.out.println("Du havde fire valgmuligheder. Ikke et helt univers. Vælg igen.");
            }
        }
    }
}
