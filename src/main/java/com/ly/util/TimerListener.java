/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ly.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author zw
 */
public class TimerListener implements ServletContextListener {
    
    private static Timer timer;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                LoadTaobaoData load = new LoadTaobaoData();
                new Thread(load).start();
            }
        };

        //设置执行时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);//每天
        //定制每天的5:00:00执行，
        calendar.set(year, month, day, 5, 00, 00);
        Date date = calendar.getTime();
        
        timer = new Timer();
        timer.schedule(task, date);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        timer.cancel();
    }
}
