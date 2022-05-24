package com.hkllyx.demo.basic.gui;

import javax.swing.*;
import java.awt.*;

/**
 * @author HKLLY
 * @date 2019/4/15
 */
public class SwingUtils {
    private static Dimension srnSize = Toolkit.getDefaultToolkit().getScreenSize();

    private SwingUtils() {
    }

    /**
     * 运行一个Swing的JFrame组件
     *
     * @param frame  JFrame组件
     * @param width  宽度
     * @param height 高度
     */
    public static void run(JFrame frame, int width, int height) {
        SwingUtilities.invokeLater(() -> {
            frame.setTitle(frame.getClass().getSimpleName());
            frame.setSize(width, height);
            //居于屏幕中央
            frame.setBounds((srnSize.width - width) / 2, (srnSize.height - height) / 2, width, height);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
