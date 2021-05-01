/*
Autor:  Lars Przybylek
Mail:   l.p.1999@live.de
 */
import java.io.Serializable;
import java.util.ArrayList;
public class Verwaltung implements Serializable {

    Verwaltung() {};
    ArrayList<Parkhaus> parkaeuser=new ArrayList<>();
    public void baueParkhaus(String name, String betreiber,String typDerEbene0, int parkpaezeEbene0 ){
        parkaeuser.add(new Parkhaus(name,betreiber, typDerEbene0, parkpaezeEbene0));
    }

    public ArrayList<Parkhaus> getParkaeuser() {
        return parkaeuser;
    }

    public Parkhaus findParkhaus(String name){
        for (Parkhaus p:parkaeuser) {
            if (p.getName().equalsIgnoreCase(name)) return p;
        }
        return null;
    }
}
