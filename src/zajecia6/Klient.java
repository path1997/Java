package zajecia6;

public class Klient implements Runnable{
    public int id;
    Poczta poczta;

    public Klient(int id,Poczta p){
        this.id=id;
        poczta=p;
    }

    @Override
    public void run() {
        try {
        System.out.println("Klient "+this+" przyszedł na pocztę");
        Okienko o;

        do{
            o=poczta.zajmijOkienko();
            Thread.sleep(5);
        } while(o==null);

        o.obslugaKlienta(this);
        poczta.zwolnijOkienko(o);
        System.out.println("Klient "+this+" idzie do domu");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public String toString(){
        return Integer.toString(id);
    }

    public static void main(String[] args) {
        Poczta p=new Poczta();
        for(int i=0;i<15;i++){
            Thread t= new Thread(new Klient(i+1,p));
            t.start();
        }
    }
}
