package zajecia4;

import java.util.HashSet;
import java.util.Set;

public class Zbiory {
    public static void main(String[] args) {
        Set<Car> samochody=new HashSet<>();
        samochody.add(new Car("Daewoo","Matiz",2001));
        samochody.add(new Car("Fiat","Panda",2011));
        samochody.add(new Car("Volkswagen","Passat",1998));
        System.out.println(samochody.size());
        System.out.println(samochody);
        for(Car c:samochody){
            System.out.println(c+" \t"+c.hashCode());
        }
        System.out.println("..................");
        Car c=new Car("Volkwagen","Passat",1998);
        samochody.add(c);
        System.out.println(samochody);
        samochody.add(c);
        System.out.println(samochody);
    }
}
