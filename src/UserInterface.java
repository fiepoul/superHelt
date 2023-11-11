import java.util.*;

public class UserInterface {
    private final Scanner scanner;
    private final Controller controller;

    public UserInterface(Controller controller) {
            this.scanner = new Scanner(System.in);
            this.controller = controller;
            controller.initDatabase();
        }

    public void start() {
        boolean continues = true;

        while (continues) {
            try {
                printMenu();
                int menuOption = Integer.parseInt(scanner.nextLine());

                switch (menuOption) {
                    case 1 -> createSuperhero();
                    case 2 -> displaySuperheroesSorted(false);
                    case 3 -> displaySuperheroesSorted(true);
                    case 4 -> searchSuperhero();
                    case 5 -> editSuperhero();
                    case 6 -> deleteSuperhero();
                    case 7 -> {
                        saveAndExit();
                        continues = false;
                    }
                    default -> System.out.println("Ugyldigt valg. Vælg igen (1-7): ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ugyldig input. Indtast et heltal: ");
            }
        }
    }

    private void printMenu() {
            System.out.println("\nVELKOMMEN TIL SUPERHELTE UNIVERSET <3");
            System.out.println("\nMENU: ");
            System.out.println("1. Opret superhelte");
            System.out.println("2. Vis alle superhelte");
            System.out.println("3. Sorter superheltelisten");
            System.out.println("4. Søg en superhelt");
            System.out.println("5. Rediger en superhelt");
            System.out.println("6. Slet superhelt");
            System.out.println("7. Forlad programmet");
            System.out.println("Vælg en af valgmulighederne: ");
    }
        public void createSuperhero() {
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

    public void displaySuperheroesSorted(boolean isSorted) {
        if (isSorted) {
            String primaryAttribute = getAttributeInput("Vælg primær attribut til sortering: ");
            String secondaryAttribute = getAttributeInput("Vælg sekundær attribut til sortering: ");
            List<Superhelt> sortedSuperheroes = controller.getSuperheroesSorted(primaryAttribute, secondaryAttribute);
            sortedSuperheroes.forEach(System.out::println);
        } else {
            List<Superhelt> allSuperheroes = controller.hentAlleSuperhelte();
            allSuperheroes.forEach(System.out::println);
        }
    }

    private String getAttributeInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void searchSuperhero() {
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

        public void editSuperhero() {
            System.out.println("Indtast navnet på den superhelt, du vil redigere: ");
            String navnTilRedigering = scanner.nextLine();

            if (!controller.superheroExists(navnTilRedigering)) {
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

    public void deleteSuperhero() {
        System.out.println("Indtast navnet på den superhelt du vil slette: ");
        String superheroToDelete = scanner.nextLine().trim();

        if (!controller.superheroExists(superheroToDelete)) {
            System.out.println("Superhelten findes ikke i databasen.");
        } else {
            controller.deleteSuperhero(superheroToDelete);
            System.out.println("Du har slettet " + superheroToDelete + " fra listen.");
        }
    }

    public void saveAndExit() {
        try {
            controller.saveSuperheroesIfNeeded();
            System.out.println("Superhelte er gemt.");
        } catch (Exception e) {
            System.out.println("Fejl ved gemning af superhelte: " + e.getMessage());
        }
        System.out.println("Programmet afsluttes.");
    }

}


