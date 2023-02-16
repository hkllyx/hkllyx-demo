package com.hkllyx.demo.basic.classobject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author HKLLY
 * @date 2019-08-12
 */
public class CallbackDemo {

    public static void main(String[] args) throws InterruptedException {
        // 创建监听器实例
        ActionListener listener = new TimerPrint();
        // 创建一个定时器，定时器达到指定延时就是一个事件，该事件会回调传入的回调对象
        // listener 就是被回调的对象，回调时事件会调用其相应方法
        Timer t = new Timer(1000, listener);
        t.start();

        TimeUnit.SECONDS.sleep(5);
    }
}

/**
 * 定时器打印监听器
 *
 * @author HKLLY
 */
class TimerPrint implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        // 打印当前时间
        System.out.println("当前时间为：" + new Date());
    }
}
