package com.hkllyx.demo.basic.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 显示添加listener的方法
 *
 * @author HKLLY
 * @date 2019/4/15
 */
public class ShowAddListenerMethods extends JFrame {
    /**
     * 匹配添加Listener相关的方法
     */
    private static Pattern addListener =
            Pattern.compile("(add\\w+?Listener\\(.*?\\))");
    /**
     * 匹配包名
     */
    private static Pattern qualifier =
            Pattern.compile("\\w+?\\.");
    private JLabel lblPackage = new JLabel("javax.swing.");
    private JTextField txfClassName =
            new JTextField("JTextArea", 25);
    private JTextArea txaMethods = new JTextArea(40, 65);
    private Class<?> type;
    private Method[] methods;

    public ShowAddListenerMethods() {
        JPanel top = new JPanel();
        top.add(lblPackage);
        txfClassName.addActionListener(e -> {
            //设置按enter执行
            new ActionEvent("", 0, "");
            txaMethods.setText("");
            //获取输入的类名
            String className = txfClassName.getText().trim();
            try {
                //获取类对象
                type = Class.forName(lblPackage.getText() + className);
            } catch (ClassNotFoundException ex) {
                txaMethods.setText("Class Not Found!");
                return;
            }
            //获取类和其父类的public方法
            methods = type.getMethods();
            for (Method m : methods) {
                //判断方法是否是添加Listener的方法
                Matcher matcher = addListener.matcher(m.toString());
                if (matcher.find()) {
                    //去除包名
                    txaMethods.append(qualifier.matcher(matcher.group(1))
                            .replaceAll("") + "\n");
                }
            }
        });

        top.add(txfClassName);
        add(top, BorderLayout.NORTH);
        add(new JScrollPane(txaMethods), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtils.run(new ShowAddListenerMethods(), 500, 400);
    }
}
