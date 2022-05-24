package com.hkllyx.demo.basic.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Show a gui with a line of string and some menu that could change its color and font.
 * Created on 2018-3-17.
 *
 * @author HKLLY
 */

public class FontGui extends JFrame {
    private JLabel lblDisplay;
    private JMenuBar menuBar;
    private JCheckBoxMenuItem cbxMiBold;
    private JCheckBoxMenuItem cbxMiItalic;
    private JRadioButtonMenuItem rdbMiLarge;
    private JRadioButtonMenuItem rdbMiMedium;
    private JRadioButtonMenuItem rdbMiSmall;

    public FontGui() {
        //添加显示窗口
        addDisplayLabel();
        //添加菜单栏
        addMenuBar();
        //添加工具栏
        addToolBar();
        //添加弹出窗口
        addPopupMenu();
        validate();
    }

    public static void main(String[] args) {
        SwingUtils.run(new FontGui(), 800, 600);
    }

    private void addPopupMenu() {
        JPopupMenu pm = new JPopupMenu();
        lblDisplay.setComponentPopupMenu(pm);
        JMenuItem miExit = new JMenuItem("EXIT");
        pm.add(miExit);
        miExit.addActionListener(e -> {
            String message = "Sure?";
            if (JOptionPane.showConfirmDialog(lblDisplay, message) == 0) {
                System.exit(0);
            }
        });
    }

    private void addToolBar() {
        //绘制全部部件，如果为false，则不能影响显示窗口背景
        lblDisplay.setOpaque(true);
        JToolBar tb = new JToolBar();
        add(tb, BorderLayout.NORTH);

        JButton redB = new JButton("Red");
        tb.add(redB);
        redB.addActionListener(e -> {
            lblDisplay.setBackground(Color.RED);
            menuBar.setBackground(Color.RED);
            tb.setBackground(Color.RED);
        });

        JButton greenB = new JButton("Green");
        tb.add(greenB);
        greenB.addActionListener(e -> {
            lblDisplay.setBackground(Color.GREEN);
            menuBar.setBackground(Color.GREEN);
            tb.setBackground(Color.GREEN);
        });

        JButton blueB = new JButton("Blue");
        tb.add(blueB);
        blueB.addActionListener(e -> {
            lblDisplay.setBackground(Color.BLUE);
            menuBar.setBackground(Color.BLUE);
            tb.setBackground(Color.BLUE);
        });
    }

    private void addMenuBar() {
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menuInput = new JMenu("Input");
        menuBar.add(menuInput);

        JMenuItem miNew = new JMenuItem("New");
        menuInput.add(miNew);
        miNew.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(lblDisplay, "Type here:");
            if (input != null) {
                lblDisplay.setText(input);
            }
        });

        JMenu menuFont = new JMenu("Font");
        menuBar.add(menuFont);

        JMenu menuColor = new JMenu("Color");
        menuFont.add(menuColor);

        JMenuItem miRed = new JMenuItem("Red");
        menuColor.add(miRed);
        miRed.addActionListener(e -> lblDisplay.setForeground(Color.RED));

        JMenuItem miYellow = new JMenuItem("Yellow");
        menuColor.add(miYellow);
        miYellow.addActionListener(e -> lblDisplay.setForeground(Color.YELLOW));

        JMenuItem miBlue = new JMenuItem("Blue");
        menuColor.add(miBlue);
        miBlue.addActionListener(e -> lblDisplay.setForeground(Color.BLUE));

        JMenu menuEdit = new JMenu("Edit");
        menuFont.add(menuEdit);
        ButtonGroup bg = new ButtonGroup();

        cbxMiBold = new JCheckBoxMenuItem("Bold");
        menuEdit.add(cbxMiBold);
        cbxMiBold.addActionListener(e -> fontChange());

        cbxMiItalic = new JCheckBoxMenuItem("Italic");
        menuEdit.add(cbxMiItalic);
        cbxMiItalic.addActionListener(e -> fontChange());

        rdbMiLarge = new JRadioButtonMenuItem("Large");
        bg.add(rdbMiLarge);
        menuEdit.add(rdbMiLarge);
        rdbMiLarge.addActionListener(e -> fontChange());

        rdbMiMedium = new JRadioButtonMenuItem("rdbMiMedium");
        bg.add(rdbMiMedium);
        menuEdit.add(rdbMiMedium);
        rdbMiMedium.addActionListener(e -> fontChange());

        rdbMiSmall = new JRadioButtonMenuItem("Small");
        bg.add(rdbMiSmall);
        menuEdit.add(rdbMiSmall);
        rdbMiSmall.addActionListener(e -> fontChange());
    }

    private void addDisplayLabel() {
        lblDisplay = new JLabel("A quick brown fox jumps over the lazy dog!");
        add(lblDisplay, BorderLayout.CENTER);
        lblDisplay.setFont(new Font("serif", Font.PLAIN, 24));
    }

    private void fontChange() {
        int mode = Font.PLAIN;
        int size = 0;
        if (cbxMiBold.isSelected()) {
            mode += Font.BOLD;
        }
        if (cbxMiItalic.isSelected()) {
            mode += Font.ITALIC;
        }
        if (rdbMiLarge.isSelected()) {
            size = 48;
        } else if (rdbMiMedium.isSelected()) {
            size = 24;
        } else if (rdbMiSmall.isSelected()) {
            size = 12;
        }
        lblDisplay.setFont(new Font("serif", mode, size));
    }
}
