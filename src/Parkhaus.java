/*
Autor:  Lars Przybylek
Mail:   l.p.1999@live.de
 */
import java.io.Serializable;
import java.util.ArrayList;
public class Parkhaus implements Serializable {
    public String name, betreiber;
    private ArrayList<Parkebene> ebenen;
    private ArrayList<String> allRFIDIds;

    Parkhaus(String name, String betreiber, String typ, int anzahlDerParklaezeVonEbene0) {
        ebenen = new ArrayList<>(1);
        ebenen.add(new Parkebene(anzahlDerParklaezeVonEbene0, typ, 0));
        this.name = name;
        this.betreiber = betreiber;
        allRFIDIds=new ArrayList<>();
    }

    public void ebenehinzufuegen(String typ, int anzahlParkplaetze) {
        ebenen.add(new Parkebene(anzahlParkplaetze, typ, ebenen.size()));
    }

    public ArrayList<String> getAllRFIDIds() {
        return allRFIDIds;
    }

    public ArrayList<Parkebene> getEbenen() {
        return ebenen;
    }

    public boolean obersteEbeneEntfernen(){
        boolean allesLeer=true;
        ArrayList<Parkplatz> obersteEbene=ebenen.get(ebenen.size()-1).parkplaeze;
        for (Parkplatz p:obersteEbene) {
            if (!p.istFrei()) allesLeer = false;
        }
        if (allesLeer){
            ebenen.remove(ebenen.size()-1);
            return true;
        }
        return false;
    }

    public String toStringExtendet() {
        String ausgabe= "Parkhaus{" ;
        for (Parkebene e:ebenen) {
            ausgabe+="\n\t"+ e.toString() + "\n";
        }
        ausgabe+=  '}';
        return ausgabe;
    }

    @Override
    public String toString() {
        return "Parkhaus{" +
                "name='" + name + '\'' +
                ", betreiber='" + betreiber + '\'' +
                ", ebenen=" + ebenen.size() +
                '}';
    }

    public String getName() {
        return name;
    }

    public Parkplatz getPakplatz(int parkplatzNR){
        for (Parkebene e:ebenen) {
            for (Parkplatz p:e.parkplaeze) {
                if (parkplatzNR==p.getParkplatzNR()) return p;
            }
        }
        return null;
    }

    public RFID getRFIDCip(String id){
        for (Parkebene e:ebenen) {
            for (Parkplatz p:e.parkplaeze) {
                if (p.getChip()!= null && p.getChip().getId().equalsIgnoreCase(id)) return p.getChip();
            }
        }
        return null;
    }


}


