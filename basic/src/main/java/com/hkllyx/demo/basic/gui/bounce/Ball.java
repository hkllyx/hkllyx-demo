package com.hkllyx.demo.basic.gui.bounce;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 * 弹跳球，到了边界便返回
 *
 * @author HKLLY
 * @date 2019-8-1
 */
public class Ball implements Runnable {
    private static final Random RANDOM = new Random(47);
    private static final int X_SIZE = 30;
    private static final int Y_SIZE = 30;
    private static final int DELAY = 3;

    public final Color color;

    private BallComponent comp;
    private double x = RANDOM.nextInt(50);
    private double y = RANDOM.nextInt(50);
    private double dx = 1;
    private double dy = 1;

    public Ball(BallComponent comp) {
        this.comp = comp;
        color = new Color(RANDOM.nextInt(255),
                RANDOM.nextInt(255),
                RANDOM.nextInt(255));
    }

    /**
     * 移动，如果到了边框的边界，则方向反转
     */
    public void move(Rectangle2D bounds) {
        x += dx;
        y += dy;
        if (x < bounds.getMinX()) {
            x = bounds.getMinX();
            dx = -dx;
        }
        if (x + X_SIZE >= bounds.getMaxX()) {
            x = bounds.getMaxX() - X_SIZE;
            dx = -dx;
        }
        if (y < bounds.getMinY()) {
            y = bounds.getMinY();
            dy = -dy;
        }
        if (y + Y_SIZE >= bounds.getMaxY()) {
            y = bounds.getMaxY() - Y_SIZE;
            dy = -dy;
        }
    }

    /**
     * 获取当前位置的圆形
     */
    public Ellipse2D getShape() {
        return new Ellipse2D.Double(x, y, X_SIZE, Y_SIZE);
    }

    @Override
    public void run() {
        comp.add(this);
        while (!Thread.interrupted()) {
            comp.paint(comp.getGraphics());
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}