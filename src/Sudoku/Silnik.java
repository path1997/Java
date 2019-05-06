package Sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Silnik {

    Random generator = new Random();
    List<Integer> miejsca_zle= new ArrayList<>();
    List<Integer> miejsca_puste=new ArrayList<>();

    public int[][] tablica = new int[9][9];
    public int[][] odpowiedz = new int[9][9];

    public void uzupelnij_tablice(){
        int licznik = 0, licznik1 = 0, liczba = 0;
        boolean stan = false, od_nowa = false;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tablica[i][j] = -1;
            }
        }
        for (int i = 0; i < 9; i++) {
            licznik = 0;
            for (int j = 0; j < 9; j++) {
                licznik1 = 0;
                liczba = (generator.nextInt(9))+1;
                do {
                    stan = czy_mozna_wstawic(i, j, liczba);
                    if (stan == true) {
                        tablica[i][j] = liczba;
                    }
                    if (liczba == 1) {
                        liczba = 9;
                    }
                    else {
                        liczba--;
                    }
                    licznik1++;
                    if (licznik1 > 10) {
                        licznik++;
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
        if (licznik == 1) {
            uzupelnij_tablice();
            return;
        }
        if (od_nowa == false) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    odpowiedz[i][j] = tablica[i][j];
                }
            }
        }
    }

    public boolean czy_mozna_wstawic(int x,int y,int liczba){
        int licznik = 0;
        for (int k = 0; k < 9; k++) {
            if (liczba == tablica[k][y] && x != k) {
                licznik++;
            }
        }
        for (int k = 0; k < 9; k++) {
            if (liczba == tablica[x][k] && y != k) {
                licznik++;
            }
        }
        int kwadratStartW = 0;
        int kwadratStartK = 0;
        if (x <= 2) {
            kwadratStartW = 0;
        }
        else if (x >= 3 && x <= 5) {
            kwadratStartW = 3;
        }
        else if (x >= 6) {
            kwadratStartW = 6;
        }
        if (y <= 2) {
            kwadratStartK = 0;
        }
        else if (y >= 3 && y <= 5) {
            kwadratStartK = 3;
        }
        else if (y >= 6) {
            kwadratStartK = 6;
        }
        for (int k = kwadratStartW; k <= kwadratStartW + 2; k++) {
            for (int l = kwadratStartK; l <= kwadratStartK + 2; l++) {
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
                wiersz = (generator.nextInt(9));
                kolumna = (generator.nextInt(9));
                if (tablica[wiersz][kolumna] != -1) {
                    tablica[wiersz][kolumna] = -1;
                    miejsca_puste.add((9*wiersz)+kolumna);
                    licznik++;
                }
            } while (licznik != 1);
        }
    }

    public boolean sprawdz_wynik(){
        int licznik=0;
        int miejsce;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (czy_mozna_wstawic(i, j, tablica[i][j]) == false) {
                    miejsce=(9*i)+j;
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
