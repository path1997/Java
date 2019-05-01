package zajecia4;

import java.util.*;

public class Listy {
    public static void main(String[] args) {
        List<Car> samochody=new ArrayList<>();
        samochody.add(new Car("Daewoo","Matiz",2001));
        samochody.add(new Car("Fiat","Panda",2011));
        samochody.add(new Car("Volkswagen","Passat",1998));
        System.out.println(samochody.size());
        System.out.println(samochody.get(1).getBrand());
        System.out.println("...........");
        Collections.sort(samochody);
        Iterator<Car> it =samochody.iterator();
        while(it.hasNext()){
            Car c=it.next();
            System.out.println(c.getBrand());
        }
        System.out.println("..........");
        for(Car c: samochody){
            System.out.println(c.getBrand());
        }

    }
}
