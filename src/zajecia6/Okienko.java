package zajecia6;

import java.util.Random;

public class Okienko {
    private char nazwa;
    private Random rand=new Random();

    public Okienko(char nazwa){
        this.nazwa=nazwa;
    }

    public void obslugaKlienta(Klient k) throws InterruptedException {
        System.out.println("Klient "+k+" podszedł do okienka "+nazwa);
        int czas=rand.nextInt(50);
        Thread.sleep(czas+50);
        System.out.println("Klient "+k+" odszedl od okienta "+nazwa);
    }

}
