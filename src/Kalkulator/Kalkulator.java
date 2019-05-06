package Kalkulator;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

// PROSTY KALKULATOR ;)

public class Kalkulator {


    public long numer1, numer2;

    public boolean plus, minus, razy, dziel;

    public RenderPanel renderpanel;

    private void reset()
    {
        plus = false;
        minus = false;
        razy = false;
        dziel = false;
    }
    public void oblicz(int i)
    {
        if (RenderPanel.cKey[i]==Color.BLACK && RenderPanel.sEkran.length()<16)
        {
            if (RenderPanel.sEkran=="0") RenderPanel.sEkran=RenderPanel.bKey[i].getText(); else
                RenderPanel.sEkran+=RenderPanel.sEkran=RenderPanel.bKey[i].getText();
            RenderPanel.text.setText(RenderPanel.sEkran);
        } else
        if (i==3) {RenderPanel.sEkran="0";reset();RenderPanel.text.setText(RenderPanel.sEkran);} else
        if (i==7)
        {
            if (plus)
            {
                numer2 = Long.parseLong(RenderPanel.sEkran);
                numer1+=numer2;
                RenderPanel. sEkran = String.valueOf(numer1);
                RenderPanel.text.setText(RenderPanel.sEkran);
                RenderPanel.sEkran = "0";
            } else
            {
                reset();
                plus = true;
                numer1 = Long.parseLong(RenderPanel.sEkran);
                RenderPanel.sEkran = "0";
            }
        } else
        if (i==11)
        {
            if (minus)
            {
                numer2 = Long.parseLong(RenderPanel.sEkran);
                numer1-=numer2;
                RenderPanel.sEkran = String.valueOf(numer1);
                RenderPanel.text.setText(RenderPanel.sEkran);
                RenderPanel.sEkran = "0";
            } else
            {
                reset();
                minus = true;
                numer1 = Long.parseLong(RenderPanel.sEkran);
                RenderPanel.sEkran = "0";
            }
        } else
        if (i==13)
        {
            if (razy)
            {
                numer2 = Long.parseLong(RenderPanel.sEkran);
                numer1*=numer2;
                RenderPanel.sEkran = String.valueOf(numer1);
                RenderPanel.text.setText(RenderPanel.sEkran);
                RenderPanel.sEkran = "0";
            } else
            {
                reset();
                razy = true;
                numer1 = Long.parseLong(RenderPanel.sEkran);
                RenderPanel.sEkran = "0";
            }
        } else
        if (i==14)
        {
            if (dziel)
            {
                numer2 = Long.parseLong(RenderPanel.sEkran);
                numer1/=numer2;
                RenderPanel.sEkran = String.valueOf(numer1);
                RenderPanel.text.setText(RenderPanel.sEkran);
                RenderPanel.sEkran = "0";
            } else
            {
                reset();
                dziel = true;
                numer1 = Long.parseLong(RenderPanel.sEkran);
                RenderPanel.sEkran = "0";
            }
        } else
        if (i==15)
        {
            if (plus)
            {
                numer2 = Long.parseLong(RenderPanel.sEkran);
                numer1+=numer2;
            } else
            if (minus)
            {
                numer2 = Long.parseLong(RenderPanel.sEkran);
                numer1-=numer2;
            } else
            if (razy)
            {
                numer2 = Long.parseLong(RenderPanel.sEkran);
                numer1*=numer2;
            } else
            if (dziel)
            {
                numer2 = Long.parseLong(RenderPanel.sEkran);
                numer1/=numer2;
            }
            RenderPanel.sEkran = String.valueOf(numer1);
            reset();
            RenderPanel.text.setText(RenderPanel.sEkran);
        }

    }


    public static void main(String[] args) {
        new RenderPanel();
    }

}
