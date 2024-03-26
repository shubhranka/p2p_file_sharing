package util;

import javax.swing.*;

public class RedefinedUI {
    public static JTextField goodLabel(){
        JTextField label = new JTextField();
        label.setEditable(false);
        label.setBackground(null);
        label.setBorder(null);
        return label;
    }
}
