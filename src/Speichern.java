/*
Autor:  Lars Przybylek
Mail:   l.p.1999@live.de
 */
import java.io.*;
import java.util.ArrayList;


public class Speichern {
    public static void spreichern(Verwaltung a){
        try(FileOutputStream fos=new FileOutputStream("save.txt",false) ;
            ObjectOutputStream oos=new ObjectOutputStream(fos)){
            oos.writeObject(a);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void RFIDSspreichern(ArrayList<String> rfids){
        File temp=new File("RFID.txt");
        try(FileOutputStream fos=new FileOutputStream(temp) ;
            ObjectOutputStream oos=new ObjectOutputStream(fos)){
            oos.writeObject(rfids);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static Verwaltung laden(){
        try (FileInputStream fis=new FileInputStream(Einlesen.readString("Geben sie den Namen der Dartei mit der Darteiendung '.txt' ein"));
             ObjectInputStream ois=new ObjectInputStream(fis)){
            return (Verwaltung) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Dartei nicht gefunden");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("1");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("2");
        }
        System.out.println("Lade-Fehler!!! Neuer Datenstand Erstellt");
        return new Verwaltung();

    }
    public static ArrayList<String> ladenAlleRFIDs(){
        try (FileInputStream fis=new FileInputStream("RFID.txt");
             ObjectInputStream ois=new ObjectInputStream(fis)){
            return (ArrayList<String>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Dartei nicht gefunden(RFID)");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("1");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("2");
        }
        System.out.println("Lade-Fehler!!!(RFID) Neuer Datenstand erstellt");
        return new ArrayList<String>();

    }

    public static int ladenHighestRFID(){
        ArrayList<String> allRFIDIds=ladenAlleRFIDs();
            int id=1;
            ArrayList<Integer> ints=new ArrayList<>();
            if (allRFIDIds.size()>0){
                for (String s:allRFIDIds) {
                    s=s.replace("#","0");
                    ints.add(Integer.parseInt(s));
                }
            }
            for (int i:ints) {
                if (i>id) id=i;
            }
            return id;
        }
    }



