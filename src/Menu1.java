public class Menu1 {
    public static void run() {
        Verwaltung verwaltung = new Verwaltung();
        String auswahl = "";
        while (!auswahl.equalsIgnoreCase("exit")) {
            System.out.println("1. Alle Parkhäuser anzeigen. ");
            System.out.println("2. Parkhaus auswählen ");
            System.out.println("3. Neues Parkhaus erzeugen");
            System.out.println("4. Parkhaus löschen");
            System.out.println("'Exit' zum zurück gehen oder beenden");
            auswahl = Einlesen.readString("Tippen sie die nummer des Menüpunktes ein: ");
            switch (auswahl) {
                case "1":
                    System.out.println("\n\n");
                    int zähler = 0;
                    for (Parkhaus p : verwaltung.getParkaeuser()) {
                        zähler++;
                        System.out.println(zähler + ". " + p.toString());
                    }
                    break;
                case "2":
                    System.out.println("\n\n");
                    int auswahl1 = Einlesen.readInt("ParkhausNr eintippen: ");
                    parkhausAuswaehlen(verwaltung.parkaeuser.get(auswahl1-1));
                    break;
                case "3":
                    System.out.println("\n\n");
                    String name=Einlesen.readString("Geben sie den Namen des Parkhauses ein: ");
                    String nameBetreiber=Einlesen.readString("Geben sie den Namen der Betreibers ein: ");
                    String typ="";
                    boolean richtigeEingabe=false;
                    while (!richtigeEingabe){
                        typ = Einlesen.readString("Geben sie den Typ der Parkplätze der unteren Parkebene ein (lkw/pkw/mieter): ").toLowerCase();
                        if (typ.equalsIgnoreCase("pkw") || typ.equalsIgnoreCase("lkw") || typ.equalsIgnoreCase("mieter")) richtigeEingabe=true;
                    }
                    int maenge=Einlesen.readInt("Geben sie die Mänge der Parkplätze auf der unteren Parkebene an: ");
                    verwaltung.parkaeuser.add(new Parkhaus(name,nameBetreiber,typ,maenge));
                    break;
                case "4":
                    System.out.println("\n\n");
                    verwaltung.getParkaeuser().remove(verwaltung.findParkhaus(Einlesen.readString("Geben sie den Namen des zu löschenden Parkhauses an: ")));
                    break;
                default:
                    System.out.println("Falsche Eingabe wählen Sie eine der oben genannten Optionen.");
                    break;
            }
        }
    }

    private static void parkhausAuswaehlen(Parkhaus parkhaus) {
        String auswahl = "";
        System.out.println("\n\n");
        while (!auswahl.equalsIgnoreCase("exit1")) {
            System.out.println("1. Status aller Parkpläze anzeigen");
            System.out.println("2. Parken");
            System.out.println("3. Ausparken");
            System.out.println("4. Parkhaus editieren");
            System.out.println("'exit' zum zurück gehen oder beenden");
            auswahl = Einlesen.readString("Tippen sie die nummer des Menüpunktes ein: ");
            switch (auswahl) {
                case "1":
                    System.out.println("\n\n");
                    System.out.println(parkhaus.toStringExtendet());
                    break;
                case "2":
                    System.out.println("\n\n");
                    Parkplatz parkplatz=parkhaus.getPakplatz(Einlesen.readInt("Wähle eine Parkplatznummer: "));
                    if (parkplatz==null){
                        System.out.println("Parkplatz ist vergeben, bitte wähle einen anderen.");
                    }else {
                        System.out.println(parkplatz.parken(Einlesen.readString("Tippen sie ihren Fahzeugtyp an ein (pkw/lkw/mieter): ")));
                    }
                    break;
                case "3":System.out.println("\n\n");
                    RFID chip = parkhaus.getRFIDCip(Einlesen.readString("Geben Sie bitte ihre RFID id ein: "));
                    if (chip == null) {
                        System.out.println("Die ID exestiert nicht. Überprüfen sie ihre Eingabe.");
                        break;
                    }
                    System.out.println("Die Kosten balaufen sich auf: " + chip.ausparken() + " Euro\n Vielen Dank für ihren Besuch");
                    break;
                case "4":
                    System.out.println("\n\n");
                    parkhausEdeitieren(parkhaus);
                    break;
                case "exit":
                    auswahl="exit1";
                    break;
                default:
                    System.out.println("\n\n");
                    System.out.println("Falsche Eingabe wählen Sie eine der oben genannten Optionen.");
                    break;
            }
        }


    }

    public static void parkhausEdeitieren(Parkhaus parkhaus) {
        String auswahl = "";
        System.out.println("\n\n");
        while (!auswahl.equalsIgnoreCase("exit2")) {
            System.out.println("1. Ebene hinzufügen");
            System.out.println("2. Oberste Ebene entfernen");
            System.out.println("'Exit' zum zurück gehen oder beenden");
            auswahl = Einlesen.readString("Tippen sie die nummer des Menüpunktes ein: ");
            switch (auswahl) {
                case "1":
                    System.out.println("\n\n");
                    String typ="";
                    boolean richtigeEingabe=false;
                    while (!richtigeEingabe){
                        typ = Einlesen.readString("Geben sie den Typ der Parkplätze der Parkebene ein (lkw/pkw/mieter): ").toLowerCase();
                        if (typ.equalsIgnoreCase("pkw") || typ.equalsIgnoreCase("lkw") || typ.equalsIgnoreCase("mieter")) richtigeEingabe=true;
                    }
                    int maenge=Einlesen.readInt("Geben sie die Mänge der Parkplätze auf der unteren Parkebene an: ");
                    parkhaus.ebenehinzufuegen(typ,maenge);

                    break;
                case "2":
                    boolean test= parkhaus.obersteEbeneEntfernen();
                    if (!test) System.out.println("Ebene konnte nicht entfernt werden, da dort noch Autos parken");
                    break;
                case "exit":
                    auswahl="exit2";
                    break;
                default:
                    System.out.println("Falsche Eingabe wählen Sie eine der oben genannten Optionen.");
                    break;
            }
        }
    }
}
