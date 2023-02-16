package com.hkllyx.demo.basic.gui;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

/**
 * @author HKLLY
 * @date 2019/4/15
 */
public class NegativeHelloLabel {
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Hello Swing");
        JLabel label = new JLabel("A label.");
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setVisible(true);
        TimeUnit.SECONDS.sleep(3);
        label.setText("Hey! This is different!");
    }
}
