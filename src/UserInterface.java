import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class UserInterface {
    private Scanner scanner;
    private Database database;
    private Controller controller;

    public UserInterface() {
            this.scanner = new Scanner(System.in);
            this.database = new Database();
            this.controller = new Controller(database);
        }

    public void start() {
        boolean fortsæt = true;

        while (fortsæt) {
            try {
                System.out.print("VELKOMMEN TIL SUPERHELTE UNIVERSET <3");
                System.out.println("\nMENU: ");
                System.out.println("1. Opret superhelte");
                System.out.println("2. Vis alle superhelte");
                System.out.println("3. Søg en superhelt");
                System.out.println("4. Rediger en superhelt");
                System.out.println("5. Forlad programmet");

                System.out.println("Vælg en af valgmulighederne: ");
                int menuValg = scanner.nextInt();
                scanner.nextLine();

                switch (menuValg) {
                    case 1:
                        opretSuperhelte();
                        break;
                    case 2:
                        visAlleSuperhelte();
                        fortsæt = false;
                        break;
                    case 3:
                        søgSuperhelt();
                        fortsæt = false;
                        break;
                    case 4:
                        redigerSuperhelt();
                        fortsæt = false;
                        break;
                    case 5:
                        System.out.println("Forlader superheltene");
                        fortsæt = false;
                        scanner.close();
                        break;

                    default:
                        System.out.println("Du havde fem valgmuligheder. Ikke et helt univers. Vælg igen.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ugyldig input. Indtast et heltal.");
                scanner.nextLine(); // Ryd op i input buffer
            }
        }
    }
        public void opretSuperhelte () {
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
                controller.validerJaNejInput(igen);
                if (!igen.equalsIgnoreCase("ja")) {
                    break;
                }
            }
        }

        public void visAlleSuperhelte () {
            ArrayList<Superhelt> alleSuperhelte = controller.hentAlleSuperhelte();

            if (alleSuperhelte != null) {
                for (Superhelt superhelt : alleSuperhelte) {
                    System.out.println(superhelt);
                }
            } else {
                System.out.println("Ingen superhelte fundet i databasen.");
            }
        }

        public void søgSuperhelt () {
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
        }

        public void redigerSuperhelt () {
            System.out.println("Indtast navnet på den superhelt, du vil redigere: ");
            String nytNavn = scanner.nextLine();

            if (!controller.eksistererSuperhelt(nytNavn)) {
                System.out.println("Superhelten findes ikke i databasen.");
                return; // Afslut metoden uden at forsøge at redigere
            }

            System.out.print("Indtast ny superkraft: ");
            String nySuperkraft = scanner.nextLine();

            System.out.print("Er superhelten et menneske eller ej? ja/nej: ");
            Boolean nyErMenneske = scanner.nextLine().equalsIgnoreCase("ja");

            System.out.print("Indtast nyt oprettelsesår: ");
            int nytOprettelsesår = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Indtast ny styrke: ");
            String nyStyrke = scanner.nextLine();

            database.editSuperhero(nytNavn, nySuperkraft, nyErMenneske, nytOprettelsesår, nyStyrke);
        }
    }


