package Statki_serwer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class Serwer {
    //static int odblokowanie_planszy=0;

    private static final int PORT = 9001;
    private static int rozmiar=10;
    private static List<String> names = new ArrayList<String>();
    private static List<String> odblokowanie_planszy=new ArrayList<>();
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }

    private static class Handler extends Thread {
        static public int[] tablica1=new int[100];
        static public int[] tablica2=new int[100];

        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {


                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    out.println("name?");
                    name = in.readLine();
                    if (name == null) {
                        return;
                    }
                    synchronized (names) {
                        if (!names.contains(name)) {
                            names.add(name);
                            break;
                        }
                    }
                }


                out.println("accepted");

                int licznik=0;
                String input = in.readLine();
                    if (input.equals(names.get(0))) {
                        //System.out.println("elo");
                        while (true) {
                            input = in.readLine();
                            if (input == null) {
                                return;
                            } else {

                                tablica1[licznik] = Integer.parseInt(input);
                                out.println(input);
                                //System.out.println(input);
                                licznik++;
                            }
                            if (licznik == (rozmiar*rozmiar)) {
                                licznik=0;
                                //odblokowanie_planszy++;
                                break;
                            }
                        }
                    } else if (input.equals(names.get(1))) {
                        while (true) {
                            //System.out.println("elo1");
                            input = in.readLine();
                            if (input == null) {
                                return;
                            } else {

                                tablica2[licznik] = Integer.parseInt(input);
                                out.println(input);
                                //System.out.println(input);
                                licznik++;
                            }
                            if (licznik == (rozmiar*rozmiar)) {
                                licznik=0;
                                //odblokowanie_planszy++;
                                break;
                            }
                        }
                    }
                /*for(int i=0;i<(rozmiar*rozmiar);i++){
                    System.out.print(tablica1[i]);
                }
                System.out.println("");
                for(int i=0;i<7;i++){
                    System.out.print(tablica2[i]);
                }*/
                System.out.println(names.get(0)+ " "+ names.get(1));
                    writers.add(out);
                    odblokowanie_planszy.add("cos");
                    synchronized (odblokowanie_planszy) {
                        /*while (true) {
                            //System.out.println("jestem");
                            if (odblokowanie_planszy == 2) {*/
                        if(odblokowanie_planszy.size()==2){
                        for (PrintWriter writer : writers) {
                            writer.println(names.get(0) + "1");
                        }
                                /*}
                                break;
                            }
                        }*/
                        }
                    }

                while (true) {
                    System.out.println();
                    int index,wartosc;
                    input = in.readLine();
                    System.out.println(input);
                    if(input.startsWith(names.get(0))){
                        index=Integer.parseInt(input.substring(names.get(0).length()+1));
                        wartosc=tablica2[index];
                        tablica2[index]=0;
                        int licznik1=0;
                        for(int i=0;i<(rozmiar*rozmiar);i++){
                            if(tablica2[i]!=0){
                                licznik1++;
                                break;
                            }
                        }
                        if(licznik1==0){
                            for (PrintWriter writer : writers) {
                                writer.println("wygral "+names.get(0));
                            }
                        } else {
                            for (PrintWriter writer : writers) {
                                if(wartosc==0){
                                    writer.println(names.get(1)+" i "+index);
                                } else {
                                    writer.println(names.get(1)+" b "+index);
                                }

                                writer.println(names.get(0)+" w "+wartosc);
                            }
                        }
                        //System.out.println(names.get(1)+" "+index+" "+wartosc);


                    } else if(input.startsWith(names.get(1))) {
                        System.out.println("test");
                        index = Integer.parseInt(input.substring(names.get(1).length() + 1));
                        wartosc = tablica1[index];
                        tablica1[index] = 0;
                        //System.out.println(names.get(0)+" "+index+" "+wartosc);
                        int licznik1 = 0;
                        for (int i = 0; i < (rozmiar*rozmiar); i++) {
                            if (tablica1[i] != 0) {
                                licznik1++;
                                break;
                            }
                        }
                        if (licznik1 == 0) {
                            for (PrintWriter writer : writers) {
                                writer.println("wygral " + names.get(1));
                            }
                        } else {
                            for (PrintWriter writer : writers) {
                                if(wartosc==0){
                                    writer.println(names.get(0)+" i "+index);
                                } else {
                                    writer.println(names.get(0)+" b "+index);
                                }
                                writer.println(names.get(1) + " w " + wartosc);
                            }
                        }
                    }
                    /*for (PrintWriter writer : writers) {
                        writer.println(names.get(0));
                    }*/
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {

                if (name != null) {
                    names.remove(name);
                }
                if (out != null) {
                    writers.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}