package com.hkllyx.demo.basic.concurrent.others.simulation;

import com.hkllyx.demo.basic.concurrent.util.ExecutorUtils;

import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 温室模拟器
 *
 * @author HKLLY
 * @date 2019/4/13
 */
public class GreenhouseScheduler {
    private volatile boolean light = false;
    private volatile boolean water = false;
    private String thermostat = "Day";
    private ScheduledThreadPoolExecutor scheduler = ExecutorUtils.newScheduledThreadPool(10);
    private Calendar lastTime = Calendar.getInstance();

    {
        lastTime.set(Calendar.MINUTE, 30);
        lastTime.set(Calendar.SECOND, 0);
    }

    private float lastTemp = 65.0f;
    private int tempDirection = +1;
    private float lastHumidity = 50.0f;
    private int humidityDirection = +1;
    private Random rand = new Random(47);
    private List<DataPoint> data = Collections.synchronizedList(new ArrayList<>());

    public synchronized String getThermostat() {
        return thermostat;
    }

    public synchronized void setThermostat(String value) {
        thermostat = value;
    }

    /**
     * 定期触发一个事件
     *
     * @param event 事件
     * @param delay 触发延时 / 间隔
     */
    public void schedule(Runnable event, long delay) {
        scheduler.schedule(event, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * 定期重复一个事件
     *
     * @param event        事件
     * @param initialDelay 初始延时，重复事件在等待初始延时后才开始第一次执行
     * @param period       两个重复事件的间隔
     */
    public void repeat(Runnable event, long initialDelay, long period) {
        scheduler.scheduleAtFixedRate(
                event, initialDelay, period, TimeUnit.MILLISECONDS);
    }

    /**
     * 数据点。描述一个时间段温室的数据
     */
    static class DataPoint {
        final Calendar time;
        final float temperature;
        final float humidity;

        public DataPoint(Calendar d, float temp, float hum) {
            time = d;
            temperature = temp;
            humidity = hum;
        }

        @Override
        public String toString() {
            return time.getTime() + String.format(
                    " temperature: %1$.1f humidity: %2$.2f",
                    temperature, humidity);
        }
    }

    /**
     * 收集数据任务
     */
    class CollectData implements Runnable {
        @Override
        public void run() {
            System.out.println("Collecting data");
            synchronized (GreenhouseScheduler.this) {
                lastTime.set(Calendar.MINUTE,
                        lastTime.get(Calendar.MINUTE) + 30);
                //  五分之一的概率反转温度变化方向
                if (rand.nextInt(5) == 4) {
                    tempDirection = -tempDirection;
                }
                //  温度变化
                lastTemp = lastTemp +
                        tempDirection * (1.0f + rand.nextFloat());

                //  五分之一的概率反转湿度变化方向
                if (rand.nextInt(5) == 4) {
                    humidityDirection = -humidityDirection;
                }
                //  湿度变化
                lastHumidity = lastHumidity +
                        humidityDirection * rand.nextFloat();

                //  将变化后的数据加入数据集合
                data.add(new DataPoint((Calendar) lastTime.clone(),
                        lastTemp, lastHumidity));
            }
        }
    }

    /**
     * 开启灯光
     */
    class LightOn implements Runnable {
        @Override
        public void run() {
            System.out.println("Turning on lights");
            light = true;
        }
    }

    /**
     * 关闭灯光
     */
    class LightOff implements Runnable {
        @Override
        public void run() {
            System.out.println("Turning off lights");
            light = false;
        }
    }

    /**
     * 开始浇水
     */
    class WaterOn implements Runnable {
        @Override
        public void run() {
            System.out.println("Turning greenhouse water on");
            water = true;
        }
    }

    /**
     * 停止浇水
     */
    class WaterOff implements Runnable {
        @Override
        public void run() {
            //  Put hardware control code here.
            System.out.println("Turning greenhouse water off");
            water = false;
        }
    }

    /**
     * 恒温器模式转为夜晚
     */
    class ThermostatNight implements Runnable {
        @Override
        public void run() {
            System.out.println("Thermostat to night setting");
            setThermostat("Night");
        }
    }

    /**
     * 恒温器模式转为白天
     */
    class ThermostatDay implements Runnable {
        @Override
        public void run() {
            System.out.println("Thermostat to day setting");
            setThermostat("Day");
        }
    }

    /**
     * 响铃
     */
    class Bell implements Runnable {
        @Override
        public void run() {
            System.out.println("Bing!");
        }
    }

    /**
     * 终止温室运行
     */
    class Terminate implements Runnable {
        @Override
        public void run() {
            System.out.println("Terminating");
            scheduler.shutdownNow();
            new Thread(() -> {
                for (DataPoint d : data) {
                    System.out.println(d);
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        GreenhouseScheduler gh = new GreenhouseScheduler();
        gh.schedule(gh.new Terminate(), 5000);
        gh.repeat(gh.new Bell(), 0, 1000);
        gh.repeat(gh.new ThermostatNight(), 0, 2000);
        gh.repeat(gh.new LightOn(), 0, 200);
        gh.repeat(gh.new LightOff(), 0, 400);
        gh.repeat(gh.new WaterOn(), 0, 600);
        gh.repeat(gh.new WaterOff(), 0, 800);
        gh.repeat(gh.new ThermostatDay(), 0, 1400);
        gh.repeat(gh.new CollectData(), 500, 500);
    }
}
