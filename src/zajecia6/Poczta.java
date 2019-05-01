package zajecia6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Poczta {
    List<Okienko> okienka= new ArrayList<>();
    public Poczta(){
        okienka.add(new Okienko('A'));
        okienka.add(new Okienko('B'));
        okienka.add(new Okienko('C'));

    }
    public synchronized Okienko zajmijOkienko(){
        if(okienka.size()==0) return null;
        Iterator<Okienko> it=okienka.iterator();
        Okienko o=it.next();
        it.remove();
        return o;
    }

    public synchronized void zwolnijOkienko(Okienko o){
        okienka.add(o);
    }
}
