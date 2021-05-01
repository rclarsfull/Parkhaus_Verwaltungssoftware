/*
Autor:  Lars Przybylek
Mail:   l.p.1999@live.de
 */
import java.io.Serializable;
import java.util.ArrayList;
public class Parkebene implements Serializable {
    ArrayList<Parkplatz> parkplaeze;
    Parkebene(int parkplaezeProEbene, String typ, int ebene){
        if (parkplaezeProEbene>1000){
            System.out.println("FEHLER: Ebene "+ebene+" wurde ohne Parkplätze erstellt, jede Ebene darf maximal 1000 Parkplätze haben!!");
        } else {
            parkplaeze=new ArrayList<>();

            for (int i = 0; i < parkplaezeProEbene; i++) {
                parkplaeze.add(new Parkplatz(typ,ebene*1000+i));
            }

        }
    }

    public ArrayList<Parkplatz> getParkplaeze() {
        return parkplaeze;
    }

    public int getAnzahlParkplaetze(){
        return parkplaeze.size();
    }

    public ArrayList<Parkplatz> getFreiParkplaeze(){
        ArrayList<Parkplatz> ausgabe=new ArrayList<>();
        for (Parkplatz p:parkplaeze) {
            if (p.istFrei()){
                ausgabe.add(p);
            }
        }
       return ausgabe;
    }

    @Override
    public String toString() {
        String ausgabe= "Parkebene{ " ;
        for (Parkplatz p:parkplaeze) {
            ausgabe+="\n\t"+ p.toString() + "\n";
        }
              ausgabe+=  '}';
              return ausgabe;
    }
}
