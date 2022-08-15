package com.leeyaonan.flappybird.entity;

import javax.swing.*;

/**
 * 游戏开始界面
 */
public class WelcomeInterface extends JFrame {

    private static volatile WelcomeInterface INSTANCE;

    private WelcomeInterface() {

    }

    /**
     * 单例模式获取欢迎界面
     * @return
     */
    public static WelcomeInterface getInstance() {
        if (INSTANCE == null) {
            synchronized (WelcomeInterface.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WelcomeInterface();
                }
            }
        }
        return INSTANCE;
    }
}
