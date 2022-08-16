package com.leeyaonan.flappybird.entity;

import com.leeyaonan.flappybird.constant.GameModeEnum;
import com.leeyaonan.flappybird.constant.GameStatusEnum;

import java.awt.event.KeyEvent;

/**
 * 游戏控制台主体
 */
public class Game {

    /**
     * 游戏实体，单例模式
     */
    private static volatile Game INSTANCE;

    /**
     * 游戏当前状态
     */
    private static volatile int GAME_STATUS = GameStatusEnum.GAME_NOT_READY.getStatus();

    /**
     * 游戏逻辑主体
     */
    private GameFrame gameFrame;

    /**
     * 构造方法，在这里完成游戏初始化
     */
    private Game(GameModeEnum gameMode) {
        gameFrame = GameFrame.getInstance();
        gameFrame.init();
        // 初始化游戏状态
        setGameStatus(GameStatusEnum.GAME_READY);
    }

    private Game() {
        new Game(GameModeEnum.NORMAL);
    }

    /**
     * 双重校验锁获取Game启动器单例
     * @return
     */
    public static Game getInstance(GameModeEnum gameMode) {
        if (gameMode == null) {
            gameMode = GameModeEnum.NORMAL;
        }
        if (INSTANCE == null) {
            synchronized (Game.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Game(gameMode);
                }
            }
        }
        return INSTANCE;
    }

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
    private static void setGameStatus(GameStatusEnum gameStatus) {
        GAME_STATUS = gameStatus.getStatus();
    }

    public static GameStatusEnum getGameStatus() {
        return GameStatusEnum.byStatus(GAME_STATUS);
    }

    /**
     * 启动游戏
     */
    public void start() {
        // 更新游戏状态为游戏开始-运行中
        setGameStatus(GameStatusEnum.GAME_START);

        // 飞行物开始飞行，重力生效
        gameFrame.oneMove();
    }

    /**
     * todo:重新开始游戏
     */
    private void restart() {
        setGameStatus(GameStatusEnum.GAME_READY);
    }

    public void keyPressed(int keyCode) {
        switch (Game.getGameStatus()) {
            case GAME_NOT_READY:
                // do nothing
                break;
            case GAME_READY:
                // 游戏启动界面按下空格，则游戏开始
                if (keyCode == KeyEvent.VK_SPACE) {
                    start();
                }
                break;
            case GAME_START:
                // 游戏过程中按下空格，持续飞行
                if (keyCode == KeyEvent.VK_SPACE) {
                    GameFrame.getInstance().oneMove();
                }
                // 游戏过程中按下"P"，暂停游戏
                else if (keyCode == KeyEvent.VK_P) {
                    // todo:暂停逻辑待开发

                }
                break;
            case GAME_PAUSE:
                // todo:暂停逻辑待开发

                break;
            case GAME_OVER:
                // 游戏结束时按下空格，重新开始游戏
                if (keyCode == KeyEvent.VK_SPACE) {
                    restart();
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + Game.getGameStatus());
        }

    }

    public void keyReleased(int keyCode) {

    }
}
