package zajecia6;

import java.util.ArrayList;
import java.util.List;

public class Licznik implements Runnable {
private static int x;
private static Object synchMonitor=new Object();
private int id;
public Licznik(int id) {
    this.id=id;
}

private static synchronized void next(){
    x++;
}
    @Override
    public void run() {
        for(int i=0;i<1000;i++){
            /*synchronized (synchMonitor){
                x++;
            }*/
            next();
            if(x%50==0){
                System.out.println("w"+id+" --"+x);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
    List<Thread> th= new ArrayList<>();
    for(int i=0;i<100;i++){
        Thread t= new Thread(new Licznik(i+1));
        t.start();
        th.add(t);
    }
        for(Thread t: th){
            t.join();
        }
        System.out.println(x);
        System.out.println("Koniec programu");
    }
}
