package e2;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import java.util.*;
import java.util.Map.Entry;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton,Pair<Integer,Integer>> buttons = new HashMap<>();
    private final Logics logics;
    
    public GUI(int size) {
        this.logics = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,panel);
        
        ActionListener onClick = (e)->{
            final JButton bt = (JButton)e.getSource();
            final Pair<Integer,Integer> pos = buttons.get(bt);
            boolean aMineWasFound = logics.isBomb(pos); // call the logic here to tell it that cell at 'pos' has been seleced
            boolean isThereVictory = logics.victory();
                
            if (aMineWasFound) {
                quitGame();
                JOptionPane.showMessageDialog(this, "You lost!!");
            } else if(isThereVictory) {
                quitGame();
                JOptionPane.showMessageDialog(this, "You won!!");
                System.exit(0);   	
            }
            drawBoard();  
        };

        MouseInputListener onRightClick = new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final JButton bt = (JButton)e.getSource();
                if (bt.isEnabled()){
                    final Pair<Integer,Integer> pos = buttons.get(bt);
                    // call the logic here to put/remove a flag
                    logics.hit(pos);
                }
                drawBoard(); 

            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                jb.addActionListener(onClick);
                jb.addMouseListener(onRightClick);
                this.buttons.put(jb,new Pair<>(i,j));
                panel.add(jb);
            }
        }
        this.drawBoard();
        this.setVisible(true);
    }
    
    private void quitGame() {
        this.drawBoard();
    	for (var entry: this.buttons.entrySet()) {
            JButton bt = entry.getKey();
            Pair<Integer, Integer> position = entry.getValue();
            if(bt.isEnabled()){
                bt.setText(logics.isBomb(position) ? "*" : "W");
            }
            bt.setEnabled(false);
    	}
    }

    private void drawBoard() {
        for (var entry: this.buttons.entrySet()) {
            // call the logic here
            // if this button is a cell with counter, put the number
            // if this button has a flag, put the flag
            JButton bt = entry.getKey();
            Pair<Integer, Integer> position = entry.getValue();
            if(bt.isEnabled()){
                String numberText = logics.getNumber(position);
                System.out.println(numberText);
                
                bt.setText(numberText);
                bt.setEnabled(!logics.hasNumber(position));
                recurrentZeros(numberText, position);
            }
    	}
    }

    private void recurrentZeros(String numberText, Pair<Integer, Integer> position){
        if(numberText!="" && Integer.parseInt(numberText) == 0){
            hitTheAdiacents(position);
        }
    }

    private void hitTheAdiacents(Pair<Integer, Integer> position) {
        for (Pair<Integer,Integer> c : logics.setAdiacentCells(position)) {
            logics.hit(c);
            drawBoard();
        }
    }
    
}
