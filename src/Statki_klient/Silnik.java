package Statki_klient;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Silnik {
    public int rozmiar=10;
    String nazwa_gracza;
    List<JButton> przyciski_gracza = new ArrayList<>();
    List<JButton> przyciski_przeciwnika = new ArrayList<>();
    List<JButton> przyciski_uzyte=new ArrayList<>();
    public int[][] temp=new int[rozmiar][rozmiar];
    public int[][] pola_gracza=new int[rozmiar][rozmiar];
    public int il_c=3;
    public int il_z=2;
    public int il_n=1;
    public int il_c2=il_c;
    public int il_z2=il_z;
    public int il_n2=il_n;


    void wyczysc_tablice(){
        for(int i=0;i<rozmiar;i++){
            for(int j=0;j<rozmiar;j++){
                pola_gracza[rozmiar][rozmiar]=0;
            }
        }
    }
    Boolean sprawdz_czy_dobrze(){
        int il_c1=0;
        int il_n1=0;
        int il_z1=0;
        for(int i=0;i<rozmiar;i++){
            for(int j=0;j<rozmiar;j++){
                temp[i][j]=pola_gracza[i][j];
            }
        }
        for(int i=0;i<rozmiar;i++){
            for(int j=0;j<rozmiar;j++){
                if(temp[i][j]==1){
                    if(j+2<rozmiar) {
                        if (temp[i][j + 1] == 1 && temp[i][j + 2] == 1) {
                            temp[i][j] = 0;
                            temp[i][j + 1] = 0;
                            temp[i][j + 2] = 0;
                            il_c1 = il_c1 + 3;
                        }
                    }
                    if(i+2<rozmiar) {
                        if (temp[i + 1][j] == 1 && temp[i + 2][j] == 1) {
                            temp[i][j] = 0;
                            temp[i + 1][j] = 0;
                            temp[i + 2][j] = 0;
                            il_c1 = il_c1 + 3;
                        }
                    }
                }
                if(temp[i][j]==2) {
                    if (j + 1 < rozmiar) {
                        if (temp[i][j + 1] == 2) {
                            temp[i][j] = 0;
                            temp[i][j + 1] = 0;
                            il_z1 = il_z1 + 2;
                        }
                    }
                    if (i + 1 < rozmiar) {
                        if (temp[i + 1][j] == 2) {
                            temp[i][j] = 0;
                            temp[i + 1][j] = 0;
                            il_z1 = il_z1 + 2;
                        }
                    }
                }
                if(temp[i][j]==3){
                    temp[i][j]=0;
                    il_n1++;
                }
            }
        }
        if(il_c1==il_c2 && il_n1==il_n2 && il_z2==il_z1){
            return true;
        } else {
            return false;
        }
    }

}
