package project_v02;

import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Design {
    public JLabel setLabel(JLabel lbl,String lbltxt,int right, int down, int weidth, int height, int size){
        lbl.setText(lbltxt);
        lbl.setBounds(right, down,weidth, height);
        lbl.setFont(new Font("Dialogue",Font.BOLD,size));
        return lbl;   
    }
    
    public JButton setButton(JButton button,String btntxt, int right, int down, int weidth, int height, ActionListener action){
        button.setText(btntxt);
        button.setBounds(right,down,weidth,height);
        button.addActionListener(action);
        return button;
    }
}
