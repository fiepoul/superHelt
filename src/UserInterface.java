import java.io.*;
import java.util.*;

public class UserInterface {
    private Scanner scanner;
    private Controller controller;

    public UserInterface(Controller controller) {
            this.scanner = new Scanner(System.in);
            this.controller = controller;
            controller.initDatabase();
        }

    public void start() {
        boolean fortsæt = true;
        controller.initDatabase();

        while (fortsæt) {
            try {
                printMenu();
                int menuValg = scanner.nextInt();
                scanner.nextLine();

                switch (menuValg) {
                    case 1:
                        opretSuperhelte();
                        break;
                    case 2:
                        visAlleSuperhelte();
                        break;
                    case 3:
                        visSorteretListe();
                        break;
                    case 4:
                        søgSuperhelt();
                        break;
                    case 5:
                        redigerSuperhelt();
                        break;
                    case 6:
                        saveAndExit();
                        fortsæt = false;
                        scanner.close();
                        break;

                    default:
                        System.out.println("Du havde syv valgmuligheder. Ikke et helt univers. Vælg igen.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ugyldig input. Indtast et heltal: ");
                scanner.next(); // Ryd op i unvalid input
            }
        }
        scanner.close();
    }

    private void printMenu() {
            System.out.println("\nVELKOMMEN TIL SUPERHELTE UNIVERSET <3");
            System.out.println("\nMENU: ");
            System.out.println("1. Opret superhelte");
            System.out.println("2. Vis alle superhelte");
            System.out.println("3. Vis alle superhelt sorteret");
            System.out.println("4. Søg en superhelt");
            System.out.println("5. Rediger en superhelt");
            System.out.println("6. Slet superhelt");
            System.out.println("7. Forlad programmet");
            System.out.println("Vælg en af valgmulighederne: ");
    }
        public void opretSuperhelte () {
            while (true) {
                System.out.print("\nIndtast superheltnavn: ");
                String navn = scanner.nextLine();

                System.out.print("Er superhelten et menneske? (ja/nej): ");
                boolean erMenneske = scanner.nextLine().equalsIgnoreCase("ja");

                System.out.print("Hvilken superkraft har superhelten: ");
                String superkraft = scanner.nextLine();

                int oprettelsesår = readIntegerWithPrompt("Indtast oprettelsesår (fire cifre): ", 1200, 2023);

                int styrke = readIntegerWithPrompt("Indtast styrke (1-10): ", 1, 10);

                Superhelt superhelt = new Superhelt(navn, erMenneske, superkraft, oprettelsesår, styrke);

                controller.addSuperHeroToDatabase(superhelt);

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

    public void visSorteretListe() {
        System.out.println("Hvilken attribut vil du sortere efter? (navn/oprettelsesår/styrke)");
        String attribut = scanner.nextLine();

        Comparator<Superhelt> comparator;
        switch (attribut.toLowerCase()) {
            case "navn":
                comparator = new SuperHeltComparator();
                break;
            case "oprettelsesår":
                comparator = Comparator.comparing(Superhelt::getOprettelsesår);
                break;
            case "styrke":
                comparator = Comparator.comparing(Superhelt::getStyrke);
                break;
            default:
                System.out.println("Ukendt attribut. Sorterer efter navn som standard.");
                comparator = new SuperHeltComparator();
        }

        ArrayList<Superhelt> sorteretListe = new ArrayList<>(controller.hentAlleSuperhelte());
        sorteretListe.sort(comparator);

        for (Superhelt superhelt : sorteretListe) {
            System.out.println(superhelt.toString());
        }
    }

    public void søgSuperhelt() {
        System.out.println("Søg ved hjælp af et eller flere bogstaver på din superhelt: ");
        String søgeord = scanner.nextLine();
        List<Superhelt> matchendeSuperhelte = controller.søgSuperhelte(søgeord);

        if (matchendeSuperhelte.isEmpty()) {
            System.out.println("Ingen matchende superhelte blev fundet.");
        } else {
            System.out.println("Matchende superhelte:");
            for (Superhelt superhelt : matchendeSuperhelte) {
                System.out.println(superhelt);
            }
        }
    }

        public void redigerSuperhelt () {
            System.out.println("Indtast navnet på den superhelt, du vil redigere: ");
            String navnTilRedigering = scanner.nextLine();

            if (!controller.eksistererSuperhelt(navnTilRedigering)) {
                System.out.println("Superhelten findes ikke i databasen.");
                return; // Afslut metoden uden at forsøge at redigere
            }

            System.out.println("Indtast nyt navn: ");
            String nytNavn = scanner.nextLine();

            System.out.print("Indtast ny superkraft: ");
            String nySuperkraft = scanner.nextLine();

            System.out.print("Er superhelten et menneske eller ej? ja/nej: ");
            Boolean nyErMenneske = scanner.nextLine().equalsIgnoreCase("ja");

            int nytOprettelsesår = readIntegerWithPrompt("Indtast nyt oprettelsesår (fire cifre): ", 1200, 2023);

            int nyStyrke = readIntegerWithPrompt("Indtast ny styrke (1-10): ", 1, 10);

            controller.editSuperhero(nytNavn, nySuperkraft, nyErMenneske, nytOprettelsesår, nyStyrke);
        }

    private int readIntegerWithPrompt(String prompt, int min, int max) {
        int number;
        while (true) {
            System.out.print(prompt);
            try {
                number = scanner.nextInt();
                if (number >= min && number <= max) {
                    break;
                } else {
                    System.out.println("Input er uden for grænsen.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Prøv igen: ");
            }
            scanner.nextLine(); // Clear buffer
        }
        scanner.nextLine(); // Consume the remaining newline
        return number;
    }

    public void saveAndExit() {
        controller.saveSuperheroesIfNeeded();
        System.out.println("Programmet afsluttes og superhelte gemmes.");
        System.exit(0);
    }

    }


