package zajecia4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapy {
    public static void main(String[] args) {
        Map<String,List<Car>> pojazdy= new HashMap<>();
        List<Car> l=new ArrayList<>();
        l.add(new Car("Volkswagen","Golf",2010));
        pojazdy.put("Jan Kowalski", l);
        l=new ArrayList<>();
        l.add(new Car("Skoda","Octavia",2016));
        l.add(new Car("Fiat","Panda",2010));

        pojazdy.put("Izabela Kania", l);
        System.out.println(pojazdy);
        for(String osoba: pojazdy.keySet()){
            System.out.print(osoba+" => ");
            for(Car c: pojazdy.get(osoba)){
                System.out.print(c.getModel()+" ");
            }
            System.out.println();
        }
    }
}
