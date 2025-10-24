import java.util.Scanner; // Importerar Scanner-klassen för att läsa inmatning från terminalen

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Skapar en Scanner för att läsa användarens inmatning
        PersonList personList = new PersonList(); // Skapar ett objekt av PersonList som håller listan över personer

        while (true) { // En oändlig loop som kör programmet tills användaren väljer att avsluta
            System.out.println("\n==== MENY ====");
            System.out.println("1. Lägg till person");
            System.out.println("2. Visa lista");
            System.out.println("3. Sök person");
            System.out.println("4. Ta bort person");
            System.out.println("5. Sortera lista");
            System.out.println("6. Slumpa lista");
            System.out.println("7. Spara lista till fil");
            System.out.println("8. Läs in lista från fil");
            System.out.println("9. Avsluta");
            System.out.print("Välj ett alternativ: ");

            int choice = scanner.nextInt(); // Läser användarens menyval
            scanner.nextLine(); // Rensar scanner-bufferten för att undvika problem med nextLine()

            switch (choice) {
                case 1: // **Lägg till en ny person**
                    System.out.print("Förnamn: ");
                    String fName = scanner.nextLine(); // Läser användarens förnamn
                    System.out.print("Efternamn: ");
                    String lName = scanner.nextLine(); // Läser användarens efternamn
                    int height = 0; // Deklarerar en variabel för att lagra längd

                    // **Säkerställ att height endast innehåller siffror**
                    while (true) {
                        System.out.print("Längd (cm): ");
                        String input = scanner.nextLine(); // Läser användarens inmatning
                        if (input.matches("\\d+")) { // Kontrollerar att inmatningen består av endast siffror
                            height = Integer.parseInt(input); // Konverterar strängen till ett heltal
                            break; // Avslutar loopen om det är ett giltigt tal
                        } else {
                            System.out.println("Fel! Ange en siffra."); // Meddelar användaren om felaktig inmatning
                        }
                    }

                    // **Läs in adressinformation**
                    System.out.print("Adress: ");
                    String street = scanner.nextLine();
                    System.out.print("Postnummer: ");
                    String zip = scanner.nextLine();
                    System.out.print("Postort: ");
                    String city = scanner.nextLine();

                    // Skapa ett Address-objekt och ett Person-objekt
                    Address address = new Address(street, zip, city);
                    Person person = new Person(fName, lName, height, address);

                    // Lägg till personen i listan
                    personList.addPerson(person);
                    System.out.println("Person tillagd!");
                    break;

                case 2: // **Visa listan över personer**
                    personList.printPersons();
                    break;

                case 3: // **Sök efter en person med signatur**
                    System.out.print("Ange signatur att söka: ");
                    String sign = scanner.nextLine(); // Läser in signaturen att söka efter
                    personList.searchPerson(sign);
                    break;

                case 4: // **Ta bort en person från listan**
                    System.out.print("Ange signatur att ta bort: ");
                    String removeSign = scanner.nextLine(); // Läser in signaturen att ta bort
                    personList.removePerson(removeSign);
                    break;

                case 5: // **Sortera listan efter signatur**
                    personList.sortByName();
                    break;

                case 6: // **Slumpa ordningen i listan**
                    personList.shuffleList();
                    break;

                case 7: // **Spara listan till en fil med namn från terminalen**
                    System.out.print("Ange filnamn som slutar med (.txt) att spara till: ");
                    String saveFileName = scanner.nextLine(); // Användaren skriver in filnamnet
                    personList.saveToFile(saveFileName);
                    break;

                case 8: // **Läs in listan från en fil med namn från terminalen**
                    System.out.print("Ange filnamn som slutar med (.txt) att läsa från: ");
                    String loadFileName = scanner.nextLine(); // Användaren skriver in filnamnet
                    personList.loadFromFile(loadFileName);
                    break;

                case 9: // **Avsluta programmet**
                    System.out.println("Avslutar programmet...");
                    scanner.close(); // Stänger scannern
                    return; // Avslutar `main()`-metoden och därmed programmet

                default: // **Felhantering om användaren skriver in ett ogiltigt val**
                    System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }
}
