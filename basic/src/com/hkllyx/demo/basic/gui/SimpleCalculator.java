package com.hkllyx.demo.basic.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

/**
 * Show a simple calculator.
 * Created on 2018-3-17.
 *
 * @author HKLLY
 */
public class SimpleCalculator extends JFrame {
    /**
     * 显示窗口
     */
    private JTextField txfDisplay;
    /**
     * 是否是新的输入
     */
    private boolean isStart = true;
    /**
     * 运算的第一个数
     */
    private BigDecimal first;
    /**
     * 运算的第二个数
     */
    private BigDecimal second;

    private final String equal = "=";
    private final String add = "+";
    private final String subtract = "-";
    private final String multiply = "*";
    private final String divide = "/";

    /**
     * 当前的操作
     */
    private String operation = equal;

    public SimpleCalculator() {
        addDisplayTextField();
        addOperationPanel();
        validate();
    }

    public static void main(String[] args) {
        SwingUtils.run(new SimpleCalculator(), 400, 600);
    }

    /**
     * 添加显示域
     */
    private void addDisplayTextField() {
        txfDisplay = new JTextField("0");
        txfDisplay.setHorizontalAlignment(JTextField.RIGHT);
        txfDisplay.setFont(new Font("Serif", Font.PLAIN, 52));
        txfDisplay.setBorder(BorderFactory.createBevelBorder(1));
        txfDisplay.setEditable(false);
        add(txfDisplay, BorderLayout.NORTH);
    }

    /**
     * 添加操作面板
     */
    private void addOperationPanel() {
        //创建操作面板
        JPanel pnlOperation = new JPanel(new GridLayout(4, 4));
        //新建面板边框
        pnlOperation.setBorder(BorderFactory.createTitledBorder("INPUT"));
        //新建四则运算操作监听器
        ActionListener operationListener = e -> {
            //获取第一个用于运算的数
            first = BigDecimal.valueOf(Double.parseDouble(txfDisplay.getText()));
            //获取当前操作
            operation = e.getActionCommand();
            //新输入设为false
            isStart = true;
        };
        //新建等于运算监听器
        ActionListener equalListener = e -> {
            //获取第二个用于运算的数
            second = BigDecimal.valueOf(Double.parseDouble(txfDisplay.getText()));
            switch (operation) {
                case equal:
                    txfDisplay.setText("" + second);
                    break;
                case add:
                    txfDisplay.setText("" + (first.add(second)));
                    break;
                case subtract:
                    txfDisplay.setText("" + (first.subtract(second)));
                    break;
                case multiply:
                    txfDisplay.setText("" + (first.multiply(second)));
                    break;
                case divide:
                    if (second.equals(BigDecimal.ZERO)) {
                        txfDisplay.setText("error");
                    } else {
                        //如果运算时小数无限，转为double运算
                        try {
                            txfDisplay.setText("" + (first.divide(second)));
                        } catch (ArithmeticException ae) {
                            double result = first.doubleValue() / second.doubleValue();
                            txfDisplay.setText(String.format("%.2f", result));
                        }
                    }
                default:
            }
            operation = equal;
            isStart = true;
        };
        //数字监听
        ActionListener numListener = e -> {
            String num = e.getActionCommand();
            if (isStart) {
                txfDisplay.setText(num);
                isStart = false;
            } else {
                txfDisplay.setText(txfDisplay.getText() + num);
            }
        };
        //小数点监听
        ActionListener pointerListener = e -> {
            String num = e.getActionCommand();
            if (isStart) {
                txfDisplay.setText("error");
            } else {
                txfDisplay.setText(txfDisplay.getText() + num);
            }
        };

        add(pnlOperation, BorderLayout.CENTER);
        addBtnToPnl(pnlOperation, "7", numListener);
        addBtnToPnl(pnlOperation, "8", numListener);
        addBtnToPnl(pnlOperation, "9", numListener);
        addBtnToPnl(pnlOperation, add, operationListener);
        addBtnToPnl(pnlOperation, "4", numListener);
        addBtnToPnl(pnlOperation, "5", numListener);
        addBtnToPnl(pnlOperation, "6", numListener);
        addBtnToPnl(pnlOperation, subtract, operationListener);
        addBtnToPnl(pnlOperation, "1", numListener);
        addBtnToPnl(pnlOperation, "2", numListener);
        addBtnToPnl(pnlOperation, "3", numListener);
        addBtnToPnl(pnlOperation, multiply, operationListener);
        addBtnToPnl(pnlOperation, ".", pointerListener);
        addBtnToPnl(pnlOperation, "0", numListener);
        addBtnToPnl(pnlOperation, equal, equalListener);
        addBtnToPnl(pnlOperation, divide, operationListener);
    }

    /**
     * 向panel添加Button
     *
     * @param pnl      panel
     * @param btnText  Button内容
     * @param listener Button动作监听
     */
    private void addBtnToPnl(JPanel pnl, String btnText, ActionListener listener) {
        JButton btn = new JButton(btnText);
        btn.setFont(new Font("Serif", Font.PLAIN, 48));
        btn.addActionListener(listener);
        pnl.add(btn);
    }
}
