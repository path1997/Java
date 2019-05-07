package Sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Silnik {
    public int rozmiar=9;
    public int rozmiar_petla=3;

    Random generator = new Random();
    List<Integer> miejsca_zle= new ArrayList<>();
    List<Integer> miejsca_puste=new ArrayList<>();

    public int[][] tablica;
    public int[][] odpowiedz;

    public void uzupelnij_tablice(){

        tablica = new int[rozmiar][rozmiar];
        odpowiedz = new int[rozmiar][rozmiar];
        int licznik = 0, licznik1 = 0, liczba = 0;
        boolean stan = false, od_nowa = false;

        for (int i = 0; i < rozmiar; i++) {
            for (int j = 0; j < rozmiar; j++) {
                tablica[i][j] = -1;
            }
        }
        for (int i = 0; i < rozmiar; i++) {
            for (int j = 0; j < rozmiar; j++) {
                licznik1 = 0;
                liczba = (generator.nextInt(rozmiar))+1;
                do {
                    stan = czy_mozna_wstawic(i, j, liczba);
                    if (stan == true) {
                        tablica[i][j] = liczba;
                    }
                    if (liczba == 1) {
                        liczba = rozmiar;
                    }
                    else {
                        liczba--;
                    }
                    licznik1++;
                    if (licznik1 > rozmiar+1) {
                        od_nowa = true;
                        break;
                    }
                } while (stan != true);
                if (od_nowa == true) {
                    break;
                }
            }
            if (od_nowa == true) {
                break;
            }
        }
        if (od_nowa == true) {
            uzupelnij_tablice();
            return;
        }
        if (od_nowa == false) {
            for (int i = 0; i < rozmiar; i++) {
                for (int j = 0; j < rozmiar; j++) {
                    odpowiedz[i][j] = tablica[i][j];
                }
            }
        }
    }

    public boolean czy_mozna_wstawic(int x,int y,int liczba){
        int licznik = 0;
        for (int k = 0; k < rozmiar; k++) {
            if (liczba == tablica[k][y] && x != k) {
                licznik++;
            }
        }
        for (int k = 0; k < rozmiar; k++) {
            if (liczba == tablica[x][k] && y != k) {
                licznik++;
            }
        }
        int kwadratStartW = 0;
        int kwadratStartK = 0;

        for(int t=0;t<rozmiar_petla;t++){
            if((t*rozmiar_petla)<=x && ((t*rozmiar_petla)+rozmiar_petla)>x){
                kwadratStartW=(t*rozmiar_petla);
                break;
            }
        }

        for(int t=0;t<rozmiar_petla;t++){
            if((t*rozmiar_petla)<=y && ((t*rozmiar_petla)+rozmiar_petla)>y){
                kwadratStartK=(t*rozmiar_petla);
                break;
            }
        }
        for (int k = kwadratStartW; k < (kwadratStartW + rozmiar_petla); k++) {
            for (int l = kwadratStartK; l < (kwadratStartK + rozmiar_petla); l++) {
                if (tablica[k][l] == liczba && k != x && l != y) {
                    licznik++;
                }
            }
        }

        if (licznik == 0) {
            return true;
        }
        else {
            return false;
        }
    }


    public void wstaw_puste(int ilosc_pustych){
        int licznik,wiersz,kolumna;
        for (int i = 0; i < ilosc_pustych; i++) {
            licznik = 0;
            do {
                wiersz = (generator.nextInt(rozmiar));
                kolumna = (generator.nextInt(rozmiar));
                if (tablica[wiersz][kolumna] != -1) {
                    tablica[wiersz][kolumna] = -1;
                    miejsca_puste.add((rozmiar*wiersz)+kolumna);
                    licznik++;
                }
            } while (licznik != 1);
        }
    }

    public boolean sprawdz_wynik(){
        int licznik=0;
        int miejsce;
        for (int i = 0; i < rozmiar; i++) {
            for (int j = 0; j < rozmiar; j++) {
                if (czy_mozna_wstawic(i, j, tablica[i][j]) == false) {
                    miejsce=(rozmiar*i)+j;
                    for(Integer p: miejsca_puste) {
                        if(p==miejsce){
                            licznik++;
                            miejsca_zle.add(miejsce);
                        }
                    }
                }
            }
        }
        if(licznik==0) {
            return true;
        } else {
            return false;
        }
    }

}
