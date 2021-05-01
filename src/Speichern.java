/*
Autor:  Lars Przybylek
Mail:   l.p.1999@live.de
 */
import java.io.*;


public class Speichern {
    public static void spreichern(Verwaltung a){
        try(FileOutputStream fos=new FileOutputStream("save.txt") ;
            ObjectOutputStream oos=new ObjectOutputStream(fos)){
            oos.writeObject(a);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ueberschreiben(Verwaltung a){
        File temp=new File("save.txt");
        try(FileOutputStream fos=new FileOutputStream(temp) ;
            ObjectOutputStream oos=new ObjectOutputStream(fos)){
            oos.writeObject(a);
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
        System.out.println("Lade-Fehler!!!");
        return new Verwaltung();

    }
}


