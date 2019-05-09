package zajecia9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Krzysztof Podlaski on 07.05.2019.
 */
public class ReflexTester extends JPanel{
    List<JButton> buttons = new ArrayList<>();
    int rows, cols;
    private long startTime;
    private Random rand = new Random();
    ScheduledExecutorService executor =
            Executors.newSingleThreadScheduledExecutor();
    ReflexSwitcherWithoutSleep task =new ReflexSwitcherWithoutSleep();


    public ReflexTester(int rows, int cols) {
        this.rows=rows;
        this.cols=cols;
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Reflex tester");
        ReflexTester rf = ReflexTester.createPanel(3,3);
        frame.setContentPane(rf);
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        rf.switchOn(2,1);
    }

    private void switchOn(int row, int column) {
        int index = (row-1)*cols+column-1;
        switchOn(index);
    }

    private void switchOn(int index){
        JButton b = buttons.get(index);
        b.setText("ON");
        startTime = System.currentTimeMillis();
    }

    private void switchOff(int row, int column) {
        int index = (row-1)*cols+column-1;
        JButton b = buttons.get(index);
        b.setText(""+row+column);
        long stopTime= System.currentTimeMillis();
        System.out.println(1.0*(stopTime-startTime)/1000+"s");
        startTime = 0;
        //Uruchom wątek zaplający następny klawisz
        //new Thread(new ReflexSwitcherWithSleep()).start();
        executor.schedule(task,
                rand.nextInt(3000), TimeUnit.MILLISECONDS);
    }


    private static ReflexTester createPanel(int rows, int cols) {
        ReflexTester rf = new ReflexTester(rows, cols);
        rf.setLayout(new GridLayout(rows, cols));
        for (int r = 1; r<=rows; r++){
            for (int c=1; c<=cols; c++){
                JButton b = new JButton(""+r+c);
                rf.add(b);
                rf.buttons.add(b);
                b.addActionListener(rf.new ReflexActionListener());
            }
        }
        return rf;
    }

    private class ReflexActionListener
            implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();
            int index;
            for (index = 0; index<buttons.size(); index++){
                if(buttons.get(index)==b) break;
            }
            int row = index/cols + 1;
            int col = index%cols +1;
            if (b.getText().equals("ON")){

                switchOff(row,col);
            }
            /*
            else {
                switchOn(row,col);
            }
            */
        }
    }

    private class ReflexSwitcherWithSleep implements Runnable{
        @Override
        public void run() {
            int index = rand.nextInt(buttons.size());
            try {
                Thread.sleep(rand.nextInt(3000)); //Max 3 s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switchOn(index);
        }
    }

    private class ReflexSwitcherWithoutSleep implements Runnable{
        @Override
        public void run() {
            int index = rand.nextInt(buttons.size());
            switchOn(index);
        }
    }
}