/*
Autor:  Lars Przybylek
Mail:   l.p.1999@live.de
 */
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

public class RFID implements Serializable {
    private final int ZUSATZGEBUER=15;
    private final int LKWENTSORGUNGSKOSTEN=300;
    private final int PREISINERSTEN30MIN=1;
    private final int PREISPROSTUNDE=5;
    private final int PREISPROTAG=35;
    private String id;
    private static int zaehler =1;
    private LocalDateTime eingparkt_am;
    private String typFahrzeug;
    private int ebene;
    private Parkplatz aktuellerParkplatz;

    RFID(String typ,int ebene,Parkplatz aktuellerParkplatz){
        eingparkt_am=LocalDateTime.now();
        this.typFahrzeug =typ.toLowerCase();
        if (zaehler<10){
            id="#0000"+zaehler;
        }else if (zaehler<100){
            id="#000"+zaehler;
        }else if (zaehler<1000){
            id="#00"+zaehler;
        }else if (zaehler<10000){
            id="#0"+zaehler;
        }else {
            id = "#" + zaehler;
        }
        zaehler++;
        this.ebene=ebene;
        this.aktuellerParkplatz=aktuellerParkplatz;
    }

    public void setEingparkt_am(LocalDateTime eingparkt_am) {
        this.eingparkt_am = eingparkt_am;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "RFID{" +
                "id='" + id + '\'' +
                ", eingparkt_am=" + eingparkt_am +
                ", typFahrzeug='" + typFahrzeug + '\'' +
                ", ebene=" + ebene +
                ", aktuellerParkplatz=" + aktuellerParkplatz +
                '}';
    }

    public String parkdauerAusgeben(){
        long minEingeparkt= Duration.between(LocalDateTime.now(),eingparkt_am).toMinutes();
        int tage=(int)minEingeparkt/60/24;
        int stunden=(int)minEingeparkt/60-(tage*24);
        int min=(int)minEingeparkt-(stunden*60)-(tage*24*60);

        return "Die Parkdauer war "+tage+". Tage "+stunden+". und "+min+". Minuten";
    }

    public int ausparken(){
        long minEingeparkt= Duration.between(LocalDateTime.now(),eingparkt_am).toMinutes();
        int preis=0;

        int tage=(int)minEingeparkt/60/24;
        int stunden=(int)minEingeparkt/60-(tage*24);
        int min=(int)minEingeparkt-(stunden*60)-(tage*24*60);

        if (typFahrzeug.equals("pkw")){
            if (aktuellerParkplatz.getTypParkpaltz().equalsIgnoreCase("lkw") && minEingeparkt>60){
                for (int i = 0; i < ((int) minEingeparkt/60); i++) {
                    preis+=ZUSATZGEBUER;
                }
            }

            if (minEingeparkt<=30){
                preis+=PREISINERSTEN30MIN;
            }else if (tage==0){
                preis+=stunden*PREISPROSTUNDE;
            } else {
                preis+= tage*PREISPROTAG;
                preis+=stunden*PREISPROSTUNDE;
                if (min>0) preis+=PREISPROSTUNDE;
            }

        }else if (typFahrzeug.equals("lkw")){
            if (minEingeparkt>180){
                preis+=LKWENTSORGUNGSKOSTEN;
            }
        }
        return preis;
    }

}

