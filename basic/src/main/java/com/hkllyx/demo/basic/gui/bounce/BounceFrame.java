package com.hkllyx.demo.basic.gui.bounce;

import com.hkllyx.demo.basic.concurrent.util.ExecutorUtils;
import com.hkllyx.demo.basic.gui.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;

/**
 * 主界面，包括开始、关闭按钮
 *
 * @author HKLLY
 * @date 2019-8-1
 */
public class BounceFrame extends JFrame {
    private final BallComponent comp;

    public BounceFrame() {
        comp = new BallComponent();
        add(comp, BorderLayout.CENTER);
        JPanel pnl = new JPanel();

        JButton btnStart = new JButton("Start");
        ExecutorService pool = ExecutorUtils.newSingleThreadExecutor();
        btnStart.addActionListener(e -> pool.execute(new Ball(comp)));
        JButton btnClose = new JButton("Close");

        btnClose.addActionListener(e -> System.exit(0));
        pnl.add(btnStart);
        pnl.add(btnClose);
        add(pnl, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtils.run(new BounceFrame(), 450, 350);
    }
}