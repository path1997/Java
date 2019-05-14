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
        System.out.println("Serwer gry statki uruchomiony.");
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
        static public int[][] tablica1=new int[100][101];
        //static public int[] tablica2=new int[100];

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
                int pozycja;
                pozycja=names.size()-1;
                System.out.println(pozycja);
                out.println("accepted");
                while(true) {
                    tablica1[pozycja][100]=0;
                    //System.out.println("poczatek petli");
                    int licznik = 0;
                    String input = in.readLine();
                    //System.out.println(input);
                    if (input.equals(names.get(pozycja))) {
                        //System.out.println("elo");
                        while (true) {
                            input = in.readLine();
                            if (input == null) {
                                return;
                            } else {

                                tablica1[pozycja][licznik] = Integer.parseInt(input);
                                //out.println(input);
                                //System.out.println(input);
                                licznik++;
                            }
                            if (licznik == (rozmiar * rozmiar)) {
                                licznik = 0;
                                //odblokowanie_planszy++;
                                break;
                            }
                        }
                    }
                /*for(int i=0;i<7;i++){
                    System.out.print(tablica1[0][i]);
                }
                System.out.println("");
                for(int i=0;i<7;i++){
                    System.out.print(tablica1[1][i]);
                }*/
                    //System.out.println("jestem");
                    //System.out.println(names.get(0) + " " + names.get(1));
                    writers.add(out);
                    //odblokowanie_planszy.add("cos");
                    tablica1[pozycja][100]=1;
                    //System.out.println(odblokowanie_planszy.size());
                    //synchronized (odblokowanie_planszy) {
                        /*while (true) {
                            //System.out.println("jestem");
                            if (odblokowanie_planszy == 2) {*/
                        /*if (odblokowanie_planszy.size() % 2==0) {
                            for (PrintWriter writer : writers) {
                                if(pozycja%2==0) {
                                    writer.println(names.get(pozycja));
                                    writer.println(names.get(pozycja + 1));
                                } else {
                                    writer.println(names.get(pozycja));
                                    writer.println(names.get(pozycja -1));
                                }
                            }
                            for (PrintWriter writer : writers) {
                                if(pozycja%2==1) {
                                    writer.println(names.get(pozycja-1) + "1");
                                } else {
                                    writer.println(names.get(pozycja) + "1");
                                }
                            }
                        }

                    }*/
                    synchronized (tablica1){
                        if(pozycja%2==0){
                            if(tablica1[pozycja][100]==1 && tablica1[pozycja+1][100]==1){
                                for (PrintWriter writer : writers) {
                                    writer.println(names.get(pozycja));
                                    writer.println(names.get(pozycja + 1));
                                }
                                for (PrintWriter writer : writers) {
                                    writer.println(names.get(pozycja) + "1");
                                }
                                //break;
                            }
                        } else if(pozycja%2==1){
                            if(tablica1[pozycja-1][100]==1 && tablica1[pozycja][100]==1){
                                for (PrintWriter writer : writers) {
                                    writer.println(names.get(pozycja));
                                    writer.println(names.get(pozycja-1));
                                }
                                for (PrintWriter writer : writers) {
                                    writer.println(names.get(pozycja-1) + "1");
                                }
                                //break;
                            }
                        }
                    }


                    while (true) {
                        //System.out.println();
                        int index, wartosc;
                        input = in.readLine();
                        System.out.println(input);
                        if(pozycja%2==0){
                        if (input.startsWith(names.get(pozycja))) {
                            index = Integer.parseInt(input.substring(names.get(pozycja).length() + 1));
                            wartosc = tablica1[pozycja+1][index];
                            tablica1[pozycja+1][index] = 0;
                            int licznik1 = 0;
                            for (int i = 0; i < (rozmiar * rozmiar); i++) {
                                if (tablica1[pozycja+1][i] != 0) {
                                    licznik1++;
                                    break;
                                }
                            }
                            if (licznik1 == 0) {
                                for (PrintWriter writer : writers) {
                                    writer.println("wygral " + names.get(pozycja+1));
                                    writer.println("przegral " + names.get(pozycja));
                                }
                            } else {
                                for (PrintWriter writer : writers) {
                                    if (wartosc == 0) {
                                        writer.println(names.get(pozycja+1) + " i " + index);
                                    } else {
                                        writer.println(names.get(pozycja+1) + " b " + index);
                                    }

                                    writer.println(names.get(pozycja) + " w " + wartosc);
                                }
                            }
                            //System.out.println(names.get(1)+" "+index+" "+wartosc);
                        }

                        } if(pozycja%2==1){
                            if (input.startsWith(names.get(pozycja))) {
                                //System.out.println("test");
                                index = Integer.parseInt(input.substring(names.get(1).length() + 1));
                                wartosc = tablica1[pozycja-1][index];
                                tablica1[pozycja-1][index] = 0;
                                //System.out.println(names.get(0)+" "+index+" "+wartosc);
                                int licznik1 = 0;
                                for (int i = 0; i < (rozmiar * rozmiar); i++) {
                                    if (tablica1[pozycja-1][i] != 0) {
                                        licznik1++;
                                        break;
                                    }
                                }
                                if (licznik1 == 0) {
                                    for (PrintWriter writer : writers) {
                                        writer.println("wygral " + names.get(pozycja));
                                        writer.println("przegral " + names.get(pozycja-1));
                                    }
                                } else {
                                    for (PrintWriter writer : writers) {
                                        if (wartosc == 0) {
                                            writer.println(names.get(pozycja-1) + " i " + index);
                                        } else {
                                            writer.println(names.get(pozycja-1) + " b " + index);
                                        }
                                        writer.println(names.get(pozycja) + " w " + wartosc);
                                    }
                                }
                            }
                        } if (input.startsWith("nowa gra")) {
                            if(pozycja%2==0) {
                                for (PrintWriter writer : writers) {
                                    writer.println("nowa gra "+names.get(pozycja));
                                    writer.println("nowa gra "+names.get(pozycja+1));
                                }
                            }
                            else {
                                for (PrintWriter writer : writers) {
                                    writer.println("nowa gra "+names.get(pozycja));
                                    writer.println("nowa gra "+names.get(pozycja-1));
                                }
                            }
                        } else if(input.startsWith("nowagraok")){
                            //odblokowanie_planszy.clear();
                            //System.out.println("nowa gra ok");
                            System.out.println("jestem "+pozycja);
                            break;
                        }
                    /*for (PrintWriter writer : writers) {
                        writer.println(names.get(0));
                    }*/

                    }
                    continue;
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