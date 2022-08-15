package com.leeyaonan.flappybird.entity;

import com.leeyaonan.flappybird.constant.GameModeEnum;
import com.leeyaonan.flappybird.constant.GameStatusEnum;
import com.leeyaonan.flappybird.util.Logger;

/**
 * 游戏主体
 */
public class Game {

    /**
     * 游戏实体，单例模式
     */
    private static Game INSTANCE;

    /**
     * 游戏当前状态
     */
    private static volatile int GAME_STATUS;

    /**
     * 游戏开始界面
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
    private FlyObject character;
    /**
     * 障碍物
     */
    private BarrierObject barrier;

    /**
     * 构造方法，在这里完成游戏初始化
     */
    private Game() {
        // 初始化欢迎界面
        initWelcomeInterface();
        // 初始化游戏对象
        initGame();
        // 初始化游戏状态
        setGameStatus(GameStatusEnum.GAME_READY);
    }

    /**
     * 初始化欢迎界面
     */
    private void initWelcomeInterface() {
        welcomeInterface = WelcomeInterface.getInstance();
        welcomeInterface.setVisible(true);
    }

    /**
     * 初始化游戏元素
     */
    private void initGame() {

    }

    /**
     * 双重校验锁获取Game启动器单例
     * @return
     */
    public static Game getInstance() {
        if (INSTANCE == null) {
            synchronized (Game.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Game();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 设置游戏状态
     * @param gameStatus
     */
    private void setGameStatus(GameStatusEnum gameStatus) {
        GAME_STATUS = gameStatus.getStatus();
    }

    /**
     * 启动游戏
     * @param mode
     */
    public void start(GameModeEnum mode) {
        if (GAME_STATUS != GameStatusEnum.GAME_READY.getStatus()) {
            // 游戏已经启动
            Logger.info("游戏正在运行请勿重复启动");
            return;
        }

        setGameStatus(GameStatusEnum.GAME_START);
    }
}
