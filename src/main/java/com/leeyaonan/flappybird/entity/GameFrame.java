package com.leeyaonan.flappybird.entity;

import com.leeyaonan.flappybird.constant.GameStatusEnum;
import com.leeyaonan.flappybird.listener.GameKeyListener;
import com.leeyaonan.flappybird.util.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import static com.leeyaonan.flappybird.constant.GameConstant.*;

/**
 * 游戏开始界面
 */
public class GameFrame extends JFrame {

    /**
     * 欢迎界面
     */
    private WelcomeInterface welcomeInterface;
    /**
     * 游戏背景
     */
    private BackgroundLayer background;
    /**
     * 游戏前景
     */
    private ForegroundLayer foreground;
    /**
     * 操作主体
     */
    private FlyerObject flyer;
    /**
     * 障碍物
     */
    private GameElementLayer gameElementLayer;

    private static volatile GameFrame INSTANCE;

    // 项目中存在两个线程：系统线程，自定义的线程：调用repaint()。
    // 系统线程：屏幕内容的绘制，窗口事件的监听与处理
    // 两个线程会抢夺系统资源，可能会出现一次刷新周期所绘制的内容，并没有在一次刷新周期内完成
    // （双缓冲）单独定义一张图片，将需要绘制的内容绘制到这张图片，再一次性地将图片绘制到窗口
    private final BufferedImage bufImg = new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);

    private GameFrame() {

    }

    /**
     * 单例模式获取欢迎界面
     * @return
     */
    public static GameFrame getInstance() {
        if (INSTANCE == null) {
            synchronized (GameFrame.class) {
                if (INSTANCE == null) {
                    INSTANCE = new GameFrame();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 初始化游戏
     */
    public void init() {
        // 初始化窗口
        initFrame();
        // 初始化游戏元素
        initGameObject();
    }

    /**
     * 初始化游戏窗口
     */
    private void initFrame() {
        // 设置窗口参数
        // -- 窗口大小
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        // -- 窗口标题
        setTitle(GAME_TITLE);
        // -- 窗口初始化位置
        setLocation(FRAME_X, FRAME_Y);
        // -- 窗口大小不可变
        setResizable(false);

        // 添加关闭窗口事件，关闭窗口则游戏进程终止
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // 添加按键监听
        addKeyListener(new GameKeyListener());

        setVisible(true);
    }

    /**
     * 绘制游戏内容 当repaint()方法被调用时，JVM会调用update()，参数g是系统提供的画笔，由系统进行实例化
     * 单独启动一个线程，不断地快速调用repaint()，让系统对整个窗口进行重绘
     *
     * @param g the Graphics context in which to paint
     */
    @Override
    public void update(Graphics g) {
        Graphics bufG = bufImg.getGraphics(); // 获得图片画笔
        // 使用图片画笔将需要绘制的内容绘制到图片
        background.draw(bufG, flyer); // 背景层
        foreground.draw(bufG, flyer); // 前景层
        if (Game.getGameStatus().equals(GameStatusEnum.GAME_READY)) { // 游戏未开始
            welcomeInterface.draw(bufG);
        } else { // 游戏结束
            gameElementLayer.draw(bufG, flyer); // 游戏元素层
        }
        flyer.draw(bufG);
        g.drawImage(bufImg, 0, 0, null); // 一次性将图片绘制到屏幕上
    }

    /**
     * 初始化游戏元素
     */
    private void initGameObject() {
        // 初始化欢迎界面
        welcomeInterface = new WelcomeInterface();
        // 初始化背景
        background = new BackgroundLayer();
        // 初始化前景
        foreground = new ForegroundLayer();
        // 初始化飞行物
        flyer = new Bird();
        // 游戏元素
        gameElementLayer = new GameElementLayer();

        // 启动用于刷新窗口的线程
        new Thread(() -> {
            while (true) {
                repaint();
                try {
                    Thread.sleep(FPS);
                } catch (InterruptedException e) {
                    Logger.info(Arrays.toString(e.getStackTrace()));
                }
            }
        });
    }

    public void oneMove() {
        flyer.flap();
        flyer.fall();
    }
}
