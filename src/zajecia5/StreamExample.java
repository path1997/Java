package zajecia5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class StreamExample {
    static public int licznik=0;
    static public int licznik1=0;
    static public String slowo=null;
    public static String readFromSource(InputStream is) throws IOException {
        int b;
        StringBuilder sb= new StringBuilder();
        while((b=is.read())>-1){
            if((char)b==' ')
            slowo+=(char)b;

            if((char) b=='o'){
                licznik++;
            }
            sb.append((char) b);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        InputStream is = new FileInputStream("PanTadeusz.txt");
        System.out.println(readFromSource(is));
        System.out.println("Ilosc liter o: "+licznik);
        is.close();

        /*URL url=new URL("https://www.uni.lodz.pl");
        is=url.openStream();
        System.out.println(readFromSource(url.openStream()));
        is.close();*/
    }
}
