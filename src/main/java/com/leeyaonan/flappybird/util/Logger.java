package com.leeyaonan.flappybird.util;

import java.util.Date;

/**
 * 自定义日志工具
 * todo:实现日志输出到本地文件
 */
public class Logger {

    public static void info(String log) {
        Date date = new Date();
        System.out.println("Time:" + date.toString() + ",log:" + log);
    }
}
