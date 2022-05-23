package com.hkllyx.demo.basic.gui;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

/**
 * Submit Swing Program
 *
 * @author HKLLY
 * @date 2019/4/15
 */
public class PositiveHelloLabel extends JFrame {
    private static PositiveHelloLabel ssp;
    private JLabel label;

    public PositiveHelloLabel() {
        super("Hello Swing");
        setSize(300, 100);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        label = new JLabel("A label.");
        add(label);
    }

    public static void main(String[] args) throws InterruptedException {
        SwingUtilities.invokeLater(() ->
                ssp = new PositiveHelloLabel());
        TimeUnit.SECONDS.sleep(3);
        SwingUtilities.invokeLater(() ->
                ssp.label.setText("Hey! This is different!"));
    }
}
