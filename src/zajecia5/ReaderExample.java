package zajecia5;

import java.io.*;
import java.net.URL;

public class ReaderExample {
    static public int licznik=0;
    static public int licznik1=0;
    public static String readFromSource(Reader r) throws IOException {
        BufferedReader br=new BufferedReader(r);
        StringBuilder sb=new StringBuilder();
        String line;
        while((line=br.readLine())!=null){
            sb.append(line+"\n");
            licznik++;
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {

        Reader r=new FileReader("PanTadeusz.txt");
        System.out.println(readFromSource(r));
        r.close();
        System.out.println("Ilosc lini: "+licznik);
        /*URL url= new URL("https://www.uni.lodz.pl");
        r= new InputStreamReader(url.openStream());
        System.out.println(readFromSource(r));
        r.close();*/
    }
}

//PATTERN MATCHER
