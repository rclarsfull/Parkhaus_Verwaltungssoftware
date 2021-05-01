public class Test {
   public static void main(String[]args){
       Parkhaus testparkhaus= new Parkhaus("testhaus","Lars","lkw",100);
       testparkhaus.ebenehinzufuegen("pkw",300);

       System.out.println(testparkhaus.toString());

       System.out.println(testparkhaus.getPakplatz(1134).toString());

       Menu1.run();
   }


}
