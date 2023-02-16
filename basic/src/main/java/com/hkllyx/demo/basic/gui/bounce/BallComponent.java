package com.hkllyx.demo.basic.gui.bounce;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 绘制圆形
 *
 * @author HKLLY
 * @date 2019-8-1
 */
public class BallComponent extends JPanel {
    private List<Ball> balls = new ArrayList<>();

    /**
     * 添加一个球
     *
     * @param b 球
     */
    public void add(Ball b) {
        balls.add(b);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Ball b : balls) {
            b.move(this.getBounds());
            g2.setColor(b.color);
            g2.fill(b.getShape());
        }
    }
}
