
public class Parkplatz {
    private boolean istVerwendet;
    private boolean istDefekt;
    private int parkplatzNR;
    private String typParkpaltz;
    private RFID chip;
    private int ebene;



    Parkplatz(String typ, int parkplatzNR){
        this.typParkpaltz =typ;
        this.ebene= (int)parkplatzNR/1000;
        this.parkplatzNR=parkplatzNR;
        istVerwendet=false;
    }

    public boolean istFrei(){
        return !istVerwendet;
    }
    public boolean IstDefekt(){
        return istDefekt;
    }

    public RFID getChip() {
        return chip;
    }

    public String getTypParkpaltz() {
        return typParkpaltz;
    }

    public String getStatusLED(){
        if (istDefekt){
            return "gelb";
        }
        if (istVerwendet){
            return  "rot";
        }
        if (typParkpaltz.equalsIgnoreCase("miet")){
            return "blau";
        }
        return "gruen";
    }

    public void setIstDefekt(boolean istDefekt) {
        this.istDefekt = istDefekt;
    }

    public int getParkplatzNR() {
        return parkplatzNR;
    }

    public int getEbene() {
        return ebene;
    }

    public RFID parken(String typFahrzeug){
        if(istDefekt){
            return null;
        }
        if(istVerwendet){
            return null;
        }
        if (this.typParkpaltz.equals("pkw") && typFahrzeug.equals("lkw")){
            System.out.println("Dies ist ein PKW Parkparkatz bitte w�hlen sie einen LKW Parkpalatz f�r ihr Fahrzeug");
            return null;
        }
        if (this.typParkpaltz.equals("mieter") && !typFahrzeug.equals("mieter")){
            System.out.println("Dies ist ein  f�r Mieter resavierter Parkpakplatz bitte w�hlen sie einen normalen Parkpalatz aus");
            return null;
        }
        chip=new RFID(typFahrzeug,ebene,this);
        istVerwendet=true;
        return chip;
    }

    public int ausparken(){
        istVerwendet=false;
        System.out.println(chip.parkdauerAusgeben());
        return chip.ausparken();
    }

    @Override
    public String toString() {
        return "Parkplatz{" +
                "parkplatzNR=" + parkplatzNR +
                ", status'" + getStatusLED() + '\'' +
                ", typ='" + typParkpaltz + '\'' +
                ", ebene=" + ebene +
                '}';
    }
}
