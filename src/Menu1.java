/*
Autor:  Lars Przybylek
Mail:   l.p.1999@live.de
 */
public class Menu1 {
    public static void run() {

        boolean laden=Einlesen.readBoolean("Tippen sie 'true' eun wenn sie einen Speicherstand laden m�chten, bei jeder anderen Eingabe wird ein neuer Speicherstand erzeugt.");
        Verwaltung verwaltung = new Verwaltung();
        if (laden) verwaltung=Speichern.laden();
        Speichern.spreichern(verwaltung);





        String auswahl = "";
        while (!auswahl.equalsIgnoreCase("exit")) {
            System.out.println("1. Alle Parkh�user anzeigen. ");
            System.out.println("2. Parkhaus ausw�hlen ");
            System.out.println("3. Neues Parkhaus erzeugen");
            System.out.println("4. Parkhaus l�schen");
            System.out.println("'Exit' zum zur�ck gehen oder beenden");
            auswahl = Einlesen.readString("Tippen sie die nummer des Men�punktes ein: ");
            switch (auswahl) {
                case "1":
                    System.out.println("\n\n");
                    int z�hler = 0;
                    for (Parkhaus p : verwaltung.getParkaeuser()) {
                        z�hler++;
                        System.out.println(z�hler + ". " + p.toString());
                    }
                    break;
                case "2":
                    System.out.println("\n\n");
                    int auswahl1 = Einlesen.readInt("ParkhausNr eintippen: ");
                    parkhausAuswaehlen(verwaltung.parkaeuser.get(auswahl1-1),verwaltung);
                    break;
                case "3":
                    System.out.println("\n\n");
                    String name=Einlesen.readString("Geben sie den Namen des Parkhauses ein: ");
                    String nameBetreiber=Einlesen.readString("Geben sie den Namen der Betreibers ein: ");
                    String typ="";
                    boolean richtigeEingabe=false;
                    while (!richtigeEingabe){
                        typ = Einlesen.readString("Geben sie den Typ der Parkpl�tze der unteren Parkebene ein (lkw/pkw/mieter): ").toLowerCase();
                        if (typ.equalsIgnoreCase("pkw") || typ.equalsIgnoreCase("lkw") || typ.equalsIgnoreCase("mieter")) richtigeEingabe=true;
                    }
                    int maenge=Einlesen.readInt("Geben sie die M�nge der Parkpl�tze auf der unteren Parkebene an: ");
                    verwaltung.parkaeuser.add(new Parkhaus(name,nameBetreiber,typ,maenge));
                    Speichern.ueberschreiben(verwaltung);
                    break;
                case "4":
                    System.out.println("\n\n");
                    verwaltung.getParkaeuser().remove(verwaltung.findParkhaus(Einlesen.readString("Geben sie den Namen des zu l�schenden Parkhauses an: ")));
                    Speichern.ueberschreiben(verwaltung);
                    break;
                default:
                    System.out.println("Falsche Eingabe w�hlen Sie eine der oben genannten Optionen.");
                    break;
            }
        }
    }

    private static void parkhausAuswaehlen(Parkhaus parkhaus,Verwaltung verwaltung) {
        String auswahl = "";
        System.out.println("\n\n");
        while (!auswahl.equalsIgnoreCase("exit1")) {
            System.out.println("1. Status aller Parkpl�ze anzeigen");
            System.out.println("2. Parken");
            System.out.println("3. Ausparken");
            System.out.println("4. Parkhaus editieren");
            System.out.println("'exit' zum zur�ck gehen oder beenden");
            auswahl = Einlesen.readString("Tippen sie die nummer des Men�punktes ein: ");
            switch (auswahl) {
                case "1":
                    System.out.println("\n\n");
                    System.out.println(parkhaus.toStringExtendet());
                    break;
                case "2":
                    System.out.println("\n\n");
                    Parkplatz parkplatz=parkhaus.getPakplatz(Einlesen.readInt("W�hle eine Parkplatznummer: "));
                    if (parkplatz==null){
                        System.out.println("Parkplatz ist vergeben, bitte w�hle einen anderen.");
                    }else {
                        System.out.println(parkplatz.parken(Einlesen.readString("Tippen sie ihren Fahzeugtyp an ein (pkw/lkw/mieter): ")));
                    }
                    Speichern.ueberschreiben(verwaltung);
                    break;
                case "3":System.out.println("\n\n");
                    RFID chip = parkhaus.getRFIDCip(Einlesen.readString("Geben Sie bitte ihre RFID id ein: "));
                    if (chip == null) {
                        System.out.println("Die ID exestiert nicht. �berpr�fen sie ihre Eingabe.");
                        break;
                    }
                    System.out.println("Die Kosten balaufen sich auf: " + chip.ausparken() + " Euro\n Vielen Dank f�r ihren Besuch");
                    Speichern.ueberschreiben(verwaltung);
                    break;
                case "4":
                    System.out.println("\n\n");
                    parkhausEdeitieren(parkhaus,verwaltung);
                    Speichern.ueberschreiben(verwaltung);
                    break;
                case "exit":
                    auswahl="exit1";
                    break;
                default:
                    System.out.println("\n\n");
                    System.out.println("Falsche Eingabe w�hlen Sie eine der oben genannten Optionen.");
                    break;
            }
        }


    }

    public static void parkhausEdeitieren(Parkhaus parkhaus, Verwaltung verwaltung) {
        String auswahl = "";
        System.out.println("\n\n");
        while (!auswahl.equalsIgnoreCase("exit2")) {
            System.out.println("1. Ebene hinzuf�gen");
            System.out.println("2. Oberste Ebene entfernen");
            System.out.println("'Exit' zum zur�ck gehen oder beenden");
            auswahl = Einlesen.readString("Tippen sie die nummer des Men�punktes ein: ");
            switch (auswahl) {
                case "1":
                    System.out.println("\n\n");
                    String typ="";
                    boolean richtigeEingabe=false;
                    while (!richtigeEingabe){
                        typ = Einlesen.readString("Geben sie den Typ der Parkpl�tze der Parkebene ein (lkw/pkw/mieter): ").toLowerCase();
                        if (typ.equalsIgnoreCase("pkw") || typ.equalsIgnoreCase("lkw") || typ.equalsIgnoreCase("mieter")) richtigeEingabe=true;
                    }
                    int maenge=Einlesen.readInt("Geben sie die M�nge der Parkpl�tze auf der Parkebene an: ");
                    parkhaus.ebenehinzufuegen(typ,maenge);
                    Speichern.ueberschreiben(verwaltung);
                    break;
                case "2":
                    boolean test= parkhaus.obersteEbeneEntfernen();
                    if (!test) System.out.println("Ebene konnte nicht entfernt werden, da dort noch Autos parken");
                    Speichern.ueberschreiben(verwaltung);
                    break;
                case "exit":
                    auswahl="exit2";
                    break;
                default:
                    System.out.println("Falsche Eingabe w�hlen Sie eine der oben genannten Optionen.");
                    break;
            }
        }
    }
}
